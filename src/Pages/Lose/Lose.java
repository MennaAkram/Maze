/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Pages.Lose;

import Pages.ChooseLevel.Single.ChooseLevel;
import Pages.Home.HomePage;
import javax.sound.sampled.Clip;
import java.awt.event.WindowEvent;
import static Core.Utils.playMusic;

public class Lose extends javax.swing.JFrame {
    static Clip voice;

    public Lose() {
        initComponents();
        setLocationRelativeTo(null);
        if (voice == null) voice = playMusic("src/Assets/lose.wav", false);
        if (HomePage.voice != null) HomePage.voice.stop();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Restart = new javax.swing.JButton();
        Menu = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        Restart.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        Restart.setText("Restart");
        Restart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RestartActionPerformed();
            }
        });

        Menu.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        Menu.setText("Back to Menu");
        Menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuActionPerformed();
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon("src\\Assets\\QUC8.gif"));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(Restart, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(Menu, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Menu, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Restart, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RestartActionPerformed() {
        this.setVisible(true);
    }

    private void MenuActionPerformed() {
        this.dispose();
        new ChooseLevel().setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Lose.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Lose.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Lose.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Lose.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Lose().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Menu;
    private javax.swing.JButton Restart;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables

    public void processWindowEvent(final WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            voice.stop();
            new HomePage().setVisible(true);
            HomePage.voice.start();
        }
    }
}
