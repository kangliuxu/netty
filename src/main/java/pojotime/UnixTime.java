package pojotime;

import java.util.Date;

/**
 * @author xkl
 * @date 2020/3/11
 * @description
 **/
public class UnixTime {
    private final long value;

    public UnixTime() {
        this(System.currentTimeMillis()/1000L + + 2208988800L);
    }

    public UnixTime(long value) {
        this.value = value;
    }

    public long value(){
        return value;
    }

    @Override
    public String toString() {
        return new Date((value() - 2208988800L) * 1000L).toString();
    }
}
