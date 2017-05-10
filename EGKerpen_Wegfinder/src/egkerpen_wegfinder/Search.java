/*
 * This class is used for an extended search query
 */

package egkerpen_wegfinder;

import listenklassen.List_extended;
import db.DBController;
import javax.swing.JOptionPane;

/**
 *
 * @author Q2.08Heinrichs
 */
public class Search extends javax.swing.JFrame {
    
    /**
     * Creates new form Search
     */
    public Search() {
        initComponents();
        
        buttonGroup1.add(jRadioButton1);
        buttonGroup1.add(jRadioButton2);
        buttonGroup1.add(jRadioButton3);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Erweiterte Suche");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Wonach suchen Sie?");

        jButton1.setText("Suche");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                executeSearch(evt);
            }
        });

        jButton2.setText("Abbrechen");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                close(evt);
            }
        });

        jRadioButton1.setText("In welchem Raum befindet sich LehrerIn XY?");

        jRadioButton2.setText("Welche(r) LehrerIn befindet sich in Raum XY?");

        jRadioButton3.setText("Welche LehrerInnen sind krank bzw. verhindert?");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton3)
                            .addComponent(jRadioButton2)
                            .addComponent(jRadioButton1)
                            .addComponent(jLabel1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton3)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Closes the frame.
     * 
     * @param evt The event calling the method.
     */
    private void close(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_close
        this.dispose();
    }//GEN-LAST:event_close

    /**
     * This method executes a query depending on which radio button is selected.
     * 
     * @param evt The event calling the method.
     */
    private void executeSearch(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_executeSearch
        DBController con = DBController.getInstance();
        
        if(jRadioButton1.isSelected()){
            String lehrer = JOptionPane.showInputDialog("Welche Lehrkraft (Nachname) ist gesucht?");
            String query = "SELECT * FROM raumverteilung WHERE Nachname = '" + lehrer + "' AND NOT raum = '- (Verhindert)'";
            
            con.executeQuery(query);
            
            List_extended<List_extended<String>> l = con.getResults();
            
            String[][] s = new String[con.getResultsAmount()][4];
            
            l.toFirst();
            
            for(int i = 0; i < s.length; i++){
                List_extended<String> l2 = l.getObject();
                l2.toFirst();
                
                for(int j = 0; j < s[i].length; j++){
                    s[i][j] = l2.getObject();
                    l2.next();
                }
                
                l.next();
            }
            
            String res = "";
            
            for(int i = 0; i < s.length; i++){
                res = res + "\n" + s[i][0] + " (" + s[i][2] + " " + s[i][1] + ")";
            }
            
            switch(s.length){
                case 0:
                    JOptionPane.showMessageDialog(null, "Ihre Suche lieferte keine Ergebnisse!\nBitte überprüfen Sie Ihre Eingabe gegebenenfalls auf Fehler oder überprüfen Sie, ob der/die gesuchte LehrerIn verhindert ist.", "Ergebnis Ihrer Suche", JOptionPane.PLAIN_MESSAGE);
                    break;
                case 1:
                    JOptionPane.showMessageDialog(null, "Der/Die LehrerIn mit dem gesuchten Nachnamen befindet sich in folgendem Raum:" + res + "\n\nFalls dies nicht der/die gesuchte LehrerIn ist, ist die gesuchte Lehrkraft vermutlich heute verhindert.", "Ergebnis Ihrer Suche", JOptionPane.PLAIN_MESSAGE);
                    break;
                
                default:
                    JOptionPane.showMessageDialog(null, "Die LehrerInnen mit dem gesuchten Nachnamen befinden sich in folgenden Räumen" + res + "\n\nFalls der/die gesuchte LehrerIn nicht darunter ist, ist die gesuchte Lehrkraft vermutlich heute verhindert.", "Ergebnis Ihrer Suche", JOptionPane.PLAIN_MESSAGE);
            }
            
        } else if(jRadioButton2.isSelected()){
            String[] possibleSel = {"Raum 38", "Raum 39", "Raum 42", "Raum 63", "Raum 64", "Raum 65", "Raum 66", "Raum 67", "Raum 70", "Raum 71", "Raum 72", "Raum 73", "Raum 87", "Raum 88", "Raum 89", "Raum 90", "Raum 92", "Raum 93", "Raum 94", "Raum 95", "Raum 96", "Raum 97", "Raum 104", "Raum 106", "Raum 107", "Raum 108", "Raum 109", "Raum 110", "Raum 132", "Raum 134", "Raum 135", "Raum 136", "Raum 137", "Raum 141", "Raum 142", "Raum 144", "Raum 145", "Raum 150", "Raum 151", "Raum 154", "Raum 155", "Raum 156", "Raum 166", "Raum 167", "Raum 168", "Raum 169", "Raum 170", "Raum 171", "Raum 174", "Raum 175", "Raum 184", "Raum 185", "Raum 188", "Raum 189", "Raum 190", "Raum 191", "Raum 192/193", "Raum 200", "Raum 203/204", "Raum 205", "Raum 206", "Raum 208", "Raum 209", "Raum 210", "Raum 212", "Raum 213", "Raum 215", "Raum 216", "Raum 217", "Raum 218", "Raum 219", "Raum 220", "Raum 222", "Raum 223", "Raum 227", "Raum 228", "Raum 229", "Raum 230", "Raum 231", "Raum 235", "Raum 236", "Raum 237", "Raum 238", "Raum 240", "Raum 241", "Raum 242", "Raum 250", "Raum 251", "Raum 253", "Raum 254", "Raum 255", "Raum 262", "Raum 263", "Raum 264", "Raum 266", "Raum 267", "Raum 269", "Raum 270", "Raum 283", "Raum 284", "Raum 286", "Raum 288", "Raum 289", "Raum 290", "Raum 293", "Raum 302", "Raum 303", "Raum 304", "Raum 305", "Raum 306", "Raum 307", "Raum 308", "Raum 309", "Raum 310", "Raum 311", "Raum 312", "Raum 313", "Raum 314", "Werkraum 8", "Werkraum 14", "Werkraum 15", "Werkraum 17", "Werkraum 20", "Werkraum 21", "Werkraum 24", "Werkraum 27", "Werkraum 32", "Mensa", "Büro der Schulleitung/Sekretariat", "Büro der stellv. Schulleitung", "Saftladen", "Bibliothek"};
            
            String raum = (String) JOptionPane.showInputDialog(null, "Welcher Raum ist gesucht?", null, JOptionPane.PLAIN_MESSAGE, null, possibleSel, "Raum 38");
            con.executeQuery("SELECT * FROM raumverteilung WHERE Raum = '" + raum + "'");
            
            List_extended<List_extended<String>> l = con.getResults();
            
            String[][] s = new String[con.getResultsAmount()][4];
            
            l.toFirst();
            
            for(int i = 0; i < s.length; i++){
                List_extended<String> l2 = l.getObject();
                l2.toFirst();
                
                for(int j = 0; j < s[i].length; j++){
                    s[i][j] = l2.getObject();
                    l2.next();
                }
                
                l.next();
            }
            
            String res = "";
            
            for(int i = 0; i < s.length; i++){
                res = res + "\n" + s[i][2] + " " + s[i][1];
            }
            
            switch(s.length){
                case 0:
                    JOptionPane.showMessageDialog(null, "Ihre Suche lieferte keine Ergebnisse!\nBitte überprüfen Sie Ihre Eingabe gegebenenfalls auf Fehler.", "Ergebnis Ihrer Suche", JOptionPane.PLAIN_MESSAGE);
                    break;
                case 1:
                    JOptionPane.showMessageDialog(null, "Im gesuchten Raum (" + raum + ") befindet sich folgende(r) LehrerIn:" + res, "Ergebnis Ihrer Suche", JOptionPane.PLAIN_MESSAGE);
                    break;
                    
                default:
                    JOptionPane.showMessageDialog(null, "Im gesuchten Raum (" + raum + ") befinden sich folgende LehrerInnen:" + res, "Ergebnis Ihrer Suche", JOptionPane.PLAIN_MESSAGE);
                    
            }
            
        } else if(jRadioButton3.isSelected()){
            con.executeQuery("SELECT * FROM raumverteilung WHERE Verhindert = 1");
            
            List_extended<List_extended<String>> l = con.getResults();
            
            String[][] s = new String[con.getResultsAmount()][4];
            
            l.toFirst();
            
            for(int i = 0; i < s.length; i++){
                List_extended<String> l2 = l.getObject();
                l2.toFirst();
                
                for(int j = 0; j < s[i].length; j++){
                    s[i][j] = l2.getObject();
                    l2.next();
                }
                
                l.next();
            }
            
            String res = "";
            
            for(int i = 0; i < s.length; i++){
                res = res + "\n" + s[i][2] + " " + s[i][1];
            }
            
            switch(s.length){
                case 0:
                    JOptionPane.showMessageDialog(null, "Alle LehrerInnen sind anwesend.", "Ergebnis Ihrer Suche", JOptionPane.PLAIN_MESSAGE);
                    break;
                    
                default:
                    JOptionPane.showMessageDialog(null, "Folgende LehrerInnen sind verhindert:" + res, "Ergebnis Ihrer Suche", JOptionPane.PLAIN_MESSAGE);
            }
            
        }
    }//GEN-LAST:event_executeSearch

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    // End of variables declaration//GEN-END:variables
}
