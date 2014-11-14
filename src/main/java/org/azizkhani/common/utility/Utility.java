package org.azizkhani.common.utility;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
public class Utility {
	public static void aa() {
		List<Integer> list=new ArrayList<Integer>();
		list.add(12);
		Utility.<Integer>fillBoxes(12);
	}
	public static <U> void fillBoxes(U u){
		System.out.println(u.getClass());
	}
	
	
	public static void main3(String[] argv) {
		 
		System.out.println("-------- Oracle JDBC Connection Testing ------");
 
		try {
 
			Class.forName("oracle.jdbc.driver.OracleDriver");
 
		} catch (ClassNotFoundException e) {
 
			System.out.println("Where is your Oracle JDBC Driver?");
			e.printStackTrace();
			return;
 
		}
		//-Duser.language=en -Duser.country=US
		System.out.println(Locale.getDefault());

		System.out.println("Oracle JDBC Driver Registered!");
 
		Connection connection = null;
 
		try {
			Properties pty = new java.util.Properties();
			pty.put ("user", "sys");
			pty.put ("password", "azizkhani");
			pty.put ("internal_logon","sysdba");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@46.38.131.174:1521:AFC", pty);
			Statement statement = null;
			String insertTableSQL = "INSERT INTO users(id,firstname, lastname, username, password, email, visitedcount, createdby, updatedby,CREATEDDATE,UPDATEDDATE,ISENABLED) ";
			insertTableSQL=insertTableSQL+" values(1,1,	1,	'admin',	'admin',	'1',	1,	1,	1,TO_DATE('1998/05/31 12:00:00', 'YYYY/MM/DD HH24:MI:SS'),TO_DATE('1998/05/31 12:00:00', 'YYYY/MM/DD HH24:MI:SS'))";
			statement = connection.createStatement();
			 
			System.out.println(insertTableSQL);
 
			// execute insert SQL stetement
			statement.executeUpdate(insertTableSQL);
		} catch (SQLException e) {
 
			System.out.println(e.toString());
			return;
 
		}
 
		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
	}
	public static void main2(String[] args) {
		double d=2.005446919524449;
		System.out.println(d);
	}
	
}
