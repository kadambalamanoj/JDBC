package com.klef.ep.statementdemo;
import java.sql.*;
import java.util.Scanner;
public class StmtCRUDOperations {
 
	public static void main(String args[]) {
		StmtCRUDOperations operations =new StmtCRUDOperations();
//		  operations.insertstudent();
		operations.viewallstudents();
//		operations.viewstudentbyid();
//		operations.updatestudent();
//		operations.deletestudent();
		
	} 
	public void insertstudent()
	{
		try
		{
			Connection con=null;
			Class.forName("com.mysql.cj.jdbc.Driver");
    		System.out.println("Driver Class Loaded");
    		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc2", "root", "1234");
    		System.out.println("Connection Estlabished ");
    		int sid=104;
    		String sname="Sai";
    		String sgender="MALE";
    		double sage=45.3;
    		String semail="sai@gmail.com";
    		
    		String qry="   insert into student values("+sid+",'"+sname+"','"+sgender+"',"+sage+",'"+semail+"')  ";
    		Statement stmt=con.createStatement();
    		int n=stmt.executeUpdate(qry);
    		
    		System.out.println(n+"Records Inserted Successfully");
    		stmt.close();
    		con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public void viewallstudents() {
		try {
			Connection con=null;
			Class.forName("com.mysql.cj.jdbc.Driver");
    		System.out.println("Driver Class Loaded");
    		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc2", "root", "1234");
    		System.out.println("Connection Estlabished ");
    		String qry="select * from student";
    		Statement stmt=con.createStatement();
    		 ResultSet rs=stmt.executeQuery(qry);
    		 System.out.println("*****Student Details***");
    		 while(rs.next())
    		 {
    			 System.out.println("Student Record:"+rs.getRow());
    			 System.out.println("ID="+rs.getInt(1));
    			 System.out.println("Name="+rs.getString(2));
    			 System.out.println("Gender="+rs.getString(3));
    			 System.out.println("Age="+rs.getDouble(4));
    			 System.out.println("Email="+rs.getString(5));
    			//  similarly like above we can give below the both will give same output
//    			 System.out.println("ID="+rs.getInt("id"));
//    			 System.out.println("Name="+rs.getString("name"));
//    			 System.out.println("Gender="+rs.getString("gender"));
//    			 System.out.println("Age="+rs.getDouble("age"));
//    			 System.out.println("Email="+rs.getString("email"));
    		 }
    		stmt.close();
    		rs.close();
    		con.close();
		}
		catch(Exception e)
		{
		System.out.println(e);
		}
	}
	public void viewstudentbyid()
	{
		try {
			Connection con=null;
			Class.forName("com.mysql.cj.jdbc.Driver");
    		System.out.println("Driver Class Loaded");
    		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc2", "root", "1234");
    		System.out.println("Connection Estlabished ");
    		Scanner sc=new Scanner(System.in);
    		System.out.println("Enter student Id");
    		int sid=sc.nextInt();
    		String qry="select * from student where id="+sid+"";
    		Statement stmt=con.createStatement();
    		ResultSet rs=stmt.executeQuery(qry);
    		if(rs.next())
    		{
    			System.out.println("Name="+rs.getString("name"));
    			System.out.println("Email="+rs.getString("email"));
                 //you can include remaining columns also 
    		}
    		else {
    			System.out.println("Studnt ID Not Found");
    		}
    		stmt.close();
    		rs.close();
    		con.close();
    		sc.close();
		}
		
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public void updatestudent()
	{
		try {
			Connection con=null;
			Class.forName("com.mysql.cj.jdbc.Driver");
    		System.out.println("Driver Class Loaded");
    		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc2", "root", "1234");
    		System.out.println("Connection Estlabished ");
    		int sid=101;
    		String sname="KLEF";
    		double sage=30.5;
//    		 String qry="update student where id=101,name=''";
    		 Statement stmt=con.createStatement();
    		 int n=stmt.executeUpdate(" update student set name='"+sname+"'   ,age="+sage+"  where id="+sid+" ");
    		 if(n>0)
    		 {
    	    		System.out.println(n+"Records Updated Successfully");

    		 }
    		 else {
    			 System.out.println("Student Id not Found");
    		 }
    		 stmt.close();
    		 con.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	public void deletestudent()
	{
		try {
			Connection con=null;
			Class.forName("com.mysql.cj.jdbc.Driver");
    		System.out.println("Driver Class Loaded");
    		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc2", "root", "1234");
    		System.out.println("Connection Estlabished ");
    		System.out.println("Enter student id");
    		Scanner sc=new Scanner(System.in);
    		int sid =sc.nextInt();
    		int n=con.createStatement().executeUpdate(" delete from student where id=" +sid+" ");
    		if(n>0)
    		{
    			System.out.println(n+"Records Deleted Successfully");
    		}
    		else {
    			System.out.println("Student Id not Found");
    		}
    		sc.close();
    		con.close();
		}
		catch(Exception e)
		{
			 System.out.println(e);
		}
	}
}
