package model;

import java.sql.Timestamp;

public class Parametro {
    private int paraId;
    private String paraDescripcion;
    private String paraUnidad;
    private String paraEstado;
    private String usuCrea;
    private Timestamp fechaCrea;
    private String paraUsuAnula;
    private Timestamp paraFechaAnula;
    private String paraFormula;
    private String paraReferencia1;
    private String paraReferencia2;

    public Parametro(int paraId, String paraDescripcion, String paraUnidad, String paraEstado, String usuCrea, Timestamp fechaCrea, String paraUsuAnula, Timestamp paraFechaAnula, String paraFormula, String paraReferencia1, String paraReferencia2) {
        this.paraId = paraId;
        this.paraDescripcion = paraDescripcion;
        this.paraUnidad = paraUnidad;
        this.paraEstado = paraEstado;
        this.usuCrea = usuCrea;
        this.fechaCrea = fechaCrea;
        this.paraUsuAnula = paraUsuAnula;
        this.paraFechaAnula = paraFechaAnula;
        this.paraFormula = paraFormula;
        this.paraReferencia1 = paraReferencia1;
        this.paraReferencia2 = paraReferencia2;
    }

    public Parametro() {
        this.paraId = -1;
        this.paraDescripcion = "";
        this.paraUnidad = "";
        this.paraEstado = "";
        this.usuCrea = "";
        this.fechaCrea = null;
        this.paraUsuAnula = "";
        this.paraFechaAnula = null;
        this.paraFormula = "";
        this.paraReferencia1 = "";
        this.paraReferencia2 = "";
    }

    public int getParaId() {
        return paraId;
    }

    public void setParaId(int paraId) {
        this.paraId = paraId;
    }

    public String getParaDescripcion() {
        return paraDescripcion;
    }

    public void setParaDescripcion(String paraDescripcion) {
        this.paraDescripcion = paraDescripcion;
    }

    public String getParaUnidad() {
        return paraUnidad;
    }

    public void setParaUnidad(String paraUnidad) {
        this.paraUnidad = paraUnidad;
    }

    public String getParaEstado() {
        return paraEstado;
    }

    public void setParaEstado(String paraEstado) {
        this.paraEstado = paraEstado;
    }

    public String getUsuCrea() {
        return usuCrea;
    }

    public void setUsuCrea(String usuCrea) {
        this.usuCrea = usuCrea;
    }

    public Timestamp getFechaCrea() {
        return fechaCrea;
    }

    public void setFechaCrea(Timestamp fechaCrea) {
        this.fechaCrea = fechaCrea;
    }

    public String getParaUsuAnula() {
        return paraUsuAnula;
    }

    public void setParaUsuAnula(String paraUsuAnula) {
        this.paraUsuAnula = paraUsuAnula;
    }

    public Timestamp getParaFechaAnula() {
        return paraFechaAnula;
    }

    public void setParaFechaAnula(Timestamp paraFechaAnula) {
        this.paraFechaAnula = paraFechaAnula;
    }

    public String getParaFormula() {
        return paraFormula;
    }

    public void setParaFormula(String paraFormula) {
        this.paraFormula = paraFormula;
    }

    public String getParaReferencia1() {
        return paraReferencia1;
    }

    public void setParaReferencia1(String paraReferencia1) {
        this.paraReferencia1 = paraReferencia1;
    }

    public String getParaReferencia2() {
        return paraReferencia2;
    }

    public void setParaReferencia2(String paraReferencia2) {
        this.paraReferencia2 = paraReferencia2;
    }

    @Override
    public String toString() {
        return "Parametro {" +
                "paraId=" + paraId +
                ", paraDescripcion='" + paraDescripcion + '\'' +
                ", paraUnidad='" + paraUnidad + '\'' +
                ", paraEstado='" + paraEstado + '\'' +
                ", usuCrea='" + usuCrea + '\'' +
                ", fechaCrea=" + fechaCrea +
                ", paraUsuAnula='" + paraUsuAnula + '\'' +
                ", paraFechaAnula=" + paraFechaAnula +
                ", paraFormula='" + paraFormula + '\'' +
                ", paraReferencia1='" + paraReferencia1 + '\'' +
                ", paraReferencia2='" + paraReferencia2 + '\'' +
                '}';
    }
}
