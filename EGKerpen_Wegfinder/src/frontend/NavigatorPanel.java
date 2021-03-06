/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author Dominik Onyszkiewicz
 */
public class NavigatorPanel extends GroundPlanPanel {

    private GroundPlanPanel gpp;
    
    /**
     *
     * @param parent
     */
    public NavigatorPanel(Container parent, GroundPlanPanel gpp) {
        super(parent, 0);
        initComponents();
        this.gpp = gpp;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        highlightPos = new Point((int) (evt.getX() * (1/getScale())), (int) (evt.getY() * (1/getScale())));
        gpp.setHighlightPosition(highlightPos);
        repaint();
        gpp.repaint();
    }//GEN-LAST:event_formMouseDragged

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        highlightPos = null;
        gpp.setHighlightPosition(highlightPos);
        repaint();
        gpp.repaint();
    }//GEN-LAST:event_formMouseReleased

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        highlightPos = new Point((int) (evt.getX() * (1/getScale())), (int) (evt.getY() * (1/getScale())));
        gpp.setHighlightPosition(highlightPos);
        repaint();
        gpp.repaint();
    }//GEN-LAST:event_formMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
