/**
 * The main class initialising a JFrame containing the content panel.
 */
package wegfinder;

import abiturklassen.graphklassen.GraphNode;
import adt.*;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.event.*; 
import javax.swing.*; 
import static javax.swing.JFrame.EXIT_ON_CLOSE; 

/**
 *
 * @author Bjoern Heinrichs
 */
public class Wegfinder {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Raumnavigator"); 
        Content inhalt = new Content(); 
        frame.setSize(880, 954); 
        frame.add(inhalt);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

