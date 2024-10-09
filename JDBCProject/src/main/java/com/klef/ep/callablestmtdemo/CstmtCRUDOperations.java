package com.klef.ep.callablestmtdemo;

import java.sql.*;
import java.util.Scanner;

public class CstmtCRUDOperations {

    public static void main(String[] args) {
        CstmtCRUDOperations operations = new CstmtCRUDOperations();
//        operations.insertfaculty();
        operations.viewfaculty();
//        operations.getfacultycount();
//        operations.getfacultynamebyid();
//        operations.updatefaculty();
//        operations.getfacultysalarybyid();
//        operations.deletefaculty();
//        operations.viewfacultybyid();
//        operations.gettotalfacultysalary();
    }

    public void insertfaculty() {
        try {
            Connection con = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Class Loaded");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc2", "root", "1234");
            System.out.println("Connection Established");

            int fid = 103;
            String fname = "minnie";
            String fgender = "Female";
            double fsalary = 25000.50;

            // Use java.sql.CallableStatement
            CallableStatement cstmt = con.prepareCall("{ call insertfaculty(?,?,?,?)}");
            cstmt.setInt(1, fid);
            cstmt.setString(2, fname);
            cstmt.setString(3, fgender);
            cstmt.setDouble(4, fsalary);

            cstmt.execute(); // Ensure to execute the statement
            System.out.println("Faculty inserted successfully");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void updatefaculty()
    {
    	try
    	{
    		Connection con=null;
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc2", "root", "1234");
    		System.out.println("Connection Estlabished");
    		Scanner sc=new Scanner(System.in);
    		System.out.println("Enter Faculty id,name,salary");
    		int fid=sc.nextInt();
    		String fname=sc.next();
    		double fsal=sc.nextDouble();
    		 CallableStatement cstmt= con.prepareCall("{ call updatefaculty(?,?,?)}");
    		   cstmt.setInt(1, fid);
    		   cstmt.setString(2, fname);
    		   cstmt.setDouble(3, fsal);
    		   cstmt.execute();
    	}
    	catch (Exception e) 
    	{
    		System.out.println(e);
		}
    }
	//Implement updatefaculty() and deletefaculty()
    public void viewfaculty()
	{
		try
		{
			Connection con=null;
			Class.forName("com.mysql.cj.jdbc.Driver");
    		System.out.println("Driver Class Loaded");
    		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc2", "root", "1234");
    		System.out.println("Connection Estlabished ");
    		CallableStatement cstmt=con.prepareCall("{call viewallfaculty()}");
    		ResultSet rs=cstmt.executeQuery();
    		while(rs.next())
    		{
    			System.out.println("Faculty ID="+rs.getInt("id"));
    			System.out.println("Faculty name="+rs.getString("name"));
    			System.out.println("Faculty Gender="+rs.getString("gender"));
    			System.out.println("Faculty Salary="+rs.getDouble("salary"));
    		}
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
    
    public void deletefaculty() 
    {
		try
		{
			Connection con=null;
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc2", "root", "1234");
			 Scanner sc=new Scanner(System.in);
			 System.out.println("Enter Faculty id");
			 int fid=sc.nextInt();
			CallableStatement cstmt=con.prepareCall("{ call deletefaculty(?)}");
			cstmt.setInt(1, fid);
			cstmt.execute();
		}
		catch (Exception e) 
		{
           System.out.println(e);	
         }
	}
    public void viewfacultybyid() 
    {
		try
		{
			Connection con=null;
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc2", "root", "1234");
			 Scanner sc=new Scanner(System.in);
			 System.out.println("Enter Faculty id");
			 int fid=sc.nextInt();
			CallableStatement cstmt=con.prepareCall("{ call displayfacultybyid(?)}");
			cstmt.setInt(1, fid);
			ResultSet rs=cstmt.executeQuery();
			while(rs.next())
			{
				System.out.println(rs.getInt(1));
				System.out.println(rs.getString(2));
				System.out.println(rs.getString(3));
				System.out.println(rs.getDouble(4));

			}
		}
		catch (Exception e) 
		{
           System.out.println(e);	
         }
	}
    //Implement displayfacultybyid() as like viewallfaculty() by passing one parameter which is id
    public void getfacultycount()
    {
    	try
		{
			Connection con=null;
			Class.forName("com.mysql.cj.jdbc.Driver");
    		System.out.println("Driver Class Loaded");
    		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc2", "root", "1234");
    		System.out.println("Connection Estlabished ");
    		CallableStatement cstmt=con.prepareCall("{ call getfacultycount(?)}");
    		cstmt.registerOutParameter(1, java.sql.Types.INTEGER);
    		cstmt.execute();
    		System.out.println("Faculty Count="+cstmt.getInt(1));
    		System.out.println("Faculty Count="+cstmt.getInt("fcount"));//@fcount
    		cstmt.close();
    		con.close();
    		
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
    public void gettotalfacultysalary()
    {
    	try
    	{
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc2", "root", "1234");
    		CallableStatement cstmt=con.prepareCall("{ call gettotalfacultysalary(?)}");
    		 cstmt.registerOutParameter(1, java.sql.Types.DECIMAL);
    		 cstmt.execute();
    		 System.out.println(cstmt.getDouble(1));
    	}
    	catch (Exception e) 
    	{
           System.out.println(e);
    	}
    }
    //implement gettotalfacultysalary() as like getfacultycount()
    public void getfacultynamebyid()
    {
    	try
		{
			Connection con=null;
			Class.forName("com.mysql.cj.jdbc.Driver");
    		System.out.println("Driver Class Loaded");
    		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc2", "root", "1234");
    		System.out.println("Connection Estlabished ");
    		int fid=101;//implement Scanner class
    		CallableStatement cstmt=con.prepareCall("{ call getfacultynamebyid(?,?)}");// here ? means total
    		cstmt.setInt(1, fid);
    		cstmt.registerOutParameter(2, java.sql.Types.VARCHAR);
    		cstmt.execute();
    		System.out.println("Faculty name="+cstmt.getString(2));
    		System.out.println("Faculty name="+cstmt.getString("fname"));//here fname is column parameter name
            cstmt.close();
            con.close();
    		
    		cstmt.close();
    		con.close();
    		
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
    }
    //use viewfacultybyid() stored procedure to 
    public void getfacultysalarybyid()
    {
    	try
		{
			Connection con=null;
			Class.forName("com.mysql.cj.jdbc.Driver");
    		System.out.println("Driver Class Loaded");
    		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc2", "root", "1234");
    		System.out.println("Connection Estlabished ");
    		int fid=101;
    		CallableStatement cstmt=con.prepareCall("{ call getfacultysalarybyid (?)}");// here ? means total
    		cstmt.setInt(1, fid);
    		cstmt.registerOutParameter(1,java.sql.Types.DECIMAL);
    		cstmt.execute();
    		System.out.println("Faculty Salary= "+cstmt.getDouble(1));
    		System.out.println("Faculty Salary= "+cstmt.getDouble("ep"));//@ep
            cstmt.close();
            con.close();
    		
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
    }
}
