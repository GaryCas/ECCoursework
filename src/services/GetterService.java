package services;

/**
 * Created by rd019985 on 05/03/2017.
 */
public class GetterService {

    private static StringBuilder stringBuilder;

    public static StringBuilder getStringBuilder(){
        if(stringBuilder == null){
            stringBuilder = new StringBuilder();
        }

        stringBuilder.setLength(0);

        return stringBuilder;
    }
}
