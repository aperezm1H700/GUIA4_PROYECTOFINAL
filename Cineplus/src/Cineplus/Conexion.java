package Cineplus;

import java.sql.*;
import java.util.ArrayList;

public class Conexion {
    private static String bd="Cineplus";
    private static String user="root";
    private static String pass="Sonic2910@";
    private static String url="jdbc:mysql://localhost:3306/"+bd;
    Connection cn = null;
    
    public Connection getConexion(){
        //Connection cn = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection(url,user,pass);
        }catch(Exception e){
            System.out.println("Error Conexion"+e.getMessage());
        }
        return cn;
    }
    
    public ArrayList getListaClasificaciones(){
        ArrayList mListaClasi = new ArrayList();
        Clasificacion c = null;
        Statement consulta;
        ResultSet resultado;
        try{
            consulta = cn.createStatement();
            resultado = consulta.executeQuery("select * from Clasificacion");
            
            while(resultado.next()){
                c = new Clasificacion();
                c.setId_Clasificacion(resultado.getInt("Id"));
                c.setTipo_clasificacion(resultado.getString("Clasificacion"));
                mListaClasi.add(c);
            }
        
        }catch(SQLException e){
            
        }
        return mListaClasi;
    }
    
    public ArrayList getListaPeliculas(){
        ArrayList mListaPeli = new ArrayList();
        Pelicula p = null;
        Statement consulta;
        ResultSet resultado;
        try{
            consulta = cn.createStatement();
            resultado = consulta.executeQuery("select * from Peliculas");
            
            while(resultado.next()){
                p = new Pelicula();
                p.setId_Pelicula(resultado.getInt("Id"));
                p.setNombre_Peli(resultado.getString("NomPeli"));
                mListaPeli.add(p);
            }
        
        }catch(SQLException e){
            
        }
        return mListaPeli;
    }
    
    public ArrayList getListaTipoBoleto(){
        ArrayList mListaTipoBoleto = new ArrayList();
        TipoBoleto tb = null;
        Statement consulta;
        ResultSet resultado;
        try{
            consulta = cn.createStatement();
            resultado = consulta.executeQuery("SELECT * FROM TipoBoletos");
            
            while(resultado.next()){
                tb = new TipoBoleto();
                tb.setId_TipoBoleto(resultado.getInt("Id"));
                tb.setTipo_TipoBoleto(resultado.getString("TipoBoleto"));
                mListaTipoBoleto.add(tb);
            }
        
        }catch(SQLException e){
            
        }
        return mListaTipoBoleto;
    }
    
    public ArrayList getListaSala(){
        ArrayList mListaSala = new ArrayList();
        Sala s = null;
        Statement consulta;
        ResultSet resultado;
        try{
            consulta = cn.createStatement();
            resultado = consulta.executeQuery("SELECT s.Id,p.NomPeli,s.Id_Pelicula FROM Salas AS s INNER JOIN Peliculas AS p WHERE s.Id_Pelicula = p.Id;");
            while(resultado.next()){
                s = new Sala();
                s.setId_Sala(resultado.getInt("Id"));
                s.setId_NomPeli(resultado.getString("NomPeli"));
                s.setId_Pelicula(resultado.getInt("Id_Pelicula"));
                mListaSala.add(s);
            }
        
        }catch(SQLException e){
            
        }
        return mListaSala;
    }
}
