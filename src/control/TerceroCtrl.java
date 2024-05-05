package control;


import dao.TerceroDao;
import model.Tercero;
import view.TerceroFrm;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TerceroCtrl implements ActionListener {
    private Tercero model;
    private TerceroDao dao;
    private TerceroFrm vista;

    public TerceroCtrl(Tercero model, TerceroDao dao, TerceroFrm vista) {
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
        this.model.setTiDescripcion(this.vista.txtDescripcion.getText());
        this.model.setIdentificacion(this.vista.txtIdentificacion.getText());
        this.model.setRepresentanteLegal(this.vista.txtRepresentanteLegal.getText());
        this.model.setRazonSocial(this.vista.txtRazonSocial.getText());
        this.model.setDireccion(this.vista.txtDireccion.getText());
        this.model.setTel1(this.vista.txtTel1.getText());
        this.model.setTel2(this.vista.txtTel2.getText());
        this.model.setObservaciones(this.vista.tAreaObservaciones.getText());
        this.model.setUsuCrea("");
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime);
        this.model.setFechaCrea(Timestamp.valueOf(dateTime));
        this.model.setUsuAnula("");
        this.model.setFechaAnula(Timestamp.valueOf(""));
        this.model.setRazonAnula("");
        this.model.setEstado("Activo");
        if (dao.adicionar(this.model)) {
            listar();
            JOptionPane.showMessageDialog(null, "Registro creado exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            this.limpiar();
        }
    }

    private void buscar() {
        this.model.setId(Integer.parseInt(this.vista.txtBuscar.getText()));
        if (dao.buscarId(this.model)) {
            this.limpiar();
            this.vista.txtId.setText(String.valueOf(this.model.getId()));
            this.vista.txtDescripcion.setText(this.model.getTiDescripcion());
            this.vista.txtIdentificacion.setText(this.model.getIdentificacion());
            this.vista.txtRepresentanteLegal.setText(this.model.getRepresentanteLegal());
            this.vista.txtRazonSocial.setText(this.model.getRazonSocial());
            this.vista.txtDireccion.setText(this.model.getDireccion());
            this.vista.txtTel1.setText(this.model.getTel1());
            this.vista.txtTel2.setText(this.model.getTel2());
            this.vista.tAreaObservaciones.setText(this.model.getObservaciones());
            this.model.setUsuCrea("");
//            this.model.setFechaCrea(Timestamp.valueOf(""));
//            this.model.setUsuAnula("");
//            this.model.setFechaAnula(Timestamp.valueOf(""));
//            this.model.setRazonAnula("");
//            this.model.setEstado("Activo");
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
        this.vista.lblId.setFont(fontLabels);
        this.vista.lblDescripcion.setFont(fontLabels);
        this.vista.lblDireccion.setFont(fontLabels);
        this.vista.lblRazonSocial.setFont(fontLabels);
        this.vista.lblRepresentanteLegal.setFont(fontLabels);
        this.vista.lblTel1.setFont(fontLabels);
        this.vista.lblTel2.setFont(fontLabels);
        this.vista.lblObservaciones.setFont(fontLabels);
        this.vista.btnBuscar.setFont(fontButtons);
        this.vista.btnLimpiar.setFont(fontButtons);
        this.vista.btnAdicionar.setFont(fontButtons);
        this.vista.btnModificar.setFont(fontButtons);
        this.vista.btnEliminar.setFont(fontButtons);
//        this.vista.btnAnular.setFont(fontButtons);

        this.vista.setTitle("Terceros");
        this.vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.vista.setContentPane(this.vista.panel1);
        this.vista.pack();
        this.vista.setVisible(true);
        this.vista.setSize(600, 650);
        this.vista.setLocationRelativeTo(null);
        this.listar();
    }

    private void listar() {
        DefaultTableModel model = (DefaultTableModel) vista.dataTable.getModel();
        model.setColumnIdentifiers(new Object[]{
                "Id",
                "ti Descripcion",
                "Identificación",
                "Representante legal",
                "Razón social",
                "Dirección",
                "Teléfono 1",
                "Teléfono 2",
                "Observaciones",
                "Usuario creación",
                "Fecha creación",
                "Uusario anula",
                "Fecha anula",
                "Razón anulación",
                "Estado"
        });
        model.setRowCount(0);
        for (Tercero e : dao.getTodos(1)) {
            model.addRow(new Object[]{
                    e.getId()
                    , e.getTiDescripcion()
                    , e.getIdentificacion()
                    , e.getRepresentanteLegal()
                    , e.getRazonSocial()
                    , e.getDireccion()
                    , e.getTel1()
                    , e.getTel2()
                    , e.getObservaciones()
                    , e.getUsuCrea()
                    , e.getFechaCrea()
                    , e.getUsuAnula()
                    , e.getFechaAnula()
                    , e.getRazonAnula()
                    , e.getEstado()
            });
        }
    }

    private void limpiar() {
        this.vista.txtDescripcion.setText("");
        this.vista.txtIdentificacion.setText("");
        this.vista.txtRepresentanteLegal.setText("");
        this.vista.txtRazonSocial.setText("");
        this.vista.txtDireccion.setText("");
        this.vista.txtTel1.setText("");
        this.vista.txtTel2.setText("");
        this.vista.tAreaObservaciones.setText("");
        this.vista.tAreaObservaciones.setText("");
    }

    private void modificar() {
        this.model.setTiDescripcion(this.vista.txtDescripcion.getText());
        this.model.setIdentificacion(this.vista.txtIdentificacion.getText());
        this.model.setRepresentanteLegal(this.vista.txtRepresentanteLegal.getText());
        this.model.setRazonSocial(this.vista.txtRazonSocial.getText());
        this.model.setDireccion(this.vista.txtDireccion.getText());
        this.model.setTel1(this.vista.txtTel1.getText());
        this.model.setTel2(this.vista.txtTel2.getText());
        this.model.setObservaciones(this.vista.tAreaObservaciones.getText());
        if (dao.modificar(this.model)) {
            listar();
            JOptionPane.showMessageDialog(null, "Registro modificado exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            this.limpiar();
        }

    }

}
