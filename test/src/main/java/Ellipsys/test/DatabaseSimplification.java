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
	
	// Methode de création et remplissage d'une table de lookup liée a une collone.
	public String createIndexTable(String collumn ) {
		ArrayList<String> elements = database.findDistinctValues(originalTableName, collumn);
		String NewTable = originalTableName+"_"+collumn+"_lkp";
		database.createIndexTable(NewTable);
		int returncode = database.InsertIndexTable(NewTable, elements);
		if (returncode ==1){
			return NewTable;
		}
		else {
			return "";
		}
	}
	
	// Méthode pour creer et remplir toutes les tables de look up à partir de la liste des collonnes.
	public ArrayList<String> createAllIndexTable(ArrayList<String> collumnList){
		
		ArrayList<String> tableCreated = new ArrayList<String>();
		
		for (int i=0; i< collumnList.size();i++) {
			String tableName = createIndexTable(collumnList.get(i));
			tableCreated.add(tableName);
		}
		
		return tableCreated;
	}
	
	// Méthode pour creer la table réduite.
	public void createTableRed() {
		ArrayList<String> collumnList = database.findCollumnName(originalTableName);
		ArrayList<String> newTable = createAllIndexTable(collumnList);
		
		database.createTableRed(originalTableName, collumnList, newTable);
		System.out.println("Table Réduite faite");
	}
	
}
