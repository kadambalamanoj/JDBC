package com.klef.ep.JDBCProject;

import java.sql.DriverManager;

//import java.sql.*;
public class DBConnection {
    public static void main( String[] args )
    {
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		System.out.println("Driver Class Loaded");
    		DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc2", "root", "1234");
    		System.out.println("Connection Estlabished ");
    	}
    	catch(Exception e)
    	{
//    		System.out.println(e.getMessage());
    		System.out.println(e);
    	}
    }
}
