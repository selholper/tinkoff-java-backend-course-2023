package edu.project3.src;

import edu.project3.model.NginxLog;
import edu.project3.model.Request;
import edu.project3.model.Response;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class NginxLogParser {
    private static final DateTimeFormatter TIME_FORMATTER =
        DateTimeFormatter.ofPattern("dd//MMM//uuuu:HH::mm::ss Z", Locale.ENGLISH);
    private static final Pattern LOG_PATTERN =
        Pattern.compile("(.*) - (.*) \\[(.*)] \"(.*) (.*) (.*)\" (\\d+) (\\d+) \"(.*)\" \"(.*)\"");


    private NginxLogParser() {
    }

    @SuppressWarnings("checkstyle:MagicNumber") public static NginxLog parse(String string) {
        Matcher matcher = LOG_PATTERN.matcher(string);

        if (!matcher.find()) {
            throw new IllegalArgumentException("String is incorrect");
        }

        return NginxLog
            .builder()
            .remoteAddress(matcher.group(1))
            .remoteUser(matcher.group(2))
            .timeLocal(OffsetDateTime.parse(matcher.group(3), TIME_FORMATTER))
            .request(
                Request
                .builder()
                .type(matcher.group(4))
                .resource(matcher.group(5))
                .protocolVersion(matcher.group(6))
                .userAgent(matcher.group(10))
                .build()
            )
            .response(
                Response
                .builder()
                .statusCode(Integer.parseInt(matcher.group(7)))
                .bodyBytesSend(Integer.parseInt(matcher.group(8)))
                .build()
            )
            .referer(matcher.group(9))
            .build();
    }
}
