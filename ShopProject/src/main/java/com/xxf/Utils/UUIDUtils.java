package com.xxf.Utils;

public class UUIDUtils {

    public static String getUUid(){
        return java.util.UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

}
