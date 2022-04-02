package Cineplus;

public class Pelicula {
    private int id_Pelicula;
    private String nombre_Peli;
    private String hora_Peli;
    private int duracion;
    private String idioma;

    public int getId_Pelicula() {
        return id_Pelicula;
    }

    public void setId_Pelicula(int id_Pelicula) {
        this.id_Pelicula = id_Pelicula;
    }

    public String getNombre_Peli() {
        return nombre_Peli;
    }

    public void setNombre_Peli(String nombre_Peli) {
        this.nombre_Peli = nombre_Peli;
    }

    public String getHora_Peli() {
        return hora_Peli;
    }

    public void setHora_Peli(String hora_Peli) {
        this.hora_Peli = hora_Peli;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    

    public Pelicula() {
        id_Pelicula = 0;
        nombre_Peli = "";
        hora_Peli = "";
        duracion = 0;
        idioma = "";
    }
    
    @Override
    public String toString(){
        //return String.valueOf(getId_Pelicula()/*+". "+getNombre_Peli()*/);
        return getNombre_Peli();
    }
    
    
}
