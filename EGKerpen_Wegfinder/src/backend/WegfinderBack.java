/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import db.DBController;
import frontend.DBInitialise;
import frontend.UserInterface;
import frontend.WegfinderFront;
import java.awt.Point;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import listenklassen.List_extended;

/**
 *
 * @author Dominik Onyszkiewicz
 */
public class WegfinderBack {
    
    private static WegfinderBack backend = new WegfinderBack();
    private WegfinderFront frontend;
    private UserInterface ui;
    
    private DBController con;
    
    private static ConnectionGraph graph = new ConnectionGraph();
    
    //<editor-fold defaultstate="collapsed" desc="Room Strings">
    public static final String[] allRooms = new String[] {
        "Foyer",
        "Werkraum 8", 
        "Werkraum 14", "Werkraum 15", "Werkraum 17", 
        "Werkraum 20", "Werkraum 21", "Werkraum 24", "Werkraum 27", 
        "Werkraum 32", 
        "Raum 38", "Raum 39", 
        "Raum 42", 
        "Kunstraum 53", "Kunstraum 59", 
        "Technikraum 62", 
        "Raum 63", "Raum 64", "Raum 65", "Raum 66", "Raum 67", 
        "Raum 70", "Raum 71", "Raum 72", "Raum 73", 
        "Raum 87", "Raum 88", "Raum 89", 
        "Raum 90", "Raum 92", "Raum 93", "Raum 94", "Raum 95", "Raum 96", "Raum 97", 
        "Raum 104", "Raum 106", "Raum 107", "Raum 108", "Raum 109", 
        "Raum 110", 
        "Raum 132", "Raum 134", "Raum 135", "Raum 136", "Raum 137", 
        "Raum 141", "Raum 142", "Raum 144", "Raum 145", 
        "Raum 150", "Raum 151", "Raum 154", "Raum 155", "Raum 156", 
        "Raum 166", "Raum 167", "Raum 168", "Raum 169", 
        "Raum 170", "Raum 171", "Raum 174", "Raum 175", 
        "Raum 184", "Raum 185", "Raum 188", "Raum 189", 
        "Raum 190", "Raum 191", "Raum 192/193", 
        "Raum 200", "Raum 203/204", "Raum 205", "Raum 206", "Raum 208", "Raum 209", 
        "Raum 210", "Raum 212", "Raum 213", "Raum 215", "Raum 216", "Raum 217", "Raum 218", "Raum 219", 
        "Raum 220", "Raum 222", "Raum 223", "Raum 227", "Raum 228", "Raum 229", 
        "Raum 230", "Raum 231", "Raum 235", "Raum 236", "Raum 237", "Raum 238", 
        "Raum 240", "Raum 241", "Raum 242", 
        "Raum 250", "Raum 251", "Raum 253", "Raum 254", "Raum 255", 
        "Raum 262", "Raum 263", "Raum 264", "Raum 266", "Raum 267", "Raum 269", 
        "Raum 270", 
        "Raum 283", "Raum 284", "Raum 286", "Raum 288", "Raum 289", 
        "Raum 290", "Raum 293", 
        "Raum 302", "Raum 303", "Raum 304", "Raum 305", "Raum 306", "Raum 307", "Raum 308", "Raum 309", 
        "Raum 310", "Raum 311", "Raum 312", "Raum 313", "Raum 314", 
        "Büro der Schulleitung/Sekretariat", "Büro der stellv. Schulleitung", 
        "Mensa", 
        "Saftbar", "Spielekeller", 
        "Bibliothek",
        "WC Herren", "WC Damen"};
    //</editor-fold>
    
    private WegfinderBack() {
        con = DBController.getInstance();
        graph = new ConnectionGraph();
    }
    
    //<editor-fold defaultstate="collapsed" desc="Local Operations">
    
    /**
     * Returns the backend manager of the programm
     * @return 
     */
    public static WegfinderBack getBackend() {
        return backend;
    }
    
    /**
     * Connect a frontend instance to the backend
     * @param front 
     */
    public void connectFrontend(WegfinderFront front) {
        frontend = front;
    }
    
    /**
     * Returns the frontend connected to the backend
     * @return 
     */
    public WegfinderFront getFrontend() {
        return frontend;
    }
    
