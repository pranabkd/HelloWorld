package org.javaeclipsedbdemo.tutorial;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.sql.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frame;
	Connection conn = null;
	private JTextField textFldEmail;
	private JPasswordField passwordField;
	ResultSet rs = null;
	PreparedStatement pst = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
		initialize();
		try{
			conn=sqliteConnection.dbConnector();
//			JOptionPane.showMessageDialog(null, "Connection Succeeded");
		}catch(Exception e){
//			JOptionPane.showMessageDialog(null, "Connection Failed");
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(74, 64, 46, 14);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(74, 89, 46, 14);
		frame.getContentPane().add(lblPassword);
		
		textFldEmail = new JTextField();
		textFldEmail.setBounds(172, 61, 201, 20);
		frame.getContentPane().add(textFldEmail);
		textFldEmail.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(172, 86, 201, 20);
		frame.getContentPane().add(passwordField);
		

		
		JButton btnLogin = new JButton("   Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try{
					
					String query = "select count(*) from Employee where Email=? and Pasword=?";
					int resultCounter = 0;
					int resultCount = 0;
					
					char[] pwd = passwordField.getPassword();
					int pwdLength = pwd.length;
					String password = "";
					
					for(int i=0; i<=pwdLength-1; i++){
						password = password + pwd[i];
					}
						
					
					
					pst = conn.prepareStatement(query);
					pst.setString(1, textFldEmail.getText());
					pst.setString(2, password);
					

					
					
					rs = pst.executeQuery();
					while(rs.next()){
						
						resultCount = rs.getInt(1);
						resultCounter = resultCounter + 1;
						
					}
					
					if(resultCount == 1){
						JOptionPane.showMessageDialog(null, "Login Successful");
					}
					else{
						JOptionPane.showMessageDialog(null, "Login Failed");
					}
					

					
					
				}catch(Exception exp){
					
					JOptionPane.showMessageDialog(null, exp.toString());
					
					
				}
				finally{
					try{
					rs.close();
					pst.close();
					}
					catch(Exception exp){
						JOptionPane.showMessageDialog(null, exp.toString());
					}
				}
			}
		});
		btnLogin.setBounds(159, 140, 89, 23);
		frame.getContentPane().add(btnLogin);
	}
}
