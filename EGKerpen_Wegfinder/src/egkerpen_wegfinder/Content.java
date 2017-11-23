/**
 * This class designs the panel containing an image of the school's rooms.
 * It is able to paint the path between two rooms and to print the panel.
 */

package egkerpen_wegfinder;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

/**
 *
 * @author Bjoern Heinrichs, edited by Dominik Onyszkiewicz & Leonard Cohnen
 */
public class Content extends javax.swing.JPanel{
    private JFrame frame;
    private GroundPlanPanel groundPlan;
    private lSuche lS;
    
    /**
     * Constructor. Initializes the GUI components and attributes of the class.
     * @param frame parent frame
     */
    @SuppressWarnings("empty-statement")
    public Content(JFrame frame) {
        initComponents();
        this.frame = frame;
        
        groundPlan = new GroundPlanPanel(this);
        frame.add(groundPlan);
        
        jComboBox1.setModel(new DefaultComboBoxModel(Wegfinder.allRooms));
        jComboBox2.setModel(new DefaultComboBoxModel(Wegfinder.allRooms));
        
        // Press 'S' Button to open the TSE
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('s'), "tse");
        this.getActionMap().put("tse", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jButton6ActionPerformed(null);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pInputs = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Foyer", "Raum 38", "Raum 39", "Raum 42", "Raum 63", "Raum 64", "Raum 65", "Raum 66", "Raum 67", "Raum 70", "Raum 71", "Raum 72", "Raum 73", "Raum 87", "Raum 88", "Raum 89", "Raum 90", "Raum 92", "Raum 93", "Raum 94", "Raum 95", "Raum 96", "Raum 97", "Raum 104", "Raum 106", "Raum 107", "Raum 108", "Raum 109", "Raum 110", "Raum 132", "Raum 134", "Raum 135", "Raum 136", "Raum 137", "Raum 141", "Raum 142", "Raum 144", "Raum 145", "Raum 150", "Raum 151", "Raum 154", "Raum 155", "Raum 156", "Raum 166", "Raum 167", "Raum 168", "Raum 169", "Raum 170", "Raum 171", "Raum 174", "Raum 175", "Raum 184", "Raum 185", "Raum 188", "Raum 189", "Raum 190", "Raum 191", "Raum 192/193", "Raum 200", "Raum 203/204", "Raum 205", "Raum 206", "Raum 208", "Raum 209", "Raum 210", "Raum 212", "Raum 213", "Raum 215", "Raum 216", "Raum 217", "Raum 218", "Raum 219", "Raum 220", "Raum 222", "Raum 223", "Raum 227", "Raum 228", "Raum 229", "Raum 230", "Raum 231", "Raum 235", "Raum 236", "Raum 237", "Raum 238", "Raum 240", "Raum 241", "Raum 242", "Raum 250", "Raum 251", "Raum 253", "Raum 254", "Raum 255", "Raum 262", "Raum 263", "Raum 264", "Raum 266", "Raum 267", "Raum 269", "Raum 270", "Raum 283", "Raum 284", "Raum 286", "Raum 288", "Raum 289", "Raum 290", "Raum 293", "Raum 302", "Raum 303", "Raum 304", "Raum 305", "Raum 306", "Raum 307", "Raum 308", "Raum 309", "Raum 310", "Raum 311", "Raum 312", "Raum 313", "Raum 314", "Werkraum 8", "Werkraum 14", "Werkraum 15", "Werkraum 17", "Werkraum 20", "Werkraum 21", "Werkraum 24", "Werkraum 27", "Werkraum 32", "Kunstraum 53", "Kunstraum 59", "Technikraum 62", "Mensa", "Lehrerzimmer", "Büro der Schulleitung/Sekretariat", "Büro der stellv. Schulleitung", "Saftladen", "Spielekeller", "Bibliothek", "WC Damen", "WC Herren" }));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Foyer", "Raum 38", "Raum 39", "Raum 42", "Raum 63", "Raum 64", "Raum 65", "Raum 66", "Raum 67", "Raum 70", "Raum 71", "Raum 72", "Raum 73", "Raum 87", "Raum 88", "Raum 89", "Raum 90", "Raum 92", "Raum 93", "Raum 94", "Raum 95", "Raum 96", "Raum 97", "Raum 104", "Raum 106", "Raum 107", "Raum 108", "Raum 109", "Raum 110", "Raum 132", "Raum 134", "Raum 135", "Raum 136", "Raum 137", "Raum 141", "Raum 142", "Raum 144", "Raum 145", "Raum 150", "Raum 151", "Raum 154", "Raum 155", "Raum 156", "Raum 166", "Raum 167", "Raum 168", "Raum 169", "Raum 170", "Raum 171", "Raum 174", "Raum 175", "Raum 184", "Raum 185", "Raum 188", "Raum 189", "Raum 190", "Raum 191", "Raum 192/193", "Raum 200", "Raum 203/204", "Raum 205", "Raum 206", "Raum 208", "Raum 209", "Raum 210", "Raum 212", "Raum 213", "Raum 215", "Raum 216", "Raum 217", "Raum 218", "Raum 219", "Raum 220", "Raum 222", "Raum 223", "Raum 227", "Raum 228", "Raum 229", "Raum 230", "Raum 231", "Raum 235", "Raum 236", "Raum 237", "Raum 238", "Raum 240", "Raum 241", "Raum 242", "Raum 250", "Raum 251", "Raum 253", "Raum 254", "Raum 255", "Raum 262", "Raum 263", "Raum 264", "Raum 266", "Raum 267", "Raum 269", "Raum 270", "Raum 283", "Raum 284", "Raum 286", "Raum 288", "Raum 289", "Raum 290", "Raum 293", "Raum 302", "Raum 303", "Raum 304", "Raum 305", "Raum 306", "Raum 307", "Raum 308", "Raum 309", "Raum 310", "Raum 311", "Raum 312", "Raum 313", "Raum 314", "Werkraum 8", "Werkraum 14", "Werkraum 15", "Werkraum 17", "Werkraum 20", "Werkraum 21", "Werkraum 24", "Werkraum 27", "Werkraum 32", "Kunstraum 53", "Kunstraum 59", "Technikraum 62", "Mensa", "Lehrerzimmer", "Büro der Schulleitung/Sekretariat", "Büro der stellv. Schulleitung", "Saftladen", "Spielekeller", "Bibliothek", "WC Damen", "WC Herren" }));

        jButton1.setText("Suche");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findPath(evt);
            }
        });

        jButton2.setText("Drucken");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                print(evt);
            }
        });

        jButton3.setText("Erw. Suche");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openSearch(evt);
            }
        });

        jButton5.setText("Beenden");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quit(evt);
            }
        });

        jButton6.setText("Lehrersuche");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pInputsLayout = new javax.swing.GroupLayout(pInputs);
        pInputs.setLayout(pInputsLayout);
        pInputsLayout.setHorizontalGroup(
            pInputsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pInputsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pInputsLayout.setVerticalGroup(
            pInputsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pInputsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pInputsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pInputs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pInputs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 691, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * This method paints the path between the selected rooms on the panel.
     * 
     * @param evt The event which called the method.
     */
    private void findPath(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findPath
        groundPlan.drawPath((String) jComboBox1.getSelectedItem(),(String) jComboBox2.getSelectedItem());
    }//GEN-LAST:event_findPath
    
    /**
     * This method prints the content of the panel.
     * IMPORTANT NOTE: Modify the String 'filename' to alter the location
     * to which the image is saved.
     * 
     * @param evt The event which called the method.
     */
    private void print(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_print
        groundPlan.print();
    }//GEN-LAST:event_print

    private void openSearch(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openSearch
        Search s = new Search();
        s.setVisible(true);
    }//GEN-LAST:event_openSearch

    private void quit(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quit
        // Quits the application
        System.exit(0);
    }//GEN-LAST:event_quit

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
            if(lS.isDisplayable()) {
                lS.setVisible(true);
                lS.jTextField1.requestFocus();
            }
            else {
                lS = new lSuche(this);
                lS.setLocationRelativeTo(null);
                lS.setVisible(true);
                lS.jTextField1.requestFocus();
            }
        }
        catch(NullPointerException npe) {
            lS = new lSuche(this);
            lS.setLocationRelativeTo(null);
            lS.setVisible(true);
            lS.jTextField1.requestFocus();
        } 
    }//GEN-LAST:event_jButton6ActionPerformed
   
    /**
     * This method paints the components of the panel
     * and scales them depending on user input.
     * 
     * @param g Graphics of the panel.
     */
    @Override 
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.setSize(frame.getContentPane().getBounds().width, frame.getContentPane().getBounds().height);
        groundPlan.setSize(Math.min(this.getHeight() - 51, this.getWidth()), Math.min(this.getHeight() - 51, this.getWidth()));
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getter">
    /**
     * Returns the GroundPlanPanel contained in the content panel.
     * @return 
     */
    public GroundPlanPanel getGroundPlan() {
        return groundPlan;
    }
    
    /**
     * Returns the parented frame of the Content object.
     * @return 
     */
    public JFrame getParentFrame() {
        return frame;
    }
    //</editor-fold>

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JPanel pInputs;
    // End of variables declaration//GEN-END:variables
}
