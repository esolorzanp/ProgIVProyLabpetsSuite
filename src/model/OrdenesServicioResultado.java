package model;

import java.sql.Timestamp;
import java.time.LocalDate;

public class OrdenesServicioResultado {
    private int osrId;
    private int osId;
    private int dosExaId;
    private int paraId;
    private String osrResultado;
    private String usuCrea;
    private Timestamp fechaCrea;

    public OrdenesServicioResultado(int osrId, int osId, int dosExaId, int paraId, String osrResultado, String usuCrea, Timestamp fechaCrea) {
        this.osrId = osrId;
        this.osId = osId;
        this.dosExaId = dosExaId;
        this.paraId = paraId;
        this.osrResultado = osrResultado;
        this.usuCrea = usuCrea;
        this.fechaCrea = fechaCrea;
    }

    public OrdenesServicioResultado() {
        this.osrId = -1;
        this.osId = -1;
        this.dosExaId = -1;
        this.paraId = -1;
        this.osrResultado = "";
        this.usuCrea = "";
        this.fechaCrea = Timestamp.valueOf(LocalDate.now().atStartOfDay());
    }

    public int getOsrId() {
        return osrId;
    }

    public void setOsrId(int osrId) {
        this.osrId = osrId;
    }

    public int getOsId() {
        return osId;
    }

    public void setOsId(int osId) {
        this.osId = osId;
    }

    public int getDosExaId() {
        return dosExaId;
    }

    public void setDosExaId(int dosExaId) {
        this.dosExaId = dosExaId;
    }

    public int getParaId() {
        return paraId;
    }

    public void setParaId(int paraId) {
        this.paraId = paraId;
    }

    public String getOsrResultado() {
        return osrResultado;
    }

    public void setOsrResultado(String osrResultado) {
        this.osrResultado = osrResultado;
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

    @Override
    public String toString() {
        return "OrdenesServicioResultado {" +
                "osrId=" + osrId +
                ", osId=" + osId +
                ", dosExaId=" + dosExaId +
                ", paraId=" + paraId +
                ", osrResultado='" + osrResultado + '\'' +
                ", usuCrea='" + usuCrea + '\'' +
                ", fechaCrea=" + fechaCrea +
                '}';
    }
}
