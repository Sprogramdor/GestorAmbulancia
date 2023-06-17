
package view.ui.component;

import control.ConductorController;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import model.dto.ConductorDTO;

/**
 *
 * @author Jesus
 */
public class ConductorRegistrar extends javax.swing.JPanel {

    /**
     * Creates new form ConductorRegistrar
     */
    
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
     // Agrupar los botones de opción en un ButtonGroup
        ButtonGroup buttonGroup = new ButtonGroup();
    
    public ConductorRegistrar() {
        initComponents();
       
        buttonGroup.add(this.rbMasculino);
        buttonGroup.add(this.rbFemenino);
        // Definir el formato de fecha deseado
         

    }

 /**
 * Registra un nuevo conductor en la base de datos utilizando los valores ingresados en los campos del formulario.
 * Obtiene los valores ingresados en los campos de texto y selecciones, y crea un nuevo objeto ConductorDTO.
 * Luego, llama al método de registro en el ConductorController para almacenar los datos en la base de datos.
 * Si el registro se realiza con éxito, se muestra un mensaje de éxito y se limpian los campos del formulario.
 * Si ocurre algún error durante el registro, se muestra un mensaje de error.
 */
    public void Registrar(){
        String sexo;
        
        ConductorDTO cd=new ConductorDTO();
        cd.setCedula(this.tfCedula.getText());
        cd.setNombre(this.tfNombres.getText());
        cd.setApellidos(this.tfApellidos.getText());
        cd.setCorreo(this.tfCorreo.getText());
        
        // Obtener la fecha seleccionada
        Date fechascontrato = this.dcContrato.getDate();

        // Verificar si se ha seleccionado una fecha
        if (fechascontrato != null) {
            // Convertir la fecha a un objeto Timestamp
          
        cd.setFechaNacimiento(dateFormat.format(this.dcNacimiento.getDate()));
        cd.setFechaContrato(dateFormat.format(this.dcContrato.getDate()));
        }
        if(this.rbMasculino.isSelected()){
            sexo="Masculino";
        }else{
            sexo="Femenino";
        }
        cd.setSexo(sexo);
        if(this.cbEstado.getSelectedItem().toString().equals("Disponible")){
              cd.setEstado(true);
        }else{
            cd.setEstado(false);
        }
      
        ConductorController cc= new ConductorController();
        
        if( cd.PropiedadesVacias()){
            JOptionPane.showMessageDialog(this, "Es necesario llenar todos los datos");
        }else{
            
             if(cc.registrarConductor(cd)){
                         JOptionPane.showMessageDialog(this, "Registro realizado con Exito");
                                     this.tfCedula.setText(" "); 
                                     this.tfNombres.setText(" "); 
                                     this.tfApellidos.setText(" "); 
                                     this.tfCorreo.setText(" "); 
                                     this.dcContrato.setDate(null);
                                     this.dcNacimiento.setDate(null);
                                     this.cbEstado.setSelectedIndex(0);
                                     this.buttonGroup.clearSelection();
         }else{
             JOptionPane.showMessageDialog(this, "El registro Fallo, intente de nuevo");
         }
            
            
        }
        
        
        
        
    }
    
  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tfNombres = new javax.swing.JTextField();
        tfApellidos = new javax.swing.JTextField();
        cbEstado = new javax.swing.JComboBox<>();
        tfCedula = new javax.swing.JTextField();
        dcContrato = new com.toedter.calendar.JDateChooser();
        btnAgregar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        tfCorreo = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        rbMasculino = new javax.swing.JRadioButton();
        rbFemenino = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        dcNacimiento = new com.toedter.calendar.JDateChooser();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(810, 490));

        jPanel1.setBackground(new java.awt.Color(0, 122, 204));
        jPanel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Registrar Conductor");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(323, 323, 323)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Cedula:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("Nombres:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setText("Apellidos:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel5.setText("Fecha de nacimiento:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setText("Estado:");

        tfNombres.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        tfApellidos.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        cbEstado.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        cbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Seleccionar-", "Disponible", "No disponible" }));

        tfCedula.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        dcContrato.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        btnAgregar.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAgregarMouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel7.setText("Correo");

        tfCorreo.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel10.setText("Sexo");

        rbMasculino.setBackground(new java.awt.Color(255, 255, 255));
        rbMasculino.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        rbMasculino.setText("Masculino");

        rbFemenino.setBackground(new java.awt.Color(255, 255, 255));
        rbFemenino.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        rbFemenino.setText("Femenino");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel8.setText("Fecha de contrato:");

        dcNacimiento.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(97, 97, 97)
                                .addComponent(tfCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(62, 62, 62)
                                .addComponent(rbMasculino)
                                .addGap(32, 32, 32)
                                .addComponent(rbFemenino))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel3))
                                        .addGap(62, 62, 62)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(tfApellidos, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                                            .addComponent(tfNombres)
                                            .addComponent(tfCedula)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(89, 89, 89)
                                        .addComponent(cbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(dcNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel10)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(116, 116, 116)
                                        .addComponent(jLabel8)
                                        .addGap(0, 64, Short.MAX_VALUE)))))
                        .addContainerGap(59, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(dcContrato, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(112, 112, 112))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAgregar)
                .addGap(336, 336, 336))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(tfCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel5)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(dcNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(tfNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(tfApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbMasculino)
                            .addComponent(rbFemenino))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(cbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(dcContrato, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(btnAgregar)
                .addGap(23, 23, 23))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarMouseClicked
        
        this.Registrar();
       
        
    }//GEN-LAST:event_btnAgregarMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JComboBox<String> cbEstado;
    private com.toedter.calendar.JDateChooser dcContrato;
    private com.toedter.calendar.JDateChooser dcNacimiento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton rbFemenino;
    private javax.swing.JRadioButton rbMasculino;
    private javax.swing.JTextField tfApellidos;
    private javax.swing.JTextField tfCedula;
    private javax.swing.JTextField tfCorreo;
    private javax.swing.JTextField tfNombres;
    // End of variables declaration//GEN-END:variables
}
