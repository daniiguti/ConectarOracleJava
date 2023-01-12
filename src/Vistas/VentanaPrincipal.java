package Vistas;

import Controladores.ControladorBDD;
import Controladores.ControladorFicheros;
import Controladores.ControladorTablas;
import Modelos.Alumno;
import Modelos.Curso;
import Modelos.Examen;
import Modelos.Matricula;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class VentanaPrincipal extends javax.swing.JFrame {
    //Conexion
    public static Connection conexion;
    
    //Controladores
    public ControladorBDD cbdd;
    public ControladorTablas ct;
    public ControladorFicheros cf;
    
    //Modelos
    public DefaultTableModel modeloAlumnos;
    public DefaultTableModel modeloCursos;
    public DefaultTableModel modeloMatriculas;
    public DefaultTableModel modeloExamenes;
    
    //Arrays
    public ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
    public ArrayList<Curso> cursos = new ArrayList<Curso>();
    public ArrayList<Matricula> matriculas = new ArrayList<Matricula>();
    public ArrayList<Examen> examenes = new ArrayList<Examen>();
    
    //Registros
    int rowAlumnos = 0;
    int rowCursos = 0;
    int rowMatriculas = 0;
    int rowExamenes = 0;          
    
    //Variablaes aux que vamos a usar muchas veces
    //guardan el codigo de alumno y el codigo de curso que hemos ido pulsando
    String codAlumnoPulsado = "";
    String codCursoPulsado = "";
    int numExamenPulsado = 0;
    
    public VentanaPrincipal() {
        //Nos conectamos a la base de datos.
        cbdd = new ControladorBDD();
        try{
            conexion = cbdd.conectarBDD();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this,"No se ha podido conectar a la base de datos");
            System.exit(0);
        }
        
        initComponents();
        this.setLocationRelativeTo(null);
        
        //Inicializmos el controlador
        ct = new ControladorTablas();
        cf = new ControladorFicheros();
        
        //Obtenemos los modelos de las tablas
        modeloAlumnos = (DefaultTableModel) JTableAlumnos.getModel();
        modeloCursos =  (DefaultTableModel) JTableCursos.getModel();
        modeloMatriculas =  (DefaultTableModel) JTableMatriculas.getModel();
        modeloExamenes = (DefaultTableModel) JTableExamenes.getModel();
        
        //Nada más abrir el programa, cargamos los datos de la base de datos
        //(alumnos y cursos) en las tablas.
        rellenarTablaAlumnos("");
        rellenarTablaCursos("");
        
        //Nos creamos dos listeners para cuando se pulse en las tablas
        //Alumnos y cursos y matriculas
        JTableAlumnos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JTableAlumnos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rowAlumnos = JTableAlumnos.rowAtPoint(evt.getPoint()); 
                codAlumnoPulsado = (String) JTableAlumnos.getValueAt(rowAlumnos, 0);
                rellenarTablaMatriculas();
            }            
        });
        JTableCursos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JTableCursos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rowCursos = JTableCursos.rowAtPoint(evt.getPoint());    
                codCursoPulsado = (String) JTableCursos.getValueAt(rowCursos, 0);
            }
        });        
        JTableMatriculas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JTableMatriculas.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rowMatriculas = JTableMatriculas.rowAtPoint(evt.getPoint());
                codAlumnoPulsado = (String) JTableMatriculas.getValueAt(rowMatriculas, 0);
                codCursoPulsado = (String) JTableMatriculas.getValueAt(rowMatriculas, 2);     
                rellenarTablaExamanes();
            }            
        });
        JTableExamenes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JTableExamenes.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rowExamenes = JTableExamenes.rowAtPoint(evt.getPoint()); 
                numExamenPulsado = (int) JTableExamenes.getValueAt(rowExamenes, 0);
                rellenarCeldasExamenes();
            }            
        });
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        JTableAlumnos = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTableCursos = new javax.swing.JTable();
        btMatricular = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        JTableMatriculas = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        JTableExamenes = new javax.swing.JTable();
        btActualizar = new javax.swing.JButton();
        tfFechaExamen = new javax.swing.JTextField();
        tfNota = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btJSON = new javax.swing.JButton();
        btXML = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        JTableAlumnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo Alumno", "Nombre"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(JTableAlumnos);
        if (JTableAlumnos.getColumnModel().getColumnCount() > 0) {
            JTableAlumnos.getColumnModel().getColumn(0).setResizable(false);
            JTableAlumnos.getColumnModel().getColumn(1).setResizable(false);
        }

        JTableCursos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo Curso", "Nombre Curso", "Nº Examenes"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(JTableCursos);
        if (JTableCursos.getColumnModel().getColumnCount() > 0) {
            JTableCursos.getColumnModel().getColumn(0).setResizable(false);
            JTableCursos.getColumnModel().getColumn(1).setResizable(false);
            JTableCursos.getColumnModel().getColumn(2).setResizable(false);
        }

        btMatricular.setText("Matricular Alumno en Curso");
        btMatricular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMatricularActionPerformed(evt);
            }
        });

        JTableMatriculas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo Alumno", "Nombre Alumno", "Codigo Curso", "Nombre Curso", "Nota Media"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(JTableMatriculas);
        if (JTableMatriculas.getColumnModel().getColumnCount() > 0) {
            JTableMatriculas.getColumnModel().getColumn(0).setResizable(false);
            JTableMatriculas.getColumnModel().getColumn(1).setResizable(false);
            JTableMatriculas.getColumnModel().getColumn(2).setResizable(false);
            JTableMatriculas.getColumnModel().getColumn(3).setResizable(false);
            JTableMatriculas.getColumnModel().getColumn(4).setResizable(false);
        }

        JTableExamenes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numero Examen", "Fecha Examen", "Nota"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(JTableExamenes);
        if (JTableExamenes.getColumnModel().getColumnCount() > 0) {
            JTableExamenes.getColumnModel().getColumn(0).setResizable(false);
            JTableExamenes.getColumnModel().getColumn(1).setResizable(false);
            JTableExamenes.getColumnModel().getColumn(2).setResizable(false);
        }

        btActualizar.setText("Actualizar");
        btActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btActualizarActionPerformed(evt);
            }
        });

        jLabel1.setText("Fecha Examen");

        jLabel2.setText("Nota");

        btJSON.setText("Boletin JSON");
        btJSON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btJSONActionPerformed(evt);
            }
        });

        btXML.setText("Listado Matricula XML");
        btXML.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btXMLActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(358, 358, 358)
                .addComponent(btMatricular)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(54, 54, 54))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btXML, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btJSON, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(122, 122, 122))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfFechaExamen, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                                    .addComponent(tfNota)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(btActualizar)
                                        .addGap(12, 12, 12)))
                                .addGap(64, 64, 64))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(34, 34, 34)
                .addComponent(btMatricular)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfFechaExamen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addComponent(btActualizar)
                        .addGap(35, 35, 35)
                        .addComponent(btJSON)
                        .addGap(18, 18, 18)
                        .addComponent(btXML)
                        .addGap(35, 35, 35))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btMatricularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMatricularActionPerformed
        if(JTableAlumnos.getSelectedRowCount() > 0 && JTableCursos.getSelectedRowCount() > 0){         
            int error = ct.altaMatricula(codAlumnoPulsado, codCursoPulsado);
            rellenarTablaMatriculas();
        }else{
            JOptionPane.showMessageDialog(this, "Seleccione un registro en la tabla alumnos y cursos");
        }
    }//GEN-LAST:event_btMatricularActionPerformed

    private void btActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btActualizarActionPerformed
        if(JTableExamenes.getSelectedRowCount() > 0){
            String nuevaFecha = tfFechaExamen.getText();
            double nuevaNota = Double.parseDouble(tfNota.getText());
            ct.actualizarExamen(codAlumnoPulsado, codCursoPulsado, numExamenPulsado, nuevaFecha, nuevaNota);
            rellenarTablaMatriculas();
            rellenarTablaExamanes();
        }else{
            JOptionPane.showMessageDialog(this, "Seleccione un registro en la tabla examenes");
        }
    }//GEN-LAST:event_btActualizarActionPerformed

    private void btXMLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btXMLActionPerformed
       String rutaArchivo = abrirFileChooser();
       cf.guardarArchivoXML(rutaArchivo, codAlumnoPulsado, codCursoPulsado);
       JOptionPane.showMessageDialog(this, "Archivo XML guardado exitosamente");
    }//GEN-LAST:event_btXMLActionPerformed

    private void btJSONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btJSONActionPerformed
        String rutaArchivo = abrirFileChooser();
        cf.guardarArchivoJSON(rutaArchivo, codAlumnoPulsado, codCursoPulsado);
        JOptionPane.showMessageDialog(this, "Archivo JSON guardado exitosamente");
    }//GEN-LAST:event_btJSONActionPerformed
    
    //metodo para rellenar la tabla de alumnos
    private void rellenarTablaAlumnos(String where) {
        modeloAlumnos.setRowCount(0);
        alumnos = ct.consultarAlumnos(where);
        
        for(Alumno aux: alumnos){
            modeloAlumnos.addRow(new Object[]{aux.getCodAlumno(), aux.getNomAlumno()});
        }
    }
    //metodo para rellenar la tabla de cursos
    private void rellenarTablaCursos(String where) {
        modeloCursos.setRowCount(0);
        cursos = ct.consultarCursos(where);
        
        for(Curso aux: cursos){
            modeloCursos.addRow(new Object[]{aux.getCodCurso(), aux.getNomCurso(), aux.getNumExamenes()});
        }
    }
    //metodo para rellenar la tabla de matriculas
    private void rellenarTablaMatriculas(){
        modeloMatriculas.setRowCount(0);        
        matriculas = ct.consultarMatriculas(codAlumnoPulsado);
        
        for(Matricula aux: matriculas){
            modeloMatriculas.addRow(new Object[]{aux.getCodAlu(), aux.getNombreAlu(), aux.getCodCurso(), aux.getNombreCurso(), aux.getNotaMedia()});
        }
    }
    //metodo para rellenar la tabla de examenes
    private void rellenarTablaExamanes(){
        modeloExamenes.setRowCount(0);        
        examenes = ct.consultarExamenes(codAlumnoPulsado, codCursoPulsado);
        for(Examen aux: examenes){
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String fecha = sdf.format(aux.getFechaExam());
            modeloExamenes.addRow(new Object[]{aux.getNumExamen(), fecha, aux.getNota()});
        }
    }
    //método para rellenar las celdas del examen
    private void rellenarCeldasExamenes(){        
        Examen aux = ct.consultarExamenesPorNum(codAlumnoPulsado, codCursoPulsado, numExamenPulsado);        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = sdf.format(aux.getFechaExam());
        tfFechaExamen.setText(fecha);
        tfNota.setText(aux.getNota() + "");
    }
    
    public String abrirFileChooser(){
        String rutaArchivo = "";
        int opcion = 0;
        JFileChooser jfc = null;
        //Bucle para que hasta que no seleccione un fichero no se salga del jfc
        do{
            jfc = new JFileChooser();
            opcion = jfc.showOpenDialog(this);
        }while(opcion == 1);
        //obtenemos la ruta para devolverla
        rutaArchivo = jfc.getSelectedFile().getAbsolutePath();
        return rutaArchivo;
    }
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JTableAlumnos;
    private javax.swing.JTable JTableCursos;
    private javax.swing.JTable JTableExamenes;
    private javax.swing.JTable JTableMatriculas;
    private javax.swing.JButton btActualizar;
    private javax.swing.JButton btJSON;
    private javax.swing.JButton btMatricular;
    private javax.swing.JButton btXML;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField tfFechaExamen;
    private javax.swing.JTextField tfNota;
    // End of variables declaration//GEN-END:variables

}
