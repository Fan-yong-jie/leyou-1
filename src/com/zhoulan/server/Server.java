package com.zhoulan.server;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;


@SuppressWarnings("all")
public class Server{
    private static Vector<user> all = new Vector<>();
    private static PropertyConfigurator propertyConfigurator;
    public static Vector<user> getAll() {
        return all;
    }
    public static void setAll(Vector<user> all) {
        Server.all = all;
    }
    public List<String> getactive(){
        List<String> namelist=new ArrayList<>();
        for(int i=0;i<all.size();i++){
            namelist.add(i,all.get(i).name);
        }
        return namelist;
    }

    /**
     * 一个user代表一个客户端
     */
        static class user implements Runnable{
            private DataOutputStream dataOutputStream;
        private DataInputStream  dataInputStream;
        private Socket client;
        private String name;
        private boolean isRunning;

        public DataInputStream getDataInputStream() {
            return dataInputStream;
        }

        public Socket getClient() {
            return client;
        }

        public String getName() {
            return name;
        }

        public boolean isRunning() {
            return isRunning;
        }

        public void setDataOutputStream(DataOutputStream dataOutputStream) {
            this.dataOutputStream = dataOutputStream;
        }

        public void setDataInputStream(DataInputStream dataInputStream) {
            this.dataInputStream = dataInputStream;
        }

        public void setClient(Socket client) {
            this.client = client;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setRunning(boolean running) {
            isRunning = running;
        }


        private static Logger logger=Logger.getLogger(Server.class);
        private Server server=new Server();
        public user(){}
        public user(Socket client){
               this.client=client;
               this.isRunning=true;
            try {
                dataInputStream=new DataInputStream(client.getInputStream());
                dataOutputStream=new DataOutputStream(client.getOutputStream());
                 this.name=receive();
                 this.send("欢迎进入明得科技聊天室"+time());
                 this.sendothers(this.name+"来到了聊天室"+time(),true);
                 this.send("当前在线用户:"+server.getactive());
            } catch (IOException e) {
                e.printStackTrace();
                relase();
            }


        }

        public DataOutputStream getDataOutputStream() {
            return dataOutputStream;
        }

        private String receive(){
            String msg="";
            String msg2="";
            String msg3="";
            try {
                msg=dataInputStream.readUTF();
                if((this.name)!=null&&!msg.equals("exit")&&!msg.contains("@")) {
                    System.out.println(this.name + "在" + time() + "发送了" + msg);
                }
                if(msg.equals("exit")||msg==null){
                    relase();
                }
                if(msg.startsWith("@")){
                    int index=msg.indexOf(':');
                    msg2=msg.substring(1,index);
                    msg3=msg.substring(index+1);
                    System.out.println(this.name+"在"+time()+"对"+msg2+"发送了"+msg3);
                }
                return msg;
            } catch (IOException e) {
                logger.error("客户端名为"+this.name+"在"+time()+"异常关闭");
                relase2();
            }
            return msg;
        }
        public void send(String msg){
            try {
                dataOutputStream.writeUTF(msg);
                dataOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
                relase();
            }
        }

        /**
         * 实现群聊和私聊
         * 约定私聊协议: @xxx:
         */
        private void sendothers(String msg,boolean isSYS){
            if(msg.startsWith("@")&&msg.contains(":")){
                int index=msg.indexOf(':');
                String msg2=msg.substring(index+1);
                String name2=msg.substring(1,index);
                for(user other:all){
                    if(other==this){
                        continue;
                    }else{
                        if(other.name.equals(name2)){
                            other.send(this.name+"私聊你说:"+msg2+time());
                        }
                    }
                }
            }else {
                for (user other : all) {
                    if (other == this) {
                        continue;
                    }
                    if (!isSYS&&!msg.equals("exit")) {
                        other.send(this.name + "说:" + msg + time());
                    } else if(!msg.equals("exit")){
                        other.send(msg);
                    }
                }
            }
        }
        public String time(){
            Date date=new Date(System.currentTimeMillis());
            String time=new SimpleDateFormat("yyyy:MM:dd HH:mm:ss").format(date);
            return time;
        }


        @Override
        public void run() {
           while (isRunning){
               String msg=receive();
               if(!msg.equals("")){
                   sendothers(msg,false);
               }
           }
        }
        private void relase(){
//            Clientsend ct=new Clientsend();
//            ClientReceive cr=new ClientReceive();
//            ct.relase();
//            cr.relase();
            System.out.println("客户名为"+this.name+"在"+time()+"断开连接");
            this.isRunning=false;
            CloseUntil.close(dataInputStream,dataOutputStream,client);
            all.remove(this);
            sendothers(this.name+"离开群聊"+time(),true);
        }
         public void relase2(){
//            Clientsend ct=new Clientsend();
//            ClientReceive cr=new ClientReceive();
//            ct.relase();
//            cr.relase();
            this.isRunning=false;
            CloseUntil.close(dataInputStream,dataOutputStream,client);
            all.remove(this);
            sendothers(this.name+"离开群聊"+time(),true);
        }

    }

}
