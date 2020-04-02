package com.zhoulan.server;

import java.io.Closeable;

public class CloseUntil {
    public static void close(Closeable...targets){
        for(Closeable target:targets){
            try {
                if(target!=null){
                    target.close();
                }
            }catch (Exception e){
            }
        }
    }
}
