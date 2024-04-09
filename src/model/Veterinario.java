package model;

import java.sql.Date;
import java.sql.Timestamp;

public class Veterinario {
    private int vetId;
    private String vetNombre;
    private String vetEmail;
    private String vetTel;
    private String vetVeterinaria;
    private String vetEstado;
    private String usuCrea;
    private Date fechaCrea;
    private String usuAnula;
    private Date fechaAnula;

    public Veterinario(int vetId, String vetNombre, String vetEmail, String vetTel, String vetVeterinaria, String vetEstado, String usuCrea, Date fechaCrea, String usuAnula, Date fechaAnula) {
        this.vetId = vetId;
        this.vetNombre = vetNombre;
        this.vetEmail = vetEmail;
        this.vetTel = vetTel;
        this.vetVeterinaria = vetVeterinaria;
        this.vetEstado = vetEstado;
        this.usuCrea = usuCrea;
        this.fechaCrea = fechaCrea;
        this.usuAnula = usuAnula;
        this.fechaAnula = fechaAnula;
    }

    public Veterinario() {
        this.vetId = -1;
        this.vetNombre = "";
        this.vetEmail = "";
        this.vetTel = "";
        this.vetVeterinaria = "";
        this.vetEstado = "";
        this.usuCrea = "";
        this.fechaCrea = null;
        this.usuAnula = "";
        this.fechaAnula = null;
    }

    public int getVetId() {
        return vetId;
    }

    public void setVetId(int vetId) {
        this.vetId = vetId;
    }

    public String getVetNombre() {
        return vetNombre;
    }

    public void setVetNombre(String vetNombre) {
        this.vetNombre = vetNombre;
    }

    public String getVetEmail() {
        return vetEmail;
    }

    public void setVetEmail(String vetEmail) {
        this.vetEmail = vetEmail;
    }

    public String getVetTel() {
        return vetTel;
    }

    public void setVetTel(String vetTel) {
        this.vetTel = vetTel;
    }

    public String getVetVeterinaria() {
        return vetVeterinaria;
    }

    public void setVetVeterinaria(String vetVeterinaria) {
        this.vetVeterinaria = vetVeterinaria;
    }

    public String getVetEstado() {
        return vetEstado;
    }

    public void setVetEstado(String vetEstado) {
        this.vetEstado = vetEstado;
    }

    public String getUsuCrea() {
        return usuCrea;
    }

    public void setUsuCrea(String usuCrea) {
        this.usuCrea = usuCrea;
    }

    public Date getFechaCrea() {
        return fechaCrea;
    }

    public void setFechaCrea(Date fechaCrea) {
        this.fechaCrea = fechaCrea;
    }

    public String getUsuAnula() {
        return usuAnula;
    }

    public void setUsuAnula(String usuAnula) {
        this.usuAnula = usuAnula;
    }

    public Date getFechaAnula() {
        return fechaAnula;
    }

    public void setFechaAnula(Date fechaAnula) {
        this.fechaAnula = fechaAnula;
    }

    @Override
    public String toString() {
        return "Veterinario {" +
                "vetId=" + vetId +
                ", vetNombre='" + vetNombre + '\'' +
                ", vetEmail='" + vetEmail + '\'' +
                ", vetTel='" + vetTel + '\'' +
                ", vetVeterinaria='" + vetVeterinaria + '\'' +
                ", vetEstado='" + vetEstado + '\'' +
                ", usuCrea='" + usuCrea + '\'' +
                ", fechaCrea=" + fechaCrea +
                ", usuAnula='" + usuAnula + '\'' +
                ", fechaAnula=" + fechaAnula +
                '}';
    }
}
