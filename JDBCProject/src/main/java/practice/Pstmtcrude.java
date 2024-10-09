package practice;
import java.util .*;
import java.sql.*;
public class Pstmtcrude {
	public static void main(String[] args) {

		
	   Pstmtcrude ob= new Pstmtcrude();
//	   ob.addfaculy();
//	   ob.viewfaculty();
	   ob.viewbyid();
	}
	public  void addfaculy()
	{
		 try 
		 {
			 Connection con=null;
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/pdb", "root", "1234");
			 System.out.println(" Enter faculty id,name,gender,email");
              Scanner sc=new Scanner(System.in)	;
                 int fid=sc.nextInt();
                 String fname=sc.next();
                 String fgender=sc.next();
                 String femail=sc.next();
              String qry="insert into faculty values(?,?,?,?)";
            PreparedStatement pstmt=con.prepareStatement(qry);
             pstmt.setInt(1, fid);
             pstmt.setString(2, fname);
             pstmt.setString(3, fgender);
             pstmt.setString(4, femail);
              int n=pstmt.executeUpdate();
              System.out.println(n+ "Records successfully inserted");
              con.close();
              pstmt.close();
		 }
		 catch (Exception e)
		 {
			 System.out.println(e);
		 }
	}
    public void viewfaculty()
    {
    	try 
		 {
			 Connection con=null;
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/pdb", "root", "1234");
			String qry="select * from faculty";
			PreparedStatement pstmt= con.prepareStatement(qry);
			 ResultSet rs=pstmt.executeQuery();
			 while(rs.next())
			 {
				 System.out.println("Id: "+rs.getInt(1));
				 System.out.println("Name: "+rs.getString(2));
				 System.out.println("Gender: "+rs.getString(3));
				 System.out.println("Email: "+rs.getString(4));
			 }
             con.close();
             pstmt.close();
		 }
		 catch (Exception e)
		 {
			 System.out.println(e);
		 }
    }
    public void viewbyid()
    {
    	try 
		 {
			 Connection con=null;
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/pdb", "root", "1234");
             Scanner sc=new Scanner(System.in)	;
              System.out.println("Enter faculy id");
			 int fid=sc.nextInt();
			String qry="select name,email from faculty where id="+fid+"";
			PreparedStatement pstmt= con.prepareStatement(qry);
			 ResultSet rs=pstmt.executeQuery();
			 while(rs.next())
			 {
				 System.out.println("Name: "+rs.getString(1));
				 System.out.println("Email: "+rs.getString(2));
			 }
            con.close();
            pstmt.close();
		 }
		 catch (Exception e)
		 {
			 System.out.println(e);
		 }
    }
}
