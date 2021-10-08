package Ellipsys.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException
    {
    	String URL = "jdbc:sqlite:ellipsys_test_db.db3";

		String originalTableName= "oa_trf_src";
		
		// Commandes pour réduire la table originaTableName de la base de donnée URL.
       DatabaseSimplification simple = new DatabaseSimplification(URL,originalTableName);
	
       simple.createTableRed();
    	
		
		
    }
}
