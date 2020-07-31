package util;

/**
 * @Author:xiang
 * @Date:2020/7/15 21:02
 */
public class ThreadLocalHolder {

    private static final ThreadLocal<Integer> COUNT=new ThreadLocal<>();

    public static ThreadLocal<Integer> get(){
        return COUNT;
    }
}
