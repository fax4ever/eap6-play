package it.redhat.demo.util;

/**
 * Created by fabio on 28/10/16.
 */
public class Counter {

    public static final int DEFAULT_TOTAL = 10;
    public static final int HOW_MANY_LOG = 10;

    private Integer total;
    private Integer star;
    private Integer counter = 0;

    public Counter(Integer total) {

        //if total is null or invalid use default value
        if (total == null || total < 0) {
            total = DEFAULT_TOTAL;
        }
        this.total = total;

        star = total / HOW_MANY_LOG;
        int rest = total % 10;
        if (rest > 0) {
            star++;
        }

    }

    public Integer getCounter() {
        return counter;
    }

    public void increment() {
        counter++;
    }

    public boolean logThisTime() {
        return (counter % star == 0);
    }

    public boolean isComplete() {
        return (counter+1) >= total;
    }

}
