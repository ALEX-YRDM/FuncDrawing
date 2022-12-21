package com.zbq.ui;

import com.zbq.pars.Parser;
import com.zbq.pars.ParserImpl;

import javax.swing.*;
import java.awt.*;

/**
 * @author zbq
 * @date 2022/12/20 22:05
 */
public class Window extends JFrame {
    JLabel lab1,lab2;
    JTextField jtf;
    JButton button;
    JTextArea jta;

    public Window(){
        setLayout(new FlowLayout());
    }

    public static void main(String[] args) {
        Window window=new Window();
        window.lab1=new JLabel("文件路径: ");
        window.lab2=new JLabel("分析信息: ");
        window.jtf=new JTextField(60);
        window.jta=new JTextArea(30,35);
        window.jta.setBounds(510, 300, 600, 500);
        window.jta.setEditable(true);
        window.jta.setLineWrap(true);
        window.button=new JButton("运行");
        //window.button2=new JButton("以散点图查看");
        window.add(window.lab1);
        window.add(window.jtf);
        window.add(window.button);
        window.add(window.lab2);
        window.add(window.jta);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Java绘图器");
        window.setBounds(500,300,800,600);
        window.setVisible(true);
        window.button.addActionListener(e->{
            String path=window.jtf.getText();
            if(path.trim().equals("")){
                window.jta.setText("文件路径为空,请输入");
            }else{
                Parser parser=new ParserImpl();
                parser.initParser(path);
                parser.parser();
            }
        });
    }
}
