package model;

public class Raza {
    private int razaId;
    private String razaDescripcion;
    private String razaEstado;
    private int espId;

    public Raza(int razaId, String razaDescripcion, String razaEstado, int espId) {
        this.razaId = razaId;
        this.razaDescripcion = razaDescripcion;
        this.razaEstado = razaEstado;
        this.espId = espId;
    }

    public Raza() {
        this.razaId = -1;
        this.razaDescripcion = "";
        this.razaEstado = "";
        this.espId = -1;
    }

    public int getRazaId() {
        return razaId;
    }

    public void setRazaId(int razaId) {
        this.razaId = razaId;
    }

    public String getRazaDescripcion() {
        return razaDescripcion;
    }

    public void setRazaDescripcion(String razaDescripcion) {
        this.razaDescripcion = razaDescripcion;
    }

    public String getRazaEstado() {
        return razaEstado;
    }

    public void setRazaEstado(String razaEstado) {
        this.razaEstado = razaEstado;
    }

    public int getEspId() {
        return espId;
    }

    public void setEspId(int espId) {
        this.espId = espId;
    }

    @Override
    public String toString() {
        return "Raza {" +
                "razaId=" + razaId +
                ", razaDescripcion='" + razaDescripcion + '\'' +
                ", razaEstado='" + razaEstado + '\'' +
                ", espId=" + espId +
                '}';
    }
}
