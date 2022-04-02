package Cineplus;

public class Sala {
    private int id_Sala;
    private int NumSala;
    private int CanAsientos;
    private int Id_Pelicula;
    private String Id_NomPeli;

    public String getId_NomPeli() {
        return Id_NomPeli;
    }

    public void setId_NomPeli(String Id_NomPeli) {
        this.Id_NomPeli = Id_NomPeli;
    }

    public int getId_Sala() {
        return id_Sala;
    }

    public void setId_Sala(int id_Sala) {
        this.id_Sala = id_Sala;
    }

    public int getNumSala() {
        return NumSala;
    }

    public void setNumSala(int NumSala) {
        this.NumSala = NumSala;
    }

    public int getCanAsientos() {
        return CanAsientos;
    }

    public void setCanAsientos(int CanAsientos) {
        this.CanAsientos = CanAsientos;
    }

    public int getId_Pelicula() {
        return Id_Pelicula;
    }

    public void setId_Pelicula(int Id_Pelicula) {
        this.Id_Pelicula = Id_Pelicula;
    }
    
    public Sala() {
        id_Sala = 0;
        NumSala = 0;
        CanAsientos = 0;
        Id_Pelicula = 0;
        Id_NomPeli = "";
    }
    
    @Override
    public String toString(){
        return getId_NomPeli();/*String.valueOf(getId_Sala());*/
    }
}
