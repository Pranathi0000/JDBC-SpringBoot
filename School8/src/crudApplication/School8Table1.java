package crudApplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class School8Table1 {
   
	 public static void createTable1(Connection connect) throws SQLException
	 {
		 PreparedStatement ps = connect.prepareStatement("CREATE TABLE IF NOT EXISTS StudentDetails (" +
                 "regno INT, " +
                 "name VARCHAR(20), " +
                 "cls INT, " +
                 "section VARCHAR(1), " +
                 "dob VARCHAR(10))");
        ps.executeUpdate();
		 
	 }
}
