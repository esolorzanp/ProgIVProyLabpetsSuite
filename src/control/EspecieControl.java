package control;

import dao.EspecieDao;
import model.Especie;
import view.EspecieView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class EspecieControl implements ActionListener {
    private Especie model;
    private final EspecieDao dao;
    private final EspecieView view;
    private Especie eForm;

    public EspecieControl(Especie model, EspecieDao dao, EspecieView view) {
        this.model = model;
        this.dao = dao;
        this.view = view;
        this.view.btnLimpiar.addActionListener(this);
        this.view.btnBuscar.addActionListener(this);
        this.view.btnAdicionar.addActionListener(this);
        this.view.btnModificar.addActionListener(this);
        this.view.btnEliminar.addActionListener(this);
//        this.view.dataTable.addKeyListener(this);
//        this.view.dataTable.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.view.btnBuscar) {
            this.buscar();
        }
        if (e.getSource() == this.view.btnLimpiar)
            this.limpiar();
        if (e.getSource() == this.view.btnAdicionar) {
            this.adicionar();
        }
        if (e.getSource() == this.view.btnModificar) {
            this.modificar();
        }
        if (e.getSource() == this.view.btnEliminar) {
            this.eliminar();
        }

    }

//    @Override
//    public void keyTyped(KeyEvent e) {
//
//    }
//
//    @Override
//    public void keyPressed(KeyEvent e) {
//
//    }
//
//    @Override
//    public void keyReleased(KeyEvent e) {
//        if (e.getKeyCode() == 38 || e.getKeyCode() == 40) {
//            this.getRowSelected();
//        }
//
//    }
//
//    @Override
//    public void mouseClicked(MouseEvent e) {
//        if (e.getButton() == 1) {
//            this.getRowSelected();
//        }
//    }
//
//    @Override
//    public void mousePressed(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseReleased(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseEntered(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseExited(MouseEvent e) {
//
//    }

    private void adicionar() {
        this.model.setEspDescripcion(this.view.txtEspecie.getText());
        this.model.setEspEstado(String.valueOf(this.view.cmbEstado.getSelectedItem()));
        if (dao.adicionar(this.model)) {
            listar();
            JOptionPane.showMessageDialog(null, "Registro creado exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            this.limpiar();
        }
    }

    private void buscar() {
        this.model.setEspId(Integer.parseInt(this.view.txtBuscar.getText()));
        if (dao.buscarId(this.model)) {
            this.limpiar();
            this.view.txtId.setText(String.valueOf(this.model.getEspId()));
            this.view.txtEspecie.setText(this.model.getEspDescripcion());
            this.view.cmbEstado.setSelectedItem(this.model.getEspEstado());
        }
    }

    private void eliminar() {
        this.model.setEspId(Integer.parseInt(this.view.txtId.getText()));
        if (dao.eliminar(this.model)) {
            listar();
            JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            this.limpiar();
        }
    }

    private void getRowSelected() {
        int row = this.view.dataTable.getSelectedRow();
        this.model.setEspId((Integer) this.view.dataTable.getModel().getValueAt(row, 0));
        this.view.txtId.setText(String.valueOf(this.model.getEspId()));
        this.view.txtEspecie.setText(this.model.getEspDescripcion());
        this.view.cmbEstado.setSelectedItem(this.model.getEspEstado());
    }

    public void iniciar() {
        this.view.cmbEstado.addItem("Activo");
        this.view.cmbEstado.addItem("Inactivo");
        this.view.cmbEstado.setSelectedIndex(-1);

        Font fontLabels = new Font("Verdana", Font.PLAIN, 16);
        Font fontButtons = new Font("Verdana", Font.PLAIN, 16);
        this.view.lblBuscar.setFont(fontLabels);
        this.view.lblId.setFont(fontLabels);
        this.view.lblEspecie.setFont(fontLabels);
        this.view.lblEstado.setFont(fontLabels);
        this.view.btnBuscar.setFont(fontButtons);
        this.view.btnLimpiar.setFont(fontButtons);
        this.view.btnAdicionar.setFont(fontButtons);
        this.view.btnModificar.setFont(fontButtons);
        this.view.btnEliminar.setFont(fontButtons);

        this.view.setTitle("Especies");
        this.view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.view.setContentPane(this.view.panel1);
        this.view.pack();
        this.view.setVisible(true);
        this.view.setSize(600, 450);
        this.view.setLocationRelativeTo(null);
        this.listar();
    }

    private void modificar() {
        this.model.setEspId(Integer.parseInt(this.view.txtId.getText()));
        this.model.setEspDescripcion(this.view.txtEspecie.getText());
        this.model.setEspEstado(String.valueOf(this.view.cmbEstado.getSelectedItem()));
        if (dao.modificar(this.model)) {
            listar();
            JOptionPane.showMessageDialog(null, "Registro modificado exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            this.limpiar();
        }
    }

    private void limpiar() {
        this.view.txtEspecie.setText("");
        this.view.txtId.setText("");
        this.view.txtBuscar.setText("");
        this.view.cmbEstado.setSelectedIndex(-1);
    }

    private void listar() {
        DefaultTableModel model = (DefaultTableModel) view.dataTable.getModel();
        model.setColumnIdentifiers(new Object[]{"Id", "Especie", "Estado"});
        model.setRowCount(0);
        for (Especie e : dao.getTodos(1)) {
            model.addRow(new Object[]{e.getEspId(), e.getEspDescripcion(), e.getEspEstado()});
        }
    }
}
