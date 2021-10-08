package Ellipsys.test;

import java.util.ArrayList;

/**
 * Classe qui contient les fonction permettant d'effectuer la simplification de la base en utilisant la classe DatabaseConncetion.
 * @author remi huguenot
 *
 */
public class DatabaseSimplification {

	private DatabaseConnection database;
	private String originalTableName;
	
	
	public DatabaseSimplification(String databaseURL, String tableName) {
		super();
		this.database = new DatabaseConnection(databaseURL);
		this.originalTableName = tableName;
	}
	
	public String createIndexTable(String collumn ) {
		ArrayList<String> elements = database.findDistinctValues(originalTableName, collumn);
		String NewTable = originalTableName+"_"+collumn+"_lkp";
		database.createIndexTable(NewTable, collumn);
		int returncode = database.InsertIndexTable(NewTable, elements, collumn);
		if (returncode ==1){
			return NewTable;
		}
		else {
			return "";
		}
	}
	
	// TODO faire sans parametre en retrouvant les différentes collones de la table
	public ArrayList<String> createAllIndexTable(ArrayList<String> collumnList){
		
		ArrayList<String> tableCreated = new ArrayList<String>();
		
		for (int i=0; i< collumnList.size();i++) {
			String tableName = createIndexTable(collumnList.get(i));
			tableCreated.add(tableName);
		}
		
		return tableCreated;
	}
	
	
	public void createTableRed(ArrayList<String> collumnList) {
		ArrayList<String> newTable = createAllIndexTable(collumnList);
		
		database.createTableRed(collumnList, newTable);
		
		database.fillTableRed(originalTableName, collumnList,newTable);
	}
	
}
