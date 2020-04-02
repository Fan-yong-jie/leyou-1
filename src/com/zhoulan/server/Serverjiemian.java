package com.zhoulan.server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Serverjiemian extends JFrame implements ActionListener {
    JPanel jp1;
    JButton jb1, jb2, jb3;
    JTextArea jta;

    DefaultListModel<String> jlist = new DefaultListModel<>();
    JList<String> list = new JList<>(jlist);

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Serverjiemian sh = new Serverjiemian();
    }

    public Serverjiemian() {
        jp1 = new JPanel();
        jta = new JTextArea();
        jb1 = new JButton("启动服务器");
        jb2 = new JButton("关闭服务器");
        jb3 = new JButton("显示用户列表");

        jp1.add(jb1);
        jb1.addActionListener(this);
        jb2.addActionListener(this);
        jb3.addActionListener(this);
        jp1.add(jb2);
        jp1.add(jb3);
        jta.setSize(700, 700);
        jta.add(list);

        this.add(jp1, "North");
        this.add(jta, "Center");
        this.setDefaultCloseOperation(3);
        this.setSize(1000, 800);
        this.setVisible(true);
    }



    public void printUser() {
        Server s=new Server();
        List list=s.getactive();
        String msg=list.toString();
        String msg2=jlist.toString();
        if(!msg.equals(msg2)) {
            for (int i = 0; i < list.size(); i++) {
                jlist.addElement(list.get(i).toString());
            }
        }
        System.out.println(jlist);
        jlist.removeAllElements();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ServerStart server = new ServerStart();
            if (e.getSource() == jb1) {
                new Thread(server).start();
            }
            if (e.getSource() == jb2) {
                System.out.println("------");
                 server.close();
            }
            if (e.getSource() == jb3) {
                printUser();
            }

        }
    }


