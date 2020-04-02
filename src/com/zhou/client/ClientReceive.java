package com.zhou.client;

import com.zhoulan.server.CloseUntil;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 消息接受端
 */
public class ClientReceive implements Runnable {
    private DataInputStream dataInputStream;
    private Socket client;
    private boolean isRunning;
    private boolean isstop=false;
    public ClientReceive(){}
    public  ClientReceive(Socket client){
        this.client=client;
        this.isRunning=true;
        try {
            dataInputStream=new DataInputStream(client.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            relase();
        }
    }
    private String receive(){
        String msg="";
        try {
            msg=dataInputStream.readUTF();
        } catch (IOException e) {
           relase();
        }
        return msg;
    }

    /**
     * 释放资源
     */
    public void relase(){
        CloseUntil.close(dataInputStream,client);
        this.isstop=true;
    }
    @Override
    public void run() {
      while (isRunning){
          String msg=receive();
          if(!msg.equals("")){
              System.out.println(msg);
          }
          if(isstop){
              System.exit(0);
          }
      }
    }
}
