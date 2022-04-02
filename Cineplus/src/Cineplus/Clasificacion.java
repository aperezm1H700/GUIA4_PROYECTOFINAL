package Cineplus;

public class Clasificacion {
    private int id_Clasificacion;
    private String tipo_clasificacion;

    public int getId_Clasificacion() {
        return id_Clasificacion;
    }

    public void setId_Clasificacion(int id_Clasificacion) {
        this.id_Clasificacion = id_Clasificacion;
    }

    public String getTipo_clasificacion() {
        return tipo_clasificacion;
    }

    public void setTipo_clasificacion(String tipo_clasificacion) {
        this.tipo_clasificacion = tipo_clasificacion;
    }

    public Clasificacion() {
        id_Clasificacion = 0;
        tipo_clasificacion = "";
    }
    
    @Override
    public String toString(){
        return getTipo_clasificacion();
    }
    
    
}
