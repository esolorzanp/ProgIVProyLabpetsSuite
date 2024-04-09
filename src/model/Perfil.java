package model;

public class Perfil {
    private int perId;
    private String perDescripcion;
    private String perEstado;

    public Perfil(int perId, String perDescripcion, String perEstado) {
        this.perId = perId;
        this.perDescripcion = perDescripcion;
        this.perEstado = perEstado;
    }

    public Perfil() {
        this.perId = -1;
        this.perDescripcion = "";
        this.perEstado = "";
    }

    public int getPerId() {
        return perId;
    }

    public void setPerId(int perId) {
        this.perId = perId;
    }

    public String getPerDescripcion() {
        return perDescripcion;
    }

    public void setPerDescripcion(String perDescripcion) {
        this.perDescripcion = perDescripcion;
    }

    public String getPerEstado() {
        return perEstado;
    }

    public void setPerEstado(String perEstado) {
        this.perEstado = perEstado;
    }

    @Override
    public String toString() {
        return "Perfil {" +
                "perId=" + perId +
                ", perDescripcion='" + perDescripcion + '\'' +
                ", perEstado='" + perEstado + '\'' +
                '}';
    }
}
