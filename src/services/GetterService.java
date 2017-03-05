package services;

/**
 * Created by rd019985 on 05/03/2017.
 */
public class GetterService {

    public final static StringBuilder stringBuilder = new StringBuilder();

    public static void flushSB(){
        stringBuilder.setLength(0);
    }

}
