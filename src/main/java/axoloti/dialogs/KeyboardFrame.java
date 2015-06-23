/**
 * Copyright (C) 2013, 2014 Johannes Taelman
 *
 * This file is part of Axoloti.
 *
 * Axoloti is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * Axoloti is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Axoloti. If not, see <http://www.gnu.org/licenses/>.
 */
package axoloti.dialogs;

import axoloti.Connection;
import axoloti.MainFrame;
import axoloti.SerialConnection;
import components.PianoComponent;
import components.control.ACtrlEvent;
import components.control.ACtrlListener;
import components.control.DialComponent;
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SpinnerNumberModel;
import jssc.SerialPortException;

/**
 *
 * @author Johannes Taelman
 */
public class KeyboardFrame extends javax.swing.JFrame {

    /**
     * Creates new form PianoFrame
     */
    PianoComponent piano;

    DialComponent pbenddial;

    public KeyboardFrame() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/resources/axoloti_icon.png")).getImage());
        piano = new PianoComponent() {
            @Override
            public void KeyDown(int key) {
                Connection connection = MainFrame.mainframe.getQcmdprocessor().serialconnection;
                if ((connection != null) && connection.isConnected()) {
                    connection.SendMidi(0x90 + ((SpinnerNumberModel) jSpinner1.getModel()).getNumber().intValue() - 1, key & 0x7F, jSliderVelocity.getValue());
                }
            }

            @Override
            public void KeyUp(int key) {
                Connection connection = MainFrame.mainframe.getQcmdprocessor().serialconnection;
                if ((connection != null) && connection.isConnected()) {
                    connection.SendMidi(0x80 + ((SpinnerNumberModel) jSpinner1.getModel()).getNumber().intValue() - 1, key & 0x7F, 80);
                }
            }

        };
        Dimension d = new Dimension(910, 64);
        piano.setMinimumSize(d);
        piano.setSize(d);
        piano.setPreferredSize(d);
        piano.setMaximumSize(d);
        piano.setVisible(true);
        jPanelKeyb.add(piano);
        pbenddial = new DialComponent(0.0, -64, 63, 1);
        pbenddial.addACtrlListener(new ACtrlListener() {
            @Override
            public void ACtrlAdjusted(ACtrlEvent e) {
                Connection connection = MainFrame.mainframe.getQcmdprocessor().serialconnection;
                if ((connection != null) && connection.isConnected()) {
                    connection.SendMidi(0xE0 + ((SpinnerNumberModel) jSpinner1.getModel()).getNumber().intValue() - 1, 0, 0x07F & (int) (pbenddial.getValue() - 64.0));
                }
            }
        });
        jPanel1.add(new JLabel("bend"));
        jPanel1.add(pbenddial);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelKeyb = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0));
        jSpinner1 = new javax.swing.JSpinner();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0));
        jLabel2 = new javax.swing.JLabel();
        jSliderVelocity = new javax.swing.JSlider();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0));
        jButtonAllNotesOff = new javax.swing.JButton();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0), new java.awt.Dimension(32767, 0));

        setMaximumSize(null);
        setMinimumSize(new java.awt.Dimension(200, 60));
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.TOOLKIT_EXCLUDE);
        setName("Keyboard"); // NOI18N
        setPreferredSize(new java.awt.Dimension(917, 160));
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.Y_AXIS));

        jPanelKeyb.setAlignmentX(0.0F);
        jPanelKeyb.setAlignmentY(0.0F);
        jPanelKeyb.setMaximumSize(new java.awt.Dimension(905, 64));
        jPanelKeyb.setMinimumSize(new java.awt.Dimension(905, 64));
        jPanelKeyb.setPreferredSize(new java.awt.Dimension(905, 64));
        jPanelKeyb.setLayout(new javax.swing.BoxLayout(jPanelKeyb, javax.swing.BoxLayout.LINE_AXIS));
        getContentPane().add(jPanelKeyb);

        jPanel1.setAlignmentX(0.0F);
        jPanel1.setAlignmentY(0.0F);
        jPanel1.setMinimumSize(new java.awt.Dimension(500, 51));
        jPanel1.setPreferredSize(new java.awt.Dimension(500, 51));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        jLabel1.setText("Midi Channel");
        jPanel1.add(jLabel1);
        jPanel1.add(filler1);

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(1, 1, 16, 1));
        jSpinner1.setMaximumSize(null);
        jSpinner1.setMinimumSize(null);
        jSpinner1.setPreferredSize(new java.awt.Dimension(0, 25));
        jPanel1.add(jSpinner1);
        jPanel1.add(filler2);

        jLabel2.setText("Velocity");
        jPanel1.add(jLabel2);

        jSliderVelocity.setMajorTickSpacing(32);
        jSliderVelocity.setMaximum(127);
        jSliderVelocity.setMinimum(1);
        jSliderVelocity.setPaintTicks(true);
        jSliderVelocity.setValue(80);
        jSliderVelocity.setMinimumSize(new java.awt.Dimension(64, 31));
        jPanel1.add(jSliderVelocity);
        jPanel1.add(filler4);

        jButtonAllNotesOff.setText("All Notes Off");
        jButtonAllNotesOff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAllNotesOffActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonAllNotesOff);
        jPanel1.add(filler3);

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAllNotesOffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAllNotesOffActionPerformed
        Connection connection = MainFrame.mainframe.getQcmdprocessor().serialconnection;
        if ((connection != null) && connection.isConnected()) {
            connection.SendMidi(0xB0 + ((SpinnerNumberModel) jSpinner1.getModel()).getNumber().intValue() - 1, 0x7B, 80);
        }
    }//GEN-LAST:event_jButtonAllNotesOffActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.JButton jButtonAllNotesOff;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelKeyb;
    private javax.swing.JSlider jSliderVelocity;
    private javax.swing.JSpinner jSpinner1;
    // End of variables declaration//GEN-END:variables
}
