package model;

public class OrdenServicioDetalle {
    private int dosId;
    private int dosOsId;
    private int dosExaId;
    private int dosExaValor;
    private String dosExaCantidad;
    private String dosExaObservaciones;

    public OrdenServicioDetalle(int dosId, int dosOsId, int dosExaId, int dosExaValor, String dosExaCantidad, String dosExaObservaciones) {
        this.dosId = dosId;
        this.dosOsId = dosOsId;
        this.dosExaId = dosExaId;
        this.dosExaValor = dosExaValor;
        this.dosExaCantidad = dosExaCantidad;
        this.dosExaObservaciones = dosExaObservaciones;
    }

    public OrdenServicioDetalle() {
        this.dosId = -1;
        this.dosOsId = -1;
        this.dosExaId = -1;
        this.dosExaValor = 0;
        this.dosExaCantidad = "";
        this.dosExaObservaciones = "";
    }

    public int getDosId() {
        return dosId;
    }

    public void setDosId(int dosId) {
        this.dosId = dosId;
    }

    public int getDosOsId() {
        return dosOsId;
    }

    public void setDosOsId(int dosOsId) {
        this.dosOsId = dosOsId;
    }

    public int getDosExaId() {
        return dosExaId;
    }

    public void setDosExaId(int dosExaId) {
        this.dosExaId = dosExaId;
    }

    public int getDosExaValor() {
        return dosExaValor;
    }

    public void setDosExaValor(int dosExaValor) {
        this.dosExaValor = dosExaValor;
    }

    public String getDosExaCantidad() {
        return dosExaCantidad;
    }

    public void setDosExaCantidad(String dosExaCantidad) {
        this.dosExaCantidad = dosExaCantidad;
    }

    public String getDosExaObservaciones() {
        return dosExaObservaciones;
    }

    public void setDosExaObservaciones(String dosExaObservaciones) {
        this.dosExaObservaciones = dosExaObservaciones;
    }

    @Override
    public String toString() {
        return "OrdenServicioDetalle{" +
                "dosId=" + dosId +
                ", dosOsId=" + dosOsId +
                ", dosExaId=" + dosExaId +
                ", dosExaValor=" + dosExaValor +
                ", dosExaCantidad='" + dosExaCantidad + '\'' +
                ", dosExaObservaciones='" + dosExaObservaciones + '\'' +
                '}';
    }
}
