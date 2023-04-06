package com.view.gui;

import com.dao.UserDAO;
import com.model.UserInfo;

import javax.swing.*;
import java.awt.*;

import static com.model.UserInfo.isIsLOGIN;

public class LoginPage {

    JFrame frame = new JFrame();
    JButton loginButton = new JButton("LogIn");
    JButton resetButton = new JButton("Reset");
    JTextField userIdInput = new JTextField();
    JPasswordField passwordInput = new JPasswordField();
    JLabel userIdLabel = new JLabel("USERNAME:");
    JLabel passwordLabel = new JLabel("PASSWORD:");
    JLabel messageLabel = new JLabel();
    UserDAO dao = new UserDAO();

    public LoginPage() {

        userIdLabel.setBounds(50,100,75,25);
        passwordLabel.setBounds(50,150,75,25);

        messageLabel.setBounds(125,250,250,35);
        messageLabel.setFont(new Font(null,Font.ITALIC,25));

        userIdInput.setBounds(125,100,200,25);
        passwordInput.setBounds(125,150,200,25);

        loginButton.setBounds(125,200,100,25);
        loginButton.setFocusable(false);
        loginButton.addActionListener(e -> {
            String user = userIdInput.getText();
            String pass = String.valueOf(passwordInput.getPassword());
            if(user != null && pass.length()>5) {
                dao.login(new UserInfo(user,pass));
                if(isIsLOGIN()){
                    messageLabel.setForeground(Color.green);
                    userIdInput.setText("");
                    passwordInput.setText("");
                    messageLabel.setText("Login successful");
                }else {
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("Failed to Login");
                }
            }
        });

        resetButton.setBounds(225,200,100,25);
        resetButton.setFocusable(false);
        resetButton.addActionListener(e -> {
            userIdInput.setText("");
            passwordInput.setText("");
            messageLabel.setText("");
        });
        frame.add(messageLabel);
        frame.add(userIdLabel);
        frame.add(userIdInput);
        frame.add(passwordLabel);
        frame.add(passwordInput);
        frame.add(loginButton);
        frame.add(resetButton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
