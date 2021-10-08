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
		ArrayList<String> tableCreated = new ArrayList<String>();
		tableCreated.add("oa_trf_src_id_lkp");
		tableCreated.add("oa_trf_src_trf_lkp");
		tableCreated.add("oa_trf_src_tgtTb_lkp");
		tableCreated.add("oa_trf_src_tgtLab_lkp");
		tableCreated.add("oa_trf_src_srcTb_lkp");
		tableCreated.add("oa_trf_src_srcLab_lkp");
		tableCreated.add("oa_trf_src_impact_lkp");
    
		
		
		
		
		database.createTableRed( originalTableName,collones, tableCreated) ;

        DatabaseSimplification simple = new DatabaseSimplification("jdbc:sqlite:ellipsys_test_db.db3","oa_trf_src");
		
        //simple.createTableRed(collumnList);
    	
    }
}
