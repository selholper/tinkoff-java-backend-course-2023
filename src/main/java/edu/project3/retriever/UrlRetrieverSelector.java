package edu.project3.retriever;

public class UrlRetrieverSelector extends RetrieverSelector {

    private static final String URL_PREFIX = "http";

    @Override
    public LogRetriever selectRetriever(String path) {
        if (path.startsWith(URL_PREFIX)) {
            return new UrlLogRetriever(path);
        }
        return checkNext(path);
    }
}
