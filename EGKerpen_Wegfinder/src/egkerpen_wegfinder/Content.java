/**
 * This class designs the panel containing an image of the school's rooms.
 * It is able to paint the path between two rooms and to print the panel.
 */

package egkerpen_wegfinder;

import graphklassen.Graph;
import graphklassen.GraphNode;
import listenklassen.List;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import javax.imageio.ImageIO;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import static javax.print.attribute.standard.Chromaticity.COLOR;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Bjoern Heinrichs
 */
public class Content extends javax.swing.JPanel{
    private final Image bg;
    private final Graph schoolGraph;
    private List<GraphNode> path;
    private final Dijkstra d;
    private JFrame frame;
    private int xOffset, yOffset, bgSideLength;
    private double diff;
    
    /**
     * Include these attributes to check every path of the graph.
     */
    // private String[] s1 = { "Foyer", "Raum 38", "Raum 39", "Raum 42", "Raum 63", "Raum 64", "Raum 65", "Raum 66", "Raum 67", "Raum 70", "Raum 71", "Raum 72", "Raum 73", "Raum 87", "Raum 88", "Raum 89", "Raum 90", "Raum 92", "Raum 93", "Raum 94", "Raum 95", "Raum 96", "Raum 97", "Raum 104", "Raum 106", "Raum 107", "Raum 108", "Raum 109", "Raum 110", "Raum 132", "Raum 134", "Raum 135", "Raum 136", "Raum 137", "Raum 141", "Raum 142", "Raum 144", "Raum 145", "Raum 150", "Raum 151", "Raum 154", "Raum 155", "Raum 156", "Raum 166", "Raum 167", "Raum 168", "Raum 169", "Raum 170", "Raum 171", "Raum 174", "Raum 175", "Raum 184", "Raum 185", "Raum 188", "Raum 189", "Raum 190", "Raum 191", "Raum 192/193", "Raum 200", "Raum 203/204", "Raum 205", "Raum 206", "Raum 208", "Raum 209", "Raum 210", "Raum 212", "Raum 213", "Raum 215", "Raum 216", "Raum 217", "Raum 218", "Raum 219", "Raum 220", "Raum 222", "Raum 223", "Raum 227", "Raum 228", "Raum 229", "Raum 230", "Raum 231", "Raum 235", "Raum 236", "Raum 237", "Raum 238", "Raum 240", "Raum 241", "Raum 242", "Raum 250", "Raum 251", "Raum 253", "Raum 254", "Raum 255", "Raum 262", "Raum 263", "Raum 264", "Raum 266", "Raum 267", "Raum 269", "Raum 270", "Raum 283", "Raum 284", "Raum 286", "Raum 288", "Raum 289", "Raum 290", "Raum 293", "Raum 302", "Raum 303", "Raum 304", "Raum 305", "Raum 306", "Raum 307", "Raum 308", "Raum 309", "Raum 310", "Raum 311", "Raum 312", "Raum 313", "Raum 314", "Werkraum 8", "Werkraum 14", "Werkraum 15", "Werkraum 17", "Werkraum 20", "Werkraum 21", "Werkraum 24", "Werkraum 27", "Werkraum 32", "Mensa", "Lehrerzimmer", "Büro der Schulleitung/Sekretariat", "Büro der stellv. Schulleitung", "Saftladen", "Bibliothek", "WC Damen", "WC Herren" };
    // private String[] s2 = { "Foyer", "Raum 38", "Raum 39", "Raum 42", "Raum 63", "Raum 64", "Raum 65", "Raum 66", "Raum 67", "Raum 70", "Raum 71", "Raum 72", "Raum 73", "Raum 87", "Raum 88", "Raum 89", "Raum 90", "Raum 92", "Raum 93", "Raum 94", "Raum 95", "Raum 96", "Raum 97", "Raum 104", "Raum 106", "Raum 107", "Raum 108", "Raum 109", "Raum 110", "Raum 132", "Raum 134", "Raum 135", "Raum 136", "Raum 137", "Raum 141", "Raum 142", "Raum 144", "Raum 145", "Raum 150", "Raum 151", "Raum 154", "Raum 155", "Raum 156", "Raum 166", "Raum 167", "Raum 168", "Raum 169", "Raum 170", "Raum 171", "Raum 174", "Raum 175", "Raum 184", "Raum 185", "Raum 188", "Raum 189", "Raum 190", "Raum 191", "Raum 192/193", "Raum 200", "Raum 203/204", "Raum 205", "Raum 206", "Raum 208", "Raum 209", "Raum 210", "Raum 212", "Raum 213", "Raum 215", "Raum 216", "Raum 217", "Raum 218", "Raum 219", "Raum 220", "Raum 222", "Raum 223", "Raum 227", "Raum 228", "Raum 229", "Raum 230", "Raum 231", "Raum 235", "Raum 236", "Raum 237", "Raum 238", "Raum 240", "Raum 241", "Raum 242", "Raum 250", "Raum 251", "Raum 253", "Raum 254", "Raum 255", "Raum 262", "Raum 263", "Raum 264", "Raum 266", "Raum 267", "Raum 269", "Raum 270", "Raum 283", "Raum 284", "Raum 286", "Raum 288", "Raum 289", "Raum 290", "Raum 293", "Raum 302", "Raum 303", "Raum 304", "Raum 305", "Raum 306", "Raum 307", "Raum 308", "Raum 309", "Raum 310", "Raum 311", "Raum 312", "Raum 313", "Raum 314", "Werkraum 8", "Werkraum 14", "Werkraum 15", "Werkraum 17", "Werkraum 20", "Werkraum 21", "Werkraum 24", "Werkraum 27", "Werkraum 32", "Mensa", "Lehrerzimmer", "Büro der Schulleitung/Sekretariat", "Büro der stellv. Schulleitung", "Saftladen", "Bibliothek", "WC Damen", "WC Herren" };
    // private int i, j;
    
    /**
     * Constructor. Initializes the GUI components and attributes of the class.
     * @param frame parent frame
     */
    @SuppressWarnings("empty-statement")
    public Content(JFrame frame) {
        initComponents();
        this.frame = frame;
        bg = new ImageIcon(getClass().getResource("grundriss.png")).getImage();
        
        schoolGraph = new Graph();
        buildGraph();
        
        path = new List<>();
        
        d = new Dijkstra(schoolGraph);
        
        /**
        * Include these lines to check every path of the graph.
        */
        // i = 0;
        // j = 0;
    }
    
