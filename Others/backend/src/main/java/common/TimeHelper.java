package common;

/**
 * @author: OY
 * @date: 19:16 2019/12/15
 */
public class TimeHelper {
    private long time;
    public void start(){
        time = System.currentTimeMillis();
    }
    public long getTime(){
        return  System.currentTimeMillis()-time;
    }
    public void reset(){
        time=0;
    }
}
