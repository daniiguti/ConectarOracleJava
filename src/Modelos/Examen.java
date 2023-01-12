package Modelos;

import java.util.Date;

public class Examen {    
    private String codAlumno;
    private String codCurso;
    private int numExamen;
    private Date fechaExamen;
    private double nota;

    public Examen(String codAlumno, String codCurso, int numExamen, Date fechaNac, double nota) {
        this.codAlumno = codAlumno;
        this.codCurso = codCurso;
        this.numExamen = numExamen;
        this.fechaExamen = fechaNac;
        this.nota = nota;
    }

    public String getCodAlumno() {
        return codAlumno;
    }
    public void setCodAlumno(String codAlumno) {
        this.codAlumno = codAlumno;
    }

    public String getCodCurso() {
        return codCurso;
    }
    public void setCodCurso(String codCurso) {
        this.codCurso = codCurso;
    }

    public int getNumExamen() {
        return numExamen;
    }
    public void setNumExamen(int numExamen) {
        this.numExamen = numExamen;
    }

    public Date getFechaExam() {
        return fechaExamen;
    }
    public void setFechaExam(Date fechaNac) {
        this.fechaExamen = fechaNac;
    }

    public double getNota() {
        return nota;
    }
    public void setNota(double nota) {
        this.nota = nota;
    }
}
