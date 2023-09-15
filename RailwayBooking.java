/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bookingsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author LENOVO
 */
public class RailwayBooking {
    public static void main(String[] args) {
        
    int ch;
     Connection conn=null;
        try{
           Class.forName("oracle.jdbc.OracleDriver");
          conn=DriverManager.getConnection("jdbc:oracle:thin:@//DESKTOP-3JDRGG3:1521/xe","oracle","abc");
          PreparedStatement ps=conn.prepareStatement("insert into railway values(?,?,?,?,?)");
                 Scanner sc=new Scanner(System.in);
                 
                 System.out.println("\t\tWelcome to Railway Management System");
                 System.out.println("\t\tWelcome to Login page");
                 System.out.print("Enter your name:");
                 String name=sc.next();
                 System.out.print("Enter password:");
                 String pwd=sc.next();
                 if( name.equalsIgnoreCase("Jatin")||pwd.equalsIgnoreCase("abc"))
                 {
                     System.out.println("Welcome Jatin !!!!");
                 }
                 else
                 {
                     System.out.println("You enter wrong details");
                     System.exit(0);
                 }
                
               
                do
                {
                    System.out.println("_________________________________________________________");
                      System.out.println("What to do you want to do:");
                 System.out.println("1.Book Ticket\n2.View Tickets\n3.Cancel Ticket\n4.Your Ticket\n5.Exit");
                 System.out.print("Enter your choice:");
                  ch=sc.nextInt();
                    switch(ch)
                 {
                     
                     case 1:
                     {
                         //logic for booking ticket
                         System.out.print("Enter your name");
                          name=sc.next();
                        
                         System.out.print("Enter starting point:");
                          String s=sc.next();
                            System.out.print("Enter destination:");
                            String d=sc.next();
                            System.out.print("Enter booking date:");
                           String date=sc.next();
                            System.out.print("Enter Train No:");
                            int t=sc.nextInt();
                            ps.setString(1,name);
                            ps.setString(2, s);
                            ps.setString(3, d);
                            ps.setString(4, d);
                            ps.setString(4, date);
                            ps.setInt(5, t);
                            ps.executeUpdate();
                            System.out.println("Ticket Booked Sucesfully");
                            break;
                            
                            
                     }
                     case 2:
                     {
                         //view ticket
                        Statement st=conn.createStatement();
                        ResultSet rs=st.executeQuery("select * from railway");
                        while(rs.next())
                        {
                            System.out.println("Name:"+rs.getString(1));
                            System.out.println("Starting point:"+rs.getString(2));
                            System.out.println("Destination:"+rs.getString(3));
                            System.out.println("Date of Bokking:"+rs.getString(4));
                            System.out.println("Train No:"+rs.getInt(5));
                            System.out.println("___________________________________");
                        }
                        break;
                     }
                     case 3:
                     {
                         //logic for cancelling ticket
                         System.out.println("Enter your name to cancel ticket:");
                          name=sc.next();
                         int count=0;
                           Statement st=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        ResultSet rs=st.executeQuery("select name from railway ");
                        while(rs.next())
                        {
                            if(rs.getString(1).equals(name))
                            {
                                count++;
                                rs.deleteRow();
                                System.out.println("Ticket cancelled succefully");
                                break;
                            }
                        }
                         if(count==0)
                         {
                             System.out.println("Record Not found");
                         }
                         break;
                     }
                     case 4:
                     {
                         System.out.println("Enter your name to view your ticket:");
                         name=sc.next();
                          Statement st=conn.createStatement();
                        ResultSet rs=st.executeQuery("select * from railway ");
                        while(rs.next())
                        {
                            if(rs.getString(1).equals(name))
                            {
                            System.out.println("Name:"+rs.getString(1));
                            System.out.println("Starting point:"+rs.getString(2));
                            System.out.println("Destination:"+rs.getString(3));
                            System.out.println("Date of Bokking:"+rs.getString(4));
                            System.out.println("Train No:"+rs.getInt(5));
                            break;
                            }
                        }
                         break;
                     }
                    }
                    }while(ch!=5);
                 
        
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            try
            {
                conn.close();
                
            }
            catch(Exception e)
            {
                System.out.println("Error!"+e.getMessage());
            }
        }
    }
}
