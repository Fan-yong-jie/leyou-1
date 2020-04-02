package com.zhou.client;

import com.zhoulan.server.CloseUntil;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Clientsend implements Runnable {
    private DataOutputStream dataOutputStream;
    private BufferedReader bufferedReader;
    private Socket client;
    private String name;
    private boolean isRunning=true;
    private boolean isstop=false;
    public Clientsend(){}
    public Clientsend(Socket client,String name){
        this.client=client;
        this.name=name;
        bufferedReader=new BufferedReader(new InputStreamReader(System.in));
        try {
            dataOutputStream=new DataOutputStream(client.getOutputStream());
            send(name);
        } catch (IOException e) {
           this.relase();
        }
    }
    private void send(String msg){
        try {
            dataOutputStream.writeUTF(msg);
            dataOutputStream.flush();
        } catch (IOException e) {
            relase();
        }
    }
    public void exitclient(){
        String exitmasg="exit";
        send(exitmasg);
    }

    /**
     * 从控制台获取消息
     */
    private String getmsg(){
        String msg="";
        try {
            msg=bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            relase();
        }
        return msg;
    }

    @Override
    public void run() {
      while (isRunning){
          String msg=getmsg();
          if(!msg.equals("")) {
              send(msg);
          }
          if(isstop){
              System.exit(0);
          }
      }
    }
   public void relase(){
       CloseUntil.close(dataOutputStream,client);
       this.isstop=true;
    }
}
