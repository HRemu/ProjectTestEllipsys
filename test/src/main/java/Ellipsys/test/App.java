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
    	ArrayList<String> collones = new ArrayList<String>();
    	collones.add("id");
    	collones.add("trf");
    	collones.add("tgtTb");
    	collones.add("tgtLab");
    	collones.add("srcTb");
    	collones.add("srcLab");
    	collones.add("impact");

		String originalTableName= "oa_trf_src";
		
    
		
        DatabaseSimplification simple = new DatabaseSimplification("jdbc:sqlite:ellipsys_test_db.db3",originalTableName);
		
        simple.createTableRed(collones);
    	
    }
}
