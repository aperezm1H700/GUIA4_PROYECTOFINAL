/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cineplus;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ALFONSO
 */
public class EliminarPelicula extends javax.swing.JFrame {

    Conexion c1;
    ArrayList ListaClasi;
    
    public EliminarPelicula() {
        initComponents();
        this.getContentPane().setBackground(Color.DARK_GRAY);
         this.setLocationRelativeTo(null);
         c1 = new Conexion();
         ListaClasi = new ArrayList();
         mostrarDatos("");
    }
    

    public void mostrarDatos(String valor){
        Conexion cc = new Conexion();
        Connection cn=cc.getConexion();
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("Id");
        modelo.addColumn("Nombre");
        modelo.addColumn("Horario");
        modelo.addColumn("Duracion");
        modelo.addColumn("Idioma");
        modelo.addColumn("Clasificación");
        
        jtbPeliculas.setModel(modelo);
        String sql = "SELECT p.Id,p.NomPeli,p.HoraPeli,p.Duracion,p.Idioma,c.Clasificacion FROM Peliculas AS p INNER JOIN Clasificacion AS c WHERE p.Id_Clasificacion = c.Id;";
        
        String []datos = new String[6];
        try{
            Statement st=cn.createStatement();
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                datos[3]=rs.getString(4);
                datos[4]=rs.getString(5);
                datos[5]=rs.getString(6);
                
                modelo.addRow(datos);
            }
        }catch(SQLException e){
            System.out.println("Error: "+e);
        }
        
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel10 = new javax.swing.JLabel();
        IdPeli = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbPeliculas = new javax.swing.JTable();
        jButton8 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(390, 490));
        setPreferredSize(new java.awt.Dimension(390, 490));
        setResizable(false);
        setSize(new java.awt.Dimension(390, 490));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Id");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 20, -1));

        IdPeli.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        getContentPane().add(IdPeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 320, -1));

        jtbPeliculas.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jScrollPane1.setViewportView(jtbPeliculas);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 350, 280));

        jButton8.setBackground(new java.awt.Color(204, 0, 0));
        jButton8.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("ELIMINAR PELÍCULA");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, 320, 40));

        jLabel1.setFont(new java.awt.Font("Bahnschrift", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Eliminar Película");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 280, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        Conexion cc = new Conexion();
        Connection cn=cc.getConexion();
        try{
            if("".equals(IdPeli.getText())){
                JOptionPane.showMessageDialog(null,"Debes eliminar a travez del ID");
            }else{
                String id = IdPeli.getText();
                PreparedStatement pst=cn.prepareStatement("DELETE FROM Peliculas WHERE Id="+Integer.parseInt(id));
                pst.executeUpdate();
            
                mostrarDatos("");
                JOptionPane.showMessageDialog(null,"Película Eliminada Correctamente");
                NewJFrame frame = new NewJFrame();
                frame.setVisible(true);
                this.setVisible(false);
            }
        }catch(SQLException e){
            System.out.println("Error "+e);
            JOptionPane.showMessageDialog(null, "NO SE PUEDE ELIMINAR YA QUE ESTA REGISTRADA EN UNA SALA");
        }
        
        
    }//GEN-LAST:event_jButton8ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        NewJFrame frame = new NewJFrame();
        frame.setVisible(true);
        
        
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(EliminarPelicula.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EliminarPelicula.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EliminarPelicula.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EliminarPelicula.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EliminarPelicula().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField IdPeli;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtbPeliculas;
    // End of variables declaration//GEN-END:variables
}
