package view.ui.component;

import control.ConductorController;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import model.dto.ConductorDTO;

/**
 *
 * @author Jesus
 */
public class ConductorModificar extends javax.swing.JPanel {

    /**
     * Creates new form ConductorRegistrar
     */
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    // Agrupar los botones de opción en un ButtonGroup
    ButtonGroup buttonGroup = new ButtonGroup();
    ConductorController cc = new ConductorController();
    long id;

    public ConductorModificar() {
        initComponents();

        buttonGroup.add(this.rbMasculino);
        buttonGroup.add(this.rbFemenino);
        // Definir el formato de fecha deseado

    }

    /**
     * Modifica los datos de un conductor existente en base a los valores
     * ingresados en los campos del formulario. Obtiene los valores ingresados
     * en los campos de texto y selecciones, y actualiza el objeto ConductorDTO
     * correspondiente. Luego, llama al método de actualización en el
     * ConductorController para aplicar los cambios en la base de datos. Si se
     * realizan cambios exitosamente, se muestra un mensaje de éxito y se
     * limpian los campos del formulario. Si ocurre algún error durante la
     * actualización, se muestra un mensaje de error.
     */
    public void Modificar() {
        String sexo;
        ConductorController cc = new ConductorController();
        ConductorDTO cd = new ConductorDTO();

        // Obtener los datos del formulario
        cd.setCedula(this.tfCedula.getText());
        cd.setNombre(this.tfNombres.getText());
        cd.setApellidos(this.tfApellidos.getText());
        cd.setCorreo(this.tfCorreo.getText());

        // Obtener la fecha de contrato seleccionada
        Date fechaContrato = this.dcContrato.getDate();

        // Verificar si se ha seleccionado una fecha de contrato
        if (fechaContrato != null) {
            // Convertir la fecha a un formato deseado y establecerla en el objeto ConductorDTO
            cd.setFechaContrato(dateFormat.format(fechaContrato));
        }

        // Obtener la fecha de nacimiento seleccionada
        Date fechaNacimiento = this.dcNacimiento.getDate();

        // Verificar si se ha seleccionado una fecha de nacimiento
        if (fechaNacimiento != null) {
            // Convertir la fecha a un formato deseado y establecerla en el objeto ConductorDTO
            cd.setFechaNacimiento(dateFormat.format(fechaNacimiento));
        }

        // Obtener el sexo seleccionado
        if (this.rbMasculino.isSelected()) {
            sexo = "Masculino";
        } else {
            sexo = "Femenino";
        }
        cd.setSexo(sexo);

        // Obtener el estado seleccionado
        if (this.cbEstado.getSelectedItem().toString().equals("Disponible")) {
            cd.setEstado(true);
        } else {
            cd.setEstado(false);
        }

        // Establecer el ID del conductor a modificar
        cd.setId(id);

        // Verificar si hay propiedades vacías en el objeto ConductorDTO
        if (cd.PropiedadesVacias()) {
            JOptionPane.showMessageDialog(this, "Es necesario llenar todos los datos");
            System.out.println(cd.toString());
        } else {
            // Actualizar los datos del conductor
            if (cc.actualizarDatos(cd)) {
                JOptionPane.showMessageDialog(this, "Actualización realizada con éxito");
                this.Limpiar();
            } else {
                JOptionPane.showMessageDialog(this, "Problemas para actualizar datos");
            }
        }
    }

