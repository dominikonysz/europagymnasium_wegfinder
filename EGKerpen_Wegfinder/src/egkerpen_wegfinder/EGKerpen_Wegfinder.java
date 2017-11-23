/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egkerpen_wegfinder;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

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
        frame.setSize(920, 963); 
        Content content = new Content(frame);
        content.setBounds(0,0,frame.getContentPane().getWidth(), frame.getContentPane().getHeight());
        frame.getContentPane().add(content);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
}