    public Content() {
        initComponents();
        frame = new JFrame("Placeholder");
        bg = new ImageIcon(getClass().getResource("grundriss.png")).getImage();
                
        schoolGraph = new Graph();
        buildGraph();
        
        path = new List<>();
        
        d = new Dijkstra(schoolGraph);
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
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        lDiff = new javax.swing.JLabel();
        lFrom = new javax.swing.JLabel();
        lTo = new javax.swing.JLabel();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Foyer", "Raum 38", "Raum 39", "Raum 42", "Raum 63", "Raum 64", "Raum 65", "Raum 66", "Raum 67", "Raum 70", "Raum 71", "Raum 72", "Raum 73", "Raum 87", "Raum 88", "Raum 89", "Raum 90", "Raum 92", "Raum 93", "Raum 94", "Raum 95", "Raum 96", "Raum 97", "Raum 104", "Raum 106", "Raum 107", "Raum 108", "Raum 109", "Raum 110", "Raum 132", "Raum 134", "Raum 135", "Raum 136", "Raum 137", "Raum 141", "Raum 142", "Raum 144", "Raum 145", "Raum 150", "Raum 151", "Raum 154", "Raum 155", "Raum 156", "Raum 166", "Raum 167", "Raum 168", "Raum 169", "Raum 170", "Raum 171", "Raum 174", "Raum 175", "Raum 184", "Raum 185", "Raum 188", "Raum 189", "Raum 190", "Raum 191", "Raum 192/193", "Raum 200", "Raum 203/204", "Raum 205", "Raum 206", "Raum 208", "Raum 209", "Raum 210", "Raum 212", "Raum 213", "Raum 215", "Raum 216", "Raum 217", "Raum 218", "Raum 219", "Raum 220", "Raum 222", "Raum 223", "Raum 227", "Raum 228", "Raum 229", "Raum 230", "Raum 231", "Raum 235", "Raum 236", "Raum 237", "Raum 238", "Raum 240", "Raum 241", "Raum 242", "Raum 250", "Raum 251", "Raum 253", "Raum 254", "Raum 255", "Raum 262", "Raum 263", "Raum 264", "Raum 266", "Raum 267", "Raum 269", "Raum 270", "Raum 283", "Raum 284", "Raum 286", "Raum 288", "Raum 289", "Raum 290", "Raum 293", "Raum 302", "Raum 303", "Raum 304", "Raum 305", "Raum 306", "Raum 307", "Raum 308", "Raum 309", "Raum 310", "Raum 311", "Raum 312", "Raum 313", "Raum 314", "Werkraum 8", "Werkraum 14", "Werkraum 15", "Werkraum 17", "Werkraum 20", "Werkraum 21", "Werkraum 24", "Werkraum 27", "Werkraum 32", "Mensa", "Lehrerzimmer", "Büro der Schulleitung/Sekretariat", "Büro der stellv. Schulleitung", "Saftladen", "Bibliothek", "WC Damen", "WC Herren" }));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Foyer", "Raum 38", "Raum 39", "Raum 42", "Raum 63", "Raum 64", "Raum 65", "Raum 66", "Raum 67", "Raum 70", "Raum 71", "Raum 72", "Raum 73", "Raum 87", "Raum 88", "Raum 89", "Raum 90", "Raum 92", "Raum 93", "Raum 94", "Raum 95", "Raum 96", "Raum 97", "Raum 104", "Raum 106", "Raum 107", "Raum 108", "Raum 109", "Raum 110", "Raum 132", "Raum 134", "Raum 135", "Raum 136", "Raum 137", "Raum 141", "Raum 142", "Raum 144", "Raum 145", "Raum 150", "Raum 151", "Raum 154", "Raum 155", "Raum 156", "Raum 166", "Raum 167", "Raum 168", "Raum 169", "Raum 170", "Raum 171", "Raum 174", "Raum 175", "Raum 184", "Raum 185", "Raum 188", "Raum 189", "Raum 190", "Raum 191", "Raum 192/193", "Raum 200", "Raum 203/204", "Raum 205", "Raum 206", "Raum 208", "Raum 209", "Raum 210", "Raum 212", "Raum 213", "Raum 215", "Raum 216", "Raum 217", "Raum 218", "Raum 219", "Raum 220", "Raum 222", "Raum 223", "Raum 227", "Raum 228", "Raum 229", "Raum 230", "Raum 231", "Raum 235", "Raum 236", "Raum 237", "Raum 238", "Raum 240", "Raum 241", "Raum 242", "Raum 250", "Raum 251", "Raum 253", "Raum 254", "Raum 255", "Raum 262", "Raum 263", "Raum 264", "Raum 266", "Raum 267", "Raum 269", "Raum 270", "Raum 283", "Raum 284", "Raum 286", "Raum 288", "Raum 289", "Raum 290", "Raum 293", "Raum 302", "Raum 303", "Raum 304", "Raum 305", "Raum 306", "Raum 307", "Raum 308", "Raum 309", "Raum 310", "Raum 311", "Raum 312", "Raum 313", "Raum 314", "Werkraum 8", "Werkraum 14", "Werkraum 15", "Werkraum 17", "Werkraum 20", "Werkraum 21", "Werkraum 24", "Werkraum 27", "Werkraum 32", "Mensa", "Lehrerzimmer", "Büro der Schulleitung/Sekretariat", "Büro der stellv. Schulleitung", "Saftladen", "Bibliothek", "WC Damen", "WC Herren" }));

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

        jButton4.setText("Neustart");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newInit(evt);
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
                .addGap(18, 18, 18)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
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
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pInputs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lDiff, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lTo, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pInputs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lDiff, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lTo, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 637, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * This method paints the path between the selected rooms on the panel.
     * 
     * @param evt The event which called the method.
     */
    private void findPath(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findPath
        /**
        * Exclude this line to check every path of the graph.
        */
        path = d.getPath(schoolGraph.getNode((String) jComboBox1.getSelectedItem()), schoolGraph.getNode((String) jComboBox2.getSelectedItem()));
        
        
        // For debugging use only
        path.toFirst();
        while(path.hasAccess()){
            System.out.println(path.getObject().getName());
            path.next();
        }
        path.toFirst();
        /**
        * Include these lines to check every path of the graph.
        */
        /* path = d.getPath(schoolGraph.getNode(s1[i]), schoolGraph.getNode(s2[j]));
        
        if(j == s2.length - 1){
            j = 0;
            i++;
        } else j++;
        */
        
        repaint();
    }//GEN-LAST:event_findPath

    public void findPath(String start, String dest){
        /**
        * Exclude this line to check every path of the graph.
        */
        path = d.getPath(schoolGraph.getNode(start),schoolGraph.getNode(dest));
        
        
        // For debugging use only
        path.toFirst();
        while(path.hasAccess()){
            System.out.println(path.getObject().getName());
            path.next();
        }
        path.toFirst();
        /**
        * Include these lines to check every path of the graph.
        */
        /* path = d.getPath(schoolGraph.getNode(s1[i]), schoolGraph.getNode(s2[j]));
        
        if(j == s2.length - 1){
            j = 0;
            i++;
        } else j++;
        */
        
        repaint();
    } 
    
    /**
     * This method prints the content of the panel.
     * IMPORTANT NOTE: Modify the String 'filename' to alter the location
     * to which the image is saved.
     * 
     * @param evt The event which called the method.
     */
    private void print(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_print
        BufferedImage img = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        paint(img.createGraphics());
        
        // Modify this String to alter the location to which the image is saved.
        String filename = ".\\image.jpg";
        
        try {
            ImageIO.write(img, "jpg", new File(filename));
        } catch(IOException e){
            e.printStackTrace();
        }
        
        try {
            
            FileInputStream textStream = new FileInputStream(filename);
            
            DocFlavor formatSTREAM = DocFlavor.INPUT_STREAM.JPEG;
            PrintService service = PrintServiceLookup.lookupDefaultPrintService();
            DocPrintJob job = service.createPrintJob();
            Doc doc = new SimpleDoc(textStream, formatSTREAM, null);
            PrintRequestAttributeSet color = new HashPrintRequestAttributeSet();
            color.add(COLOR);
            //job.print(doc, color);         Nichts Drucken beim Ausprobieren!
 
        } catch (/*PrintException | */FileNotFoundException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_print

    private void openSearch(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openSearch
        Search s = new Search();
        s.setVisible(true);
    }//GEN-LAST:event_openSearch

    private void newInit(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newInit
        DBAktualisiere.main(null);
        
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.dispose();
    }//GEN-LAST:event_newInit

    private void quit(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quit
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.dispose();
    }//GEN-LAST:event_quit

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        lSuche lS = new lSuche(this);
        lS.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed
   
    /**
     * This method paints the components of the panel
     * including the path between the selected rooms.
     * 
     * @param g Graphics of the panel.
     */
    @Override 
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        this.setSize(frame.getContentPane().getBounds().width, frame.getContentPane().getBounds().height);
        
        calculateOffsets();
        g.drawImage(bg, this.getLocation().x + xOffset,this.getLocation().y + yOffset,
                bgSideLength, bgSideLength,this);
        
        NumberFormat n = NumberFormat.getInstance();
        n.setMaximumFractionDigits(2);
        lDiff.setText("Zoom: x" + n.format(diff));
        
        lFrom.setText(jComboBox1.getSelectedItem().toString());
        lTo.setText(jComboBox2.getSelectedItem().toString());
        
        if(!path.isEmpty()){
            g.setColor(Color.red);
            GraphNode n1, n2;
        
            int verticalOffset = -4;
            
            path.toFirst();
        
            n1 = path.getObject();
        
            path.next();
            
            while(path.hasAccess()){
                n2 = path.getObject();
                
                if(!(n1.getName().charAt(0) == 'T' && n2.getName().charAt(0) == 'T')){
                    g.fillRect(n1.getX() - 2, n1.getY() - 2 + verticalOffset, 5, 5);
                    g.drawLine(n1.getX(), n1.getY() + verticalOffset, n2.getX(), n2.getY() + verticalOffset);
                    g.fillRect(n2.getX() - 2, n2.getY() - 2 + verticalOffset, 5, 5);
                }
            
                n1 = n2;
                path.next();
            }
            
            g.setColor(Color.green);
            
            path.toFirst();
            g.fillRect(path.getObject().getX() - 2, path.getObject().getY() - 2 + verticalOffset, 5, 5);
            
            path.toLast();
            g.fillRect(path.getObject().getX() - 2, path.getObject().getY() - 2 + verticalOffset, 5, 5);
        }
    }
    
    private void calculateOffsets() {
        Container cPane = frame.getContentPane();
        yOffset = pInputs.getHeight();
        xOffset = (int) ((cPane.getWidth() - (cPane.getHeight() - yOffset)) * 0.5);
        bgSideLength = cPane.getHeight() - yOffset;
        diff = (double) bgSideLength / 850;
        System.out.println(diff);
        if(diff == 1.0) {
            System.out.println(frame.getSize());
        }
    }
    
    /**
     * This method builds the graph containing rooms, waypoints and stairs.
     */
    private void buildGraph(){
                // Nodes:
        
            // Ground floor:
        
        // Stairs:
        
        schoolGraph.addNode(new GraphNode("T1_0", 575, 707));
        schoolGraph.addNode(new GraphNode("T2_0", 675, 722));
        schoolGraph.addNode(new GraphNode("T3_0", 673, 647));
        schoolGraph.addNode(new GraphNode("T4_0", 596, 586));
        schoolGraph.addNode(new GraphNode("T5_0", 656, 581));
        schoolGraph.addNode(new GraphNode("T6_0", 589, 746));
        
        // Waypoints:
        
        schoolGraph.addNode(new GraphNode("P000", 591, 739));
        schoolGraph.addNode(new GraphNode("P001", 583, 739));
        schoolGraph.addNode(new GraphNode("P002", 546, 739));
        schoolGraph.addNode(new GraphNode("P003", 537, 739));
        schoolGraph.addNode(new GraphNode("P004", 591, 707));
        schoolGraph.addNode(new GraphNode("P005", 583, 707));
        schoolGraph.addNode(new GraphNode("P007", 627, 739));
        schoolGraph.addNode(new GraphNode("P008", 591, 684));
        schoolGraph.addNode(new GraphNode("P010", 566, 646));
        schoolGraph.addNode(new GraphNode("P011", 566, 636));
        schoolGraph.addNode(new GraphNode("P012", 566, 597));
        schoolGraph.addNode(new GraphNode("P013", 591, 611));
        schoolGraph.addNode(new GraphNode("P014", 591, 573));
        schoolGraph.addNode(new GraphNode("P016", 594, 557));
        schoolGraph.addNode(new GraphNode("P017", 594, 536));
        schoolGraph.addNode(new GraphNode("P018", 605, 536));
        schoolGraph.addNode(new GraphNode("P019", 643, 536));
        schoolGraph.addNode(new GraphNode("P020", 667, 536));
        schoolGraph.addNode(new GraphNode("P021", 680, 536));
        schoolGraph.addNode(new GraphNode("P022", 680, 554));
        schoolGraph.addNode(new GraphNode("P023", 680, 564));
        schoolGraph.addNode(new GraphNode("P024", 680, 593));
        schoolGraph.addNode(new GraphNode("P025", 656, 593));
        schoolGraph.addNode(new GraphNode("P026", 673, 670));
        schoolGraph.addNode(new GraphNode("P027", 700, 670));
        schoolGraph.addNode(new GraphNode("P028", 729, 670));
        schoolGraph.addNode(new GraphNode("P029", 759, 670));
        schoolGraph.addNode(new GraphNode("P030", 784, 670));
        schoolGraph.addNode(new GraphNode("P032", 803, 670));
        schoolGraph.addNode(new GraphNode("P033", 824, 670));
        schoolGraph.addNode(new GraphNode("P034", 675, 711));
        schoolGraph.addNode(new GraphNode("P035", 700, 711));
        schoolGraph.addNode(new GraphNode("P036", 700, 732));
        schoolGraph.addNode(new GraphNode("P037", 750, 732));
        schoolGraph.addNode(new GraphNode("P039", 761, 732));
        schoolGraph.addNode(new GraphNode("P040", 782, 732));
        schoolGraph.addNode(new GraphNode("P041", 782, 744));
        schoolGraph.addNode(new GraphNode("P042", 789, 732));
        schoolGraph.addNode(new GraphNode("P043", 816, 732));
        schoolGraph.addNode(new GraphNode("P044", 816, 749));
        
        // Rooms:
        
        schoolGraph.addNode(new GraphNode("Bibliothek", 609, 739));
        schoolGraph.addNode(new GraphNode("Foyer", 627, 707));
        schoolGraph.addNode(new GraphNode("WC Herren", 546, 724));
        schoolGraph.addNode(new GraphNode("WC Damen", 537, 724));
        schoolGraph.addNode(new GraphNode("Mensa", 527, 739));
        schoolGraph.addNode(new GraphNode("Saftladen", 596, 586));
        schoolGraph.addNode(new GraphNode("Raum 38", 548, 646));
        schoolGraph.addNode(new GraphNode("Raum 39", 548, 637));
        schoolGraph.addNode(new GraphNode("Raum 42", 548, 597));
        schoolGraph.addNode(new GraphNode("Werkraum 8", 594, 525));
        schoolGraph.addNode(new GraphNode("Werkraum 14", 605, 525));
        schoolGraph.addNode(new GraphNode("Werkraum 15", 643, 525));
        schoolGraph.addNode(new GraphNode("Werkraum 17", 667, 525));
        schoolGraph.addNode(new GraphNode("Werkraum 20", 680, 525));
        schoolGraph.addNode(new GraphNode("Werkraum 21", 693, 551));
        schoolGraph.addNode(new GraphNode("Werkraum 24", 693, 576));
        schoolGraph.addNode(new GraphNode("Werkraum 27", 668, 557));
        schoolGraph.addNode(new GraphNode("Werkraum 32", 605, 557));
        schoolGraph.addNode(new GraphNode("Kunstraum 53", 596, 586));
        schoolGraph.addNode(new GraphNode("Raum 63", 729, 656));
        schoolGraph.addNode(new GraphNode("Raum 64", 759, 656));
        schoolGraph.addNode(new GraphNode("Raum 65", 782, 656));
        schoolGraph.addNode(new GraphNode("Raum 66", 803, 656));
        schoolGraph.addNode(new GraphNode("Raum 67", 824, 656));
        schoolGraph.addNode(new GraphNode("Raum 70", 824, 694));
        schoolGraph.addNode(new GraphNode("Raum 71", 803, 694));
        schoolGraph.addNode(new GraphNode("Raum 72", 785, 694));
        schoolGraph.addNode(new GraphNode("Raum 73", 760, 694));
        schoolGraph.addNode(new GraphNode("Raum 87", 761, 720));
        schoolGraph.addNode(new GraphNode("Raum 88", 789, 720));
        schoolGraph.addNode(new GraphNode("Raum 89", 816, 720));
        schoolGraph.addNode(new GraphNode("Raum 90", 829, 732));
        schoolGraph.addNode(new GraphNode("Raum 92", 829, 749));
        schoolGraph.addNode(new GraphNode("Raum 93", 816, 764));
        schoolGraph.addNode(new GraphNode("Raum 94", 801, 747));
        schoolGraph.addNode(new GraphNode("Raum 95", 789, 764));
        schoolGraph.addNode(new GraphNode("Raum 96", 782, 764));
        schoolGraph.addNode(new GraphNode("Raum 97", 750, 763));
        
            // First floor:
        
        // Stairs:
        
        schoolGraph.addNode(new GraphNode("T1_1", 133, 703));
        schoolGraph.addNode(new GraphNode("T2_1", 275, 713));
        schoolGraph.addNode(new GraphNode("T3_1", 272, 622));
        schoolGraph.addNode(new GraphNode("T4_1", 161, 531));
        schoolGraph.addNode(new GraphNode("T5_1", 242, 531));
        schoolGraph.addNode(new GraphNode("T6_1", 161, 848));
        schoolGraph.addNode(new GraphNode("T7_1", 35, 815));
        schoolGraph.addNode(new GraphNode("T8_1", 412, 731));
        schoolGraph.addNode(new GraphNode("T9_1", 412, 656));
        
        // Waypoints:
        
        schoolGraph.addNode(new GraphNode("P100", 53, 815));
        schoolGraph.addNode(new GraphNode("P101", 65, 815));
        schoolGraph.addNode(new GraphNode("P102", 93, 815));
        schoolGraph.addNode(new GraphNode("P103", 115, 815));
        schoolGraph.addNode(new GraphNode("P104", 146, 815));
        schoolGraph.addNode(new GraphNode("P105", 161, 815));
        schoolGraph.addNode(new GraphNode("P106", 161, 835));
        schoolGraph.addNode(new GraphNode("P107", 161, 737));
        schoolGraph.addNode(new GraphNode("P108", 146, 737));
        schoolGraph.addNode(new GraphNode("P109", 89, 737));
        schoolGraph.addNode(new GraphNode("P110", 66, 737));
        schoolGraph.addNode(new GraphNode("P111", 146, 703));
        schoolGraph.addNode(new GraphNode("P112", 161, 703));
        schoolGraph.addNode(new GraphNode("P113", 161, 688));
        schoolGraph.addNode(new GraphNode("P114", 161, 649));
        schoolGraph.addNode(new GraphNode("P115", 161, 628));
        schoolGraph.addNode(new GraphNode("P116", 161, 592));
        schoolGraph.addNode(new GraphNode("P117", 161, 579));
        schoolGraph.addNode(new GraphNode("P118", 161, 561));
        schoolGraph.addNode(new GraphNode("P119", 161, 549));
        schoolGraph.addNode(new GraphNode("P120", 196, 688));
        schoolGraph.addNode(new GraphNode("P121", 226, 688));
        schoolGraph.addNode(new GraphNode("P122", 242, 688));
        schoolGraph.addNode(new GraphNode("P123", 242, 656));
        schoolGraph.addNode(new GraphNode("P124", 242, 604));
        schoolGraph.addNode(new GraphNode("P125", 242, 561));
        schoolGraph.addNode(new GraphNode("P126", 242, 549));
        schoolGraph.addNode(new GraphNode("P127", 272, 656));
        schoolGraph.addNode(new GraphNode("P128", 292, 656));
        schoolGraph.addNode(new GraphNode("P130", 315, 656));
        schoolGraph.addNode(new GraphNode("P131", 334, 656));
        schoolGraph.addNode(new GraphNode("P132", 346, 656));
        schoolGraph.addNode(new GraphNode("P133", 366, 656));
        schoolGraph.addNode(new GraphNode("P134", 379, 656));
        schoolGraph.addNode(new GraphNode("P135", 398, 656));
        schoolGraph.addNode(new GraphNode("P136", 275, 702));
        schoolGraph.addNode(new GraphNode("P137", 292, 702));
        schoolGraph.addNode(new GraphNode("P138", 292, 731));
        schoolGraph.addNode(new GraphNode("P139", 325, 731));
        schoolGraph.addNode(new GraphNode("P140", 325, 745));
        schoolGraph.addNode(new GraphNode("P141", 364, 731));
        schoolGraph.addNode(new GraphNode("P142", 395, 731));
        
        // Rooms:
        
        schoolGraph.addNode(new GraphNode("Lehrerzimmer", 66, 751));
        schoolGraph.addNode(new GraphNode("Büro der Schulleitung/Sekretariat", 66, 716));
        schoolGraph.addNode(new GraphNode("Büro der stellv. Schulleitung", 89, 710));
        schoolGraph.addNode(new GraphNode("Raum 104", 176, 835));
        schoolGraph.addNode(new GraphNode("Raum 106", 146, 841));
        schoolGraph.addNode(new GraphNode("Raum 107", 115, 841));
        schoolGraph.addNode(new GraphNode("Raum 108", 93, 841));
        schoolGraph.addNode(new GraphNode("Raum 109", 65, 841));
        schoolGraph.addNode(new GraphNode("Raum 110", 53, 841));
        schoolGraph.addNode(new GraphNode("Raum 132", 144, 649));
        schoolGraph.addNode(new GraphNode("Raum 134", 144, 628));
        schoolGraph.addNode(new GraphNode("Raum 135", 144, 592));
        schoolGraph.addNode(new GraphNode("Raum 136", 144, 579));
        schoolGraph.addNode(new GraphNode("Raum 137", 144, 549));
        schoolGraph.addNode(new GraphNode("Raum 141", 180, 549));
        schoolGraph.addNode(new GraphNode("Raum 142", 180, 561));
        schoolGraph.addNode(new GraphNode("Raum 144", 196, 668));
        schoolGraph.addNode(new GraphNode("Raum 145", 226, 668));
        schoolGraph.addNode(new GraphNode("Raum 150", 225, 561));
        schoolGraph.addNode(new GraphNode("Raum 151", 225, 549));
        schoolGraph.addNode(new GraphNode("Raum 154", 259, 549));
        schoolGraph.addNode(new GraphNode("Raum 155", 259, 561));
        schoolGraph.addNode(new GraphNode("Raum 156", 259, 606));
        schoolGraph.addNode(new GraphNode("Raum 166", 315, 638));
        schoolGraph.addNode(new GraphNode("Raum 167", 334, 638));
        schoolGraph.addNode(new GraphNode("Raum 168", 346, 638));
        schoolGraph.addNode(new GraphNode("Raum 169", 367, 638));
        schoolGraph.addNode(new GraphNode("Raum 170", 379, 638));
        schoolGraph.addNode(new GraphNode("Raum 171", 399, 638));
        schoolGraph.addNode(new GraphNode("Raum 174", 396, 682));
        schoolGraph.addNode(new GraphNode("Raum 175", 365, 682));
        schoolGraph.addNode(new GraphNode("Raum 184", 364, 715));
        schoolGraph.addNode(new GraphNode("Raum 185", 395, 715));
        schoolGraph.addNode(new GraphNode("Raum 188", 395, 760));
        schoolGraph.addNode(new GraphNode("Raum 189", 364, 760));
        schoolGraph.addNode(new GraphNode("Raum 190", 342, 745));
        schoolGraph.addNode(new GraphNode("Raum 191", 298, 765));
        schoolGraph.addNode(new GraphNode("Raum 192/193", 279, 734));
        
            // Second floor:
        
        // Stairs:
        
        schoolGraph.addNode(new GraphNode("T1_2", 557, 280));
        schoolGraph.addNode(new GraphNode("T2_2", 697, 290));
        schoolGraph.addNode(new GraphNode("T3_2", 694, 201));
        schoolGraph.addNode(new GraphNode("T4_2", 584, 109));
        schoolGraph.addNode(new GraphNode("T5_2", 665, 109));
        schoolGraph.addNode(new GraphNode("T6_2", 584, 424));
        schoolGraph.addNode(new GraphNode("T7_2", 460, 391));
        schoolGraph.addNode(new GraphNode("T8_2", 833, 308));
        schoolGraph.addNode(new GraphNode("T9_2", 833, 233));
        
        // Waypoints:
        
        schoolGraph.addNode(new GraphNode("P200", 489, 391));
        schoolGraph.addNode(new GraphNode("P201", 507, 391));
        schoolGraph.addNode(new GraphNode("P202", 517, 391));
        schoolGraph.addNode(new GraphNode("P203", 538, 391));
        schoolGraph.addNode(new GraphNode("P204", 569, 391));
        schoolGraph.addNode(new GraphNode("P205", 584, 391));
        schoolGraph.addNode(new GraphNode("P206", 584, 401));
        schoolGraph.addNode(new GraphNode("P207", 584, 413));
        schoolGraph.addNode(new GraphNode("P208", 584, 348));
        schoolGraph.addNode(new GraphNode("P209", 584, 311));
        schoolGraph.addNode(new GraphNode("P210", 569, 311));
        schoolGraph.addNode(new GraphNode("P211", 539, 311));
        schoolGraph.addNode(new GraphNode("P212", 508, 311));
        schoolGraph.addNode(new GraphNode("P213", 478, 311));
        schoolGraph.addNode(new GraphNode("P214", 569, 280));
        schoolGraph.addNode(new GraphNode("P215", 584, 280));
        schoolGraph.addNode(new GraphNode("P216", 584, 264));
        schoolGraph.addNode(new GraphNode("P217", 584, 227));
        schoolGraph.addNode(new GraphNode("P218", 584, 206));
        schoolGraph.addNode(new GraphNode("P219", 584, 170));
        schoolGraph.addNode(new GraphNode("P220", 584, 157));
        schoolGraph.addNode(new GraphNode("P221", 584, 137));
        schoolGraph.addNode(new GraphNode("P222", 584, 127));
        schoolGraph.addNode(new GraphNode("P223", 616, 264));
        schoolGraph.addNode(new GraphNode("P225", 652, 264));
        schoolGraph.addNode(new GraphNode("P227", 665, 264));
        schoolGraph.addNode(new GraphNode("P228", 665, 233));
        schoolGraph.addNode(new GraphNode("P229", 665, 181));
        schoolGraph.addNode(new GraphNode("P230", 665, 139));
        schoolGraph.addNode(new GraphNode("P231", 665, 127));
        schoolGraph.addNode(new GraphNode("P232", 694, 233));
        schoolGraph.addNode(new GraphNode("P233", 715, 233));
        schoolGraph.addNode(new GraphNode("P234", 715, 279));
        schoolGraph.addNode(new GraphNode("P235", 715, 308));
        schoolGraph.addNode(new GraphNode("P236", 738, 308));
        schoolGraph.addNode(new GraphNode("P237", 768, 308));
        schoolGraph.addNode(new GraphNode("P238", 697, 279));
        schoolGraph.addNode(new GraphNode("P239", 783, 308));
        schoolGraph.addNode(new GraphNode("P240", 817, 308));
        schoolGraph.addNode(new GraphNode("P241", 740, 233));
        schoolGraph.addNode(new GraphNode("P242", 758, 233));
        schoolGraph.addNode(new GraphNode("P243", 770, 233));
        schoolGraph.addNode(new GraphNode("P244", 789, 233));
        schoolGraph.addNode(new GraphNode("P246", 818, 233));
        schoolGraph.addNode(new GraphNode("P247", 477, 391));
        schoolGraph.addNode(new GraphNode("P248", 700, 311));
        
        // Rooms:
        
        schoolGraph.addNode(new GraphNode("Raum 200", 599, 313));
        schoolGraph.addNode(new GraphNode("Raum 203/204", 599, 348));
        schoolGraph.addNode(new GraphNode("Raum 205", 599, 401));
        schoolGraph.addNode(new GraphNode("Raum 206", 599, 413));
        schoolGraph.addNode(new GraphNode("Raum 208", 569, 413));
        schoolGraph.addNode(new GraphNode("Raum 209", 538, 413));
        schoolGraph.addNode(new GraphNode("Raum 210", 517, 413));
        schoolGraph.addNode(new GraphNode("Raum 212", 489, 413));
        schoolGraph.addNode(new GraphNode("Raum 213", 477, 413));
        schoolGraph.addNode(new GraphNode("Raum 215", 477, 374));
        schoolGraph.addNode(new GraphNode("Raum 216", 507, 374));
        schoolGraph.addNode(new GraphNode("Raum 217", 508, 329));
        schoolGraph.addNode(new GraphNode("Raum 218", 478, 329));
        schoolGraph.addNode(new GraphNode("Raum 219", 463, 311));
        schoolGraph.addNode(new GraphNode("Raum 220", 478, 293));
        schoolGraph.addNode(new GraphNode("Raum 222", 508, 293));
        schoolGraph.addNode(new GraphNode("Raum 223", 539, 293));
        schoolGraph.addNode(new GraphNode("Raum 227", 567, 227));
        schoolGraph.addNode(new GraphNode("Raum 228", 567, 206));
        schoolGraph.addNode(new GraphNode("Raum 229", 567, 170));
        schoolGraph.addNode(new GraphNode("Raum 230", 567, 157));
        schoolGraph.addNode(new GraphNode("Raum 231", 567, 127));
        schoolGraph.addNode(new GraphNode("Raum 235", 604, 127));
        schoolGraph.addNode(new GraphNode("Raum 236", 604, 139));
        schoolGraph.addNode(new GraphNode("Raum 237", 618, 246));
        schoolGraph.addNode(new GraphNode("Raum 238", 649, 246));
        schoolGraph.addNode(new GraphNode("Raum 240", 615, 282));
        schoolGraph.addNode(new GraphNode("Raum 241", 654, 282));
        schoolGraph.addNode(new GraphNode("Raum 242", 665, 282));
        schoolGraph.addNode(new GraphNode("Raum 250", 647, 139));
        schoolGraph.addNode(new GraphNode("Raum 251", 647, 127));
        schoolGraph.addNode(new GraphNode("Raum 253", 683, 127));
        schoolGraph.addNode(new GraphNode("Raum 254", 683, 138));
        schoolGraph.addNode(new GraphNode("Raum 255", 683, 181));
        schoolGraph.addNode(new GraphNode("Raum 262", 740, 216));
        schoolGraph.addNode(new GraphNode("Raum 263", 758, 216));
        schoolGraph.addNode(new GraphNode("Raum 264", 770, 216));
        schoolGraph.addNode(new GraphNode("Raum 266", 792, 216));
        schoolGraph.addNode(new GraphNode("Raum 267", 820, 216));
        schoolGraph.addNode(new GraphNode("Raum 269", 816, 250));
        schoolGraph.addNode(new GraphNode("Raum 270", 787, 250));
        schoolGraph.addNode(new GraphNode("Raum 283", 786, 291));
        schoolGraph.addNode(new GraphNode("Raum 284", 817, 291));
        schoolGraph.addNode(new GraphNode("Raum 286", 817, 335));
        schoolGraph.addNode(new GraphNode("Raum 288", 780, 335));
        schoolGraph.addNode(new GraphNode("Raum 289", 768, 335));
        schoolGraph.addNode(new GraphNode("Raum 290", 738, 335));
        schoolGraph.addNode(new GraphNode("Raum 293", 700, 335));
        
            // Third floor:
        
        // Stairs:
        
        schoolGraph.addNode(new GraphNode("T1_3", 137, 282));
        schoolGraph.addNode(new GraphNode("T2_3", 289, 308));
        schoolGraph.addNode(new GraphNode("T3_3", 289, 211));
        
        // Waypoints:
        
        schoolGraph.addNode(new GraphNode("P300", 166, 282));
        schoolGraph.addNode(new GraphNode("P301", 166, 288));
        schoolGraph.addNode(new GraphNode("P302", 166, 271));
        schoolGraph.addNode(new GraphNode("P303", 179, 271));
        schoolGraph.addNode(new GraphNode("P304", 198, 271));
        schoolGraph.addNode(new GraphNode("P305", 205, 271));
        schoolGraph.addNode(new GraphNode("P306", 218, 271));
        schoolGraph.addNode(new GraphNode("P307", 225, 271));
        schoolGraph.addNode(new GraphNode("P308", 238, 271));
        schoolGraph.addNode(new GraphNode("P309", 245, 271));
        schoolGraph.addNode(new GraphNode("P310", 251, 271));
        schoolGraph.addNode(new GraphNode("P311", 266, 271));
        schoolGraph.addNode(new GraphNode("P312", 289, 271));
        schoolGraph.addNode(new GraphNode("P313", 289, 278));
        schoolGraph.addNode(new GraphNode("P314", 289, 262));
        
        // Rooms:
        
        schoolGraph.addNode(new GraphNode("Raum 302", 166, 301));
        schoolGraph.addNode(new GraphNode("Raum 303", 186, 288));
        schoolGraph.addNode(new GraphNode("Raum 304", 205, 288));
        schoolGraph.addNode(new GraphNode("Raum 305", 225, 288));
        schoolGraph.addNode(new GraphNode("Raum 306", 245, 288));
        schoolGraph.addNode(new GraphNode("Raum 307", 266, 288));
        schoolGraph.addNode(new GraphNode("Raum 308", 314, 278));
        schoolGraph.addNode(new GraphNode("Raum 309", 310, 262));
        schoolGraph.addNode(new GraphNode("Raum 310", 251, 252));
        schoolGraph.addNode(new GraphNode("Raum 311", 238, 252));
        schoolGraph.addNode(new GraphNode("Raum 312", 218, 252));
        schoolGraph.addNode(new GraphNode("Raum 313", 198, 252));
        schoolGraph.addNode(new GraphNode("Raum 314", 179, 252));
        
        
                // Edges:
            
            // Stairs to stairs:
        
        // Ground floor to first floor:
        
        schoolGraph.addEdge(schoolGraph.getNode("T1_0"), schoolGraph.getNode("T1_1"), 17);
        schoolGraph.addEdge(schoolGraph.getNode("T2_0"), schoolGraph.getNode("T2_1"), 17);
        schoolGraph.addEdge(schoolGraph.getNode("T3_0"), schoolGraph.getNode("T3_1"), 17);
        schoolGraph.addEdge(schoolGraph.getNode("T4_0"), schoolGraph.getNode("T4_1"), 17);
        schoolGraph.addEdge(schoolGraph.getNode("T5_0"), schoolGraph.getNode("T5_1"), 17);
        schoolGraph.addEdge(schoolGraph.getNode("T6_0"), schoolGraph.getNode("T6_1"), 17);
        
        // First floor to second floor:
        
        schoolGraph.addEdge(schoolGraph.getNode("T1_1"), schoolGraph.getNode("T1_2"), 17);
        schoolGraph.addEdge(schoolGraph.getNode("T2_1"), schoolGraph.getNode("T2_2"), 17);
        schoolGraph.addEdge(schoolGraph.getNode("T3_1"), schoolGraph.getNode("T3_2"), 17);
        schoolGraph.addEdge(schoolGraph.getNode("T4_1"), schoolGraph.getNode("T4_2"), 17);
        schoolGraph.addEdge(schoolGraph.getNode("T5_1"), schoolGraph.getNode("T5_2"), 17);
        schoolGraph.addEdge(schoolGraph.getNode("T6_1"), schoolGraph.getNode("T6_2"), 17);
        schoolGraph.addEdge(schoolGraph.getNode("T7_1"), schoolGraph.getNode("T7_2"), 17);
        schoolGraph.addEdge(schoolGraph.getNode("T8_1"), schoolGraph.getNode("T8_2"), 17);
        schoolGraph.addEdge(schoolGraph.getNode("T9_1"), schoolGraph.getNode("T9_2"), 17);
        
        // Second floor to third floor:
        
        schoolGraph.addEdge(schoolGraph.getNode("T1_2"), schoolGraph.getNode("T1_3"), 17);
        schoolGraph.addEdge(schoolGraph.getNode("T2_2"), schoolGraph.getNode("T2_3"), 17);
        schoolGraph.addEdge(schoolGraph.getNode("T3_2"), schoolGraph.getNode("T3_3"), 17);
        
            // Stairs to waypoints:
        
        // Ground floor:
        
        schoolGraph.addEdge(schoolGraph.getNode("T1_0"), schoolGraph.getNode("P005"), 1);
        schoolGraph.addEdge(schoolGraph.getNode("T2_0"), schoolGraph.getNode("P034"), 1);
        schoolGraph.addEdge(schoolGraph.getNode("T3_0"), schoolGraph.getNode("P026"), 4);
        schoolGraph.addEdge(schoolGraph.getNode("T4_0"), schoolGraph.getNode("P014"), 2);
        schoolGraph.addEdge(schoolGraph.getNode("T5_0"), schoolGraph.getNode("P025"), 2);
        schoolGraph.addEdge(schoolGraph.getNode("T6_0"), schoolGraph.getNode("P000"), 34.5);
        
        // First floor:
        
        schoolGraph.addEdge(schoolGraph.getNode("T1_1"), schoolGraph.getNode("P111"), 1);
        schoolGraph.addEdge(schoolGraph.getNode("T2_1"), schoolGraph.getNode("P136"), 1);
        schoolGraph.addEdge(schoolGraph.getNode("T3_1"), schoolGraph.getNode("P127"), 3);
        schoolGraph.addEdge(schoolGraph.getNode("T4_1"), schoolGraph.getNode("P119"), 1);
        schoolGraph.addEdge(schoolGraph.getNode("T5_1"), schoolGraph.getNode("P126"), 1);
        schoolGraph.addEdge(schoolGraph.getNode("T6_1"), schoolGraph.getNode("P106"), 6);
        schoolGraph.addEdge(schoolGraph.getNode("T7_1"), schoolGraph.getNode("P100"), 1);
        schoolGraph.addEdge(schoolGraph.getNode("T8_1"), schoolGraph.getNode("P142"), 1);
        schoolGraph.addEdge(schoolGraph.getNode("T9_1"), schoolGraph.getNode("P135"), 1);
        
        // Second floor:
        
        schoolGraph.addEdge(schoolGraph.getNode("T1_2"), schoolGraph.getNode("P214"), 1);
        schoolGraph.addEdge(schoolGraph.getNode("T2_2"), schoolGraph.getNode("P238"), 1);
        schoolGraph.addEdge(schoolGraph.getNode("T3_2"), schoolGraph.getNode("P232"), 1);
        schoolGraph.addEdge(schoolGraph.getNode("T4_2"), schoolGraph.getNode("P222"), 0.5);
        schoolGraph.addEdge(schoolGraph.getNode("T5_2"), schoolGraph.getNode("P231"), 0.5);
        schoolGraph.addEdge(schoolGraph.getNode("T6_2"), schoolGraph.getNode("P207"), 1.5);
        schoolGraph.addEdge(schoolGraph.getNode("T7_2"), schoolGraph.getNode("P247"), 2.5);
        schoolGraph.addEdge(schoolGraph.getNode("T8_2"), schoolGraph.getNode("P240"), 1.5);
        schoolGraph.addEdge(schoolGraph.getNode("T9_2"), schoolGraph.getNode("P246"), 1.5);
        
        // Third floor:
        
        schoolGraph.addEdge(schoolGraph.getNode("T1_3"), schoolGraph.getNode("P300"), 1);
        schoolGraph.addEdge(schoolGraph.getNode("T2_3"), schoolGraph.getNode("P313"), 3);
        schoolGraph.addEdge(schoolGraph.getNode("T3_3"), schoolGraph.getNode("P314"), 6.5);
        
            // Waypoints to waypoints:
        
        // Ground floor:
        
        schoolGraph.addEdge(schoolGraph.getNode("P000"), schoolGraph.getNode("P001"), 3.5);
        schoolGraph.addEdge(schoolGraph.getNode("P000"), schoolGraph.getNode("P004"), 7.5);
        schoolGraph.addEdge(schoolGraph.getNode("P001"), schoolGraph.getNode("P002"), 17);
        schoolGraph.addEdge(schoolGraph.getNode("P001"), schoolGraph.getNode("P005"), 7.5);
        schoolGraph.addEdge(schoolGraph.getNode("P002"), schoolGraph.getNode("P003"), 4);
        schoolGraph.addEdge(schoolGraph.getNode("P004"), schoolGraph.getNode("P005"), 3.5);
        schoolGraph.addEdge(schoolGraph.getNode("P004"), schoolGraph.getNode("P008"), 10);
        schoolGraph.addEdge(schoolGraph.getNode("P008"), schoolGraph.getNode("P010"), 17);
        schoolGraph.addEdge(schoolGraph.getNode("P010"), schoolGraph.getNode("P011"), 3);
        schoolGraph.addEdge(schoolGraph.getNode("P011"), schoolGraph.getNode("P012"), 15);
        schoolGraph.addEdge(schoolGraph.getNode("P011"), schoolGraph.getNode("P013"), 11);
        schoolGraph.addEdge(schoolGraph.getNode("P013"), schoolGraph.getNode("P014"), 12);
        schoolGraph.addEdge(schoolGraph.getNode("P014"), schoolGraph.getNode("P016"), 10.5);
        schoolGraph.addEdge(schoolGraph.getNode("P016"), schoolGraph.getNode("P017"), 9);
        schoolGraph.addEdge(schoolGraph.getNode("P017"), schoolGraph.getNode("P018"), 2.5);
        schoolGraph.addEdge(schoolGraph.getNode("P018"), schoolGraph.getNode("P019"), 23);
        schoolGraph.addEdge(schoolGraph.getNode("P019"), schoolGraph.getNode("P020"), 5);
        schoolGraph.addEdge(schoolGraph.getNode("P020"), schoolGraph.getNode("P021"), 3.5);
        schoolGraph.addEdge(schoolGraph.getNode("P021"), schoolGraph.getNode("P022"), 8);
        schoolGraph.addEdge(schoolGraph.getNode("P022"), schoolGraph.getNode("P023"), 1);
        schoolGraph.addEdge(schoolGraph.getNode("P023"), schoolGraph.getNode("P024"), 22);
        schoolGraph.addEdge(schoolGraph.getNode("P024"), schoolGraph.getNode("P025"), 13);
        schoolGraph.addEdge(schoolGraph.getNode("P026"), schoolGraph.getNode("P027"), 13);
        schoolGraph.addEdge(schoolGraph.getNode("P027"), schoolGraph.getNode("P028"), 9);
        schoolGraph.addEdge(schoolGraph.getNode("P027"), schoolGraph.getNode("P035"), 11.5);
        schoolGraph.addEdge(schoolGraph.getNode("P028"), schoolGraph.getNode("P029"), 12);
        schoolGraph.addEdge(schoolGraph.getNode("P029"), schoolGraph.getNode("P030"), 9);
        schoolGraph.addEdge(schoolGraph.getNode("P030"), schoolGraph.getNode("P032"), 9);
        schoolGraph.addEdge(schoolGraph.getNode("P032"), schoolGraph.getNode("P033"), 9);
        schoolGraph.addEdge(schoolGraph.getNode("P034"), schoolGraph.getNode("P035"), 8);
        schoolGraph.addEdge(schoolGraph.getNode("P035"), schoolGraph.getNode("P036"), 9);
        schoolGraph.addEdge(schoolGraph.getNode("P036"), schoolGraph.getNode("P037"), 12);
        schoolGraph.addEdge(schoolGraph.getNode("P037"), schoolGraph.getNode("P039"), 9);
        schoolGraph.addEdge(schoolGraph.getNode("P039"), schoolGraph.getNode("P040"), 7);
        schoolGraph.addEdge(schoolGraph.getNode("P040"), schoolGraph.getNode("P041"), 6);
        schoolGraph.addEdge(schoolGraph.getNode("P040"), schoolGraph.getNode("P042"), 4);
        schoolGraph.addEdge(schoolGraph.getNode("P042"), schoolGraph.getNode("P043"), 13);
        schoolGraph.addEdge(schoolGraph.getNode("P043"), schoolGraph.getNode("P044"), 10);
        
        //Dirty Connetion added here
        schoolGraph.addEdge(schoolGraph.getNode("P027"), schoolGraph.getNode("P023"), 17);
        
        // First floor:
        
        schoolGraph.addEdge(schoolGraph.getNode("P100"), schoolGraph.getNode("P101"), 2.5);
        schoolGraph.addEdge(schoolGraph.getNode("P101"), schoolGraph.getNode("P102"), 9);
        schoolGraph.addEdge(schoolGraph.getNode("P102"), schoolGraph.getNode("P103"), 5);
        schoolGraph.addEdge(schoolGraph.getNode("P103"), schoolGraph.getNode("P104"), 8);
        schoolGraph.addEdge(schoolGraph.getNode("P104"), schoolGraph.getNode("P105"), 3.5);
        schoolGraph.addEdge(schoolGraph.getNode("P105"), schoolGraph.getNode("P106"), 3);
        schoolGraph.addEdge(schoolGraph.getNode("P105"), schoolGraph.getNode("P107"), 21);
        schoolGraph.addEdge(schoolGraph.getNode("P107"), schoolGraph.getNode("P108"), 3);
        schoolGraph.addEdge(schoolGraph.getNode("P107"), schoolGraph.getNode("P112"), 7);
        schoolGraph.addEdge(schoolGraph.getNode("P108"), schoolGraph.getNode("P109"), 16);
        schoolGraph.addEdge(schoolGraph.getNode("P108"), schoolGraph.getNode("P111"), 7);
        schoolGraph.addEdge(schoolGraph.getNode("P109"), schoolGraph.getNode("P110"), 6);
        schoolGraph.addEdge(schoolGraph.getNode("P111"), schoolGraph.getNode("P112"), 3);
        schoolGraph.addEdge(schoolGraph.getNode("P112"), schoolGraph.getNode("P113"), 4.5);
        schoolGraph.addEdge(schoolGraph.getNode("P113"), schoolGraph.getNode("P114"), 7);
        schoolGraph.addEdge(schoolGraph.getNode("P113"), schoolGraph.getNode("P120"), 8.5);
        schoolGraph.addEdge(schoolGraph.getNode("P114"), schoolGraph.getNode("P115"), 10);
        schoolGraph.addEdge(schoolGraph.getNode("P115"), schoolGraph.getNode("P116"), 8.5);
        schoolGraph.addEdge(schoolGraph.getNode("P116"), schoolGraph.getNode("P117"), 3.5);
        schoolGraph.addEdge(schoolGraph.getNode("P117"), schoolGraph.getNode("P118"), 5);
        schoolGraph.addEdge(schoolGraph.getNode("P118"), schoolGraph.getNode("P119"), 3.5);
        schoolGraph.addEdge(schoolGraph.getNode("P120"), schoolGraph.getNode("P121"), 8.5);
        schoolGraph.addEdge(schoolGraph.getNode("P121"), schoolGraph.getNode("P122"), 4);
        schoolGraph.addEdge(schoolGraph.getNode("P122"), schoolGraph.getNode("P123"), 8);
        schoolGraph.addEdge(schoolGraph.getNode("P123"), schoolGraph.getNode("P124"), 11.5);
        schoolGraph.addEdge(schoolGraph.getNode("P123"), schoolGraph.getNode("P127"), 8);
        schoolGraph.addEdge(schoolGraph.getNode("P124"), schoolGraph.getNode("P125"), 11);
        schoolGraph.addEdge(schoolGraph.getNode("P125"), schoolGraph.getNode("P126"), 7);
        schoolGraph.addEdge(schoolGraph.getNode("P127"), schoolGraph.getNode("P128"), 13);
        schoolGraph.addEdge(schoolGraph.getNode("P128"), schoolGraph.getNode("P130"), 3.5);
        schoolGraph.addEdge(schoolGraph.getNode("P128"), schoolGraph.getNode("P137"), 11.5);
        schoolGraph.addEdge(schoolGraph.getNode("P130"), schoolGraph.getNode("P131"), 5);
        schoolGraph.addEdge(schoolGraph.getNode("P131"), schoolGraph.getNode("P132"), 3.5);
        schoolGraph.addEdge(schoolGraph.getNode("P132"), schoolGraph.getNode("P133"), 9);
        schoolGraph.addEdge(schoolGraph.getNode("P133"), schoolGraph.getNode("P134"), 5);
        schoolGraph.addEdge(schoolGraph.getNode("P134"), schoolGraph.getNode("P135"), 2.5);
        schoolGraph.addEdge(schoolGraph.getNode("P136"), schoolGraph.getNode("P137"), 7.5);
        schoolGraph.addEdge(schoolGraph.getNode("P137"), schoolGraph.getNode("P138"), 9);
        schoolGraph.addEdge(schoolGraph.getNode("P138"), schoolGraph.getNode("P139"), 8.5);
        schoolGraph.addEdge(schoolGraph.getNode("P139"), schoolGraph.getNode("P140"), 3);
        schoolGraph.addEdge(schoolGraph.getNode("P139"), schoolGraph.getNode("P141"), 15);
        schoolGraph.addEdge(schoolGraph.getNode("P141"), schoolGraph.getNode("P142"), 5);
        
        // Second floor:
        
        schoolGraph.addEdge(schoolGraph.getNode("P200"), schoolGraph.getNode("P201"), 5);
        schoolGraph.addEdge(schoolGraph.getNode("P200"), schoolGraph.getNode("P247"), 3.5);
        schoolGraph.addEdge(schoolGraph.getNode("P201"), schoolGraph.getNode("P202"), 3.5);
        schoolGraph.addEdge(schoolGraph.getNode("P202"), schoolGraph.getNode("P203"), 8.5);
        schoolGraph.addEdge(schoolGraph.getNode("P203"), schoolGraph.getNode("P204"), 5);
        schoolGraph.addEdge(schoolGraph.getNode("P204"), schoolGraph.getNode("P205"), 3);
        schoolGraph.addEdge(schoolGraph.getNode("P205"), schoolGraph.getNode("P206"), 3.5);
        schoolGraph.addEdge(schoolGraph.getNode("P205"), schoolGraph.getNode("P208"), 8.5);
        schoolGraph.addEdge(schoolGraph.getNode("P206"), schoolGraph.getNode("P207"), 5);
        schoolGraph.addEdge(schoolGraph.getNode("P208"), schoolGraph.getNode("P209"), 11.5);
        schoolGraph.addEdge(schoolGraph.getNode("P209"), schoolGraph.getNode("P210"), 3);
        schoolGraph.addEdge(schoolGraph.getNode("P209"), schoolGraph.getNode("P215"), 7);
        schoolGraph.addEdge(schoolGraph.getNode("P210"), schoolGraph.getNode("P211"), 8.5);
        schoolGraph.addEdge(schoolGraph.getNode("P210"), schoolGraph.getNode("P214"), 7);
        schoolGraph.addEdge(schoolGraph.getNode("P211"), schoolGraph.getNode("P212"), 8);
        schoolGraph.addEdge(schoolGraph.getNode("P212"), schoolGraph.getNode("P213"), 8);
        schoolGraph.addEdge(schoolGraph.getNode("P214"), schoolGraph.getNode("P215"), 3);
        schoolGraph.addEdge(schoolGraph.getNode("P215"), schoolGraph.getNode("P216"), 4);
        schoolGraph.addEdge(schoolGraph.getNode("P216"), schoolGraph.getNode("P217"), 6.5);
        schoolGraph.addEdge(schoolGraph.getNode("P216"), schoolGraph.getNode("P223"), 8.5);
        schoolGraph.addEdge(schoolGraph.getNode("P217"), schoolGraph.getNode("P218"), 9.5);
        schoolGraph.addEdge(schoolGraph.getNode("P218"), schoolGraph.getNode("P219"), 8);
        schoolGraph.addEdge(schoolGraph.getNode("P219"), schoolGraph.getNode("P220"), 3.5);
        schoolGraph.addEdge(schoolGraph.getNode("P220"), schoolGraph.getNode("P221"), 5);
        schoolGraph.addEdge(schoolGraph.getNode("P221"), schoolGraph.getNode("P222"), 3.5);
        schoolGraph.addEdge(schoolGraph.getNode("P223"), schoolGraph.getNode("P225"), 9);
        schoolGraph.addEdge(schoolGraph.getNode("P225"), schoolGraph.getNode("P227"), 9);
        schoolGraph.addEdge(schoolGraph.getNode("P227"), schoolGraph.getNode("P228"), 8);
        schoolGraph.addEdge(schoolGraph.getNode("P228"), schoolGraph.getNode("P229"), 12);
        schoolGraph.addEdge(schoolGraph.getNode("P228"), schoolGraph.getNode("P232"), 7.5);
        schoolGraph.addEdge(schoolGraph.getNode("P229"), schoolGraph.getNode("P230"), 10.5);
        schoolGraph.addEdge(schoolGraph.getNode("P230"), schoolGraph.getNode("P231"), 6.5);
        schoolGraph.addEdge(schoolGraph.getNode("P232"), schoolGraph.getNode("P233"), 12);
        schoolGraph.addEdge(schoolGraph.getNode("P233"), schoolGraph.getNode("P234"), 11);
        schoolGraph.addEdge(schoolGraph.getNode("P233"), schoolGraph.getNode("P241"), 3);
        schoolGraph.addEdge(schoolGraph.getNode("P234"), schoolGraph.getNode("P235"), 9);
        schoolGraph.addEdge(schoolGraph.getNode("P234"), schoolGraph.getNode("P238"), 7);
        schoolGraph.addEdge(schoolGraph.getNode("P235"), schoolGraph.getNode("P236"), 8);
        schoolGraph.addEdge(schoolGraph.getNode("P235"), schoolGraph.getNode("P248"), 3);
        schoolGraph.addEdge(schoolGraph.getNode("P236"), schoolGraph.getNode("P237"), 3.5);
        schoolGraph.addEdge(schoolGraph.getNode("P237"), schoolGraph.getNode("P239"), 8.5);
        schoolGraph.addEdge(schoolGraph.getNode("P239"), schoolGraph.getNode("P240"), 9);
        schoolGraph.addEdge(schoolGraph.getNode("P241"), schoolGraph.getNode("P242"), 5);
        schoolGraph.addEdge(schoolGraph.getNode("P242"), schoolGraph.getNode("P243"), 8.5);
        schoolGraph.addEdge(schoolGraph.getNode("P243"), schoolGraph.getNode("P244"), 3);
        schoolGraph.addEdge(schoolGraph.getNode("P244"), schoolGraph.getNode("P246"), 8.5);
        
        // Third floor:
        
        schoolGraph.addEdge(schoolGraph.getNode("P300"), schoolGraph.getNode("P301"), 0.5);
        schoolGraph.addEdge(schoolGraph.getNode("P300"), schoolGraph.getNode("P302"), 5);
        schoolGraph.addEdge(schoolGraph.getNode("P302"), schoolGraph.getNode("P303"), 3.5);
        schoolGraph.addEdge(schoolGraph.getNode("P303"), schoolGraph.getNode("P304"), 5);
        schoolGraph.addEdge(schoolGraph.getNode("P304"), schoolGraph.getNode("P305"), 0.5);
        schoolGraph.addEdge(schoolGraph.getNode("P305"), schoolGraph.getNode("P306"), 4);
        schoolGraph.addEdge(schoolGraph.getNode("P306"), schoolGraph.getNode("P307"), 2);
        schoolGraph.addEdge(schoolGraph.getNode("P307"), schoolGraph.getNode("P308"), 4);
        schoolGraph.addEdge(schoolGraph.getNode("P308"), schoolGraph.getNode("P309"), 2);
        schoolGraph.addEdge(schoolGraph.getNode("P309"), schoolGraph.getNode("P310"), 2);
        schoolGraph.addEdge(schoolGraph.getNode("P310"), schoolGraph.getNode("P311"), 1.5);
        schoolGraph.addEdge(schoolGraph.getNode("P311"), schoolGraph.getNode("P312"), 4);
        schoolGraph.addEdge(schoolGraph.getNode("P312"), schoolGraph.getNode("P313"), 1);
        schoolGraph.addEdge(schoolGraph.getNode("P312"), schoolGraph.getNode("P314"), 3);
        
            // Waypoints to rooms:
        
        // Ground floor:
        
        schoolGraph.addEdge(schoolGraph.getNode("P007"), schoolGraph.getNode("Bibliothek"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P004"), schoolGraph.getNode("Foyer"), 13);
        schoolGraph.addEdge(schoolGraph.getNode("P007"), schoolGraph.getNode("Foyer"), 7);
        schoolGraph.addEdge(schoolGraph.getNode("P008"), schoolGraph.getNode("Foyer"), 15);
        schoolGraph.addEdge(schoolGraph.getNode("P026"), schoolGraph.getNode("Foyer"), 19);
        schoolGraph.addEdge(schoolGraph.getNode("P002"), schoolGraph.getNode("WC Herren"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P003"), schoolGraph.getNode("WC Damen"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P003"), schoolGraph.getNode("Mensa"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("T4_0"), schoolGraph.getNode("Saftladen"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P010"), schoolGraph.getNode("Raum 38"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P011"), schoolGraph.getNode("Raum 39"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P012"), schoolGraph.getNode("Raum 42"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P017"), schoolGraph.getNode("Werkraum 8"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P018"), schoolGraph.getNode("Werkraum 14"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P019"), schoolGraph.getNode("Werkraum 15"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P020"), schoolGraph.getNode("Werkraum 17"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P021"), schoolGraph.getNode("Werkraum 20"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P022"), schoolGraph.getNode("Werkraum 21"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P023"), schoolGraph.getNode("Werkraum 24"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P022"), schoolGraph.getNode("Werkraum 27"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P016"), schoolGraph.getNode("Werkraum 32"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("T4_0"), schoolGraph.getNode("Kunstraum 53"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P028"), schoolGraph.getNode("Raum 63"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P029"), schoolGraph.getNode("Raum 64"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P030"), schoolGraph.getNode("Raum 65"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P032"), schoolGraph.getNode("Raum 66"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P033"), schoolGraph.getNode("Raum 67"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P033"), schoolGraph.getNode("Raum 70"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P032"), schoolGraph.getNode("Raum 71"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P030"), schoolGraph.getNode("Raum 72"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P029"), schoolGraph.getNode("Raum 73"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P039"), schoolGraph.getNode("Raum 87"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P042"), schoolGraph.getNode("Raum 88"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P043"), schoolGraph.getNode("Raum 89"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P043"), schoolGraph.getNode("Raum 90"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P044"), schoolGraph.getNode("Raum 92"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P044"), schoolGraph.getNode("Raum 93"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P041"), schoolGraph.getNode("Raum 94"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P041"), schoolGraph.getNode("Raum 95"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P041"), schoolGraph.getNode("Raum 96"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P037"), schoolGraph.getNode("Raum 97"), 0.1);
        
        // First floor:
        
        schoolGraph.addEdge(schoolGraph.getNode("P110"), schoolGraph.getNode("Lehrerzimmer"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P110"), schoolGraph.getNode("Büro der Schulleitung/Sekretariat"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P109"), schoolGraph.getNode("Büro der stellv. Schulleitung"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P106"), schoolGraph.getNode("Raum 104"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P104"), schoolGraph.getNode("Raum 106"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P103"), schoolGraph.getNode("Raum 107"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P102"), schoolGraph.getNode("Raum 108"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P101"), schoolGraph.getNode("Raum 109"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P100"), schoolGraph.getNode("Raum 110"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P114"), schoolGraph.getNode("Raum 132"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P115"), schoolGraph.getNode("Raum 134"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P116"), schoolGraph.getNode("Raum 135"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P117"), schoolGraph.getNode("Raum 136"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P119"), schoolGraph.getNode("Raum 137"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P119"), schoolGraph.getNode("Raum 141"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P118"), schoolGraph.getNode("Raum 142"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P120"), schoolGraph.getNode("Raum 144"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P121"), schoolGraph.getNode("Raum 145"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P125"), schoolGraph.getNode("Raum 150"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P126"), schoolGraph.getNode("Raum 151"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P126"), schoolGraph.getNode("Raum 154"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P125"), schoolGraph.getNode("Raum 155"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P124"), schoolGraph.getNode("Raum 156"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P130"), schoolGraph.getNode("Raum 166"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P131"), schoolGraph.getNode("Raum 167"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P132"), schoolGraph.getNode("Raum 168"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P133"), schoolGraph.getNode("Raum 169"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P134"), schoolGraph.getNode("Raum 170"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P135"), schoolGraph.getNode("Raum 171"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P135"), schoolGraph.getNode("Raum 174"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P133"), schoolGraph.getNode("Raum 175"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P141"), schoolGraph.getNode("Raum 184"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P142"), schoolGraph.getNode("Raum 185"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P142"), schoolGraph.getNode("Raum 188"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P141"), schoolGraph.getNode("Raum 189"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P140"), schoolGraph.getNode("Raum 190"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P138"), schoolGraph.getNode("Raum 191"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P138"), schoolGraph.getNode("Raum 192/193"), 0.1);
        
        // Second floor:
        
        schoolGraph.addEdge(schoolGraph.getNode("P209"), schoolGraph.getNode("Raum 200"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P208"), schoolGraph.getNode("Raum 203/204"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P206"), schoolGraph.getNode("Raum 205"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P207"), schoolGraph.getNode("Raum 206"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P204"), schoolGraph.getNode("Raum 208"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P203"), schoolGraph.getNode("Raum 209"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P202"), schoolGraph.getNode("Raum 210"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P200"), schoolGraph.getNode("Raum 212"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P247"), schoolGraph.getNode("Raum 213"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P247"), schoolGraph.getNode("Raum 215"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P201"), schoolGraph.getNode("Raum 216"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P212"), schoolGraph.getNode("Raum 217"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P213"), schoolGraph.getNode("Raum 218"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P213"), schoolGraph.getNode("Raum 219"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P213"), schoolGraph.getNode("Raum 220"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P212"), schoolGraph.getNode("Raum 222"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P211"), schoolGraph.getNode("Raum 223"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P217"), schoolGraph.getNode("Raum 227"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P218"), schoolGraph.getNode("Raum 228"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P219"), schoolGraph.getNode("Raum 229"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P220"), schoolGraph.getNode("Raum 230"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P222"), schoolGraph.getNode("Raum 231"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P222"), schoolGraph.getNode("Raum 235"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P221"), schoolGraph.getNode("Raum 236"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P223"), schoolGraph.getNode("Raum 237"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P225"), schoolGraph.getNode("Raum 238"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P223"), schoolGraph.getNode("Raum 240"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P225"), schoolGraph.getNode("Raum 241"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P227"), schoolGraph.getNode("Raum 242"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P230"), schoolGraph.getNode("Raum 250"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P231"), schoolGraph.getNode("Raum 251"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P231"), schoolGraph.getNode("Raum 253"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P230"), schoolGraph.getNode("Raum 254"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P229"), schoolGraph.getNode("Raum 255"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P241"), schoolGraph.getNode("Raum 262"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P242"), schoolGraph.getNode("Raum 263"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P243"), schoolGraph.getNode("Raum 264"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P244"), schoolGraph.getNode("Raum 266"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P246"), schoolGraph.getNode("Raum 267"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P246"), schoolGraph.getNode("Raum 269"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P244"), schoolGraph.getNode("Raum 270"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P239"), schoolGraph.getNode("Raum 283"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P240"), schoolGraph.getNode("Raum 284"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P240"), schoolGraph.getNode("Raum 286"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P239"), schoolGraph.getNode("Raum 288"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P237"), schoolGraph.getNode("Raum 289"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P236"), schoolGraph.getNode("Raum 290"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P248"), schoolGraph.getNode("Raum 293"), 0.1);
        
        // Third floor:
        
        schoolGraph.addEdge(schoolGraph.getNode("P301"), schoolGraph.getNode("Raum 302"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P301"), schoolGraph.getNode("Raum 303"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P305"), schoolGraph.getNode("Raum 304"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P307"), schoolGraph.getNode("Raum 305"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P309"), schoolGraph.getNode("Raum 306"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P311"), schoolGraph.getNode("Raum 307"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P313"), schoolGraph.getNode("Raum 308"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P314"), schoolGraph.getNode("Raum 309"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P310"), schoolGraph.getNode("Raum 310"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P308"), schoolGraph.getNode("Raum 311"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P306"), schoolGraph.getNode("Raum 312"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P304"), schoolGraph.getNode("Raum 313"), 0.1);
        schoolGraph.addEdge(schoolGraph.getNode("P303"), schoolGraph.getNode("Raum 314"), 0.1);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel lDiff;
    private javax.swing.JLabel lFrom;
    private javax.swing.JLabel lTo;
    private javax.swing.JPanel pInputs;
    // End of variables declaration//GEN-END:variables
}
