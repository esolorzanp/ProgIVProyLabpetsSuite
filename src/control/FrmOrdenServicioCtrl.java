package control;

import dao.MasterDao;
import dao.OrdenServicioDao;
import model.OrdenServicio;
import view.OrdenesServicioFrm;

import javax.swing.*;
import java.awt.*;

public class FrmOrdenServicioCtrl {
//    private OrdenServicio model;
//    private OrdenServicioDao dao;
    private OrdenesServicioFrm vista;

//    public FrmOrdenServicioCtrl(OrdenServicio model, OrdenServicioDao dao, OrdenesServicioFrm vista) {
    public FrmOrdenServicioCtrl(OrdenesServicioFrm vista) {
//        this.model = model;
//        this.dao = dao;
        this.vista = vista;
//        this.vista.btnLimpiar.addActionListener(this);
//        this.vista.btnBuscar.addActionListener(this);
//        this.vista.btnAdicionar.addActionListener(this);
//        this.vista.btnModificar.addActionListener(this);
//        this.vista.btnEliminar.addActionListener(this);
    }
    public void iniciar() {
        Font fontLabels = new Font("Verdana", Font.PLAIN, 16);
        Font fontButtons = new Font("Verdana", Font.PLAIN, 16);
//        this.vista.lblBuscar.setFont(fontLabels);
//        this.vista.lblDescripcion.setFont(fontLabels);
//        this.vista.lblTipo.setFont(fontLabels);
//        this.vista.lvlValor.setFont(fontLabels);
//        this.vista.btnBuscar.setFont(fontButtons);
//        this.vista.btnLimpiar.setFont(fontButtons);
//        this.vista.btnAdicionar.setFont(fontButtons);
//        this.vista.btnModificar.setFont(fontButtons);
//        this.vista.btnEliminar.setFont(fontButtons);
//        this.vista.btnAnular.setFont(fontButtons);

        this.vista.setTitle("Ordenes de Servicio");
        this.vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.vista.setContentPane(this.vista.panel1);
        this.vista.pack();
        this.vista.setVisible(true);
        this.vista.setSize(600, 450);
        this.vista.setLocationRelativeTo(null);
//        this.listar();
        this.vista.txtNumero.setText(String.valueOf(new MasterDao().autoIncrement("FV")));
    }
}
