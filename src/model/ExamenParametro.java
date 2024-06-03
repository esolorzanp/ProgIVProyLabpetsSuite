package model;

public class ExamenParametro {
    private int epId;
    private int epExaId;
    private int epParaId;
    private int epConsecutivo;

    public ExamenParametro(int epId, int epExaId, int epParaId, int epConsecutivo) {
        this.epId = epId;
        this.epExaId = epExaId;
        this.epParaId = epParaId;
        this.epConsecutivo = epConsecutivo;
    }

    public ExamenParametro() {
        this.epId = -1;
        this.epExaId = -1;
        this.epParaId = -1;
        this.epConsecutivo = -1;
    }

    public int getEpId() {
        return epId;
    }

    public void setEpId(int epId) {
        this.epId = epId;
    }

    public int getEpExaId() {
        return epExaId;
    }

    public void setEpExaId(int epExaId) {
        this.epExaId = epExaId;
    }

    public int getEpParaId() {
        return epParaId;
    }

    public void setEpParaId(int epParaId) {
        this.epParaId = epParaId;
    }

    public int getEpConsecutivo() {
        return epConsecutivo;
    }

    public void setEpConsecutivo(int epConsecutivo) {
        this.epConsecutivo = epConsecutivo;
    }

    @Override
    public String toString() {
        return "ExamenParametro {" +
                "epId=" + epId +
                ", epExaId=" + epExaId +
                ", epParaId=" + epParaId +
                ", epConsecutivo=" + epConsecutivo +
                '}';
    }
}
