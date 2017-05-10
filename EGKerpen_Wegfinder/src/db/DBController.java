/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import listenklassen.List_extended;
/**
 *
 * @author Dominik Onyszkiewicz
 */
public class DBController {
    
    private static final DBController controller = new DBController();
    private static Connection con;
    private static final String DB_PATH = ".\\lehrerdb.db";
    
    private int attributAnzahl;
    private int datensatzAnzahl;
    private List_extended<String> attributNamen;
    private List_extended<List_extended<String>> attributWerte;
    
    private DBController() {
        attributAnzahl=0;
        datensatzAnzahl=0;
        attributNamen=new List_extended<>();
        attributWerte=new List_extended<>();
        //Laden des sqlite-JDBC Treibers
        try {
            Class.forName("org.sqlite.JDBC");
        }
        catch(ClassNotFoundException e) {
            System.out.println("JDBC-Treiber konnte nicht geladen werden.");
            e.printStackTrace();
        }
        initConnection();
    }
    
    /**
     * Gibt die Hauptinstanz des DBControllers zurück.
     * @return einzige Instanz des DBControllers
     */
    public static DBController getInstance() {
        return controller;
    }
    
    /**
     * Erstellt eine Verbindung zur Datenbank unter DB_PATH.
     * Hängt zudem einen ShutdownHook an das Programm der die Verbingung trennt,
     * wenn das Programm geschlossen wird.
     */
    private void initConnection() {
        try {
            if(con != null) 
                return;
            con = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);
            System.out.println(System.getProperty("user.dir"));
            
        }
        catch(SQLException e) {
            System.err.println(e);
        }
        
        //Schließe die Verbindung, wenn das Programm geschlossen wird.
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    if (!con.isClosed() && con != null) {
                        con.close();
                    }
                } catch (SQLException e) {
                    System.err.println(e);
                }
            }
        }); 
    }
    
    public void executeQuery(String query){
        try{
            Statement stmt = con.createStatement();
            ResultSet rst = stmt.executeQuery(query);
            ResultSetMetaData md = rst.getMetaData();
            
            datensatzAnzahl=0;
            attributNamen=new List_extended<>();
            attributWerte=new List_extended<>();
            
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
        } catch(SQLException e) {
            e.printStackTrace();
        }    
    }
    
    public void executeUpdate(String query) {
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List_extended<List_extended<String>> getResults() {
        return attributWerte;
    }
    
    public int getResultsAmount() {
        return datensatzAnzahl;
    }
    
    
    public boolean dbInitialise(File file) {
        boolean isFileCorrect = false;
        
        try{            
            Statement stmt = con.createStatement();
            Statement stmt2 = con.createStatement();
            
            BufferedReader r = new BufferedReader(new FileReader(file));
            isFileCorrect = true;
            String zeile = null; 
            stmt2.executeUpdate("CREATE TABLE IF NOT EXISTS raumverteilung (raum, nachname, vorname, verhindert, PRIMARY KEY(nachname))");
            stmt2.executeUpdate("CREATE TABLE IF NOT EXISTS lehrer (nachname, vorname, PRIMARY KEY(nachname))");
            while ((zeile = r.readLine()) != null) { 
                String[] batch = zeile.split(";");
                if(batch.length != 4){
                    isFileCorrect = false;
                    break;
                }
                
                stmt2.addBatch("INSERT INTO raumverteilung VALUES ('" + batch[0] + "', '" + batch[1] + "', '" + batch[2] + "', " + batch[3] + ")");
                stmt2.addBatch("INSERT INTO lehrer VALUES ('" + batch[1] + "', '" + batch[2] + "')");
            } 
            
            if(isFileCorrect){
                stmt.executeUpdate("DELETE FROM raumverteilung");
                stmt.executeUpdate("DELETE FROM lehrer");
                stmt2.executeBatch();
            } else
                JOptionPane.showMessageDialog(null, "Der Inhalt der Datei ist fehlerhaft!\n"
                                                + "<html>Die Datei muss die Datensätze zeilenweise in der Form <i>Raum</i>;<i>Nachname</i>;<i>Vorname</i>;<i>Verhindert (als 0 oder 1)</i> enthalten.</html>");
            
            r.close();
            stmt.close();
            stmt2.close();
        } catch(SQLException | IOException e) {
            e.printStackTrace();
        }
        return isFileCorrect;
    }
}
