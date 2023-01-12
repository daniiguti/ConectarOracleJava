package Modelos;

public class Curso {
    private String codCurso;
    private String nomCurso;
    private int numExamenes;

    public Curso(String codCurso, String nomCurso, int numExamenes) {
        this.codCurso = codCurso;
        this.nomCurso = nomCurso;
        this.numExamenes = numExamenes;
    }

    public String getCodCurso() {
        return codCurso;
    }
    public void setCodCurso(String codCurso) {
        this.codCurso = codCurso;
    }

    public String getNomCurso() {
        return nomCurso;
    }
    public void setNomCurso(String nomCurso) {
        this.nomCurso = nomCurso;
    }

    public int getNumExamenes() {
        return numExamenes;
    }
    public void setNumExamenes(int numExamenes) {
        this.numExamenes = numExamenes;
    }
}
