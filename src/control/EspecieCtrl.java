package control;

import dao.EspecieDao;
import model.Especie;
import view.EspecieView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class EspecieCtrl implements ActionListener {
    private Especie model;
    private final EspecieDao dao;
    private final EspecieView vista;

    public EspecieCtrl(Especie model, EspecieDao dao, EspecieView vista) {
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
        this.model.setEspDescripcion(this.vista.txtEspecie.getText());
        this.model.setEspEstado(String.valueOf(this.vista.cmbEstado.getSelectedItem()));
        if (dao.adicionar(this.model)) {
            listar();
            JOptionPane.showMessageDialog(null, "Registro creado exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            this.limpiar();
        }
    }

    private void buscar() {
        this.model.setEspId(Integer.parseInt(this.vista.txtBuscar.getText()));
        if (dao.buscarId(this.model)) {
            this.limpiar();
            this.vista.txtId.setText(String.valueOf(this.model.getEspId()));
            this.vista.txtEspecie.setText(this.model.getEspDescripcion());
            this.vista.cmbEstado.setSelectedItem(this.model.getEspEstado());
        }
    }

    private void eliminar() {
        this.model.setEspId(Integer.parseInt(this.vista.txtId.getText()));
        if (dao.eliminar(this.model)) {
            listar();
            JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            this.limpiar();
        }
    }

    private void getRowSelected() {
        int row = this.vista.dataTable.getSelectedRow();
        this.model.setEspId((Integer) this.vista.dataTable.getModel().getValueAt(row, 0));
        this.vista.txtId.setText(String.valueOf(this.model.getEspId()));
        this.vista.txtEspecie.setText(this.model.getEspDescripcion());
        this.vista.cmbEstado.setSelectedItem(this.model.getEspEstado());
    }

    public void iniciar() {
        this.vista.cmbEstado.addItem("Activo");
        this.vista.cmbEstado.addItem("Inactivo");
        this.vista.cmbEstado.setSelectedIndex(-1);

        Font fontLabels = new Font("Verdana", Font.PLAIN, 16);
        Font fontButtons = new Font("Verdana", Font.PLAIN, 16);
        this.vista.lblBuscar.setFont(fontLabels);
        this.vista.lblId.setFont(fontLabels);
        this.vista.lblEspecie.setFont(fontLabels);
        this.vista.lblEstado.setFont(fontLabels);
        this.vista.btnBuscar.setFont(fontButtons);
        this.vista.btnLimpiar.setFont(fontButtons);
        this.vista.btnAdicionar.setFont(fontButtons);
        this.vista.btnModificar.setFont(fontButtons);
        this.vista.btnEliminar.setFont(fontButtons);

        this.vista.setTitle("Especies");
        this.vista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.vista.setContentPane(this.vista.panel1);
        this.vista.pack();
        this.vista.setVisible(true);
        this.vista.setSize(600, 450);
        this.vista.setLocationRelativeTo(null);
        this.listar();
    }

    private void modificar() {
        this.model.setEspId(Integer.parseInt(this.vista.txtId.getText()));
        this.model.setEspDescripcion(this.vista.txtEspecie.getText());
        this.model.setEspEstado(String.valueOf(this.vista.cmbEstado.getSelectedItem()));
        if (dao.modificar(this.model)) {
            listar();
            JOptionPane.showMessageDialog(null, "Registro modificado exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            this.limpiar();
        }
    }

    private void limpiar() {
        this.vista.txtEspecie.setText("");
        this.vista.txtId.setText("");
        this.vista.txtBuscar.setText("");
        this.vista.cmbEstado.setSelectedIndex(-1);
    }

    private void listar() {
        DefaultTableModel model = (DefaultTableModel) vista.dataTable.getModel();
        model.setColumnIdentifiers(new Object[]{"Id", "Especie", "Estado"});
        model.setRowCount(0);
        for (Especie e : dao.getTodos(1)) {
            model.addRow(new Object[]{e.getEspId(),
                    e.getEspDescripcion(),
                    e.getEspEstado()});
        }
    }
}
