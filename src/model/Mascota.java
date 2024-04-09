package model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

public class Mascota {
    private int masId;
    private String masNombre;
    private int masEdad;
    private String masPropietario;
    private String masSexo;
    private int razaId;
    private String usuCrea;
    private Date fechaCrea;
    private String usuAnula;
    private Date fechaAnula;
    private String masEstado;

    public Mascota(int masId, String masNombre, int masEdad, String masPropietario, String masSexo, int razaId, String usuCrea, Date fechaCrea, String usuAnula, Date fechaAnula, String masEstado) {
        this.masId = masId;
        this.masNombre = masNombre;
        this.masEdad = masEdad;
        this.masPropietario = masPropietario;
        this.masSexo = masSexo;
        this.razaId = razaId;
        this.usuCrea = usuCrea;
        this.fechaCrea = fechaCrea;
        this.usuAnula = usuAnula;
        this.fechaAnula = fechaAnula;
        this.masEstado = masEstado;
    }

    public Mascota() {
        this.masId = -1;
        this.masNombre = "";
        this.masEdad = -1;
        this.masPropietario = "";
        this.masSexo = "";
        this.razaId = -1;
        this.usuCrea = "";
        this.fechaCrea = null;
        this.usuAnula = usuAnula;
        this.fechaAnula = fechaAnula;
        this.masEstado = masEstado;
    }

    public int getMasId() {
        return masId;
    }

    public void setMasId(int masId) {
        this.masId = masId;
    }

    public String getMasNombre() {
        return masNombre;
    }

    public void setMasNombre(String masNombre) {
        this.masNombre = masNombre;
    }

    public int getMasEdad() {
        return masEdad;
    }

    public void setMasEdad(int masEdad) {
        this.masEdad = masEdad;
    }

    public String getMasPropietario() {
        return masPropietario;
    }

    public void setMasPropietario(String masPropietario) {
        this.masPropietario = masPropietario;
    }

    public String getMasSexo() {
        return masSexo;
    }

    public void setMasSexo(String masSexo) {
        this.masSexo = masSexo;
    }

    public int getRazaId() {
        return razaId;
    }

    public void setRazaId(int razaId) {
        this.razaId = razaId;
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

    public String getMasEstado() {
        return masEstado;
    }

    public void setMasEstado(String masEstado) {
        this.masEstado = masEstado;
    }

    @Override
    public String toString() {
        return "Mascota {" +
                "masId=" + masId +
                ", masNombre='" + masNombre + '\'' +
                ", masEdad=" + masEdad +
                ", masPropietario='" + masPropietario + '\'' +
                ", masSexo='" + masSexo + '\'' +
                ", razaId=" + razaId +
                ", usuCrea='" + usuCrea + '\'' +
                ", fechaCrea=" + fechaCrea +
                ", usuAnula='" + usuAnula + '\'' +
                ", fechaAnula=" + fechaAnula +
                ", masEstado='" + masEstado + '\'' +
                '}';
    }
}
