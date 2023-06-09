/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sample.DBConnection;

/**
 *
 * @author midha
 */
public class mainFrame extends javax.swing.JFrame {

    /**
     * Creates new form mainFrame
     */
    static Connection conn;
    static String sql;
    String valNim, valNama, valJenjang, valGender, valAlamat, valNoHp;
    PreparedStatement ps;
    static Statement st;
    ResultSet rs;

    public mainFrame() {
        conn = new DBConnection().setConnection();
        initComponents();
        showData();
    }

    public void clearTxt() {
        inNim.setText("");
        inNama.setText("");
        inAlamat.setText("");
        inNoHp.setText("");
        inJenjang.setSelectedItem(" ");
        inGender.setSelectedItem(" ");
    }

    public void insertData() {
        valNim = inNim.getText();
        valNama = inNama.getText();
        valJenjang = inJenjang.getSelectedItem().toString();
        valGender = inGender.getSelectedItem().toString();
        valAlamat = inAlamat.getText();
        valNoHp = inNoHp.getText();

        try {
            sql = "INSERT INTO tbl_mhs VALUES (?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, valNim);
            ps.setString(2, valNama);
            ps.setString(3, valJenjang);
            ps.setString(4, valGender);
            ps.setString(5, valAlamat);
            ps.setString(6, valNoHp);
            ps.executeUpdate();
            showData();
            clearTxt();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan!", "Informasi", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            System.out.println("Error Insert : " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error Insert " + e.getMessage(), "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public void showData() {
        try {
            String query = "SELECT * FROM tbl_mhs";
            st = conn.createStatement();
            rs = st.executeQuery(query);
            DefaultTableModel tbl = (DefaultTableModel) tblShow.getModel();
            tbl.setRowCount(0);
            while (rs.next()) {
                Object o[] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)};
                tbl.addRow(o);
            }
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    public void cariData() {
        try {
            String search = inNimSearch.getText();
            String query = "SELECT * FROM tbl_mhs WHERE nim LIKE '%" + search + "%';";
            st = conn.createStatement();
            rs = st.executeQuery(query);
            DefaultTableModel tbl = (DefaultTableModel) tblShow.getModel();
            tbl.setRowCount(0);
            while (rs.next()) {
                Object o[] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)};
                tbl.addRow(o);
            }
            inNimSearch.setText("");
            JOptionPane.showMessageDialog(null, tbl.getRowCount() + " data ditemukan", "Informasi", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    public void editData() {
        valNim = inNim.getText();
        valNama = inNama.getText();
        valJenjang = inJenjang.getSelectedItem().toString();
        valGender = inGender.getSelectedItem().toString();
        valAlamat = inAlamat.getText();
        valNoHp = inNoHp.getText();
        /*
        try {
            sql = "UPDATE tbl_mhs SET nama = '" + valNama + "',Jenjang = '" + valJenjang + "', gender = '" + valGender + "', alamat = '" + valAlamat + "', nohp = '" + valNoHp + "' WHERE nim = '" + valNim + "';";
            ps = conn.prepareStatement(sql);
            ps.executeUpdate(sql);
            showData();
            JOptionPane.showMessageDialog(null, "Data Berhasil diedit!", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            System.out.println(sql);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error Edit " + e.getMessage(), "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }
         */
        if (valNama.isEmpty() || valAlamat.isEmpty() || valNoHp.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan", "Informasi", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                sql = "UPDATE tbl_mhs SET nama=?, Jenjang=?, gender=?, alamat=?, nohp=? WHERE nim=?;";
                ps = conn.prepareStatement(sql);

                ps.setString(1, valNama);
                ps.setString(2, valJenjang);
                ps.setString(3, valGender);
                ps.setString(4, valAlamat);
                ps.setString(5, valNoHp);
                ps.setString(6, valNim);
                ps.executeUpdate();
                showData();
                clearTxt();
                JOptionPane.showMessageDialog(null, "Data berhasil diupdate!", "Informasi", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception e) {
                System.out.println("Error Insert : " + e.getMessage());
                JOptionPane.showMessageDialog(null, "Error Edit " + e.getMessage(), "Informasi", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public void hapusData() {
        valNim = inNim.getText();
        int jawab = JOptionPane.showOptionDialog(this,
                "Hapus Data?",
                "Hapus Data",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, null);

        if (jawab == JOptionPane.YES_OPTION) {
            try {
                sql = "DELETE FROM tbl_mhs WHERE nim=?;";
                ps = conn.prepareStatement(sql);

                ps.setString(1, valNim);
                ps.executeUpdate();
                showData();
                clearTxt();
                JOptionPane.showMessageDialog(null, "Data berhasil hapus data!", "Informasi", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception e) {
                System.out.println("Error Insert : " + e.getMessage());
                JOptionPane.showMessageDialog(null, "Error hapus data " + e.getMessage(), "Informasi", JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }

    public void getData() {
        try {
            String search = inNim.getText();
            String query = "SELECT * FROM tbl_mhs WHERE nim='" + search + "';";
            st = conn.createStatement();
            rs = st.executeQuery(query);
            // DefaultTableModel tbl = (DefaultTableModel) tblShow.getModel();
            //tbl.setRowCount(0);

            if (rs.next()) {
                //Object o[] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)};
                //tbl.addRow(o);
                inNama.setText(rs.getString(2));
                inJenjang.setSelectedItem(rs.getString(3));
                inGender.setSelectedItem(rs.getString(4));
                inAlamat.setText(rs.getString(5));
                inNoHp.setText(rs.getString(6));
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Data tidak ditemukan!", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            }
            //JOptionPane.showMessageDialog(null, tbl.getRowCount() + " data ditemukan", "Informasi", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        inNama = new javax.swing.JTextField();
        inNim = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        inAlamat = new javax.swing.JTextField();
        inNoHp = new javax.swing.JTextField();
        btnSubmit = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblShow = new javax.swing.JTable();
        inJenjang = new javax.swing.JComboBox<>();
        inGender = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        btnHapus = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnGetData = new javax.swing.JButton();
        inNimSearch = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        btnShow = new javax.swing.JButton();
        Clear = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("MAHASISWA DATA");

        jLabel2.setText("NAMA");

        jLabel3.setText("NIM");

        jLabel4.setText("ALAMAT");

        jLabel5.setText("NO HP");

        jLabel6.setText("GENDER");

        btnSubmit.setText("SUBMIT");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        tblShow.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "NIM", "NAMA", "JENJANG", "GENDER", "ALAMAT", "NO HP"
            }
        ));
        jScrollPane1.setViewportView(tblShow);

        inJenjang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "S1", "S2", "S3" }));

        inGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Laki - Laki", "Perempuan" }));

        jLabel7.setText("JENJANG");

        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnGetData.setText("Get Data");
        btnGetData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetDataActionPerformed(evt);
            }
        });

        btnCari.setText("Cari");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        jLabel8.setText("NIM");

        btnShow.setText("Tampilkan Semua Data");
        btnShow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowActionPerformed(evt);
            }
        });

        Clear.setText("Clear");
        Clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearActionPerformed(evt);
            }
        });

        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(162, 162, 162)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLogout))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(15, 15, 15)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel5))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(inNim, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                                            .addComponent(inAlamat, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                                            .addComponent(inNoHp, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                                            .addComponent(inJenjang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(inNama)
                                            .addComponent(inGender, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addContainerGap(23, Short.MAX_VALUE)
                                        .addComponent(btnGetData)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnHapus)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnEdit)
                                        .addGap(10, 10, 10)
                                        .addComponent(btnSubmit)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(Clear)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnShow)
                                .addGap(323, 323, 323)
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(inNimSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCari))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 706, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addComponent(jLabel3))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(btnLogout))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(inNim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(inNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inJenjang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(inGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(inAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inNoHp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSubmit)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnHapus)
                                .addComponent(btnEdit)
                                .addComponent(btnGetData))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCari)
                        .addComponent(inNimSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnShow)
                        .addComponent(Clear)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        // TODO add your handling code here:
        insertData();
        //showData();
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void btnGetDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetDataActionPerformed
        // TODO add your handling code here:
        getData();
    }//GEN-LAST:event_btnGetDataActionPerformed

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        // TODO add your handling code here:
        cariData();
    }//GEN-LAST:event_btnCariActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        editData();

    }//GEN-LAST:event_btnEditActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        hapusData();
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowActionPerformed
        // TODO add your handling code here:
        showData();
    }//GEN-LAST:event_btnShowActionPerformed

    private void ClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearActionPerformed
        // TODO add your handling code here:
        clearTxt();
    }//GEN-LAST:event_ClearActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        LoginPage lg = new LoginPage();
        lg.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnLogoutActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" descinNim feel setting code (optional) ">
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
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Clear;
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnGetData;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnShow;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JTextField inAlamat;
    private javax.swing.JComboBox<String> inGender;
    private javax.swing.JComboBox<String> inJenjang;
    private javax.swing.JTextField inNama;
    private javax.swing.JTextField inNim;
    private javax.swing.JTextField inNimSearch;
    private javax.swing.JTextField inNoHp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblShow;
    // End of variables declaration//GEN-END:variables
}
