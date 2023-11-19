package edu.project3;

import edu.project3.src.NginxLogAnalyzerApplication;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        new NginxLogAnalyzerApplication(args).run();
    }
}
