package Controladores;

import Modelos.Alumno;
import Modelos.Curso;
import Modelos.Examen;
import Modelos.Matricula;
import Vistas.VentanaPrincipal;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ControladorTablas {
    //Sentencia que se va a ejecutar
    private String sentenciaSQL;
    //Arrays
    private ArrayList<Alumno> alumnos;
    private ArrayList<Curso> cursos;
    private ArrayList<Matricula> matriculas;
    private ArrayList<Examen> examenes;
    //Variables necesarias para trabajar con los datos
    //de la base de datos
    private PreparedStatement psp;
    private ResultSet rs;
    
    //ALUMNOS
    //para hacer consultas de alumnos
    public ArrayList<Alumno> consultarAlumnos(String where){
        alumnos = new ArrayList<Alumno>();
        
        if(VentanaPrincipal.conexion != null){
            if(where.equals("")){
                sentenciaSQL = "SELECT * FROM ALUMNOS";
            }
            else{
                sentenciaSQL = "SELECT * FROM ALUMNOS WHERE ";
            }
            try {
                //Preparamos la sentencia
                psp = VentanaPrincipal.conexion.prepareStatement(sentenciaSQL);
                rs = psp.executeQuery();
                while(rs.next()){
                    Alumno aux = new Alumno(rs.getString("cCodAlu"), rs.getString("cNomAlu"));
                    //Añadimos los alumnos del ResultSet(hay que recorrerlo)
                    alumnos.add(aux);
                }
                //Como si escribes un commit
                rs.close();
                psp.close();
            } catch (SQLException ex) {
            }
        }
        return alumnos;
    }
    
    //CURSOS
    //para hacer consultas de cursos
    public ArrayList<Curso> consultarCursos(String where){
        cursos = new ArrayList<Curso>();
        
        if(VentanaPrincipal.conexion != null){
            if(where.equals("")){
                sentenciaSQL = "SELECT * FROM CURSOS";
            }
            else{
                sentenciaSQL = "SELECT * FROM CURSOS WHERE ";
            }
            try {
                //Preparamos la sentencia
                psp = VentanaPrincipal.conexion.prepareStatement(sentenciaSQL);
                rs = psp.executeQuery();
                while(rs.next()){
                    Curso aux = new Curso(rs.getString("cCodCurso"), rs.getString("cNomCurso"), rs.getInt("nNumExa"));
                    //Añadimos los cursos del ResultSet(hay que recorrerlo)
                    cursos.add(aux);
                }
                //Como si escribes un commit
                rs.close();
                psp.close();
            } catch (SQLException ex) {
            }
        }
        return cursos;
    }
    
    //MATRICULAS
    //para matricular un alumno en un curso a través de un procedimiento
    public int altaMatricula(String codAlu, String codCurso){
        int codError = -1;        
        sentenciaSQL = "{call sp_MatricularAlumnos(?, ?, ?)}";
        try {            
            CallableStatement sentencia = VentanaPrincipal.conexion.prepareCall(sentenciaSQL);
            //parámetros de entrada
            sentencia.setString(1, codAlu);
            sentencia.setString(2, codCurso);
            //parámetros de salida
            sentencia.registerOutParameter(3, Types.INTEGER);
            //le damos al play para que ejecute la sentencia
            sentencia.executeUpdate();
            //obtenemos el parametro de salida
            codError = sentencia.getInt(3);  
            if(codError == -1){
                JOptionPane.showMessageDialog(null, "Este alumno ya esta matriculado en este curso");
            }
            else{
                //hacemos el commit
                sentencia.close();
            }    
        } catch (SQLException ex) {            
        }
        return codError;
    }   
    //para hacer consultas de matriculas
    public ArrayList<Matricula> consultarMatriculas(String ccodAlu){
        matriculas = new ArrayList<Matricula>();
        if(ccodAlu.equals("")){
            sentenciaSQL = "SELECT * FROM VISTA_MATRICULAS";
        }else{
            sentenciaSQL = "SELECT * FROM VISTA_MATRICULAS WHERE CCODALU = ?";             
        }
        try {
            //Preparamos la sentencia
            psp = VentanaPrincipal.conexion.prepareStatement(sentenciaSQL);
            if(ccodAlu.equals("") == false){
                psp.setString(1, ccodAlu);
            }
            rs = psp.executeQuery();
            while(rs.next()){
                Matricula aux = new Matricula(rs.getString("cCodAlu"), rs.getString("cNomAlu"), rs.getString("cCodCurso"), rs.getString("cNomCurso"), rs.getDouble("nNotaMedia"));
                //Añadimos las matriculas del ResultSet(hay que recorrerlo)
                matriculas.add(aux);
            }
            //Como si escribes un commit
            rs.close();
            psp.close();
        } catch (SQLException ex) {
        }
        return matriculas;
    }
    
    //EXAMENES
    //para hacer consultas de examanes
    public ArrayList<Examen> consultarExamenes(String ccodAlu, String ccodCurso){
        examenes = new ArrayList<Examen>();
        
        if(VentanaPrincipal.conexion != null){
            sentenciaSQL = "SELECT * FROM EXAMENES WHERE CCODALU = '" + ccodAlu + "' AND CCODCURSO = '" + ccodCurso + "'";                      
            try {
                //Preparamos la sentencia
                psp = VentanaPrincipal.conexion.prepareStatement(sentenciaSQL);       
                rs = psp.executeQuery();                  
                while(rs.next()){          
                    Examen aux = new Examen(rs.getString("cCodAlu"), rs.getString("cCodCurso"), rs.getInt("nNumExam"), rs.getDate("dFecExam"), rs.getDouble("nNotaExam"));
                    //Añadimos las matriculas del ResultSet(hay que recorrerlo)
                    examenes.add(aux);    
                }
                //Como si escribes un commit
                rs.close();
                psp.close();
            } catch (SQLException ex) {                
            }
        }
        return examenes;
    }    
    //para hacer consultas de un examen a través de su numero de examen
    public Examen consultarExamenesPorNum(String ccodAlu, String ccodCurso, int numExamen){
        Examen aux = null;
        
        if(VentanaPrincipal.conexion != null){
            sentenciaSQL = "SELECT * FROM EXAMENES WHERE CCODALU = '" + ccodAlu + "' AND CCODCURSO = '" + ccodCurso + "' AND NNUMEXAM = " + numExamen;                  
            try {
                //Preparamos la sentencia
                psp = VentanaPrincipal.conexion.prepareStatement(sentenciaSQL);       
                rs = psp.executeQuery();   
                while(rs.next()){                     
                    //como solo hay un examen, asociado a un alumno, con un curso, y con su num identificador       
                    aux = new Examen(rs.getString("cCodAlu"), rs.getString("cCodCurso"), rs.getInt("nNumExam"), rs.getDate("dFecExam"), rs.getDouble("nNotaExam"));
                }    
                //Como si escribes un commit
                rs.close();
                psp.close();
            } catch (SQLException ex) {                
            }
        }
        return aux;
    }
    //para actualizar el examen
    public void actualizarExamen(String ccodAlu, String ccodCurso, int numExamen, String nuevaFecha, double nuevaNota){
         if(VentanaPrincipal.conexion != null){
            sentenciaSQL = "UPDATE EXAMENES SET dFecExam = ?, nNotaExam = ? WHERE CCODALU = ? AND CCODCURSO = ? AND NNUMEXAM = ?";                  
            try {
                //Preparamos la sentencia
                psp = VentanaPrincipal.conexion.prepareStatement(sentenciaSQL);       
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                java.sql.Date fechaConvertida=null;
                //Para convertir util.date a sql.date
                try {
                    Date parsed =  dateFormat.parse(nuevaFecha);
                    fechaConvertida = new java.sql.Date(parsed.getTime());
                } catch(Exception e) {
                }
                psp.setDate(1, fechaConvertida);
                psp.setDouble(2, nuevaNota);
                psp.setString(3, ccodAlu);
                psp.setString(4, ccodCurso);
                psp.setInt(5, numExamen);
                //Le damos al play y ejecutamos
                psp.executeUpdate();
                //Como si escribes un commit               
                psp.close();
            } catch (SQLException ex) {
                System.out.println("excepcion aqui");
            }
            //automaticamente cuando se actualice una nota del examen
            //vamos a actualizar la nota media de ese alumno
            actualizarNotaMedia(ccodAlu, ccodCurso);
        }
    }
    //metodo para actualizar la nota media
    public void actualizarNotaMedia(String codAlu, String codCurso){
        int codeError = -1;
        sentenciaSQL = "{call sp_ActualizarNotaMedia(?, ?, ?)}";
        try {            
            CallableStatement sentencia = VentanaPrincipal.conexion.prepareCall(sentenciaSQL);
            //parámetros de entrada
            sentencia.setString(1, codAlu);
            sentencia.setString(2, codCurso);
            //parámetros de salida
            sentencia.registerOutParameter(3, Types.INTEGER);
            //le damos al play para que ejecute la sentencia
            sentencia.executeUpdate();
            //obtenemos el parametro de salida
            codeError = sentencia.getInt(3);            
            //hacemos el commit
            sentencia.close();
        } catch (SQLException ex) {            
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void insertarAlumno(Alumno alumno){
        try{
            String sentencia = "{call sp_AltaAlumno(?, ?, ?)}";
            PreparedStatement ps = VentanaPrincipal.conexion.prepareStatement(sentencia);
            ps.setString(1, alumno.getCodAlumno());
            ps.setString(2, alumno.getNomAlumno());
            int codError = ps.executeUpdate();
            if(codError == -1){
                //JOptionPane....
            }
            ps.close();
        }catch(SQLException ex){
            
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
