package Modelos;

public class Alumno {
    private String codAlumno;
    private String nomAlumno;

    public Alumno(String codAlumno, String nomAlumno) {
        this.codAlumno = codAlumno;
        this.nomAlumno = nomAlumno;
    }

    public String getCodAlumno() {
        return codAlumno;
    }
    public void setCodAlumno(String codAlumno) {
        this.codAlumno = codAlumno;
    }

    public String getNomAlumno() {
        return nomAlumno;
    }
    public void setNomAlumno(String nomAlumno) {
        this.nomAlumno = nomAlumno;
    }
}
