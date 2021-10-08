package Ellipsys.test;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Cette classe permet d'établir une connection avec un base de donnée et donne des methodes permettant d'effectuer des requettes sql celle-ci. 
 * @author remi huguenot
 *
 */
public class DatabaseConnection {
	
	private Connection conn;
	
	// Constructeur : cette fonction créé la connection avec la base.
	public DatabaseConnection(String URL) {
		try {
			conn = DriverManager.getConnection(URL);
			if (conn != null) {
                System.out.println("Connection réussite");
			} 
		} catch (SQLException ex) {
            ex.printStackTrace();
        }
	}
	
	// Fonction pour utiliser des requettes précise et afficher le résultat.
	// Fonction de test.
	public void CustomQuery(String query) {
		
	}
	
	// Fonction pour recupérer les noms de collone dans une table
	// TODO si  possible pour pouvoir généraliser le code a des table avec éventuellement plus de collones
	public ResultSet findCollumnName(String table) {	
		try {
			Statement stmt = conn.createStatement();
			String query = "";
			ResultSet result = stmt.executeQuery(query);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// Fonction pour récupérer les différentes valeurs apparaissant dans une collone.
	public ArrayList<String> findDistinctValues(String table, String collumnName) {
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT DISTINCT "+collumnName+" FROM "+ table;
			ResultSet result = stmt.executeQuery(query);
			// Le resultat de la requette est mis dans une liste.
			ArrayList distinctValues = new ArrayList<String>();
			while (result.next()) {
				String R1 = result.getString(1);
				distinctValues.add(R1);
			}
			
			return distinctValues;
		} catch (SQLException e) {
			// En cas d'exception on renvois un objet null.
			e.printStackTrace();
			return null;
		}
	}
	
	// Fonction pour créer une nouvelle table faisant la correspondance entre des valeurs et un index
	public void createIndexTable(String NewTable, String collumn) {
		try {
			Statement stmt = conn.createStatement();
			String query = "CREATE TABLE IF NOT EXISTS "+NewTable+" ( "
					+ "id INTEGER PRIMARY KEY,"
					+ collumn +" TEXT NOT NULL UNIQUE"
					+ ");";
					
			 stmt.execute(query);
			// Le resultat de la requette est mis dans une liste.
			} catch (SQLException e) {
			// En cas d'exception on renvois un objet null.
			e.printStackTrace();
		}
	}
	
	// TO DO 
	// Fonction qui permet d'inserer toutes les valeurs d'une liste dans une table d'index.
	public void InsertIndexTable(String table, ArrayList values) {
		
	}
	
	
	// Fonction pour créer la table finale avec index
	public void createTableFromTable() {}
	
	

}
