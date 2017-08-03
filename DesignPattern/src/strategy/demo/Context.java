package strategy.demo;

public class Context
{
    public void contextInterface()
    {
        strategy.strategyInterface();
    }

    /**
     * @link aggregation
     * @directed 
     */
    private Strategy strategy;
}
