package Modelos;

public class Matricula {
    private String codAlu;
    private String nombreAlu;
    private String codCurso;
    private String nombreCurso;
    private double notaMedia;

    public Matricula(String codAlu, String nombreAlu, String codCurso, String nombreCurso, double notaMedia) {
        this.codAlu = codAlu;
        this.nombreAlu = nombreAlu;
        this.codCurso = codCurso;
        this.nombreCurso = nombreCurso;
        this.notaMedia = notaMedia;
    }

    public String getCodAlu() {
        return codAlu;
    }
    public void setCodAlu(String codAlu) {
        this.codAlu = codAlu;
    }

    public String getNombreAlu() {
        return nombreAlu;
    }
    public void setNombreAlu(String nombreAlu) {
        this.nombreAlu = nombreAlu;
    }

    public String getCodCurso() {
        return codCurso;
    }
    public void setCodCurso(String codCurso) {
        this.codCurso = codCurso;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }
    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public double getNotaMedia() {
        return notaMedia;
    }
    public void setNotaMedia(double notaMedia) {
        this.notaMedia = notaMedia;
    }
}
