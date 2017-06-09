package design.isp;

public interface Resultset
{
    void first();

    void last();

    void next();

    void previous();

    String getExcerpt();

    String getFullRecord();
}
