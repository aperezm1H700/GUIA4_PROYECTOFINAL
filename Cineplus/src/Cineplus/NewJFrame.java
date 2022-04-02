package Cineplus;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.table.DefaultTableModel;

public class NewJFrame extends javax.swing.JFrame {

    Conexion c;
    ArrayList ListaClasi;
    ArrayList ListaPeli;
    ArrayList ListaTipoBoleto;
    ArrayList ListaSalas;

    public NewJFrame() {
        initComponents();

        this.getContentPane().setBackground(Color.DARK_GRAY);
        this.setLocationRelativeTo(null);
        c = new Conexion();
        ListaClasi = new ArrayList();
        ListaPeli = new ArrayList();
        ListaTipoBoleto = new ArrayList();
        ListaSalas = new ArrayList();
        llenarClasificacion();
        llenarPelicula();
        llenarTipoBoleto();
        llenarSalas();
        mostrarDatos("");
        mostrarSalas("");
        mostrarBoletos("");
        
        try{
            Image img = ImageIO.read(new File("logo.png"));
            this.setIconImage(img);
        }catch(Exception e){
            System.out.println("MENSAJE: "+e);
        }

    }

    public void llenarClasificacion() {
        if (c.getConexion() != null) {
            cbxClasificacion.removeAllItems();
            ListaClasi = c.getListaClasificaciones();
            Iterator itera = ListaClasi.iterator();
            while (itera.hasNext()) {
                Clasificacion clasi = (Clasificacion) itera.next();
                cbxClasificacion.addItem(clasi);
            }
        } else {
            System.out.println("Incorrecto");
        }
    }

    public void llenarPelicula() {
        if (c.getConexion() != null) {
            cbxPelicula.removeAllItems();

            ListaPeli = c.getListaPeliculas();
            Iterator itera = ListaPeli.iterator();
            while (itera.hasNext()) {
                Pelicula peli = (Pelicula) itera.next();
                cbxPelicula.addItem(peli);

            }
        } else {
            System.out.println("Incorrecto");
        }
    }

    public void llenarTipoBoleto() {
        if (c.getConexion() != null) {
            cbxTipoBoleto.removeAllItems();
            ListaTipoBoleto = c.getListaTipoBoleto();
            Iterator itera = ListaTipoBoleto.iterator();
            while (itera.hasNext()) {
                TipoBoleto boleTipo = (TipoBoleto) itera.next();
                cbxTipoBoleto.addItem(boleTipo);
            }
        } else {
            System.out.println("Incorrecto");
        }
    }

    public void llenarSalas() {
        if (c.getConexion() != null) {
            cbxPeliculas.removeAllItems();
            ListaSalas = c.getListaSala();
            Iterator itera = ListaSalas.iterator();
            while (itera.hasNext()) {
                Sala sala = (Sala) itera.next();
                cbxPeliculas.addItem(sala);
            }
        } else {
            System.out.println("Incorrecto");
        }
    }

    public void mostrarDatos(String valor) {
        Conexion cc = new Conexion();
        Connection cn = cc.getConexion();
        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("Id");
        modelo.addColumn("Nombre");
        modelo.addColumn("Horario");
        modelo.addColumn("Duracion");
        modelo.addColumn("Idioma");
        modelo.addColumn("Clasificación");

        jtbPeliculas.setModel(modelo);
        String sql = "SELECT p.Id,p.NomPeli,p.HoraPeli,p.Duracion,p.Idioma,c.Clasificacion FROM Peliculas AS p INNER JOIN Clasificacion AS c WHERE p.Id_Clasificacion = c.Id;";

        String[] datos = new String[6];
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);

