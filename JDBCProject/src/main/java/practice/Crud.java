package practice;
import java.sql.*;
import java.util.Scanner;
public class Crud {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Crud ob=new Crud();
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter 1 to insert:\nEnter 2 to view all:\nEnter 3 to view by id:\nEnter 4 to update:\nEnter 5 to delete:");
        int c=sc.nextInt();
        switch(c) {
        case 1:ob.insert();
               break;
        case 2: ob.viewallstudents();
               break;
        case  3: ob.viewstudentbyid();
               break;
        case 4: ob.updatestudent();
               break;
        case 5: ob.deletestudent();
               break;
        }
//        ob.insert();
//        ob.viewallstudents();
//        ob.viewstudentbyid();
//        ob.deletestudent();
//        ob.updatestudent();
	}
    public void insert()
    {
    	try {
    		  Connection con=null;
    		  Class.forName("com.mysql.cj.jdbc.Driver");
    		  con=DriverManager.getConnection("jdbc:mysql://localhost:3306/pdb", "root", "1234");
//    		 System.out.println("Connection set")
    		  while(true)
    		  {
    			  System.out.println("Enter details of the student:Id,name,gender,Email");
    	    		Scanner sc=new Scanner(System.in);
    	    		
    	    		   int sid=sc.nextInt();
    	    		   String sname=sc.next();
    	    		   String sgender=sc.next();
    	    		   String semail=sc.next();
    	    		   String qry=" insert into student values("+ sid+",'"+sname+"','"+sgender+"','"+semail+"')  ";
    	    		   Statement st=con.createStatement();
    	    		   int n= st.executeUpdate(qry);
    	    		   System.out.println(n+""+"Records inserted successfully");
    	    		   System.out.println("If you want to insert again enter yes if not enter no");
    	    		   String c=sc.next();
    	    		   if(c.equalsIgnoreCase("no"))
    	    		   {
    	    			   break;
    	    		   }
    		  }
    		    
    	}
    	catch(Exception e)
    	{
    		System.out.println(e);
    	}
    	
    }
    public void viewallstudents()
	{
		try {
			
			Connection con=null;
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/pdb", "root", "1234");
			String qry="select * from student";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(qry);
			 while(rs.next())
			 {
				 System.out.println("Id="+rs.getInt("id"));
				 System.out.println("Name="+rs.getString("name"));
				 System.out.println("Gender="+rs.getString("gender"));
				  System.out.println("Email="+rs.getString("email"));
			 }
			 con.close();
			 st.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
    public void viewstudentbyid()
    {
    	try
    	{
    		Connection con=null;
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/pdb", "root", "1234");
    		System.out.println("Enter Id");
    		Scanner sc=new Scanner(System.in);
    		int sid=sc.nextInt();
    		String qry=" select name,email from student where id= "+sid+"";
    		Statement st= con.createStatement();
    		      ResultSet rs=st.executeQuery(qry);
    		      while(rs.next())
    		      {
    		    	  System.out.println("Name="+rs.getString("name"));
    		    	  System.out.println("Email="+rs.getString("email"));
    		      }
    		      sc.close();
    		      st.close();
    		      con.close();
    	}
    	catch(Exception e)
    	{
    		System.out.println(e);
    	}
    }
    public void updatestudent()
    {
    	try {
    	  Driver drv= new com.mysql.cj.jdbc.Driver();
    	  DriverManager.registerDriver(drv);
//    		Class.forName("com.mysql.cj.jdbc.Driver");
    		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/pdb", "root","1234");
    		Scanner sc=new Scanner(System.in);
    		System.out.println("Enter the values to update based on id");
    		int sid=sc.nextInt();
    		String sname=sc.next();
    		String sgender=sc.next();
    		String semail=sc.next();
    		
    		String qry=" update student set name='"+sname+"',gender='"+sgender+"',email='"+ semail+"'where id="+sid+"";
    		Statement st=con.createStatement();
    		    int n=st.executeUpdate(qry);
    		   if(n>0)
       		 {
       	    		System.out.println(n+"Records Updated Successfully");

       		 }
       		 else {
       			 System.out.println("Student Id not Found");
       		 }
    		   con.close();
    		   st.close();
    		   sc.close();
    		
    	}
    	catch(Exception e)
    	{
    		System.out.println(e);
    	}
    }
    public void deletestudent()
    {
    	try {
    		
//    		 Driver d= new com.mysql.cj.jdbc.Driver();
//    		DriverManager.registerDriver(d);
         Class.forName("com.mysql.cj.jdbc.Driver");
         Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/pdb", "root", "1234");
         Statement st=con.createStatement();
         Scanner sc=new Scanner(System.in);
 		System.out.println("Enter the  id to delete record");
 		int sid=sc.nextInt();
         String qry=" delete from student where id="+sid+"";
         int n=st.executeUpdate(qry);
         if(n>0)
 		{
 			System.out.println(n+"Records Deleted Successfully");
 		}
 		else {
 			System.out.println("Student Id not Found");
 		}
         sc.close();
         con.close();
         st.close();
    		 
    	}
    	catch(Exception e)
    	{
    		System.out.println(e);
    	}
    }
}
