/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egkerpen_wegfinder;

import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JScrollPane;

/**
 *
 * @author Dominik Onyszkiewicz
 */
public class EGKerpen_Wegfinder {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Wegfinder frame = new Wegfinder("Raumnavigator"); 
        //JFrame frame = new JFrame("Raumnavigator"); 
        frame.setSize(886, 963); 
        //Content inhalt = new Content(frame);
        //frame.getContentPane().add(inhalt);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
}
