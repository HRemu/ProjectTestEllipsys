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
	
	public void createIndexTable(String collumn ) {
		ArrayList<String> elements = database.findDistinctValues(originalTableName, collumn);
		database.createIndexTable(originalTableName, collumn);
		int returncode = database.InsertIndexTable(originalTableName, elements, collumn);
		
	}
	
	
}
