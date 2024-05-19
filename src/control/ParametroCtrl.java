package control;

import dao.ParametroDao;
import model.Parametro;
import view.ParametroFrm;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ParametroCtrl implements ActionListener {
    private Parametro model;
    private ParametroDao dao;
    private ParametroFrm vista;

    public ParametroCtrl(Parametro model, ParametroDao dao, ParametroFrm vista) {
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
        this.model.setParaDescripcion(this.vista.txtDescripcion.getText());
        this.model.setParaUnidad(this.vista.txtUnidad.getText());
        this.model.setParaFormula(this.vista.txtFormula.getText());
        this.model.setParaReferencia1(this.vista.txtReferencia1.getText());
        this.model.setParaReferencia2(this.vista.txtReferencia2.getText());
        this.model.setParaEstado("Activo");
        if (dao.adicionar(this.model)) {
            listar();
            JOptionPane.showMessageDialog(null, "Registro creado exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            this.limpiar();
        }
    }

    private void buscar() {
        this.model.setParaId(Integer.parseInt(this.vista.txtBuscar.getText()));
        if (dao.buscarId(this.model)) {
            this.limpiar();
            this.vista.txtDescripcion.setText(this.model.getParaDescripcion());
            this.vista.txtUnidad.setText(this.model.getParaUnidad());
            this.vista.txtFormula.setText(this.model.getParaFormula());
            this.vista.txtReferencia1.setText(this.model.getParaReferencia1());
            this.vista.txtReferencia2.setText(this.model.getParaReferencia2());
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
        this.vista.lblDescripcion.setFont(fontLabels);
        this.vista.btnBuscar.setFont(fontButtons);
        this.vista.btnLimpiar.setFont(fontButtons);
        this.vista.btnAdicionar.setFont(fontButtons);
        this.vista.btnModificar.setFont(fontButtons);
        this.vista.btnEliminar.setFont(fontButtons);
        this.vista.btnAnular.setFont(fontButtons);
        this.vista.dataTable.setFont(fontLabels);
        this.vista.txtBuscar.setFont(fontLabels);
        this.vista.txtDescripcion.setFont(fontLabels);
        this.vista.txtUnidad.setFont(fontLabels);
        this.vista.txtFormula.setFont(fontLabels);
        this.vista.txtReferencia1.setFont(fontLabels);
        this.vista.txtReferencia2.setFont(fontLabels);

        this.vista.setTitle("Perfiles");
        this.vista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.vista.setContentPane(this.vista.panel1);
        this.vista.pack();
        this.vista.setVisible(true);
        this.vista.setSize(1000, 650);
        this.vista.setLocationRelativeTo(null);
        this.listar();
    }

    private void listar() {
        DefaultTableModel model = (DefaultTableModel) vista.dataTable.getModel();
        model.setColumnIdentifiers(new Object[]{
                "Id",
                "Parámetro",
                "Unidad",
                "Fórmula",
                "Referencia 1",
                "Referencia 2",
                "Estado",
        });
        model.setRowCount(0);
        for (Parametro p : dao.getTodos(1)) {
            model.addRow(new Object[]{
                    p.getParaId()
                    , p.getParaDescripcion()
                    , p.getParaUnidad()
                    , p.getParaFormula()
                    , p.getParaReferencia1()
                    , p.getParaReferencia2()
                    , p.getParaEstado()
            });
        }
    }

    private void limpiar() {
        this.vista.txtBuscar.setText("");
        this.vista.txtDescripcion.setText("");
        this.vista.txtUnidad.setText("");
        this.vista.txtFormula.setText("");
        this.vista.txtReferencia1.setText("");
        this.vista.txtReferencia2.setText("");
    }

    private void modificar() {
        this.model.setParaDescripcion(this.vista.txtDescripcion.getText());
        this.model.setParaUnidad(this.vista.txtUnidad.getText());
        this.model.setParaFormula(this.vista.txtFormula.getText());
        this.model.setParaReferencia1(this.vista.txtReferencia1.getText());
        this.model.setParaReferencia2(this.vista.txtReferencia2.getText());
        this.model.setParaEstado("Activo");
        if (dao.modificar(this.model)) {
            listar();
            JOptionPane.showMessageDialog(null, "Registro modificado exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            this.limpiar();
        }

    }

}
