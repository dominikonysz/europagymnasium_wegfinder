/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import backend.WegfinderBack;
import db.DBController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import listenklassen.List_extended;

/**
 * Dialog to search for a teacher
 * dynamically adds a button for each teacher to directly search for the path. 
 * @author Leonard Cohnen
 */
public class TSEFront extends javax.swing.JFrame {

    /**
     * Creates new form TSEFront
     */
    private UserInterface ui;
    private WegfinderBack backend;
    private int ButtonOffset;  //vertical offset of the buttons and labels
    private JButton buttons[]; //to keep track of dynamically generated buttons for deletion
    private JLabel label[];    //to keep track of dynamically generated labels for deletion
    private int lbIterator;    //number of generated buttons and label at that time
    private int maxEntries;
    
    
    public TSEFront() {
        initComponents();
        backend = WegfinderBack.getBackend();
        ui = UserInterface.getUI();
        
        maxEntries = 8;
        
        TSEFront frame = this;
        ButtonOffset = 0;
        buttons = new JButton[10]; //max 10 because only 5 will be displayed at the same time
        label = new JLabel[10];
        lbIterator = 0;
        jTextField1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
              update();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
              update();
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
              update();
            }
            /**
             * is executed when anything changes in the textfield
             * used to display any teacher where the given string is a substring 
             * of the (sur)name
             */
            private void update(){ 
                clean();
                
                String lRequest = jTextField1.getText();
                if(!lRequest.equals("")) {
                    String[][] teacher = backend.getTeachersWith(lRequest);
                    for(int i = 0; i < Math.min(teacher.length, maxEntries); i++){
                        String name;
                        if(teacher[i][0].equals("")) {
                            name = teacher[i][1];
                        }
                        else {
                            name = teacher[i][1] + ", " + teacher[i][0];
                        }
                        addNewTeacher(name, teacher[i][2]);
                    }
                    if(teacher.length < maxEntries) {
                        String[] rooms = backend.getRoomsWith(lRequest);
                        for (int i = 0; i < Math.min(maxEntries - teacher.length, rooms.length); i++) {
                            addNewTeacher(rooms[i], rooms[i]);
                        }
                    }
                }
            }
             
        });
        
        // Exit the TeacherSearchEngine with ESC
        TSEFront lS = this;
        JPanel panel = (JPanel) this.getContentPane();
        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "tse");
        panel.getActionMap().put("tse", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lS.dispose();
            }
        });
        
        // Change the button position and the label width when resizing the frame
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent event) {
                try {
                    for(JButton b : buttons) {
                        if(b != null) {
                            b.setLocation((int) (frame.getWidth() - b.getWidth() - 30), (int) b.getLocation().getY());
                        }
                        else {
                            break;
                        }
                    }
                    for(JLabel l : label) {
                        if(l != null && buttons.length > 0) {
                            l.setSize((int) (buttons[0].getLocation().getX() - 50), l.getHeight());
                        }
                        else {
                            break;
                        }
                    }
                }
                catch (Exception e) {
                    System.out.println("Irgendein Fehler beim skalieren des TSE Fensters");
                }
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

        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Lehrersuche");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Nachname");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(360, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed
   
    /**
     * Generates a new button on the right with
     * an increasing offset on the y - axis 
     * by clicking on it, it marks the way from "Foyer" to the right room
     */
    private void makeJButton(String msg){
        JButton nB = new JButton();
        buttons[lbIterator] = nB;
        nB.setBounds(180, 60 + ButtonOffset, 120, 27);
        nB.setText(msg);
        
        TSEFront lsuche = this;
        
        nB.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    backend.drawPath("Foyer", msg);
                    ui.updatePath("Foyer", msg);
                }
                catch(NullPointerException npe) {
                    JOptionPane.showMessageDialog(rootPane, "Weg nicht verfügbar\nBitte Weg manuell zeigen", "Hinweis", JOptionPane.WARNING_MESSAGE);
                }
                backend.getFrontend().requestFocus();
            }
        });
        
        jPanel1.add(nB);     
    }
    
    /**
     * Adds new label on the same height as the related button
     * used to label the button with the teachers name
     * @param msg 
     */
    private void makeJLabel(String msg){
        JLabel nL = new JLabel();
        label[lbIterator] = nL;
        nL.setBounds(30, 60 + ButtonOffset, 100, 27);
        nL.setText(msg);
        jPanel1.add(nL); 
        
    }

    /**
     * Adds new teacher with name and room
     * @param name 
     * @param room 
     */
    private void addNewTeacher(String name, String room){
        makeJButton(room);
        makeJLabel(name);
        ButtonOffset += 40;
        lbIterator++;
        this.invalidate();
        this.validate();
        this.repaint();
        
    }
    
    /**
     * cleans buttons and labels from the panel
     * IMPORTANT: still memory leakage 
     * TODO: proper deletion of both
     */
    public void clean(){
        for(int i = 0; i<buttons.length; i++){
            if(buttons[i] != null){
                buttons[i].setVisible(false);  
                buttons[i] = null;
            }
        }
        for(int k = 0; k<label.length;k++){
            if(label[k] != null){
                label[k].setVisible(false);
                buttons[k] = null;
            } 
        }
        lbIterator = 0;
        ButtonOffset = 0;
        this.invalidate();
        this.validate();
        this.repaint();
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
