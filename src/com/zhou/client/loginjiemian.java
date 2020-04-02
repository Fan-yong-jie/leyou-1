package com.zhou.client;

import com.sun.security.ntlm.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


    import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

    public class loginjiemian extends JFrame implements ActionListener, KeyListener {

        JButton jbt_exit, Jbt_login;
        JLabel jL_userName, JL_passWord, JL_findPassWord, JL_register, JL_Image;
        JTextField jT_userName, jT_passWord;
        JCheckBox jC_knowPassWordBox, jc_autoLoginBox;
        JComboBox jcb;
        //Clientsend clientsend;
        public static void main(String[] args) {
            loginjiemian loginjiemian=new loginjiemian();
        }


        public loginjiemian() {
            Container pane = this.getContentPane();
            getContentPane().setLayout(null);
            setTitle("QQ低配版1.0");
            // 窗体大小不能改变
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setBounds(0, 0, 355, 265);
            setLocation(500, 300);
            setVisible(true);
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    jbt_exit.doClick();
                }
            });

            // 设置背景图片
            //ImageIcon image1 = new ImageIcon("Images/1.jpg");

            // jL1.setBounds(0, 0, 355, 265);
            // QQ登录头像设定
            // JLabel jL2 = new JLabel();
            // Image image2 = new ImageIcon("e:/a.gif").getImage();
            // jL2.setIcon(new ImageIcon(image2));
            // jL2.setBounds(40, 95, 50, 60);
            //JL_Image=new JLabel(new ImageIcon("image/1.jpg"));
            //JL_Image.setBounds(0, 0, 355, 265);
            //getContentPane().add(JL_Image);
            jL_userName = new JLabel("用户账号:");
            JL_passWord = new JLabel("账号密码:");
            jL_userName.setBounds(45, 100, 70, 20);
            JL_passWord.setBounds(45, 130, 70, 20);
            getContentPane().add(jL_userName);
            getContentPane().add(JL_passWord);
            // 用户号码登录输入框
            jT_userName = new JTextField();
            jT_userName.setBounds(100, 100, 150, 20);
            getContentPane().add(jT_userName);
            // 用户号码登录输入框旁边的文字
            JL_register = new JLabel("注册账号");
            JL_register.setBounds(260, 100, 70, 20);
            getContentPane().add(JL_register);
            // 密码输入框
            jT_passWord = new JPasswordField();
            jT_passWord.setBounds(100, 130, 150, 20);
            getContentPane().add(jT_passWord);

            // 密码输入框旁边的文字
            JL_findPassWord = new JLabel("找回密码");
            JL_findPassWord.setBounds(260, 130, 70, 20);
            getContentPane().add(JL_findPassWord);
            // 输入框下方文字
            jC_knowPassWordBox = new JCheckBox("记住密码");
            jC_knowPassWordBox.setBounds(105, 155, 80, 15);
            jc_autoLoginBox = new JCheckBox("自动登录");
            jc_autoLoginBox.setBounds(185, 155, 80, 15);
            getContentPane().add(jC_knowPassWordBox);
            getContentPane().add(jc_autoLoginBox);
            // 用户登录状态选择
            JComboBox jcb = new JComboBox();
            jcb.addItem("在线");
            jcb.addItem("隐身");
            jcb.addItem("离开");
            jcb.setBounds(40, 150, 55, 20);
            getContentPane().add(jcb);
            // 按钮设定
            Jbt_login = new JButton("登录");
            Jbt_login.addActionListener(this);
            Jbt_login.addKeyListener(this);
            Jbt_login.setBounds(280, 200, 65, 20);
            getContentPane().add(Jbt_login);

            jbt_exit = new JButton("退出");
            jbt_exit.addActionListener(this);
            jbt_exit.addKeyListener(this);
            jbt_exit.setBounds(5, 200, 75, 20);
            getContentPane().add(jbt_exit);
            JButton bt3 = new JButton("设置");
            bt3.setBounds(100, 200, 65, 20);

        }


        @Override
        public void keyPressed(KeyEvent arg0) {
            if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
                Jbt_login.doClick();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void keyTyped(KeyEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void actionPerformed(ActionEvent e) {
//            if (e.getSource() == jbt_exit) {
//                setVisible(false);
//                clientsend.exitclient();
//            }
//            if (e.getSource() == Jbt_login) {
//                String username = jT_userName.getText();
//                username.trim();
//                String passWord = jT_passWord.getText();
//                if (!username.equals("")) {
//                    if (!passWord.equals("")) {
//                        String login_mess = client.login(username, passWord);
//                        if (login_mess.equals("true")) {
//                            this.setVisible(false);
//                            client.showChatFrame(username);
//                        }
//                    } else {
//                        JOptionPane.showMessageDialog(this, "密码输入有误！");
//                    }
//                } else {
//                    JOptionPane.showMessageDialog(this, "用户名输入有误！");
//                }
//            }
        }
    }

