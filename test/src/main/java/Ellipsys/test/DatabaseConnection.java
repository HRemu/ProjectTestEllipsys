package Ellipsys.test;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
	public int createIndexTable(String NewTable, String collumn) {
		try {
			Statement stmt = conn.createStatement();
			String query = "CREATE TABLE IF NOT EXISTS "+NewTable+" ( "
					+ "id INTEGER PRIMARY KEY,"
					+ "champ TEXT NOT NULL UNIQUE"
					+ ");";
					
			 int retVal = stmt.executeUpdate(query);
			 return retVal;
			} catch (SQLException e) {
			// En cas d'exception on renvois un objet null.
			e.printStackTrace();
			return -1;
		}
	}
	
	// TO DO améliorer vitesse ?
	// Fonction qui permet d'inserer toutes les valeurs d'une liste dans une table d'index.
	public int InsertIndexTable(String table, ArrayList<String> values) {
		
		try {
			String sql = "INSERT INTO "+table+" (champ) VALUES (?)";
			conn.setAutoCommit(false);
			PreparedStatement statement = conn.prepareStatement(sql);
			int rowsInserted =0;
			for (int i=0; i<values.size();i++) {
				statement.setString(1, values.get(i));
				rowsInserted +=  statement.executeUpdate();
			}
			
			
			if (rowsInserted == values.size()) {
				conn.commit();
				conn.setAutoCommit(true);

			    return 1;
			}
			else {
				conn.rollback();
				conn.setAutoCommit(true);

				return 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		 
		
	}
	
	
	// Methode qui créé la table oa_trf_src_red à partir de 
	// collumnList : liste des noms de collone de la table de base
	// NewTable : liste des nom de tables d'index liés au collones (doivent être déja créés)
	public void createTableRed(String originalTableName, ArrayList<String> collumnList, ArrayList<String> newTable) {
		try {
			Statement stmt = conn.createStatement();
			String query = "CREATE TABLE IF NOT EXISTS "+originalTableName+"_red AS "
					+ "SELECT ";
			int i = 0;
			for (i=0; i< newTable.size()-1; i++) {
				query += newTable.get(i)+".id as "+collumnList.get(i)+" , ";
			}
			
			query += newTable.get(i)+".id as "+collumnList.get(i) +""
					+ " FROM "+ originalTableName +" " ;
			for (i=0; i< newTable.size(); i++) {
				query += " JOIN " + newTable.get(i)+ " ON "+ newTable.get(i)+".champ == " + originalTableName+"."+ collumnList.get(i) ;
			}
			
			 stmt.execute(query);
			// Le resultat de la requette est mis dans une liste.
			} catch (SQLException e) {
			// En cas d'exception on renvois un objet null.
			e.printStackTrace();
		}
		
	}

	// Methode pour remplir la table _red à partir de la table originale et des tables d'index. 
	public void fillTableRed(String originalTableName, ArrayList<String> collumnList, ArrayList<String> newTable) {
		// TODO Auto-generated method stub
		
	}
	
	

}
