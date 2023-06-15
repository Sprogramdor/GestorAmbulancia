package view;

import view.ui.component.PeticionesConsultar;
import view.ui.component.ClienteRegistrar;

import view.ui.component.PeticionesAsignar;
import view.ui.component.ConductorRegistrar;
import view.ui.component.ConductorConsultar;
import view.ui.component.ConductorModificar;
import view.ui.component.AmbulanciaRegistrar;
import view.ui.component.ClienteConsultar;
import view.ui.component.AmbulanciaConsultar;
import view.ui.component.ClienteActualizar;
import view.ui.component.Home;
import view.ui.component.AmbulanciaModificar;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import view.ui.menu.MenuEvent;

/**
 *
 * @author RAVEN
 */
public class FrmMenu extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    public FrmMenu() {
        initComponents();
        
        Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Obtiene la hora actual
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            String horaActual = dateFormat.format(date);

            // Actualiza la etiqueta lblHora con la hora actual
            lblHora.setText(horaActual);
            
            // Obtener la fecha actual
            SimpleDateFormat dateFormatFecha = new SimpleDateFormat("EEEE, d 'de' MMMM 'del' yyyy");
            String fechaActual = dateFormatFecha.format(date);

            // Actualizar la etiqueta lblFecha con la fecha actual
            lblFecha.setText(fechaActual);
        }
    });
    // Inicia el Timer
    timer.start();
        
    menu1.setEvent(new MenuEvent() {
        @Override
        public void selected(int index, int subIndex) {
            /*if (index == 0) {
                showForm(new HomeForm());
            } else {
                showForm(new DefaultForm("Form : " + index + " " + subIndex));
            }*/

            switch(index){
                case 0: showForm(new Home());
                        break;
                case 1:
                        if( subIndex == 1){
                             showForm(new ClienteRegistrar());
                        }if(subIndex == 2){
                           showForm(new ClienteConsultar());
                        }if(subIndex == 3){
                           showForm(new ClienteActualizar());
                        }       
                break;

                case 2:
                     if( subIndex == 1){
                             showForm(new AmbulanciaRegistrar());
                        }if(subIndex == 2){
                           showForm(new AmbulanciaConsultar());
                        }if(subIndex == 3){
                           showForm(new AmbulanciaModificar());
                        }       
                    break;
                case 3:
                    if( subIndex == 1){
                             showForm(new ConductorRegistrar());
                        }if(subIndex == 2){
                           showForm(new ConductorConsultar());
                        }if(subIndex == 3){
                           showForm(new ConductorModificar());
                        }       
                    break;
                case 4:
                    if( subIndex == 1){
                try {
                    showForm(new PeticionesAsignar());
                } catch (Exception ex) {
                    Logger.getLogger(FrmMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
                        }if(subIndex == 2){
                           showForm(new PeticionesConsultar());
                        }
                    break;
                default:
                    showForm(new Home());
                    break;
            }                
        }
    });
    }

    private void showForm(Component com) {
        body.removeAll();
        body.add(com);
        body.repaint();
        body.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        body = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnCerrarSesión = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lblHora = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        scrollPaneWin111 = new view.ui.scroll.win11.ScrollPaneWin11();
        menu1 = new view.ui.menu.Menu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(245, 245, 245));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(163, 163, 163)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        body.setBackground(new java.awt.Color(31, 50, 100));
        body.setLayout(new java.awt.BorderLayout());
        jPanel1.add(body, new org.netbeans.lib.awtextra.AbsoluteConstraints(171, -5, 810, 490));

        jPanel3.setBackground(new java.awt.Color(31, 30, 68));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nombre Usuario");

        jLabel4.setFont(new java.awt.Font("Tahoma", 2, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Gerente");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(67, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 171, 86));

        jPanel4.setBackground(new java.awt.Color(31, 30, 68));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnCerrarSesión.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btnCerrarSesión.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrarSesión.setText("Cerrar Sesión");
        btnCerrarSesión.setContentAreaFilled(false);
        btnCerrarSesión.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCerrarSesión.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesiónActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnCerrarSesión, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnCerrarSesión, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 485, 172, 50));

        jPanel2.setBackground(new java.awt.Color(31, 67, 120));
        jPanel2.setVerifyInputWhenFocusTarget(false);

        lblHora.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblHora.setForeground(new java.awt.Color(100, 149, 237));

        lblFecha.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblFecha.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHora)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 786, Short.MAX_VALUE)
                .addComponent(lblFecha)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHora)
                    .addComponent(lblFecha))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(171, 485, 810, 50));

        scrollPaneWin111.setViewportView(menu1);

        jPanel1.add(scrollPaneWin111, new org.netbeans.lib.awtextra.AbsoluteConstraints(-1, 85, 173, 400));
        scrollPaneWin111.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarSesiónActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesiónActionPerformed
        // TODO add your handling code here:
        FrmLogin frmLogin = new FrmLogin();
        frmLogin.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCerrarSesiónActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel body;
    private javax.swing.JButton btnCerrarSesión;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblHora;
    private view.ui.menu.Menu menu1;
    private view.ui.scroll.win11.ScrollPaneWin11 scrollPaneWin111;
    // End of variables declaration//GEN-END:variables
}
