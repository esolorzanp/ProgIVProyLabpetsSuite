package control;


import dao.EspecieDao;
import dao.RazaDao;
import model.Especie;
import model.Raza;
import view.RazaFrm;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RazaCtrl implements ActionListener {
    private Raza model;
    private RazaDao dao;
    private RazaFrm vista;
    private Especie especie = new Especie();
    private EspecieDao especieDao = new EspecieDao();

    public RazaCtrl(Raza model, RazaDao dao, RazaFrm vista) {
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
        this.model.setRazaDescripcion(this.vista.txtDescripcion.getText());
        this.model.setRazaEstado("Activo");
        especie.setEspDescripcion((String) this.vista.cmbEspecie.getSelectedItem());
        especieDao.buscarDescripcion(especie);
        this.model.setEspId(especie.getEspId());
        if (dao.adicionar(this.model)) {
            listar();
            JOptionPane.showMessageDialog(null, "Registro creado exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            this.limpiar();
        }
    }

    private void buscar() {
        this.model.setRazaId(Integer.parseInt(this.vista.txtBuscar.getText()));
        if (dao.buscarId(this.model)) {
            this.limpiar();
            this.vista.txtDescripcion.setText(this.model.getRazaDescripcion());
            especie.setEspId(this.model.getEspId());
            especieDao.buscarId(especie);
            this.vista.cmbEspecie.setSelectedItem(especie.getEspDescripcion());
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
        for (Especie es : especieDao.getTodos(1)) {
            this.vista.cmbEspecie.addItem(es.getEspDescripcion());
        }

        Font fontLabels = new Font("Verdana", Font.PLAIN, 16);
        Font fontButtons = new Font("Verdana", Font.PLAIN, 14);
        this.vista.lblBuscar.setFont(fontLabels);
        this.vista.lblRaza.setFont(fontLabels);
        this.vista.lblRaza.setFont(fontLabels);
        this.vista.btnBuscar.setFont(fontButtons);
        this.vista.btnLimpiar.setFont(fontButtons);
        this.vista.btnAdicionar.setFont(fontButtons);
        this.vista.btnModificar.setFont(fontButtons);
        this.vista.btnEliminar.setFont(fontButtons);
        this.vista.btnAnular.setFont(fontButtons);
        this.vista.dataTable.setFont(fontLabels);
        this.vista.txtDescripcion.setFont(fontButtons);
        this.vista.txtBuscar.setFont(fontButtons);

        this.vista.setTitle("Razas");
        this.vista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.vista.setContentPane(this.vista.panel1);
        this.vista.pack();
        this.vista.setVisible(true);
        this.vista.setSize(600, 450);
        this.vista.setLocationRelativeTo(null);
        this.listar();
    }

    private void listar() {
        DefaultTableModel model = (DefaultTableModel) vista.dataTable.getModel();
        model.setColumnIdentifiers(new Object[]{
                "Id",
                "Raza",
                "Estado",
                "Id Especie",
                "Especie"
        });
        model.setRowCount(0);
        for (Raza rz : dao.getTodos(1)) {
            especie.setEspId(rz.getEspId());
            especieDao.buscarId(especie);
            model.addRow(new Object[]{
                    rz.getRazaId()
                    , rz.getRazaDescripcion()
                    , rz.getRazaEstado()
                    , rz.getEspId()
                    , especie.getEspDescripcion()
            });
        }
    }

    private void limpiar() {
        this.vista.txtBuscar.setText("");
        this.vista.txtDescripcion.setText("");
        this.vista.cmbEspecie.setSelectedIndex(-1);
    }

    private void modificar() {
        this.model.setRazaDescripcion(this.vista.txtDescripcion.getText());
        especie.setEspDescripcion((String) this.vista.cmbEspecie.getSelectedItem());
        especieDao.buscarDescripcion(especie);
        this.model.setEspId(especie.getEspId());
        if (dao.modificar(this.model)) {
            listar();
            JOptionPane.showMessageDialog(null, "Registro modificado exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            this.limpiar();
        }

    }

}