    /**
     * Returns the graph of the rooms and floors of the school.
     * @return 
     */
    public static ConnectionGraph getSchoolGraph() {
        return graph;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Input Operations">
    
    public void print() {
        frontend.getGroundPlan().print();
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Management Operations">
    
    public void initializeDatabase() {
        DBInitialise.main(null);
    }
    
    public String getClosestRoom(Point pos) {
        return graph.getClosestRoom(pos);
    }
    
    public void drawPath(String from, String to) {
        frontend.drawPath(from, to);
        ui = UserInterface.getUI();
        ui.getNavigator().drawPath(from, to);
        ui.updatePath(from, to);
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Database Operations">
    
        //<editor-fold defaultstate="collapsed" desc="Modifications">
    
    /**
     * Adds a new teacher to the database with the given values if the is no similar
     * teacher in the database.
     * @param firstname
     * @param lastname
     * @param room
     * @param prevented
     * @return 
     */
    public int addTeacher(String firstname, String lastname, String room, boolean prevented) {
        con.executeQuery("SELECT * FROM lehrer WHERE nachname = '" + lastname + "' AND vorname = '" + (firstname.equals("") ? "-" : firstname) + "'");
        if(con.getResultsAmount() == 0) {
            con.executeUpdate("INSERT INTO raumverteilung VALUES ('" 
                    + room + "', '" + lastname + "', '" 
                    + (firstname.equals("") ? "-" : firstname) 
                    + "', " + (prevented ? 1 : 0) + ")");
            con.executeUpdate("INSERT INTO lehrer VALUES ('" + lastname
                    + "', '" + (firstname.equals("") ? "-" : firstname) + "')");
        }
        else {
            return -1;
        }
        return 1;
    }
    
    /**
     * Removes the teacher with the given attibutes out of the database.
     * @param firstname
     * @param lastname 
     */
    public void removeTeacher(String firstname, String lastname) {
        con.executeUpdate("DELETE FROM raumverteilung WHERE nachname = '" 
                + lastname + "' AND vorname = '" + firstname + "'");
        con.executeUpdate("DELETE FROM lehrer WHERE nachname = '" 
                + lastname + "' AND vorname = '" + firstname + "'");
    }
    
    /**
     * Changes the room of the teacher with the given name to the given new room.
     * @param newRoom
     * @param name 
     */
    public void changeRoom(String newRoom, String name) {
        con.executeUpdate("UPDATE raumverteilung SET raum = '" + newRoom
                + "' WHERE nachname = '" + name + "'");
    }
    
        //</editor-fold>
    
        //<editor-fold defaultstate="collapsed" desc="Querys">
    
    /**
     * Returns an array of all teachers with their associated rooms
     * @return 
     */
    public String[][] getTeacherList() {
        List_extended<List_extended<String>> lehrerList;
        con.executeQuery("SELECT lehrer.nachname, lehrer.vorname, raumverteilung.raum FROM lehrer JOIN raumverteilung ON lehrer.nachname = raumverteilung.nachname AND lehrer.vorname = raumverteilung.vorname ORDER BY lehrer.nachname");
        lehrerList = con.getResults();
        String[][] lehrer = new String[con.getResultsAmount()][3];
            
        lehrerList.toFirst();
        for(int i = 0; i < lehrer.length; i++){
            List_extended<String> l2 = lehrerList.getObject();
            l2.toFirst();

            for(int j = 0; j < lehrer[i].length; j++){
                lehrer[i][j] = l2.getObject();
                l2.next();
            }

            lehrerList.next();
        }
        
        return lehrer;
    }
    
    /**
     * Returns the last names of all teachers as a string array.
     * @return 
     */
    public String[] getAllTeachersLastName() {   
        List_extended<List_extended<String>> lehrerList;
        con.executeQuery("SELECT DISTINCT * FROM lehrer ORDER BY nachname");
        lehrerList = con.getResults();
        int amount = con.getResultsAmount();
        String[][] lehrer = new String[amount][2];
            
        lehrerList.toFirst();
        for(int i = 0; i < lehrer.length; i++){
            List_extended<String> l2 = lehrerList.getObject();
            l2.toFirst();

            for(int j = 0; j < lehrer[i].length; j++){
                lehrer[i][j] = l2.getObject();
                l2.next();
            }

            lehrerList.next();
        }
        String[] output = new String[amount];
        for (int i = 0; i < amount; i++) {
            output[i] = lehrer[i][0];
        }
        return output;
    }
    
    /**
     * Returns the first and last names of all teachers as a string array.
     * @return 
     */
    public String[] getAllTeachersNames() {   
        List_extended<List_extended<String>> lehrerList;
        con.executeQuery("SELECT nachname, vorname FROM lehrer ORDER BY nachname");
        lehrerList = con.getResults();
        int amount = con.getResultsAmount();
        String[][] lehrer = new String[amount][2];
            
        lehrerList.toFirst();
        for(int i = 0; i < lehrer.length; i++){
            List_extended<String> l2 = lehrerList.getObject();
            l2.toFirst();

            for(int j = 0; j < lehrer[i].length; j++){
                lehrer[i][j] = l2.getObject();
                l2.next();
            }

            lehrerList.next();
        }
        String[] output = new String[amount];
        for (int i = 0; i < amount; i++) {
            output[i] = lehrer[i][1] + " " + lehrer[i][0];
        }
        return output;
    }
    
    /**
     * Return the room the given teacher is in.
     * @return 
     */
    public String getRoomOfTeacher(String tLastname) {
        List_extended<List_extended<String>> result;
        con.executeQuery("SELECT raum FROM raumverteilung WHERE nachname = '" + tLastname + "'");
        result = con.getResults();
        result.toFirst();
        result.getObject().toFirst();
        return result.getObject().getObject();
    }
    
    /**
     * Returns the teachers located in this room
     * @param room
     * @return 
     */
    public String[] getTeachersInRoom(String room) {
        List_extended<List_extended<String>> result;
        con.executeQuery("SELECT nachname FROM raumverteilung WHERE raum = '" + room + "'");
        result = con.getResults();
        int amount = con.getResultsAmount();
        String[] teachers = new String[amount];
        if(amount == 0) {
            return teachers;
        }
        
        result.toFirst();
        for(int i = 0; i < teachers.length; i++){
            List_extended<String> l2 = result.getObject();
            l2.toFirst();

            teachers[i] = l2.getObject();

            result.next();
        }
        
        return teachers;
    }
    
    /**
     * Returns all teacher objects which contain the given string in their name
     */
    public String[][] getTeachersWith(String chars) {
        List_extended<List_extended<String>> result;
        con.executeQuery("SELECT vorname, nachname, raum FROM raumverteilung WHERE nachname LIKE '%" + chars + "%'");
        result = con.getResults();
        int amount = con.getResultsAmount();
        String[][] output = new String[amount][3];
        if(amount == 0) {
            return output;
        }
        
        result.toFirst();
        for(int i = 0; i < output.length; i++){
            List_extended<String> l2 = result.getObject();
            l2.toFirst();

            for(int j = 0; j < output[i].length; j++){
                output[i][j] = l2.getObject();
                l2.next();
            }

            result.next();
        }
        
        return output;
    }
    
    /**
     * Returns all rooms which cointain the given chars in it
     */
    public String[] getRoomsWith(String chars) {
        List_extended<String> found = new List_extended<String>();
        int amount = 0;
        for(String s : allRooms) {
            if(s.toLowerCase().contains(chars.toLowerCase())) {
                found.append(s);
                amount++;
            }
        }
        String[] output = new String[amount];
        int index = 0;
        found.toFirst();
        while(found.hasAccess()) {
            output[index++] = found.getObject();
            found.next();
        }
        
        return output;
    }
    
    //</editor-fold>
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="File Operations">
    
    public int saveDatabase(String path) {
        try {
            File f = new File(path + "\\Portable.txt");
        
            con.executeQuery("SELECT * FROM raumverteilung ORDER BY raum");
            List_extended<List_extended<String>> list;
            String[][] array = new String[con.getResultsAmount()][4];
            list = con.getResults();

            list.toFirst();
            for(int i = 0; i < array.length; i++){
                List_extended<String> l2 = list.getObject();
                l2.toFirst();

                for(int j = 0; j < array[i].length; j++){
                    array[i][j] = l2.getObject();
                    l2.next();
                }

                list.next();
            }

            FileWriter fw = new FileWriter(f);
            
            for(String[] s : array) {
                insertToFile(fw, s[0], s[1], s[2], Integer.parseInt(s[3]));
            }
        }
        catch(IOException e) {
            return -1;
        }
        catch(Exception e) {
            return 0;
        }
        
        return 1;
    }
    
    /**
     * This method inserts the values of the text fields into a file creating a new data set.
     * 
     * @param raum The room where the teacher is in.
     * @param nachname The teacher's last name.
     * @param vorname The teacher's first name.
     * @param verhindert A value indicating whether the teacher is absent or not
     */
    private void insertToFile(FileWriter fw, String raum, String nachname, String vorname, int verhindert) throws IOException {
        fw.write(raum + ";" + nachname + ";" + vorname + ";" + String.valueOf(verhindert));
        fw.append(System.getProperty("line.separator"));
        fw.flush();
    }
    
    //</editor-fold>
}
