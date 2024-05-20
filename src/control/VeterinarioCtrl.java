package control;

import dao.VeterinarioDao;
import model.Veterinario;
import view.VeterinarioFrm;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;

public class VeterinarioCtrl implements ActionListener {
    private Veterinario model;
    private VeterinarioDao dao;
    private VeterinarioFrm vista;

    public VeterinarioCtrl(Veterinario model, VeterinarioDao dao, VeterinarioFrm vista) {
        this.model = model;
        this.dao = dao;
        this.vista = vista;
        this.vista.btnLimpiar.addActionListener(this);
        this.vista.btnBuscar.addActionListener(this);
        this.vista.btnAdicionar.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vista.btnBuscar) {
            this.buscar();
        }
        if (e.getSource() == this.vista.btnLimpiar)
            this.limpiar();
        if (e.getSource() == this.vista.btnAdicionar) {
            this.adicionar();
        }
        if (e.getSource() == this.vista.btnModificar) {
            this.modificar();
        }
        if (e.getSource() == this.vista.btnEliminar) {
            this.eliminar();
        }

    }

    private void adicionar() {
        this.model.setVetNombre(this.vista.txtNombre.getText());
        this.model.setVetEmail(this.vista.txtEmail.getText());
        this.model.setVetTel(this.vista.txtTelefono.getText());
        this.model.setVetVeterinaria(this.vista.txtVeterinaria.getText());
        this.model.setVetDireccion(this.vista.txtDireccion.getText());
        this.model.setVetEstado("Activo");
        this.model.setUsuCrea("");
        this.model.setFechaCrea(Date.valueOf(LocalDate.now()));
        this.model.setUsuAnula("");
        this.model.setFechaAnula(new Date(0000,0,1));
        if (dao.adicionar(this.model)) {
            listar();
            JOptionPane.showMessageDialog(null, "Registro creado exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            this.limpiar();
        }
    }

    private void buscar() {
        this.model.setVetId(Integer.parseInt(this.vista.txtBuscar.getText()));
        if (dao.buscarId(this.model)) {
            this.limpiar();
            this.vista.txtNombre.setText(this.model.getVetNombre());
            this.vista.txtEmail.setText(String.valueOf(this.model.getVetEmail()));
            this.vista.txtTelefono.setText(this.model.getVetTel());
            this.vista.txtVeterinaria.setText(this.model.getVetVeterinaria());
            this.vista.txtDireccion.setText(this.model.getVetDireccion());
        }
    }

    private void eliminar() {
        if (dao.eliminar(this.model)) {
            listar();
            JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            this.limpiar();
        }
    }

    public void iniciar() {
        Font fontLabels = new Font("Verdana", Font.PLAIN, 16);
        Font fontButtons = new Font("Verdana", Font.PLAIN, 16);
        this.vista.lblBuscar.setFont(fontLabels);
        this.vista.lblNombre.setFont(fontLabels);
        this.vista.lblEmail.setFont(fontLabels);
        this.vista.lblTelefono.setFont(fontLabels);
        this.vista.lblVeterinaria.setFont(fontLabels);
        this.vista.lblDireccion.setFont(fontLabels);
        this.vista.btnBuscar.setFont(fontButtons);
        this.vista.btnLimpiar.setFont(fontButtons);
        this.vista.btnAdicionar.setFont(fontButtons);
        this.vista.btnModificar.setFont(fontButtons);
        this.vista.btnEliminar.setFont(fontButtons);
        this.vista.btnAnular.setFont(fontButtons);
        this.vista.dataTable.setFont(fontLabels);
        this.vista.txtBuscar.setFont(fontLabels);
        this.vista.txtNombre.setFont(fontLabels);
        this.vista.txtEmail.setFont(fontLabels);
        this.vista.txtTelefono.setFont(fontLabels);
        this.vista.txtVeterinaria.setFont(fontLabels);
        this.vista.txtDireccion.setFont(fontLabels);

        this.vista.setTitle("Perfiles");
        this.vista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.vista.setContentPane(this.vista.panel1);
        this.vista.pack();
        this.vista.setVisible(true);
        this.vista.setSize(800, 550);
        this.vista.setLocationRelativeTo(null);
        this.listar();
    }

    private void listar() {
        DefaultTableModel model = (DefaultTableModel) vista.dataTable.getModel();
        model.setColumnIdentifiers(new Object[]{
                "Id",
                "Nombre",
                "Veterinaria",
                "Email",
                "Dirección",
                "Teléfono",
                "Estado",
        });
        model.setRowCount(0);
        for (Veterinario v : dao.getTodos(1)) {
            model.addRow(new Object[]{
                    v.getVetId()
                    , v.getVetNombre()
                    , v.getVetVeterinaria()
                    , v.getVetEmail()
                    , v.getVetDireccion()
                    , v.getVetTel()
                    , v.getVetEstado()
            });
        }
    }

    private void limpiar() {
        this.vista.txtBuscar.setText("");
        this.vista.txtNombre.setText("");
        this.vista.txtEmail.setText("");
        this.vista.txtTelefono.setText("");
        this.vista.txtVeterinaria.setText("");
        this.vista.txtDireccion.setText("");
    }

    private void modificar() {
        this.model.setVetNombre(this.vista.txtNombre.getText());
        this.model.setVetEmail(this.vista.txtEmail.getText());
        this.model.setVetTel(this.vista.txtTelefono.getText());
        this.model.setVetVeterinaria(this.vista.txtVeterinaria.getText());
        this.model.setVetDireccion(this.vista.txtDireccion.getText());
        if (dao.modificar(this.model)) {
            listar();
            JOptionPane.showMessageDialog(null, "Registro modificado exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            this.limpiar();
        }

    }

}
