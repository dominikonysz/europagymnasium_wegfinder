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
        
        /*String tmp = System.getProperty("user.dir");
        tmp = tmp.replace("\\", "/");
        tmp = tmp.substring(0, tmp.lastIndexOf("/"));*/
        
        res = res + "./db/dbHeinrichs";
        
        return res;
    }

    /**
     * This method initialises the database with the data sets saved in a file.
     * 
     * @param file The file with the data sets.
     * @return True, if the file was correct. False, if the file was not valid.
     */
    public boolean dbInitialise(File file) {
        boolean isFileCorrect = false;
        
        try{        
            
            String driver = "org.apache.derby.jdbc.EmbeddedDriver";
            String connectionURL = getURL();
            Class.forName(driver);
            Connection con=DriverManager.getConnection(connectionURL,null,null );
            
            Statement stmt = con.createStatement();
            Statement stmt2 = con.createStatement();
            
            BufferedReader r = new BufferedReader(new FileReader(file));
            isFileCorrect = true;
            String zeile = null; 
            while ((zeile = r.readLine()) != null) { 
                String[] batch = zeile.split(";");
                if(batch.length != 4){
                    isFileCorrect = false;
                    break;
                }
                
                stmt2.addBatch("INSERT INTO raumverteilung VALUES ('" + batch[0] + "', '" + batch[1] + "', '" + batch[2] + "', " + batch[3] + ")");
            } 
            
            if(isFileCorrect){
                stmt.executeUpdate("DELETE FROM raumverteilung");
                stmt2.executeBatch();
            } else
                JOptionPane.showMessageDialog(null, "Der Inhalt der Datei ist fehlerhaft!\n"
                                                + "<html>Die Datei muss die Datensätze zeilenweise in der Form <i>Raum</i>;<i>Nachname</i>;<i>Vorname</i>;<i>Verhindert (als 0 oder 1)</i> enthalten.</html>");
            
            r.close();
            stmt.close();
            stmt2.close();
            con.close();
        } catch(ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }
        return isFileCorrect;
    }
}
