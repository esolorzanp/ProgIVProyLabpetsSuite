package model;

public class FormaPago {
    private int fpId;
    private String fpDescripcion;
    private String fpEstado;

    public FormaPago(int fpId, String fpDescripcion, String fpEstado) {
        this.fpId = fpId;
        this.fpDescripcion = fpDescripcion;
        this.fpEstado = fpEstado;
    }

    public FormaPago() {
        this.fpId = -1;
        this.fpDescripcion = "";
        this.fpEstado = "";
    }

    public int getFpId() {
        return fpId;
    }

    public void setFpId(int fpId) {
        this.fpId = fpId;
    }

    public String getFpDescripcion() {
        return fpDescripcion;
    }

    public void setFpDescripcion(String fpDescripcion) {
        this.fpDescripcion = fpDescripcion;
    }

    public String getFpEstado() {
        return fpEstado;
    }

    public void setFpEstado(String fpEstado) {
        this.fpEstado = fpEstado;
    }

    @Override
    public String toString() {
        return "FormaPago {" +
                "fpId=" + fpId +
                ", fpDescripcion='" + fpDescripcion + '\'' +
                ", fpEstado='" + fpEstado + '\'' +
                '}';
    }
}
