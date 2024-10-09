package com.klef.ep.JDBCProject;

import java.sql.*;

public class TransactionManagement 
{
    public static void main(String[] args)
   {
        Connection con = null;
        Savepoint savepoint1 = null;
        Savepoint savepoint2 = null;
        Savepoint savepoint3 = null;

        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc2", "root", "1234");
            System.out.println("Connection Established");

            con.setAutoCommit(false);

            // Insert operation
            con.createStatement().executeUpdate("INSERT INTO user VALUES(1, 'manoj', 'MALE', '7200')");
            con.createStatement().executeUpdate("INSERT INTO user VALUES(11, 'sai', 'Male', '9')");
//            con.createStatement().executeUpdate("INSERT INTO user VALUES(111, 'saimanoj', 'MALE', '9934')");
            System.out.println("Insertion Done");
            savepoint1 = con.setSavepoint("Savepoint1");

            // Update operation
            con.createStatement().executeUpdate("UPDATE user set name='KLU' WHERE id=1001");
            System.out.println("Update Done");
            savepoint2 = con.setSavepoint("Savepoint2");

            // Delete operation
            con.createStatement().executeUpdate("DELETE FROM user WHERE id=1001");
            System.out.println("Deletion Done");
            savepoint3 = con.setSavepoint("Savepoint3");

            // Uncomment one of the following lines to rollback to a specific savepoint
            con.rollback(savepoint1);
            System.out.println("Rolled back to Savepoint1");
//             con.rollback(savepoint2);
//             System.out.println("Rolled back to Savepoint2");
            // con.rollback(savepoint3);
            // System.out.println("Rolled back to Savepoint3");

//            con.commit();
//            System.out.println("Transaction Committed Successfully");
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            try 
            {
                if (con != null) 
                {
                    con.rollback();
                    con.commit();
                    System.out.println("Transaction rolled back.");
                }
            } 
            catch (SQLException se) 
            {
                se.printStackTrace();
            }
        } 
        finally 
        {
            if (con != null) 
            {
                try 
                {
                    con.close();
                } 
                catch (SQLException se) 
                {
                    se.printStackTrace();
                }
            }
        }
    }
}
