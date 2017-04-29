/*
 * Verbindung zur DB *BackEnd*
*/

package db;

import adt.List_extended;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Die Klasse dient der eigentlichen Kommunikation mit der DB.
 * Es werden die Daten einer Tabelle ausgelesen und in speziellen
 * Datenstrukturen (ResultSet für die Inhalte und ResultSetMetaData
 * für die Spaltenüberschriften) vorgehalten.
 * @author Dr. B. Engels
 */
public class DBReader{
    
    int attributAnzahl;
    int datensatzAnzahl;
    List_extended<String> attributNamen;
    List_extended<List_extended<String>> attributWerte;
    
    /**
     * 
     */
    public DBReader(){
        attributAnzahl=0;
        datensatzAnzahl=0;
        attributNamen=new List_extended<>();
        attributWerte=new List_extended<>();
    }
    
    /**
     * Fuellt die Liste attributNamen mit dem Spaltenueberschriften
     * der Resultat-Tabelle aus der SQL-Abfrage und fuellt die Liste attributWerte
     * mit den entsprechenden Werten aller Datensaetze der Resultat-Tabelle,
     * wobei jede Tabellenzeile wieder in einer eigenen Liste steht.
     */
    public void executeQuery(String query){
        try{    
            String driver = "org.apache.derby.jdbc.EmbeddedDriver";
            String connectionURL = getURL();
            Class.forName(driver);
            Connection con=DriverManager.getConnection(connectionURL,null,null );
            
            Statement stmt = con.createStatement();
            ResultSet rst = stmt.executeQuery(query);
            ResultSetMetaData md = rst.getMetaData();
            
            attributAnzahl = md.getColumnCount();
            
            for (int i = 1; i <=attributAnzahl; i++) {
                String spaltenueberschrift=md.getColumnName(i);
                attributNamen.append(spaltenueberschrift);
            }
            
            while (rst.next()) {
                datensatzAnzahl++;
                List_extended<String> datensatz = new List_extended<>();
                for (int i = 1; i<=attributAnzahl; i++) {
                    datensatz.append(String.valueOf(rst.getObject(i)));
                }
                attributWerte.append(datensatz);
            }
            
            rst.close();
            stmt.close();
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }    
    }
    
    /**
     * This methos executes an update to the database (insert or delete).
     * 
     * @param query The query calling the method.
     */
    public void executeUpdate(String query){
        try{        
            
            String driver = "org.apache.derby.jdbc.EmbeddedDriver";
            String connectionURL = getURL();
            Class.forName(driver);
            Connection con=DriverManager.getConnection(connectionURL,null,null );
            
            Statement stmt = con.createStatement();
            int rowsAffected = stmt.executeUpdate(query);
            
            stmt.close();
            con.close();
        } catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method returns the results of a previous query.
     * 
     * @return The list of data sets.
     */
    public List_extended<List_extended<String>> getResults() {
        return attributWerte;
    }

    /**
     * This method returns the amount of results of a pervious query.
     * 
     * @return The amount of results.
     */
    public int getResultsAm() {
        return datensatzAnzahl;
    }

    /**
     * This method generates the URL of the database.
     * 
     * @return The full URL of the database.
     */
    private String getURL() {
        String res = "jdbc:derby:";
        
        String tmp = System.getProperty("user.dir");
        tmp = tmp.replace("\\", "/");
        tmp = tmp.substring(0, tmp.lastIndexOf("/"));
        
        res = res + tmp + "/db/dbHeinrichs";
        
        return res;
    }

    /**
     * This method checks whether a data set of a teacher already exists in the database.
     * 
     * @param nname The last name of the teacher.
     * @param vname The first name of a teacher.
     * @return True, if the data set exists. False, if not.
     */
    public boolean existsInDB(String nname, String vname) {
        executeQuery("SELECT * FROM Lehrer WHERE Nachname = '" + nname + "' AND Vorname = '" + vname + "'");
        
        switch(getResultsAm()){
            case 0:
                return false;
                
            default:
                return true;
        }
    }
}
