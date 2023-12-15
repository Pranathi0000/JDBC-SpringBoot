
package crudApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CRUDOperations {
	
	private static final String URL="jdbc:mysql://localhost:3306/School8";
	private static final String username="root";
	private static final String password="Mysql@1234";
	
	//creating an object for the Scanner class
	public static Scanner obj=new Scanner(System.in);
	
	//insertRecord method will insert record into the database
	public static void insertRecordTable1(Connection connect) throws SQLException
	{
		//declaring variables regno,name,cls,section,date-of-birth
		int regno,cls=0;
		String name="",section="",dob="";
		
		PreparedStatement ps=connect.prepareStatement("insert into StudentDetails values(?,?,?,?,?);");
		
		//taking the inputs from user and setting the values for parameters
		System.out.println("Enter student registration number:");
		 regno=obj.nextInt();
        System.out.println("Enter student name:");
        obj.nextLine();
        name=obj.nextLine();
        System.out.println("Enter student class:");
        cls=obj.nextInt();
        System.out.println("Enter student section:");
        section=obj.next();
        System.out.println("Enter the student date-of-birth:");
        dob=obj.next();
        
		ps.setInt(1,regno);
		ps.setString(2,name);
		ps.setInt(3, cls);
		ps.setString(4, section);
		ps.setString(5, dob);
		
		
		//displays the result of the operation
        int rows=ps.executeUpdate();
		
		if(rows==0)
		{
			System.out.println("Error occured during Insertion...Try again!!");
			System.out.println("-----------------------------------------------");
		}
		else
		{
			System.out.println("Insertion completed..");
			System.out.println("-----------------------------------------------");
		}
	}
	
	//insert records in table2-studentMarks
	
	public static void insertRecordTable2(Connection connect) throws SQLException
	{
		int regno=0,marks=0;
		String subject="";
        PreparedStatement ps=connect.prepareStatement("insert into StudentMarks values(?,?,?);");
		
		//taking the inputs from user and setting the values for parameters
         System.out.println("Enter student registration number:");
		 regno=obj.nextInt();
		 System.out.println("Enter student subject:");
		 subject=obj.next();
		 System.out.println("Enter student marks:");
		 marks=obj.nextInt();
		 
		 ps.setInt(1, regno);
		 ps.setString(2, subject);
		 ps.setInt(3, marks);
		 
		 int rows=ps.executeUpdate();
		 
		 if(rows==0)
			{
				System.out.println("Error occured during Insertion...Try again!!");
				System.out.println("-----------------------------------------------");
			}
			else
			{
				System.out.println("Insertion completed..");
				System.out.println("-----------------------------------------------");
			}
		 
		 
	}
	
	//readRecords method will retrieve all the records from the database
	
	public static void readRecords(Connection connect) throws SQLException
	{
	    String query = "SELECT SD.regno, SD.name, SD.cls, SD.section, SD.dob, SM.subject, SM.marks " +
                "FROM StudentDetails SD " +
                "LEFT JOIN StudentMarks SM ON SD.regno = SM.regno;";
		PreparedStatement ps=connect.prepareStatement(query);
		ResultSet rs= ps.executeQuery();
		
		while(rs.next())
		{
			//assiging the column values in each row to the below variables and displaying them
			int regno=rs.getInt("regno");
			String name=rs.getString("name");
			int cls=rs.getInt("cls");
			String section=rs.getString("section");
			String dob=rs.getString("dob");
			String subject=rs.getString("subject");
			int marks=rs.getInt("marks");
			
			System.out.println("-----------------------------------------------");
			System.out.println("Registration Number: "+regno+"\nName: "+name+"\nClass: "+cls+"\nSection: "+section+"\nDate-of-Birth: "+dob+"\nSubject: "+subject+"\nMarks: "+marks);
			System.out.println("-----------------------------------------------");
		}
	}
	
	//updating the records in database
	public static void updateRecordTable1(Connection connect) throws SQLException
	{
		PreparedStatement ps=connect.prepareStatement("update StudentDetails set cls=? where regno=?");
		
		//taking the inputs from user and setting the values for parameters
		System.out.println("Enter reg-no");
		int regno=obj.nextInt();
		System.out.println("Enter the class");
		int cls=obj.nextInt();
		
		ps.setInt(1, cls);
		ps.setInt(2, regno);
		
		//displays the result of the operation
		 int rows=ps.executeUpdate();
			
			if(rows==0)
			{
				System.out.println("Error occured during updating...Try again!!");
				System.out.println("-----------------------------------------------");
			}
			else
			{
				System.out.println("Record updated..");
				System.out.println("-----------------------------------------------");
			}
		
			
	} 
	
	//
	public static void updateRecordTable2(Connection connect) throws SQLException
	{
		PreparedStatement ps=connect.prepareStatement("update StudentMarks set marks=? where regno=?");
		System.out.println("Enter student registration number:");
		 int regno=obj.nextInt();
		System.out.println("Enter the marks:");
		int marks=obj.nextInt();
		
		ps.setInt(1, marks);
		ps.setInt(2, regno);
		
		int rows=ps.executeUpdate();
		

		if(rows==0)
		{
			System.out.println("Error occured during updating...Try again!!");
			System.out.println("-----------------------------------------------");
		}
		else
		{
			System.out.println("Record updated..");
			System.out.println("-----------------------------------------------");
		}
	}
	
	//deleteRecord will delete a specific row from database
	
	public static void deleteRecord(Connection connect) throws SQLException
	{
		PreparedStatement ps=connect.prepareStatement("delete from StudentDetails where regno=?");
		
		System.out.println("Enter the registration number of the student:");
		int regno=obj.nextInt();
		
		ps.setInt(1, regno);
		
		PreparedStatement ps1=connect.prepareStatement("delete from StudentMarks where regno=?");
		 ps1.setInt(1, regno);
		
		//displays the result of the operation
		 int rows=ps.executeUpdate();
		 int rows1=ps1.executeUpdate();
			
			if(rows==0||rows1==0)
			{
				System.out.println("Error occured during Deletion...Try again!!");
				System.out.println("-----------------------------------------------");
			}
			else
			{
				System.out.println("Deletion completed..");
				System.out.println("-----------------------------------------------");
			}
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		int choice=0,tableNum=0; //choice variable contains the user preferred operation
		
		//loading the driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//connection establishment
		Connection connect=DriverManager.getConnection(URL,username,password);
		
		//calling the create table methods to create tables in the database
		  School8Table1.createTable1(connect);
		  School8Table2.createTable2(connect);
		 
		 
			  while(choice!=5)
				 {
				      System.out.println("Select the table on which you want to manipulate:\n1.Student Details\n2.Student Marks");
				      tableNum=obj.nextInt();
				      if(tableNum==1)
				      {
				    	  
				    	  System.out.println("Please choose an option:");
							 System.out.println("---------------------------------");
							 System.out.println("1.Create \n 2.Read \n 3.Update \n 4.Delete \n 5.exit");
								
							 choice=obj.nextInt();
							 
							 switch(choice)
							 {
							     case 1:
							    	 insertRecordTable1(connect);
							    	 break;
							     case 2:
							    	 readRecords(connect);
							    	 break;
							     case 3:
							    	 updateRecordTable1(connect);
							    	 break;
							     case 4:
							    	 deleteRecord(connect);
							    	 break;
							     case 5:
							    	 System.out.println("Thanks for visiting....");
							    	 return;
							     default:
							    	 System.out.println("Enter an valid option");
							 }
						 }
				      else if(tableNum==2)
				      {
				    	  
				    	  System.out.println("Please choose an option:");
							 System.out.println("---------------------------------");
							 System.out.println("1.Create \n 2.Read \n 3.Update \n 4.Delete \n 5.exit");
								
							 choice=obj.nextInt();
							 
							 switch(choice)
							 {
							     case 1:
							    	 insertRecordTable2(connect);
							    	 break;
							     case 2:
							    	 readRecords(connect);
							    	 break;
							     case 3:
							    	 updateRecordTable2(connect);
							    	 break;
							     case 4:
							    	 deleteRecord(connect);
							    	 break;
							     case 5:
							    	 System.out.println("Thanks for visiting....");
							    	 return;
							     default:
							    	 System.out.println("Enter an valid option");
							 }
						 }
				      else
				      {
				    	  System.out.println("Sorry  try again.....");
				      }		
		  
		 }
	}
}
