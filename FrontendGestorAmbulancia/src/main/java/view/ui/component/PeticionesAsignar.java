
package view.ui.component;

import control.PeticionController;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import model.dto.AmbulanciaDTO;

/**
 *
 * @author Jesus
 */
public class PeticionesAsignar extends javax.swing.JPanel {

    private PeticionController peticionController;
    /**
     * Creates new form ClienteRegistro
     * @throws  java.lang.Exception
     */
    public PeticionesAsignar() throws Exception {
        initComponents();
        peticionController = new PeticionController();
        llenarComboboxAmbulancia();
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
        jLabel5 = new javax.swing.JLabel();
        btnAsignar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        cbConductor = new javax.swing.JComboBox<>();
        cbAmbulancia = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cbHospital = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tfOrigen = new javax.swing.JTextField();
        tfDestino = new javax.swing.JTextField();
        Estado = new javax.swing.JLabel();
        cbHospital1 = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(810, 490));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 122, 204));
        jPanel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Asignar Petición");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(323, 323, 323)
                .addComponent(jLabel1)
                .addContainerGap(317, Short.MAX_VALUE))
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
        jLabel2.setText("Ambulancia");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel5.setText("Conductor");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, -1, -1));

        btnAsignar.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnAsignar.setText("Asignar");
        btnAsignar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignarActionPerformed(evt);
            }
        });
        add(btnAsignar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 420, -1, -1));

        btnCancelar.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 420, -1, -1));

        cbConductor.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        add(cbConductor, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 190, 250, -1));

        cbAmbulancia.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        add(cbAmbulancia, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 90, 250, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("Hospital");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, -1, -1));

        cbHospital.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        cbHospital.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Seleccionar-", "Desplegado", "No desplegado" }));
        add(cbHospital, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 340, 250, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setText("Punto de Origen");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setText("Punto de Destino");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, -1, -1));

        tfOrigen.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        add(tfOrigen, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 240, 250, -1));

        tfDestino.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        add(tfDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 290, 250, -1));

        Estado.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        Estado.setText("Estado");
        add(Estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 350, -1, -1));

        cbHospital1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        cbHospital1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Seleccionar-", "Hospital 1", "Hospital 2", "Hospital 3" }));
        add(cbHospital1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 140, 250, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btnAsignarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarActionPerformed
        
    }//GEN-LAST:event_btnAsignarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void llenarComboboxAmbulancia() {
    try {
        List<AmbulanciaDTO> ambulancias = peticionController.obtenerAmbulancias();
        List<String> placas = new ArrayList<>();
        
        for (AmbulanciaDTO ambulancia : ambulancias) {
            placas.add(ambulancia.getPlaca());
        }
        
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(placas.toArray(new String[0]));
        cbAmbulancia.setModel(model);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al obtener las ambulancias: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Estado;
    private javax.swing.JButton btnAsignar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JComboBox<String> cbAmbulancia;
    private javax.swing.JComboBox<String> cbConductor;
    private javax.swing.JComboBox<String> cbHospital;
    private javax.swing.JComboBox<String> cbHospital1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField tfDestino;
    private javax.swing.JTextField tfOrigen;
    // End of variables declaration//GEN-END:variables
}
