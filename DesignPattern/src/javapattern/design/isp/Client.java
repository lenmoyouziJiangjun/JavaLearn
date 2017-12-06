package javapattern.design.isp;

public class Client
{
    /**
     * @link aggregation
     * @directed 
     */
    private Indexer indexCursor;

    /**
     * @link aggregation
     * @directed 
     */
    private Searcher searcher;

    /**
     * @link aggregation
     * @directed 
     */
    private BadExample lnkBadExample;
}
