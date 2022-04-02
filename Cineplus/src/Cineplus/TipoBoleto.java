package Cineplus;

public class TipoBoleto {
    private int id_TipoBoleto;
    private String tipo_TipoBoleto;

    public int getId_TipoBoleto() {
        return id_TipoBoleto;
    }

    public void setId_TipoBoleto(int id_TipoBoleto) {
        this.id_TipoBoleto = id_TipoBoleto;
    }

    public String getTipo_TipoBoleto() {
        return tipo_TipoBoleto;
    }

    public void setTipo_TipoBoleto(String tipo_TipoBoleto) {
        this.tipo_TipoBoleto = tipo_TipoBoleto;
    }

    public TipoBoleto() {
        id_TipoBoleto = 0;
        tipo_TipoBoleto = "";
    }
    
    @Override
    public String toString(){
        return getTipo_TipoBoleto();
    }
    
    
}
