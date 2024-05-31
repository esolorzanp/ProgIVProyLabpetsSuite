package model;

public class ConsecutivoDocumento {
    private String tdSigla;
    private String nitEmpresa;
    private String ultimoCreado;

    public ConsecutivoDocumento(String tdSigla, String nitEmpresa, String ultimoCreado) {
        this.tdSigla = tdSigla;
        this.nitEmpresa = nitEmpresa;
        this.ultimoCreado = ultimoCreado;
    }

    public ConsecutivoDocumento() {
        this.tdSigla = "";
        this.nitEmpresa = "";
        this.ultimoCreado = "";
    }

    public String getTdSigla() {
        return tdSigla;
    }

    public void setTdSigla(String tdSigla) {
        this.tdSigla = tdSigla;
    }

    public String getNitEmpresa() {
        return nitEmpresa;
    }

    public void setNitEmpresa(String nitEmpresa) {
        this.nitEmpresa = nitEmpresa;
    }

    public String getUltimoCreado() {
        return ultimoCreado;
    }

    public void setUltimoCreado(String ultimoCreado) {
        this.ultimoCreado = ultimoCreado;
    }

    @Override
    public String toString() {
        return "ConsecutivoDocumento {" +
                "tdSigla='" + tdSigla + '\'' +
                ", nitEmpresa='" + nitEmpresa + '\'' +
                ", ultimoCreado='" + ultimoCreado + '\'' +
                '}';
    }
}
