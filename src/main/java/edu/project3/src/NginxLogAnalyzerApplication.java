package edu.project3.src;

import edu.project3.collector.BasicInformationCollector;
import edu.project3.collector.LogStatsCollector;
import edu.project3.collector.RemoteAddressesCollector;
import edu.project3.collector.RequestTypesCollector;
import edu.project3.collector.RequestedResourcesCollector;
import edu.project3.collector.RequestsCollector;
import edu.project3.filter.DateLogFilter;
import edu.project3.filter.LogFilter;
import edu.project3.formatter.ASCIIDocLogStatsFormatter;
import edu.project3.formatter.LogStatsFormatter;
import edu.project3.formatter.MarkdownLogStatsFormatter;
import edu.project3.model.Argument;
import edu.project3.model.FormatType;
import edu.project3.model.LogData;
import edu.project3.model.LogSourceWrapper;
import edu.project3.model.TimeInterval;
import edu.project3.output.CLIWriter;
import edu.project3.output.Writer;
import edu.project3.retriever.LocalRetrieverSelector;
import edu.project3.retriever.LogRetriever;
import edu.project3.retriever.RetrieverSelector;
import edu.project3.retriever.UrlRetrieverSelector;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import static edu.project3.model.FormatType.ADOC;

public class NginxLogAnalyzerApplication {
    private final List<Argument> listArguments;
    private final Writer writer;
    private List<LogStatsCollector> collectors;
    private FormatType formatType = FormatType.MARKDOWN;
    private LogSourceWrapper logSourceWrapper;
    private TimeInterval timeInterval;
    private LogFilter logFilter;

    public NginxLogAnalyzerApplication(String[] args) {
        this.listArguments = new ArgsResolver().resolve(args);
        this.writer = new CLIWriter();
    }

    public void run() {
        initializeAllParameters();
        initializeLogFilter();
        initializeCollectors();
        writeStatistics();
    }

    @SuppressWarnings("checkstyle:InnerAssignment")
    private void initializeAllParameters() {
        TimeInterval.TimeIntervalBuilder timeIntervalBuilder = TimeInterval.builder();

        for (Argument argument : listArguments) {
            switch (argument.type()) {
                case FORMAT -> formatType = FormatType.findByValue(argument.value());
                case TO -> timeIntervalBuilder.to(argument.value());
                case FROM -> timeIntervalBuilder.from(argument.value());
                default -> {
                }
            }
        }

        timeInterval = timeIntervalBuilder.build();
        loadAllLogsToWrapper(listArguments.get(0).value().split(" "));
    }

    private void loadAllLogsToWrapper(String[] paths) {
        List<String> lines = new ArrayList<>();
        for (String path : paths) {
            LogRetriever logRetriever = getRetriever(path);
            lines.addAll(logRetriever.retrieveLogs());
        }
        logSourceWrapper =
            new LogSourceWrapper(
                new LogData(List.of(paths), timeInterval.from(), timeInterval.to()),
                lines.stream().map(NginxLogParser::parse).collect(
                    Collectors.toList())
            );
    }

    private LogRetriever getRetriever(String path) {
        RetrieverSelector selector = RetrieverSelector.link(new LocalRetrieverSelector(), new UrlRetrieverSelector());
        return selector.selectRetriever(path);
    }

    private void initializeLogFilter() {
        logFilter = LogFilter.link(new DateLogFilter(timeInterval.to(), timeInterval.from()));
    }

    private void initializeCollectors() {
        collectors = Arrays.asList(
            new BasicInformationCollector(logFilter),
            new RequestsCollector(logFilter),
            new RequestedResourcesCollector(logFilter),
            new RemoteAddressesCollector(logFilter),
            new RequestTypesCollector(logFilter)
        );
    }

    private void writeStatistics() {
        LogStatsFormatter formatter = formatType.equals(ADOC)
            ? new ASCIIDocLogStatsFormatter()
            : new MarkdownLogStatsFormatter();

        for (LogStatsCollector collector : collectors) {
            writer.out(formatter.format(collector.collect(logSourceWrapper)));
        }
    }
}

