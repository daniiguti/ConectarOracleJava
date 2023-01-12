package Controladores;

import Modelos.Examen;
import Modelos.Matricula;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class ControladorFicheros {
    private Document doc = null;
    private Transformer xformer = null;
    private Source source = null;
    private Result result = null;   
    private ControladorTablas ct = new ControladorTablas();  
    
    //Métodos necesarios para guardar un fichero XML
    public void guardarArchivoXML(String rutaArchivo, String codAlumno, String codCurso){
        //Inicializamos el doc a un arbol dom vacio
        inicializarDoc();
        
        //Nos creamos la etiqueta raiz <listaVideojuegos>
        Node nodoRaiz = doc.createElement("examenes");
        //Hay que añadirla a algo, al padre, como es la raiz, la añadimos al doc
        doc.appendChild(nodoRaiz);
        
        ArrayList<Examen> examenes = new ArrayList<>();
        examenes = ct.consultarExamenes(codAlumno, codCurso);
        //Recorremos el array
        for(Examen aux: examenes){
            //Nodo padre
            Element examen = doc.createElement("Examen");
            nodoRaiz.appendChild(examen);
           
            //codigoAlumno            
            Node codigoAlumno = doc.createElement("codAlumno");
            codigoAlumno.setTextContent(aux.getCodAlumno());           
            examen.appendChild(codigoAlumno);
            
            //codCurso
            Node codigoCurso = doc.createElement("codCurso");
            codigoCurso.setTextContent(aux.getCodCurso());
            examen.appendChild(codigoCurso);
            
            //numExamen
            Node numExamen = doc.createElement("numExamen");
            numExamen.setTextContent(aux.getNumExamen()+"");
            examen.appendChild(numExamen);
            
            //fechaExamen
            Node fechaExamen = doc.createElement("fechaExamen");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String fecha = sdf.format(aux.getFechaExam());
            fechaExamen.setTextContent(fecha);
            examen.appendChild(fechaExamen);
            
            //nota
            Node nota = doc.createElement("nota");
            nota.setTextContent(aux.getNota() + "");
            examen.appendChild(nota);
        }
        
        //Inicializamos el transformer
        try {
            xformer = TransformerFactory.newInstance().newTransformer();
        } catch (TransformerFactoryConfigurationError e) {
            e.printStackTrace();    
        } catch (TransformerConfigurationException ex) {
            ex.printStackTrace();  
        }
        
        //Propiedades del fichero XML de salida
        xformer.setOutputProperty(OutputKeys.METHOD, "xml");
        xformer.setOutputProperty(OutputKeys.INDENT, "yes"); 
        
        //Definimos la Entrada y la Salida de la Transformacion
        source = new DOMSource(doc); //el doc donde tenemos el arraylist transformado en dom
        result = new StreamResult(new File(rutaArchivo)); //donde vamos a guardar el dom
 
        //Realizamos la Transformación mediante el metodo transform()
        try {
            xformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }   
    private void inicializarDoc() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            doc = builder.newDocument();
        } catch (ParserConfigurationException ex) {            
        }       
    }
    
    public void guardarArchivoJSON(String rutaArchivo, String codAlumno, String codCursoo){
        FileWriter fw = null;
        BufferedWriter bw = null;
        String lineaEscribir = "";
        try {              
            fw = new FileWriter(new File(rutaArchivo));
            bw = new BufferedWriter(fw);
            
            ArrayList<Examen> examenes = new ArrayList<>();
            examenes = ct.consultarExamenes(codAlumno, codCursoo);
            bw.write("{\"examanes\":[" + "\n");
            for(int i= 0; i < examenes.size(); i++){                    
                String codAlu = examenes.get(i).getCodAlumno();
                String codCurso = examenes.get(i).getCodCurso();
                int nnumExam = examenes.get(i).getNumExamen();
                Date fecha = examenes.get(i).getFechaExam();     
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                //Pasamos a string la fecha con el formato deseado
                String fechaComoCadena = sdf.format(fecha);
                double nota = examenes.get(i).getNota();
                //Para que cuando vaya por el ultimo no dibuje la ultima coma
                if(i == examenes.size() - 1){
                    lineaEscribir = "{\"codAlumno\":\"" + codAlu + "\", \"codCurso\": \"" + codCurso + "\", \"numExam\": " + nnumExam +
                        ", \"fechaExamen\": \"" + fechaComoCadena + "\", \"notaExam\": " + nota + "} " + "\n";
                }else{
                    lineaEscribir = "{\"codAlumno\":\"" + codAlu + "\", \"codCurso\": \"" + codCurso + "\", \"numExam\": " + nnumExam +
                        ", \"fechaExamen\": \"" + fechaComoCadena + "\", \"notaExam\": " + nota + "}, " + "\n";
                }
                bw.write(lineaEscribir);
            }
            bw.write("]}");
        } catch (IOException ex) {
            System.out.println("Archivo no existe");
        }finally{
            try {
                bw.close();
                fw.close();
            } catch (IOException ex) {                
            }                
        }
    }
}
