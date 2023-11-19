package edu.hw6.Task5;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class HackerNews {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String REGEX = "\"title\":\"([^\"]+)\"";
    private static final Pattern PATTERN = Pattern.compile(REGEX);
    private static final int SUCCESSFUL_HTTP_RESPONSE_CODE = 200;

    private HackerNews() {
    }

    @SuppressWarnings("checkstyle:MultipleStringLiterals")
    public static long[] hackerNewsTopStories() {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest
            .newBuilder()
            .uri(URI.create("https://hacker-news.firebaseio.com/v0/topstories.json"))
            .build();

        try {
            HttpResponse<String> httpResponse =
                httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            int statusCode = httpResponse.statusCode();
            if (statusCode == SUCCESSFUL_HTTP_RESPONSE_CODE) {
                LOGGER.trace("Successful request");

                final int beginIndex = 1;
                final int endIndex = httpResponse.body().length() - 1;
                String responseBody = httpResponse.body().substring(beginIndex, endIndex);

                return Arrays
                    .stream(responseBody.split(","))
                    .map(Long::parseLong)
                    .mapToLong(Long::longValue)
                    .toArray();
            } else {
                LOGGER.trace("Request failed with status code: {}", statusCode);

                return new long[]{};
            }
        } catch (InterruptedException | IOException exception) {
            LOGGER.trace("Error during request");

            return new long[]{};
        }
    }

    @SuppressWarnings("checkstyle:MultipleStringLiterals")
    public static String news(long id) {
        final String newsName = "https://hacker-news.firebaseio.com/v0/item/" + id + ".json";
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest
            .newBuilder()
            .uri(URI.create(newsName))
            .build();

        try {
            HttpResponse<String> httpResponse =
                httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            int statusCode = httpResponse.statusCode();
            if (statusCode == SUCCESSFUL_HTTP_RESPONSE_CODE) {
                LOGGER.trace("Successful request");

                String responseBody = httpResponse.body();
                Matcher matcher = PATTERN.matcher(responseBody);

                return matcher.find() ? matcher.group(1) : "UNDEFINED";
            } else {
                LOGGER.trace("Request failed with status code: {}", statusCode);

                return "UNDEFINED";
            }
        } catch (InterruptedException | IOException exception) {
            LOGGER.trace("Error during request");

            return "UNDEFINED";
        }
    }
}