                modelo.addRow(datos);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }

    public void mostrarSalas(String valor) {
        Conexion cc = new Conexion();
        Connection cn = cc.getConexion();
        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("Id");
        modelo.addColumn("Numero");
        modelo.addColumn("Asientos");
        modelo.addColumn("Película");

        jtbSalas.setModel(modelo);
        String sql = "SELECT s.Id,s.NumSala,s.CanAsientos,p.NomPeli FROM Salas AS s INNER JOIN Peliculas AS p WHERE s.Id_Pelicula = p.Id;";

        String[] datos = new String[4];
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);

                modelo.addRow(datos);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }

    public void mostrarBoletos(String valor) {
        Conexion cc = new Conexion();
        Connection cn = cc.getConexion();
        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("Id");
        modelo.addColumn("Pelicula");
        modelo.addColumn("No. Sala");
        modelo.addColumn("No. Asiento");
        modelo.addColumn("Tipo Boleto");
        modelo.addColumn("Total $");

        jtbBoletos.setModel(modelo);
        String sql = "SELECT V.Id,V.NomPeli,V.NumSala,V.NumAsiento,T.TipoBoleto,V.TotalBoleto FROM VentaBoletos AS V INNER JOIN TipoBoletos AS T WHERE V.Id_TipoBoletos = T.Id ;";

        String[] datos = new String[6];
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);

                modelo.addRow(datos);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        IdSala = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        CanAsiento = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        cbxPelicula = new javax.swing.JComboBox();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtbSalas = new javax.swing.JTable();
        NumSala1 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
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
        jButton6 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbPeliculas = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        cbxPeliculas = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        cbxTipoBoleto = new javax.swing.JComboBox();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        totalBoleto = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        NumSala = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtbBoletos = new javax.swing.JTable();
        BoleAsiento = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);
        setSize(new java.awt.Dimension(720, 420));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Bahnschrift", 0, 55)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Cineplus");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 10, 220, -1));

        jTabbedPane2.setBackground(new java.awt.Color(204, 255, 255));
        jTabbedPane2.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel2.setText("Elimina o Actualiza a travez de ID");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, -1, -1));

        IdSala.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        IdSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IdSalaActionPerformed(evt);
            }
        });
        jPanel3.add(IdSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 180, 40));

        jLabel3.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel3.setText("Cantidad de Asientos");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        CanAsiento.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        CanAsiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CanAsientoActionPerformed(evt);
            }
        });
        jPanel3.add(CanAsiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 350, -1));

        jLabel4.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel4.setText("Película");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        jButton4.setBackground(new java.awt.Color(51, 51, 255));
        jButton4.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("REGISTRAR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 210, 170, 40));

        cbxPelicula.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxPelicula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxPeliculaActionPerformed(evt);
            }
        });
        jPanel3.add(cbxPelicula, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 350, 30));

        jButton9.setBackground(new java.awt.Color(255, 153, 0));
        jButton9.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("ACTUALIZAR");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 290, 180, 40));

        jButton10.setBackground(new java.awt.Color(204, 0, 51));
        jButton10.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("ELIMINAR");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 290, 170, 40));

        jtbSalas.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jtbSalas.setEnabled(false);
        jScrollPane2.setViewportView(jtbSalas);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, 310, 240));

        NumSala1.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jPanel3.add(NumSala1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 350, -1));

        jLabel10.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel10.setText("Número de la Sala");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(-50, -60, -1, 840));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/kisspng-soft-drink-popcorn-clip-art-popcorn-5a7dc9968e64f3.9366433215181930465833 (1).png"))); // NOI18N
        jLabel19.setText("jLabel19");
        jPanel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(-360, -120, -1, -1));

        jTabbedPane2.addTab("SALAS", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel5.setText("Nombre");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        NomPeli.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jPanel4.add(NomPeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 350, -1));

        jLabel6.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel6.setText("Horario");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        HoraPeli.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jPanel4.add(HoraPeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 350, -1));

        jLabel7.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel7.setText("Duración");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        DurPeli.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jPanel4.add(DurPeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 350, -1));

        jLabel8.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel8.setText("Idioma");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        IdioPeli.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jPanel4.add(IdioPeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 350, -1));

        jLabel9.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel9.setText("Clasificación");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        cbxClasificacion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel4.add(cbxClasificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 350, 30));

        jButton6.setBackground(new java.awt.Color(0, 51, 204));
        jButton6.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("REGISTRAR");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 170, 40));

        jtbPeliculas.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jtbPeliculas.setEnabled(false);
        jScrollPane1.setViewportView(jtbPeliculas);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, 310, 250));

        jButton7.setBackground(new java.awt.Color(204, 0, 51));
        jButton7.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("ELIMINAR");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 280, 170, 40));

        jButton8.setBackground(new java.awt.Color(255, 153, 0));
        jButton8.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("ACTUALIZAR");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 280, 180, 40));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/rollo-de-pelicula-27395.jpg"))); // NOI18N
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, -150, -1, -1));

        jTabbedPane2.addTab("PELíCULAS", jPanel4);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel11.setText("Película");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        cbxPeliculas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxPeliculas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxPeliculasActionPerformed(evt);
            }
        });
        jPanel1.add(cbxPeliculas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 350, 30));

        jLabel16.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel16.setText("Asiento");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        cbxTipoBoleto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxTipoBoleto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxTipoBoletoActionPerformed(evt);
            }
        });
        jPanel1.add(cbxTipoBoleto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 350, 30));

        jLabel17.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel17.setText("Tipo de Boleto");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        jLabel18.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        jLabel18.setText("$");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 10, -1));

        jButton11.setBackground(new java.awt.Color(0, 51, 204));
        jButton11.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setText("REGISTRAR");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 290, 170, 40));

        jLabel20.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel20.setText("Total Boleto");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));

        totalBoleto.setEditable(false);
        totalBoleto.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        totalBoleto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalBoletoActionPerformed(evt);
            }
        });
        jPanel1.add(totalBoleto, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 330, -1));

        jLabel21.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel21.setText("Sala");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        NumSala.setEditable(false);
        NumSala.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        NumSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumSalaActionPerformed(evt);
            }
        });
        jPanel1.add(NumSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 350, -1));

        jtbBoletos.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jtbBoletos.setEnabled(false);
        jScrollPane3.setViewportView(jtbBoletos);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 30, 320, 250));

        BoleAsiento.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        BoleAsiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoleAsientoActionPerformed(evt);
            }
        });
        jPanel1.add(BoleAsiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 350, -1));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/9d688693249316c5c046683f985a8db6.png"))); // NOI18N
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(-150, -50, -1, -1));

        jTabbedPane2.addTab("VENTA/BOLETOS", jPanel1);

        getContentPane().add(jTabbedPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 65, 730, 380));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Conexion cc = new Conexion();
        Connection cn = cc.getConexion();
        try {
            PreparedStatement pst = cn.prepareStatement("INSERT INTO Salas(NumSala,CanAsientos,Id_Pelicula"
                    + ") VALUES (?,?,?)");
            pst.setString(1, NumSala1.getText());
            pst.setInt(2, Integer.parseInt(CanAsiento.getText()));
            String c1 = String.valueOf(cbxPelicula.getSelectedItem());
            /*CONSULTA PARA OBTENER EL ID YA QUE SI QUIERO MOSTRAR EL NOMBRE DE LAS PELICULAS*/
            String sql = "SELECT Id FROM Peliculas WHERE NomPeli = '" + c1 + "'";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            String[] datos1 = new String[1];
            while (rs.next()) {
                datos1[0] = rs.getString(1);
            }
            int idPeli = Integer.parseInt(datos1[0]);
            /**/
            pst.setInt(3, idPeli);
            this.setVisible(true);
            int a = pst.executeUpdate();
            if (a > 0) {
                JOptionPane.showMessageDialog(null, "SALA REGISTRADA");
                mostrarDatos("");
                mostrarSalas("");
                mostrarBoletos("");
                llenarSalas();

            } else {
                JOptionPane.showMessageDialog(null, "SALA NO REGISTRADO");
            }

            NumSala1.setText("");
            CanAsiento.setText("");
            cbxPelicula.setSelectedIndex(0);
            IdSala.setText("");
            
        } catch (Exception e) {
            System.out.println("mensaje aqui" + e);
        }


    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        Conexion cc = new Conexion();
        Connection cn = cc.getConexion();
        try {
            PreparedStatement pst = cn.prepareStatement("INSERT INTO Peliculas(NomPeli,HoraPeli,Duracion,Idioma,Id_Clasificacion"
                    + ") VALUES (?,?,?,?,?)");
            pst.setString(1, NomPeli.getText());
            pst.setString(2, HoraPeli.getText());
            pst.setInt(3, Integer.parseInt(DurPeli.getText()));
            pst.setString(4, IdioPeli.getText());
            pst.setString(5, String.valueOf(cbxClasificacion.getSelectedIndex() + 1));

            int a = pst.executeUpdate();
            if (a > 0) {
                JOptionPane.showMessageDialog(null, "REGISTRADO");
                mostrarDatos("");
                mostrarSalas("");
                mostrarBoletos("");
                llenarPelicula();
                this.setVisible(false);
                this.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "NO REGISTRADO");
            }

            NomPeli.setText("");
            HoraPeli.setText("");
            DurPeli.setText("");
            IdioPeli.setText("");
            cbxClasificacion.setSelectedIndex(0);
        } catch (Exception e) {
            System.out.println("mensaje" + e);
        }


    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        this.setVisible(false);
        EliminarPelicula eliminar = new EliminarPelicula();
        eliminar.setVisible(true);

    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        this.setVisible(false);
        ActualizarPelicula pan = new ActualizarPelicula();
        pan.setVisible(true);

    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        Conexion cc = new Conexion();
        Connection cn = cc.getConexion();
        try {
            if ("".equals(IdSala.getText())) {
                JOptionPane.showMessageDialog(null, "Debes actualizar a travez del ID");
            } else {
                String c1 = String.valueOf(cbxPelicula.getSelectedItem());
                /*CONSULTA PARA OBTENER EL ID YA QUE SI QUIERO MOSTRAR EL NOMBRE DE LAS PELICULAS*/
                String sql = "SELECT Id FROM Peliculas WHERE NomPeli = '" + c1 + "'";
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                String[] datos1 = new String[1];
                while (rs.next()) {
                    datos1[0] = rs.getString(1);
                }
                int idPeli = Integer.parseInt(datos1[0]);
                String id = IdSala.getText();
                PreparedStatement pst = cn.prepareStatement("UPDATE Salas SET NumSala=" + Integer.parseInt(NumSala1.getText()) + ",CanAsientos=" + Integer.parseInt(CanAsiento.getText()) + ",Id_Pelicula=" + (idPeli) + " WHERE Id=" + Integer.parseInt(id));
                pst.executeUpdate();

                mostrarDatos("");
                mostrarSalas("");
                mostrarBoletos("");
                llenarSalas();
                JOptionPane.showMessageDialog(null, "Película Actualizada Correctamente");
            }

            NumSala1.setText("");
            CanAsiento.setText("");
            cbxPelicula.setSelectedIndex(0);
            IdSala.setText("");
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }


    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        Conexion cc = new Conexion();
        Connection cn = cc.getConexion();
        try {
            if ("".equals(IdSala.getText())) {
                JOptionPane.showMessageDialog(null, "Debes eliminar a travez del ID");
            } else {
                String id = IdSala.getText();
                PreparedStatement pst = cn.prepareStatement("DELETE FROM Salas WHERE Id=" + Integer.parseInt(id));
                pst.executeUpdate();

                mostrarDatos("");
                mostrarSalas("");
                mostrarBoletos("");
                llenarSalas();
                JOptionPane.showMessageDialog(null, "Sala Eliminada Correctamente");
            }

            NumSala1.setText("");
            CanAsiento.setText("");
            cbxPelicula.setSelectedIndex(0);

            IdSala.setText("");
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }


    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        Conexion cc = new Conexion();
        Connection cn = cc.getConexion();
        try {
            PreparedStatement pst = cn.prepareStatement("INSERT INTO VentaBoletos(NomPeli,NumSala,NumAsiento,Id_TipoBoletos,TotalBoleto"
                    + ") VALUES (?,?,?,?,?)");
            String NPeli = String.valueOf(cbxPeliculas.getSelectedItem());
            pst.setString(1, NPeli);
            pst.setInt(2, Integer.parseInt(NumSala.getText()));

            int NumBole = Integer.parseInt(BoleAsiento.getText());

//                JOptionPane.showMessageDialog(null,"EL NUMERO DE ASIENTO NO EXISTE");
//            }
            String consulSQL1 = "select NomPeli,NumAsiento FROM VentaBoletos WHERE NomPeli='" + NPeli + "' and NumAsiento = " + NumBole;
            Statement stm1 = cn.createStatement();
            ResultSet rst1 = stm1.executeQuery(consulSQL1);
            if (rst1.next() == false) {
                int nSala = Integer.parseInt(NumSala.getText());
                String consulSQLcan = "SELECT CanASientos FROM Salas WHERE NumSala =" + nSala;
                Statement stmN = cn.createStatement();
                ResultSet rstN = stmN.executeQuery(consulSQLcan);
                String[] datosN = new String[1];
                while (rstN.next()) {
                    datosN[0] = rstN.getString(1);
                }
                int canAsientosAct = Integer.parseInt(datosN[0]);
                if (NumBole <= canAsientosAct && NumBole >= 1) {
                    pst.setInt(3, NumBole);
                    /**
                     * INICIO**
                     */
                    String c2 = String.valueOf(cbxTipoBoleto.getSelectedItem());
                    String consulSQL = "SELECT Id FROM TipoBoletos WHERE TipoBoleto = '" + c2 + "'";
                    Statement stm = cn.createStatement();
                    ResultSet rst = stm.executeQuery(consulSQL);
                    String[] datos2 = new String[1];
                    while (rst.next()) {
                        datos2[0] = rst.getString(1);
                    }
                    int idTipoBoleto = Integer.parseInt(datos2[0]);
                    /**
                     * FIN
                     */
                    pst.setInt(4, idTipoBoleto);
                    pst.setInt(5, Integer.parseInt(totalBoleto.getText()));
                    int a = pst.executeUpdate();
                    /**/
                    String s1 = NumSala.getText();
                    String consulSQL2 = "SELECT Id FROM Salas WHERE NumSala = " + s1;
                    Statement stm2 = cn.createStatement();
                    ResultSet rst2 = stm2.executeQuery(consulSQL2);
                    String[] datos3 = new String[1];
                    while (rst2.next()) {
                        datos3[0] = rst2.getString(1);
                    }
                    int idSala = Integer.parseInt(datos3[0]);
                    /**/

                    if (a > 0) {
                        JOptionPane.showMessageDialog(null, "VENTA REGISTRADA");
                        PreparedStatement pstm = cn.prepareStatement("UPDATE Salas SET CanASientos= (CanAsientos-1) WHERE Id=" + idSala);
                        pstm.executeUpdate();
                        mostrarDatos("");
                        mostrarSalas("");
                        mostrarBoletos("");
                        cbxPeliculas.setSelectedIndex(0);
                        BoleAsiento.setText("");
                        cbxTipoBoleto.setSelectedIndex(0);
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "VENTA NO REGISTRADA");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "EL ASIENTO NO EXISTE");
                }
            }else{
                JOptionPane.showMessageDialog(null, "EL ASIENTO YA FUE VENDIDO");
            }

        } catch (Exception e) {
            System.out.println("mensaje aqui" + e);
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void cbxTipoBoletoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipoBoletoActionPerformed

        int tipo = cbxTipoBoleto.getSelectedIndex() + 1;
        switch (tipo) {
            case 1:
                totalBoleto.setText("45");
                break;
            case 2:
                totalBoleto.setText("35");
                break;
            case 3:
                totalBoleto.setText("25");
                break;
        }
    }//GEN-LAST:event_cbxTipoBoletoActionPerformed

    private void cbxPeliculasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxPeliculasActionPerformed
        
        Conexion cc = new Conexion();
        Connection cn = cc.getConexion();
        String nombrePeli = "";

        if (cbxPeliculas.getSelectedItem() != null) {
            nombrePeli = String.valueOf(cbxPeliculas.getSelectedItem());
            try {
                /**/
                String sql = "SELECT Id FROM Peliculas WHERE NomPeli = '" + nombrePeli + "'";
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                String[] datos1 = new String[1];
                while (rs.next()) {
                    datos1[0] = rs.getString(1);
                }

                int idPeli = Integer.parseInt(datos1[0]);
                /*--MOSTRAR EL ID DE LA SALA--*/
                String numSal = "SELECT NumSala FROM Salas WHERE Id_Pelicula = " + idPeli + ";";
                Statement st1 = cn.createStatement();
                ResultSet rs1 = st1.executeQuery(numSal);
                String[] datos2 = new String[1];
                while (rs1.next()) {
                    datos2[0] = rs1.getString(1);
                }
                NumSala.setText(datos2[0]);
                int numSala = Integer.parseInt(datos2[0]);

                /**/
                String NumAsientos = "SELECT CanAsientos FROM Salas WHERE NumSala = " + numSala;
                Statement st2 = cn.createStatement();
                ResultSet rs2 = st2.executeQuery(NumAsientos);
                String[] datos3 = new String[1];
                while (rs2.next()) {
                    datos3[0] = rs2.getString(1);
                }
                int asientos = Integer.parseInt(datos3[0]);

            } catch (SQLException ex) {
                Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }//GEN-LAST:event_cbxPeliculasActionPerformed

    private void IdSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IdSalaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IdSalaActionPerformed

    private void cbxPeliculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxPeliculaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxPeliculaActionPerformed

    private void CanAsientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CanAsientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CanAsientoActionPerformed

    private void NumSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NumSalaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NumSalaActionPerformed

    private void BoleAsientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoleAsientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BoleAsientoActionPerformed

    private void totalBoletoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalBoletoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalBoletoActionPerformed

    public static void main(String args[]) {
        Conexion c = new Conexion();
        if (c.getConexion() != null) {
            System.out.println("Correcto");
        } else {
            System.out.println("Incorrecto");
        }

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
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField BoleAsiento;
    private javax.swing.JTextField CanAsiento;
    private javax.swing.JTextField DurPeli;
    private javax.swing.JTextField HoraPeli;
    private javax.swing.JTextField IdSala;
    private javax.swing.JTextField IdioPeli;
    private javax.swing.JTextField NomPeli;
    private javax.swing.JTextField NumSala;
    private javax.swing.JTextField NumSala1;
    private javax.swing.JComboBox cbxClasificacion;
    private javax.swing.JComboBox cbxPelicula;
    private javax.swing.JComboBox cbxPeliculas;
    private javax.swing.JComboBox cbxTipoBoleto;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jtbBoletos;
    private javax.swing.JTable jtbPeliculas;
    private javax.swing.JTable jtbSalas;
    private javax.swing.JTextField totalBoleto;
    // End of variables declaration//GEN-END:variables

}
