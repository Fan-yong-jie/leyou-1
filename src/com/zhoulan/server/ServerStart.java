package com.zhoulan.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerStart implements Runnable{
    private static  Server server=new Server();
    private boolean running=true;
    @Override
    public void run() {

                System.out.println("---服务器端已启动---");
                ServerSocket socket = null;
                try {
                    socket = new ServerSocket(8888);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                    Socket client = null;
                   while (running) {
                    try {
                        client = socket.accept();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Server.user u = new Server.user(client);
                    System.out.println("--客户名为" + u.getName() + "在" + u.time() + "通过" + u.getClient().getPort() + "端口连接----");
                    server.getAll().add(u);
                    System.out.println(server.getAll().size());
                    new Thread(u).start();

            }
        }



    public void close(){
       System.exit(0);
    }
}
