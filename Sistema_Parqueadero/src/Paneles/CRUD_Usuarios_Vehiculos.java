package Paneles;

import Conexion.DataBase;
import Conexion.DataManager;
import interfaz.Principal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tefy
 */
public class CRUD_Usuarios_Vehiculos extends javax.swing.JPanel {

    /**
     * Creates new form CRUD_Usuarios_Vehiculos
     */
    Principal home;
//sirve para que los usuarios ingresen los vehiculos
    public CRUD_Usuarios_Vehiculos(Principal principal) {
        initComponents();
        this.home = principal;
        this.cargarTabla();
        this.bloquearBotonesInicio();

        jtblUsuVehi.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (jtblUsuVehi.getSelectedRow() != -1) {
                    Integer fila = jtblUsuVehi.getSelectedRow();
                    jtxtCedula.setText(jtblUsuVehi.getValueAt(fila, 1).toString());
                    String nomApe = jtblUsuVehi.getValueAt(fila, 2).toString();
                    String[] arraynomApe = nomApe.split(" ");
                    jtxtNombre.setText(arraynomApe[0]);
                    jtxtApellido.setText(arraynomApe[1]);
                    jtxtPlaca.setText(jtblUsuVehi.getValueAt(fila, 3).toString());
                    jtxtColor.setText(jtblUsuVehi.getValueAt(fila, 4).toString());
                    jtxtMarca.setText(jtblUsuVehi.getValueAt(fila, 5).toString());

                }
            }
        });
            }

  public void guardarUsuarioVechiculo() {
        try {
            String cedula, nombre, apellido, placa, color, marca;

            cedula = jtxtCedula.getText();
            nombre = jtxtNombre.getText();
            apellido = jtxtApellido.getText();
            placa = jtxtPlaca.getText();
            color = jtxtColor.getText();
            marca = jtxtMarca.getText();

            DataBase cn = new DataBase();
            Connection cc = cn.conectar();

            String sqlUsuario = "INSERT INTO usuarios (cedula, nombre, apellido) VALUES (?,?,?)";
            String sqlVehiculo = "INSERT INTO vehiculos (id_usuario, placa, marca, color) VALUES (?,?,?,?)";

            PreparedStatement psd = cc.prepareStatement(sqlUsuario);
            psd.setString(1, cedula);
            psd.setString(2, nombre);
            psd.setString(3, apellido);
            psd.executeUpdate();

            PreparedStatement psdVehiculo = cc.prepareStatement(sqlVehiculo);
            psdVehiculo.setString(1, cedula);
            psdVehiculo.setString(2, placa);
            psdVehiculo.setString(3, marca);
            psdVehiculo.setString(4, color);
            psdVehiculo.executeUpdate();

        } catch (Exception ex) {

        }

    }
  
  String[] registro = new String[6];

    public void cargarTabla() {
        try {
            DefaultTableModel modeloTabla = new DefaultTableModel();
            String[] titulos = {"N.", "CÃ©dula", "Nombre y Apellido", "Placa", "Color", "Marca"};
            modeloTabla.setColumnIdentifiers(titulos);
            jtblUsuVehi.setModel(modeloTabla);

            DataManager manejador = new DataManager();
            ResultSet datos = manejador.obtenerDatos("Select * from usuarios");
            String[] registro = new String[6];
            int num = 1;
            ArrayList<Object> lista = new ArrayList<>();

            while (datos.next()) {
                registro[0] = String.valueOf(num);
                registro[1] = datos.getString("cedula");
                String nombre = datos.getString("nombre");
                String apellido = datos.getString("apellido");
                registro[2] = nombre + " " + apellido;

                lista = manejador.resultado("SELECT placa, color, marca FROM vehiculos WHERE id_usuario='" + registro[1] + "'");
                registro[3] = lista.get(0).toString();
                registro[4] = lista.get(1).toString();
                registro[5] = lista.get(2).toString();
                modeloTabla.addRow(registro);
                num++;

            }
            manejador.cerrar();
            jtblUsuVehi.setModel(modeloTabla);
        } catch (Exception ex) {

        }

    }
    
    public void bloquearBotonesInicio(){
        jbtnGuardar.setEnabled(false);
        jbtnActualizar.setEnabled(false);
        jbtnBorrar.setEnabled(false);
    }
    
    public void desbloquearBotonesNuevo(){
        jbtnNuevo.setEnabled(false);
        jbtnActualizar.setEnabled(false);
        jbtnGuardar.setEnabled(true);
        jbtnCancelar.setEnabled(true);
        jbtnBorrar.setEnabled(false);
    }
  
    public void actualizarDatos(String cedula, String nombre, String apellido, String color) {
        DataManager manejador = new DataManager();
        manejador.ejecutarConsulta("UPDATE usuarios SET nombre='" + nombre + "', apellido='" + apellido + "' WHERE cedula='" + cedula + "';");
        manejador.ejecutarConsulta("UPDATE vehiculos SET color='" + color + "' WHERE id_usuario='" + cedula + "';");
    }
    
    
    public void eliminarUsuario(String cedula){
        DataManager manejador = new DataManager(); 
        manejador.ejecutarConsulta("Delete from usuarios where cedula='"+cedula+"';");
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbtnNuevo = new javax.swing.JButton();
        jbtnGuardar = new javax.swing.JButton();
        jbtnActualizar = new javax.swing.JButton();
        jbtnBorrar = new javax.swing.JButton();
        jbtnCancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jtxtCedula = new javax.swing.JTextField();
        jtxtNombre = new javax.swing.JTextField();
        jtxtApellido = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jtxtPlaca = new javax.swing.JTextField();
        jtxtColor = new javax.swing.JTextField();
        jtxtMarca = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblUsuVehi = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(890, 472));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jbtnNuevo.setText("Nuevo");
        jbtnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnNuevoActionPerformed(evt);
            }
        });
        add(jbtnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(699, 13, 89, -1));

        jbtnGuardar.setText("Guardar");
        jbtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnGuardarActionPerformed(evt);
            }
        });
        add(jbtnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(699, 51, 89, -1));

        jbtnActualizar.setText("Actualizar");
        jbtnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnActualizarActionPerformed(evt);
            }
        });
        add(jbtnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(699, 99, -1, -1));

        jbtnBorrar.setText("Borrar");
        jbtnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnBorrarActionPerformed(evt);
            }
        });
        add(jbtnBorrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(699, 142, 89, -1));

        jbtnCancelar.setText("Cancelar");
        add(jbtnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(699, 180, 89, -1));

        jLabel1.setText("Usuario");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(146, 17, -1, -1));

        jLabel2.setText("Cédula: ");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 55, -1, -1));

        jLabel3.setText("Nombre: ");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 102, -1, -1));

        jLabel4.setText("Apellido: ");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 146, -1, -1));
        add(jtxtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(119, 52, 120, -1));
        add(jtxtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(119, 99, 120, -1));

        jtxtApellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtApellidoActionPerformed(evt);
            }
        });
        add(jtxtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(119, 143, 120, -1));

        jLabel5.setText("Vechículo");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(432, 13, -1, -1));

        jLabel6.setText("Placa: ");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(355, 60, -1, -1));

        jLabel7.setText("Color: ");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(355, 112, -1, -1));

        jLabel8.setText("Marca:");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(354, 168, -1, -1));
        add(jtxtPlaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(432, 57, 86, -1));
        add(jtxtColor, new org.netbeans.lib.awtextra.AbsoluteConstraints(432, 109, 86, -1));
        add(jtxtMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(432, 165, 86, -1));

        jtblUsuVehi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jtblUsuVehi);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(83, 230, 650, 172));
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnGuardarActionPerformed
        // TODO add your handling code here:
       this.guardarUsuarioVechiculo();
        this.cargarTabla();
    }//GEN-LAST:event_jbtnGuardarActionPerformed

    private void jtxtApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtApellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtApellidoActionPerformed

    private void jbtnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnActualizarActionPerformed
        // TODO add your handling code here:
       String cedula = jtxtCedula.getText();
        String nombre = jtxtNombre.getText();
        String apellido = jtxtApellido.getText();
        String color = jtxtColor.getText();
        
        this.actualizarDatos(cedula, nombre, apellido, color);
    }//GEN-LAST:event_jbtnActualizarActionPerformed

    private void jbtnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnBorrarActionPerformed
        // TODO add your handling code here:
         String cedula = jtxtCedula.getText();
         this.eliminarUsuario(cedula);
         this.cargarTabla();
    }//GEN-LAST:event_jbtnBorrarActionPerformed

    private void jbtnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnNuevoActionPerformed
        // TODO add your handling code here:
       this.desbloquearBotonesNuevo();
    }//GEN-LAST:event_jbtnNuevoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnActualizar;
    private javax.swing.JButton jbtnBorrar;
    private javax.swing.JButton jbtnCancelar;
    private javax.swing.JButton jbtnGuardar;
    private javax.swing.JButton jbtnNuevo;
    private javax.swing.JTable jtblUsuVehi;
    private javax.swing.JTextField jtxtApellido;
    private javax.swing.JTextField jtxtCedula;
    private javax.swing.JTextField jtxtColor;
    private javax.swing.JTextField jtxtMarca;
    private javax.swing.JTextField jtxtNombre;
    private javax.swing.JTextField jtxtPlaca;
    // End of variables declaration//GEN-END:variables
}
