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
    	
        DatabaseSimplification simple = new DatabaseSimplification("jdbc:sqlite:ellipsys_test_db.db3","oa_trf_src");
        
        //simple.createTableRed(collumnList);
    	
    }
}