    /**
     * Realiza la búsqueda de un conductor utilizando el identificador de
     * cédula. Obtiene los datos del conductor correspondiente a la cédula
     * ingresada y los muestra en los campos del formulario. Si no se ingresa
     * ninguna cédula o no se encuentra un conductor con esa cédula, se muestra
     * un mensaje de error.
     *
     * Nota: Antes de llamar a este método, asegúrate de que el campo de texto
     * 'txtbuscar' contenga la cédula del conductor a buscar.
     *
     * @throws NullPointerException Si no se ingresa ninguna cédula
     */
    public void Buscar() {
        Date dateContrato = null;
        Date dateNacimiento = null;
        String idCedula = "";

        try {
            if (this.txtbuscar.getText() != null) {
                idCedula = this.txtbuscar.getText();

                try {
                    // Consultar el conductor por su cédula
                    ConductorDTO dt = cc.consultabyCedula(idCedula);

                    // Mostrar los datos del conductor en los campos correspondientes
                    this.tfCedula.setText(dt.getCedula());
                    this.tfNombres.setText(dt.getNombre());
                    this.tfApellidos.setText(dt.getApellidos());
                    this.tfCorreo.setText(dt.getCorreo());

                    try {
                        // Convertir las fechas de contrato y nacimiento al formato deseado
                        dateContrato = dateFormat.parse(dt.getFechaContrato());
                        dateNacimiento = dateFormat.parse(dt.getFechaNacimiento());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    // Establecer el estado del conductor en el campo correspondiente
                    if (dt.isEstado()) {
                        this.cbEstado.setSelectedIndex(1);
                    } else {
                        this.cbEstado.setSelectedIndex(2);
                    }

                    // Establecer el sexo del conductor en el campo correspondiente
                    if (dt.getSexo().equals("Masculino")) {
                        this.rbMasculino.setSelected(true);
                    } else {
                        this.rbFemenino.setSelected(true);
                    }

                    // Establecer las fechas de contrato y nacimiento en los campos correspondientes
                    this.dcContrato.setLocale(new Locale("es"));
                    this.dcContrato.setDate(dateContrato);
                    this.dcNacimiento.setDate(dateNacimiento);

                    // Establecer el ID del conductor encontrado
                    id = dt.getId();

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Ingrese la cédula antes");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Ingrese la cédula de un conductor");
            }
        } catch (NullPointerException b) {
            JOptionPane.showMessageDialog(this, "Ingrese la cédula de un conductor");
        }
    }

    /**
     * Elimina el conductor actualmente seleccionado. Si se elimina
     * correctamente, se muestra un mensaje de confirmación y se realiza la
     * limpieza de campos. Si no se puede eliminar el conductor, se muestra un
     * mensaje de error.
     */
    public void Eliminar() {

        if (cc.eliminarConductor(id)) {
            JOptionPane.showMessageDialog(this, "Conducto Eliminado");
            this.Limpiar();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo Eliminar conductor");
        }

    }

    /**
     * Limpia los campos del formulario, restableciéndolos a sus valores
     * predeterminados. Esto incluye borrar el texto en los campos de texto,
     * deseleccionar los botones de opción, establecer las fechas en los
     * componentes de fecha a nulo y restablecer la selección del estado.
     */
    public void Limpiar() {
        this.tfCedula.setText(" ");
        this.tfNombres.setText(" ");
        this.tfApellidos.setText(" ");
        this.tfCorreo.setText(" ");
        this.dcContrato.setDate(null);
        this.dcNacimiento.setDate(null);
        this.cbEstado.setSelectedIndex(0);
        this.buttonGroup.clearSelection();
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
        btnActualizar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        tfCorreo = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        rbMasculino = new javax.swing.JRadioButton();
        rbFemenino = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        dcNacimiento = new com.toedter.calendar.JDateChooser();
        btnEliminar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtbuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(810, 490));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 122, 204));
        jPanel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Modificar Conductor");

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

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Cedula:");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 165, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("Nombres:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 214, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setText("Apellidos:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 265, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel5.setText("Fecha de nacimiento:");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 140, 236, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setText("Estado:");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 319, -1, -1));

        tfNombres.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        add(tfNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 212, 190, -1));

        tfApellidos.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        add(tfApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 263, 190, -1));

        cbEstado.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        cbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Seleccionar-", "Disponible", "No disponible" }));
        add(cbEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 314, 190, -1));

        tfCedula.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        add(tfCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 156, 190, -1));

        dcContrato.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        add(dcContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(523, 362, 201, 38));

        btnActualizar.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnActualizarMouseClicked(evt);
            }
        });
        add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 430, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel7.setText("Correo");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 369, -1, -1));

        tfCorreo.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        add(tfCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 370, 244, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel10.setText("Sexo");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 230, -1, -1));

        rbMasculino.setBackground(new java.awt.Color(255, 255, 255));
        rbMasculino.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        rbMasculino.setText("Masculino");
        add(rbMasculino, new org.netbeans.lib.awtextra.AbsoluteConstraints(523, 267, -1, -1));

        rbFemenino.setBackground(new java.awt.Color(255, 255, 255));
        rbFemenino.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        rbFemenino.setText("Femenino");
        add(rbFemenino, new org.netbeans.lib.awtextra.AbsoluteConstraints(658, 267, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel8.setText("Fecha de contrato:");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(523, 315, -1, -1));

        dcNacimiento.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        add(dcNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 180, 223, 38));

        btnEliminar.setBackground(new java.awt.Color(255, 51, 51));
        btnEliminar.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(0, 0, 0));
        btnEliminar.setText("Eliminar");
        btnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEliminarMouseClicked(evt);
            }
        });
        add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 430, -1, -1));

        jLabel9.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel9.setText("Cedula");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 80, -1, -1));
        add(txtbuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 80, 180, 40));

        btnBuscar.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBuscarMouseClicked(evt);
            }
        });
        add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 80, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btnActualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizarMouseClicked

        this.Modificar();
    }//GEN-LAST:event_btnActualizarMouseClicked

    private void btnEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseClicked
        this.Eliminar();
    }//GEN-LAST:event_btnEliminarMouseClicked

    private void btnBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarMouseClicked
        this.Buscar();
    }//GEN-LAST:event_btnBuscarMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
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
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton rbFemenino;
    private javax.swing.JRadioButton rbMasculino;
    private javax.swing.JTextField tfApellidos;
    private javax.swing.JTextField tfCedula;
    private javax.swing.JTextField tfCorreo;
    private javax.swing.JTextField tfNombres;
    private javax.swing.JTextField txtbuscar;
    // End of variables declaration//GEN-END:variables
}
