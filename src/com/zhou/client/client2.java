package com.zhou.client;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class client2 {
    private static boolean check = true;
    private static String name;
    public static void main(String[] args) throws Exception {
        System.out.println("----客户端---");
        while (check) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("请输入你的账号");
            name = bufferedReader.readLine();
            Pattern pattern = Pattern.compile("\\d{6}");
            Matcher matcher = pattern.matcher(name);
            if (!matcher.matches()) {
                System.out.println("格式不合规");
            }else {
                check=false;
            }
        }
        Socket socket = new Socket("localhost", 8888);
        Clientsend clientsend = new Clientsend(socket, name);
        ClientReceive clientReceive = new ClientReceive(socket);
        new Thread(clientsend).start();
        new Thread(clientReceive).start();


    }
}
