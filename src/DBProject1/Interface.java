package DBProject1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.EventQueue;


import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class Interface {

	private JFrame frame;
	private JTextField textField_UserName;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface window = new Interface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	Connection con = null;
	private JLabel lblNewLabel_Image;
	public Interface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(300, 300, 550, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField_UserName = new JTextField();
		textField_UserName.setBounds(303, 62, 86, 20);
		frame.getContentPane().add(textField_UserName);
		textField_UserName.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("UserName");
		lblNewLabel.setBounds(179, 61, 89, 23);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Passwd");
		lblNewLabel_1.setBounds(179, 115, 96, 17);
		frame.getContentPane().add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(303, 113, 86, 20);
		frame.getContentPane().add(passwordField);
		
		lblNewLabel_Image = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/Login.jpg")).getImage();
		lblNewLabel_Image.setIcon(new ImageIcon(img));
		lblNewLabel_Image.setBounds(32, 65, 107, 118);
		frame.getContentPane().add(lblNewLabel_Image);
		
		
		JButton btnLogin = new JButton("Login");
		Image img1 = new ImageIcon(this.getClass().getResource("/ok2.png")).getImage();
		btnLogin.setIcon(new ImageIcon(img1));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
					String Username = textField_UserName.getText();
					String Passwd = passwordField.getText();
				
					if (Username.equals("C##VU") && Passwd.equals("duonghoanvu")){
						JOptionPane.showMessageDialog(null, "Login successfully");
						frame.dispose();
						DepartmentInfo reInfo = new DepartmentInfo();
						reInfo.setVisible(true);
					}
					
					else
						JOptionPane.showMessageDialog(null, "No login");			
				
			}
		});
		btnLogin.setBounds(215, 181, 209, 69);
		frame.getContentPane().add(btnLogin);
	}
}
