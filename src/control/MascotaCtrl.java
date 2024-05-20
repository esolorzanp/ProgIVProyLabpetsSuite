package control;

import dao.MascotaDao;
import dao.RazaDao;
import model.Mascota;
import model.Raza;
import view.MascotaFrm;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;

public class MascotaCtrl implements ActionListener {
    private Mascota model;
    private MascotaDao dao;
    private MascotaFrm vista;

    public MascotaCtrl(Mascota model, MascotaDao dao, MascotaFrm vista) {
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
        this.model.setMasNombre(this.vista.txtNombre.getText());
        this.model.setMasEdad(Integer.parseInt(this.vista.txtEdad.getText()));
        this.model.setMasPropietario(this.vista.txtPropietario.getText());
        Raza raza = new Raza();
        raza.setRazaDescripcion(this.vista.cmbRaza.getSelectedItem().toString());
        new RazaDao().buscarByNombew(raza);
        this.model.setRazaId(raza.getRazaId());
        this.model.setMasSexo(this.vista.cmbSexo.getSelectedItem().toString());
        this.model.setMasEstado("Activo");
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
        this.model.setMasId(Integer.parseInt(this.vista.txtBuscar.getText()));
        if (dao.buscarId(this.model)) {
            this.limpiar();
            this.vista.txtNombre.setText(this.model.getMasNombre());
            this.vista.txtEdad.setText(String.valueOf(this.model.getMasEdad()));
            this.vista.txtPropietario.setText(this.model.getMasPropietario());
            this.vista.cmbSexo.setSelectedItem(this.model.getMasSexo());
            Raza raza = new Raza();
            raza.setRazaId(this.model.getRazaId());
            new RazaDao().buscarId(raza);
            this.vista.cmbRaza.setSelectedItem(raza.getRazaDescripcion());
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
        this.vista.lblEdad.setFont(fontLabels);
        this.vista.lblPropietario.setFont(fontLabels);
        this.vista.lblRaza.setFont(fontLabels);
        this.vista.lblNombre.setFont(fontLabels);
        this.vista.btnBuscar.setFont(fontButtons);
        this.vista.btnLimpiar.setFont(fontButtons);
        this.vista.btnAdicionar.setFont(fontButtons);
        this.vista.btnModificar.setFont(fontButtons);
        this.vista.btnEliminar.setFont(fontButtons);
        this.vista.btnAnular.setFont(fontButtons);
        this.vista.dataTable.setFont(fontLabels);
        this.vista.txtBuscar.setFont(fontLabels);
        this.vista.txtNombre.setFont(fontLabels);
        this.vista.txtEdad.setFont(fontLabels);
        this.vista.txtPropietario.setFont(fontLabels);
        this.vista.cmbSexo.addItem("Seleccione..");
        this.vista.cmbSexo.addItem("M");
        this.vista.cmbSexo.addItem("F");
        this.vista.cmbRaza.addItem("Seleccione..");
        for (Raza r: new RazaDao().getTodos(1)){
            this.vista.cmbRaza.addItem(r.getRazaDescripcion());
        }

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
                "Edad",
                "Propietario",
                "Sexo",
                "Raza",
                "Estado",
        });
        model.setRowCount(0);
        for (Mascota m : dao.getTodos(1)) {
            Raza r = new Raza();
            r.setRazaId(m.getRazaId());
            new RazaDao().buscarId(r);
            model.addRow(new Object[]{
                    m.getMasId()
                    , m.getMasNombre()
                    , m.getMasEdad()
                    , m.getMasPropietario()
                    , m.getMasSexo()
                    , r.getRazaDescripcion()
                    , m.getMasEstado()
            });
        }
    }

    private void limpiar() {
        this.vista.txtBuscar.setText("");
        this.vista.txtNombre.setText("");
        this.vista.txtEdad.setText("");
        this.vista.txtPropietario.setText("");
        this.vista.cmbSexo.setSelectedIndex(0);
        this.vista.cmbRaza.setSelectedIndex(0);
    }

    private void modificar() {
        this.model.setMasNombre(this.vista.txtNombre.getText());
        this.model.setMasEdad(Integer.parseInt(this.vista.txtEdad.getText()));
        this.model.setMasPropietario(this.vista.txtPropietario.getText());
        Raza raza = new Raza();
        raza.setRazaDescripcion(this.vista.cmbRaza.getSelectedItem().toString());
        new RazaDao().buscarByNombew(raza);
        this.model.setRazaId(raza.getRazaId());
        this.model.setMasSexo(this.vista.cmbSexo.getSelectedItem().toString());
        this.model.setMasEstado("Activo");
        if (dao.modificar(this.model)) {
            listar();
            JOptionPane.showMessageDialog(null, "Registro modificado exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            this.limpiar();
        }

    }

}
