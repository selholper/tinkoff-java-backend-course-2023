package edu.project3.formatter;

import edu.project3.model.FormatterComponent;
import java.util.List;

public class ASCIIDocLogStatsFormatter extends LogStatsFormatter {

    @Override
    public String format(FormatterComponent formatterComponent) {
        StringBuilder formattedLog = new StringBuilder();
        int[] columnMaxWidth = new int[formatterComponent.tableHeaders().size()];
        calculateColumnMaxWidth(columnMaxWidth, formatterComponent);
        return formattedLog
            .append(formatHeader(formatterComponent.header()))
            .append(formatTableHeaders(formatterComponent.tableHeaders(), columnMaxWidth))
            .append(formatTableRows(formatterComponent.lines(), columnMaxWidth))
            .toString();

    }

    @Override
    protected String formatHeader(String header) {
        return "=== " + header + "\n";
    }

    @Override
    protected String formatTableHeaders(List<String> tableHeaders, int[] columnMaxWidth) {
        StringBuilder formattedTableHeaders = new StringBuilder();
        formattedTableHeaders.append("|");
        for (int j : columnMaxWidth) {
            formattedTableHeaders.append("=".repeat(j));
        }
        formattedTableHeaders.append("=");
        formattedTableHeaders.append("\n");
        for (int i = 0; i < tableHeaders.size(); i++) {
            String header = tableHeaders.get(i);
            formattedTableHeaders
                .append("|")
                .append(" ".repeat(columnMaxWidth[i] - header.length()))
                .append(header);
        }
        formattedTableHeaders
            .append("\n\n");
        return formattedTableHeaders.toString();
    }

    @Override
    protected String formatTableRows(List<String> tableRows, int[] columnMaxWidth) {
        StringBuilder formattedTableRows = new StringBuilder();
        for (String row : tableRows) {
            String[] separatedRow = row.split(SEPARATOR);
            for (int i = 0; i < separatedRow.length; i++) {
                formattedTableRows
                    .append("|")
                    .append(" ".repeat(columnMaxWidth[i] - separatedRow[i].length()))
                    .append(separatedRow[i]);
            }
            formattedTableRows.append("\n");
        }
        formattedTableRows.append("|");
        for (int j : columnMaxWidth) {
            formattedTableRows.append("=".repeat(j));
        }
        formattedTableRows.append("=").append("\n");
        return formattedTableRows.toString();
    }
}
