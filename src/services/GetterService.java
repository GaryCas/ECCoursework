package services;

import entities.ApplicationVariables;

/**
 * Created by rd019985 on 05/03/2017.
 */
public class GetterService {

    public final static StringBuilder stringBuilder = new StringBuilder();
    private static ApplicationVariables applicationVariables;

    public static void flushSB(){
        stringBuilder.setLength(0);
    }

    public static ApplicationVariables getApplicationVariables(){
        if(applicationVariables == null){
            applicationVariables = new ApplicationVariables();
        }
        return applicationVariables;
    }



}
