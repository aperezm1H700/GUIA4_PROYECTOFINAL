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
public class ActualizarPelicula extends javax.swing.JFrame {

    Conexion c1;
    ArrayList ListaClasi;
    
    public ActualizarPelicula() {
        initComponents();
        this.getContentPane().setBackground(Color.DARK_GRAY);
         this.setLocationRelativeTo(null);
         c1 = new Conexion();
         ListaClasi = new ArrayList();
         llenarClasificacion();
         mostrarDatos("");
    }
    
    public void llenarClasificacion(){
        if(c1.getConexion()!=null){
            cbxClasificacion.removeAllItems();
            ListaClasi = c1.getListaClasificaciones();
            Iterator itera = ListaClasi.iterator();
            while(itera.hasNext()){
                Clasificacion clasi = (Clasificacion) itera.next();
                cbxClasificacion.addItem(clasi);
            }
        }else{
            System.out.println("Incorrecto");
        }
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
        jLabel5 = new javax.swing.JLabel();
        NomPeli = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        HoraPeli = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        DurPeli = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        IdioPeli = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cbxClasificacion = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbPeliculas = new javax.swing.JTable();
        jButton8 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(710, 490));
        setPreferredSize(new java.awt.Dimension(715, 490));
        setResizable(false);
        setSize(new java.awt.Dimension(777, 508));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Id");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 20, -1));

        IdPeli.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        getContentPane().add(IdPeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 350, -1));

        jLabel5.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Nombre");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        NomPeli.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        getContentPane().add(NomPeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 350, -1));

        jLabel6.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Horario");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        HoraPeli.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        getContentPane().add(HoraPeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 350, -1));

        jLabel7.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Duración");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        DurPeli.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        getContentPane().add(DurPeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 350, -1));

        jLabel8.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Idioma");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, -1));

        IdioPeli.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        getContentPane().add(IdioPeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 350, -1));

        jLabel9.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Clasificación");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, -1));

        cbxClasificacion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(cbxClasificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 350, 30));

        jtbPeliculas.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jScrollPane1.setViewportView(jtbPeliculas);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 70, 310, 280));

        jButton8.setBackground(new java.awt.Color(255, 153, 0));
        jButton8.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("ACTUALIZAR PELÍCULA");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 380, 320, 40));

        jLabel1.setFont(new java.awt.Font("Bahnschrift", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Actualizar Película");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 320, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        Conexion cc = new Conexion();
        Connection cn=cc.getConexion();
        try{
            if("".equals(IdPeli.getText())){
                JOptionPane.showMessageDialog(null,"Debes actualizar a travez del ID");
            }else{
                String id = IdPeli.getText();
                PreparedStatement pst=cn.prepareStatement("UPDATE Peliculas SET NomPeli='"+NomPeli.getText()+"',HoraPeli='"+HoraPeli.getText()+"',Duracion="+Integer.parseInt(DurPeli.getText())+",Idioma='"+IdioPeli.getText()+"',Id_Clasificacion="+(cbxClasificacion.getSelectedIndex()+1)+" WHERE Id="+Integer.parseInt(id));
                pst.executeUpdate();
            
                mostrarDatos("");                
                JOptionPane.showMessageDialog(null,"Película Actualizada Correctamente");
                NewJFrame frame = new NewJFrame();
                frame.setVisible(true);
                this.setVisible(false);
            }
        }catch(SQLException e){
            System.out.println("Error "+e);
        }
        
        
    }//GEN-LAST:event_jButton8ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
       
        
    }//GEN-LAST:event_formWindowClosed

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
            java.util.logging.Logger.getLogger(ActualizarPelicula.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ActualizarPelicula.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ActualizarPelicula.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ActualizarPelicula.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ActualizarPelicula().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField DurPeli;
    private javax.swing.JTextField HoraPeli;
    private javax.swing.JTextField IdPeli;
    private javax.swing.JTextField IdioPeli;
    private javax.swing.JTextField NomPeli;
    private javax.swing.JComboBox cbxClasificacion;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtbPeliculas;
    // End of variables declaration//GEN-END:variables
}
