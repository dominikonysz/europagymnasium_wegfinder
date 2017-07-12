/*
 * This class initialises the database which connects every teacher with a room.
 */

package egkerpen_wegfinder;

import db.DBController;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import egkerpen_wegfinder.EGKerpen_Wegfinder;
import javafx.scene.input.KeyCode;

/**
 *
 * @author Bjoern Heinrichs
 */
public class DBInitialise extends javax.swing.JFrame {
    Writer fw;
    File f;
    /**
     * Creates new form DBInitialise
     */
    public DBInitialise() {
        initComponents();
        
        try {
            f = new File("dbContent.txt");
            fw = new FileWriter(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        verhindert = new javax.swing.JTextField();
        vorname = new javax.swing.JTextField();
        nachname = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        raum = new javax.swing.JComboBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Initialisierung der Datenbank");
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });

        jLabel1.setText("Raum:");

        jLabel2.setText("Lehrer (Nachname):");

        jLabel3.setText("Lehrer (Vorname):");

        jLabel4.setText("Verhindert (0 oder 1):");

        verhindert.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                verhindertKeyPressed(evt);
            }
        });

        jButton1.setText("Weiter >>");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                weiter(evt);
            }
        });

        jButton2.setText("Fertig");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fertig(evt);
            }
        });

        raum.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Raum 38", "Raum 39", "Raum 42", "Raum 63", "Raum 64", "Raum 65", "Raum 66", "Raum 67", "Raum 70", "Raum 71", "Raum 72", "Raum 73", "Raum 87", "Raum 88", "Raum 89", "Raum 90", "Raum 92", "Raum 93", "Raum 94", "Raum 95", "Raum 96", "Raum 97", "Raum 104", "Raum 106", "Raum 107", "Raum 108", "Raum 109", "Raum 110", "Raum 132", "Raum 134", "Raum 135", "Raum 136", "Raum 137", "Raum 141", "Raum 142", "Raum 144", "Raum 145", "Raum 150", "Raum 151", "Raum 154", "Raum 155", "Raum 156", "Raum 166", "Raum 167", "Raum 168", "Raum 169", "Raum 170", "Raum 171", "Raum 174", "Raum 175", "Raum 184", "Raum 185", "Raum 188", "Raum 189", "Raum 190", "Raum 191", "Raum 192/193", "Raum 200", "Raum 203/204", "Raum 205", "Raum 206", "Raum 208", "Raum 209", "Raum 210", "Raum 212", "Raum 213", "Raum 215", "Raum 216", "Raum 217", "Raum 218", "Raum 219", "Raum 220", "Raum 222", "Raum 223", "Raum 227", "Raum 228", "Raum 229", "Raum 230", "Raum 231", "Raum 235", "Raum 236", "Raum 237", "Raum 238", "Raum 240", "Raum 241", "Raum 242", "Raum 250", "Raum 251", "Raum 253", "Raum 254", "Raum 255", "Raum 262", "Raum 263", "Raum 264", "Raum 266", "Raum 267", "Raum 269", "Raum 270", "Raum 283", "Raum 284", "Raum 286", "Raum 288", "Raum 289", "Raum 290", "Raum 293", "Raum 302", "Raum 303", "Raum 304", "Raum 305", "Raum 306", "Raum 307", "Raum 308", "Raum 309", "Raum 310", "Raum 311", "Raum 312", "Raum 313", "Raum 314", "Werkraum 8", "Werkraum 14", "Werkraum 15", "Werkraum 17", "Werkraum 20", "Werkraum 21", "Werkraum 24", "Werkraum 27", "Werkraum 32", "Kunstraum 53", "Kunstraum 59", "Technikraum 62", "Mensa", "Büro der Schulleitung/Sekretariat", "Büro der stellv. Schulleitung", "Saftladen", "Spielekeller", "Bibliothek" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(verhindert)
                            .addComponent(vorname)
                            .addComponent(nachname)
                            .addComponent(raum, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(raum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nachname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(vorname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(verhindert, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu1.setText("Programm");

        jMenuItem1.setText("Import aus Textdatei");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importTxt(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    /**
     * This method calls the method insertToFile after the button "Weiter" is clicked on.
     * By this, the values in the text fields get inserted into a file.
     * 
     * @param evt The event calling the method
     */
    private void weiter(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_weiter
        nextInput();
    }//GEN-LAST:event_weiter
    
    /**
     * This method calls the method weiter, initialises the database and opens a frame of the class Wegfinder.
     * 
     * @param evt The event calling the method 
     */
    private void fertig(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fertig
        weiter(null);
        
        DBController r = DBController.getInstance();
        
        if(r.dbInitialise(f)){
        
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        
            EGKerpen_Wegfinder.main(null);
            this.dispose();
        }
    }//GEN-LAST:event_fertig

    /**
     * This method initialises the databse by importing the values of a .txt file.
     * 
     * @param evt The event calling the method 
     */
    private void importTxt(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importTxt
        try {
            JFileChooser jf = new JFileChooser();
            int returnval = jf.showDialog(null, "Öffnen");
            
            if(returnval == JFileChooser.APPROVE_OPTION) {
                if(jf.getSelectedFile().getPath().endsWith(".txt")){
                    DBController r = DBController.getInstance();

                    if(r.dbInitialise(jf.getSelectedFile())){
                        EGKerpen_Wegfinder.main(null);
                        this.dispose();
                    }
                } else
                    JOptionPane.showMessageDialog(null, "Bitte wählen Sie eine gültige Datei (\".txt\") aus.");
            }
            
        } catch (HeadlessException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_importTxt

    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped
        System.out.println(evt.getKeyCode());
    }//GEN-LAST:event_formKeyTyped

    private void verhindertKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_verhindertKeyPressed
        if(evt.getKeyCode() == 10) {
            nextInput();
        }
    }//GEN-LAST:event_verhindertKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DBInitialise().setVisible(true);
            }
        });
    }
    
    private void nextInput() {        
        if(!nachname.getText().equals("") && /*!vorname.getText().equals("") &&*/ (verhindert.getText().equals("0") || verhindert.getText().equals("1")))
        {
            insertToFile(String.valueOf(raum.getSelectedItem()), nachname.getText(), vorname.getText().equals("") ? "-" : vorname.getText(), Integer.parseInt(verhindert.getText()));
        
            nachname.setText("");
            vorname.setText("");
            verhindert.setText("");
            raum.setSelectedIndex(raum.getSelectedIndex() + 1);
            nachname.requestFocus();
        } 
        else 
        {
            if(nachname.getText().equals("") && verhindert.getText().equals("")) 
            {
                
            }
            else 
            {
                JOptionPane.showMessageDialog(null, "Fehlerhafte oder fehlende Eingabe!");
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField nachname;
    private javax.swing.JComboBox raum;
    private javax.swing.JTextField verhindert;
    private javax.swing.JTextField vorname;
    // End of variables declaration//GEN-END:variables

    /**
     * This method inserts the values of the text fields into a file creating a new data set.
     * 
     * @param raum The room where the teacher is in.
     * @param nachname The teacher's last name.
     * @param vorname The teacher's first name.
     * @param verhindert A value indicating whether the teacher is absent or not
     */
    private void insertToFile(String raum, String nachname, String vorname, int verhindert) {
        try {
            fw.write(raum + ";" + nachname + ";" + vorname + ";" + String.valueOf(verhindert));
            fw.append(System.getProperty("line.separator"));
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
