package model;

import java.sql.Date;

public class OrdenServicio {
    private int osId;
    private Date osFecha;
    private int osVetId;
    private int osMasId;
    private String osMasPropietario;
    private String osEstado;
    private int osValorTotal;
    private int osAbono;
    private String osObservaciones;
    private String osObservacionesResultados;
    private String osUsuCrea;
    private Date osFechaCrea;
    private String osUsuAnula;
    private Date osFechaAnula;
    private String osRazonAnula;
    private String osUsuResultadoCrea;

    public OrdenServicio(int osId, Date osFecha, int osVetId, int osMasId, String osMasPropietario, String osEstado, int osValorTotal, int osAbono, String osObservaciones, String osObservacionesResultados, String osUsuCrea, Date osFechaCrea, String osUsuAnula, Date osFechaAnula, String osRazonAnula, String osUsuResultadoCrea) {
        this.osId = osId;
        this.osFecha = osFecha;
        this.osVetId = osVetId;
        this.osMasId = osMasId;
        this.osMasPropietario = osMasPropietario;
        this.osEstado = osEstado;
        this.osValorTotal = osValorTotal;
        this.osAbono = osAbono;
        this.osObservaciones = osObservaciones;
        this.osObservacionesResultados = osObservacionesResultados;
        this.osUsuCrea = osUsuCrea;
        this.osFechaCrea = osFechaCrea;
        this.osUsuAnula = osUsuAnula;
        this.osFechaAnula = osFechaAnula;
        this.osRazonAnula = osRazonAnula;
        this.osUsuResultadoCrea = osUsuResultadoCrea;
    }
    public OrdenServicio() {
        this.osId = -1;
        this.osFecha = null;
        this.osVetId = -1;
        this.osMasId = -1;
        this.osMasPropietario = "";
        this.osEstado = "";
        this.osValorTotal = 0;
        this.osAbono = 0;
        this.osObservaciones = "";
        this.osObservacionesResultados = "";
        this.osUsuCrea = "";
        this.osFechaCrea = null;
        this.osUsuAnula = "";
        this.osFechaAnula = null;
        this.osRazonAnula = "";
        this.osUsuResultadoCrea = "";
    }

    public int getOsId() {
        return osId;
    }

    public void setOsId(int osId) {
        this.osId = osId;
    }

    public Date getOsFecha() {
        return osFecha;
    }

    public void setOsFecha(Date osFecha) {
        this.osFecha = osFecha;
    }

    public int getOsVetId() {
        return osVetId;
    }

    public void setOsVetId(int osVetId) {
        this.osVetId = osVetId;
    }

    public int getOsMasId() {
        return osMasId;
    }

    public void setOsMasId(int osMasId) {
        this.osMasId = osMasId;
    }

    public String getOsMasPropietario() {
        return osMasPropietario;
    }

    public void setOsMasPropietario(String osMasPropietario) {
        this.osMasPropietario = osMasPropietario;
    }

    public String getOsEstado() {
        return osEstado;
    }

    public void setOsEstado(String osEstado) {
        this.osEstado = osEstado;
    }

    public int getOsValorTotal() {
        return osValorTotal;
    }

    public void setOsValorTotal(int osValorTotal) {
        this.osValorTotal = osValorTotal;
    }

    public int getOsAbono() {
        return osAbono;
    }

    public void setOsAbono(int osAbono) {
        this.osAbono = osAbono;
    }

    public String getOsObservaciones() {
        return osObservaciones;
    }

    public void setOsObservaciones(String osObservaciones) {
        this.osObservaciones = osObservaciones;
    }

    public String getOsObservacionesResultados() {
        return osObservacionesResultados;
    }

    public void setOsObservacionesResultados(String osObservacionesResultados) {
        this.osObservacionesResultados = osObservacionesResultados;
    }

    public String getOsUsuCrea() {
        return osUsuCrea;
    }

    public void setOsUsuCrea(String osUsuCrea) {
        this.osUsuCrea = osUsuCrea;
    }

    public Date getOsFechaCrea() {
        return osFechaCrea;
    }

    public void setOsFechaCrea(Date osFechaCrea) {
        this.osFechaCrea = osFechaCrea;
    }

    public String getOsUsuAnula() {
        return osUsuAnula;
    }

    public void setOsUsuAnula(String osUsuAnula) {
        this.osUsuAnula = osUsuAnula;
    }

    public Date getOsFechaAnula() {
        return osFechaAnula;
    }

    public void setOsFechaAnula(Date osFechaAnula) {
        this.osFechaAnula = osFechaAnula;
    }

    public String getOsRazonAnula() {
        return osRazonAnula;
    }

    public void setOsRazonAnula(String osRazonAnula) {
        this.osRazonAnula = osRazonAnula;
    }

    public String getOsUsuResultadoCrea() {
        return osUsuResultadoCrea;
    }

    public void setOsUsuResultadoCrea(String osUsuResultadoCrea) {
        this.osUsuResultadoCrea = osUsuResultadoCrea;
    }

    @Override
    public String toString() {
        return "OrdenServicioDetalle {" +
                "osId=" + osId +
                ", osFecha=" + osFecha +
                ", osVetId=" + osVetId +
                ", osMasId=" + osMasId +
                ", osMasPropietario='" + osMasPropietario + '\'' +
                ", osEstado='" + osEstado + '\'' +
                ", osValorTotal=" + osValorTotal +
                ", osAbono=" + osAbono +
                ", osObservaciones='" + osObservaciones + '\'' +
                ", osObservacionesResultados='" + osObservacionesResultados + '\'' +
                ", osUsuCrea='" + osUsuCrea + '\'' +
                ", osFechaCrea=" + osFechaCrea +
                ", osUsuAnula='" + osUsuAnula + '\'' +
                ", osFechaAnula=" + osFechaAnula +
                ", osRazonAnula='" + osRazonAnula + '\'' +
                ", osUsuResultadoCrea='" + osUsuResultadoCrea + '\'' +
                '}';
    }

}
