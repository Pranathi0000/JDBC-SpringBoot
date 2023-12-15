package crudApplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class School8Table2 {
   
	public static void createTable2(Connection connect) throws SQLException
	{
		 PreparedStatement ps = connect.prepareStatement("CREATE TABLE IF NOT EXISTS StudentMarks (" +
                 "regno INT PRIMARY KEY, " +
                 "subject VARCHAR(10), " +
                 "marks INT)");
         ps.executeUpdate();
	}
}
