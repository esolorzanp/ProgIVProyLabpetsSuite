package model;

import java.sql.Date;
import java.sql.Timestamp;

public class Tercero {
    private int Id;
    private String tiDescripcion;
    private String identificacion;
    private String representanteLegal;
    private String razonSocial;
    private String direccion;
    private String tel1;
    private String tel2;
    private String observaciones;
    private String usuCrea;
    private Timestamp fechaCrea;
    private String usuAnula;
    private Timestamp fechaAnula;
    private String estado;

    public Tercero(int id, String tiDescripcion, String identificacion, String representanteLegal, String razonSocial, String direccion, String tel1, String tel2, String observaciones, String usuCrea, Timestamp fechaCrea, String usuAnula, Timestamp fechaAnula, String estado) {
        Id = id;
        this.tiDescripcion = tiDescripcion;
        this.identificacion = identificacion;
        this.representanteLegal = representanteLegal;
        this.razonSocial = razonSocial;
        this.direccion = direccion;
        this.tel1 = tel1;
        this.tel2 = tel2;
        this.observaciones = observaciones;
        this.usuCrea = usuCrea;
        this.fechaCrea = fechaCrea;
        this.usuAnula = usuAnula;
        this.fechaAnula = fechaAnula;
        this.estado = estado;
    }

    public Tercero() {
        Id = -1;
        this.tiDescripcion = "";
        this.identificacion = "";
        this.representanteLegal = "";
        this.razonSocial = "";
        this.direccion = "";
        this.tel1 = "";
        this.tel2 = "";
        this.observaciones = "";
        this.usuCrea = "";
        this.fechaCrea = new Timestamp(0);
        this.usuAnula = "";
        this.fechaAnula = new Timestamp(0);
        this.estado = "Activo";
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTiDescripcion() {
        return tiDescripcion;
    }

    public void setTiDescripcion(String tiDescripcion) {
        this.tiDescripcion = tiDescripcion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getRepresentanteLegal() {
        return representanteLegal;
    }

    public void setRepresentanteLegal(String representanteLegal) {
        this.representanteLegal = representanteLegal;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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

    public String getUsuAnula() {
        return usuAnula;
    }

    public void setUsuAnula(String usuAnula) {
        this.usuAnula = usuAnula;
    }

    public Timestamp getFechaAnula() {
        return fechaAnula;
    }

    public void setFechaAnula(Timestamp fechaAnula) {
        this.fechaAnula = fechaAnula;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Tercero {" +
                "Id=" + Id +
                ", tiDescripcion='" + tiDescripcion + '\'' +
                ", identificacion='" + identificacion + '\'' +
                ", representanteLegal='" + representanteLegal + '\'' +
                ", razonSocial='" + razonSocial + '\'' +
                ", direccion='" + direccion + '\'' +
                ", tel1='" + tel1 + '\'' +
                ", tel2='" + tel2 + '\'' +
                ", observaciones='" + observaciones + '\'' +
                ", usuCrea='" + usuCrea + '\'' +
                ", fechaCrea=" + fechaCrea +
                ", usuAnula='" + usuAnula + '\'' +
                ", fechaAnula=" + fechaAnula +
                ", estado='" + estado + '\'' +
                '}';
    }
}
