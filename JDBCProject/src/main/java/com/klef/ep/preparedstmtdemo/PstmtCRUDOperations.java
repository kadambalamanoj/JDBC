package com.klef.ep.preparedstmtdemo;
import java.sql.*;
import java.util.Scanner;
public class PstmtCRUDOperations {

	public static void main(String[] args) {
		
     PstmtCRUDOperations operations=new PstmtCRUDOperations();
//     operations.addproduct();
     operations.viewallproducts();
//     operations.viewproductsbyid();
//     operations.updateproduct();
//     operations.deleteproduct();
	}
	public void addproduct()
	{
		try {
			Connection con=null;
			Class.forName("com.mysql.cj.jdbc.Driver");
    		System.out.println("Driver Class Loaded");
    		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc2", "root", "1234");
    		System.out.println("Connection Estlabished ");
    		int pid=1003;
    		String pcatagory="Gadgets";
    		String pname="Watch";
    	  double pcost=3500.50;
    	  int pstock=50;
    	  PreparedStatement pstmt=con.prepareStatement(" insert into product values(?,?,?,?,?)");
    	  pstmt.setInt(1,pid);
    	  pstmt.setString(2,pcatagory);
    	  pstmt.setString(3, pname);
    	  pstmt.setDouble(4, pcost);
    	  pstmt.setInt(5, pstock);
    	  int n=pstmt.executeUpdate();
    	  System.out.println(n+"Records Inserted Successfully");
    	  pstmt.close();
    	  con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public void viewallproducts()
	{
		
    try {
			
    	Connection con=null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver Class Loaded");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc2", "root", "1234");
		System.out.println("Connection Estlabished ");
		PreparedStatement pstmt=con.prepareStatement("select * from product");
		ResultSet rs=pstmt.executeQuery();
		System.out.println("***Product Details ***");
		int i=1;
		while(rs.next())
		{
			System.out.println("Produt"+i);
			System.out.println("ID:"+rs.getInt(1));
			System.out.println("Category:"+rs.getString(2));
			System.out.println("Name:"+rs.getString(3));
			System.out.println("Cost:"+rs.getDouble(4));
			System.out.println("Stock:"+rs.getInt(5));
			i++;
		}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	
	}
	//display product name,cost and stock based on product id
	public void viewproductsbyid()
	{
		
		
       try {
			
    	   Connection con=null;
   		Class.forName("com.mysql.cj.jdbc.Driver");
   		System.out.println("Driver Class Loaded");
   		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc2", "root", "1234");
   		System.out.println("Connection Estlabished ");
   		Scanner sc=new Scanner(System.in);
   		System.out.println("Enter product Id");
   		int pid=sc.nextInt();
   		 String qry=" select name,cost,stock from product where id=?";
		PreparedStatement pstmt=con.prepareStatement(qry);
		pstmt.setInt(1, pid);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next())
		{
			System.out.println("Name:"+rs.getString(1));
			System.out.println("Cost:"+rs.getDouble(2));
			System.out.println("Stock:"+rs.getInt(3));
		}
		else {
			System.out.println("Product Id not found");
		}
		pstmt.close();
		con.close();
		sc.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
  public void updateproduct()
  {
	   try {
		   Connection con=null;
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Class Loaded");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc2", "root", "1234");
			System.out.println("Connection Estlabished ");
			int pid=1001;
			String pname="Track";
			int pstock=90;
			PreparedStatement pstmt=con.prepareStatement("update product set name=?,stock=? where id =?");
			
			pstmt.setString(1, pname);
			pstmt.setInt(2, pstock);
			pstmt.setInt(3, pid);
			int n=pstmt.executeUpdate();
			if(n>0)
			{
				System.out.println("product Updated successfuly");
			}
			else
			{
				System.out.println("Product id  not Found");
			}
			pstmt.close();
			con.close();
		}
	   
		catch(Exception e)
		{
			System.out.println(e);
		}
  }
  public void deleteproduct()
	{
	    try {
	    	Connection con=null;
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Class Loaded");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc2", "root", "1234");
			System.out.println("Connection Estlabished ");
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter product Id:");
			int pid=sc.nextInt();
			PreparedStatement pstmt=con.prepareStatement("delete from product where id=?");
			pstmt.setInt(1, pid);
			int n=pstmt.executeUpdate();
			if(n>0)
			{
				System.out.println("Product Deleted successfully");
			}
			else {
				System.out.println("Product ID not Found");
			}
			viewallproducts();
			con.close();
			sc.close();
			pstmt.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

}
