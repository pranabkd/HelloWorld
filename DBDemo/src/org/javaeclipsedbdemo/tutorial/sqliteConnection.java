package org.javaeclipsedbdemo.tutorial;
import java.sql.*;
import javax.swing.*;

public class sqliteConnection {
	
	public static Connection conn = null;
	
	public static Connection dbConnector(){
		
		try{
			
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Pranab\\workspace\\JavaEclipseDBDemo.sqlite");
			return conn;
		
	}catch(Exception exp){
		
		JOptionPane.showMessageDialog(null, exp);
		return null;
	}
		
	}

}
