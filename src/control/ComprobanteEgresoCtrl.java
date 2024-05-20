package control;

import dao.*;
import model.Concepto;
import model.FormaPago;
import model.OrdenServicio;
import model.Tercero;
import view.ComprobanteEgresoFrm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComprobanteEgresoCtrl implements ActionListener {
    private ComprobanteEgresoFrm vista;

    public ComprobanteEgresoCtrl(ComprobanteEgresoFrm vista) {
        this.vista = vista;
        this.vista.btnAdicionar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
    }

    public void iniciar() {
        Font fontLabels = new Font("Verdana", Font.PLAIN, 16);
        Font fontButtons = new Font("Verdana", Font.PLAIN, 16);
        this.vista.lblNumero.setFont(fontLabels);
        this.vista.lblFecha.setFont(fontLabels);
        this.vista.lblTercero.setFont(fontLabels);
        this.vista.lblFormaPago.setFont(fontLabels);
        this.vista.lblConcepto.setFont(fontLabels);
        this.vista.lblOrden.setFont(fontLabels);
        this.vista.lblFormaPago.setFont(fontLabels);
        this.vista.lblValorEgreso.setFont(fontLabels);
        this.vista.txtNumero.setFont(fontButtons);
        this.vista.txtFecha.setFont(fontButtons);
        this.vista.txtValorEgreso.setFont(fontButtons);
        this.vista.cmbTerceros.setFont(fontButtons);
        this.vista.cmbFormasPago.setFont(fontButtons);
        this.vista.cmbConceptos.setFont(fontButtons);
        this.vista.cmbOrdenes.setFont(fontButtons);
        this.vista.btnAdicionar.setFont(fontButtons);
        this.vista.btnLimpiar.setFont(fontButtons);
        this.vista.setTitle("Comprobantes de egreso");
        this.vista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.vista.setContentPane(this.vista.panel1);
        this.vista.pack();
        this.vista.setSize(800, 550);
        this.vista.setLocationRelativeTo(null);
        this.vista.setVisible(true);

        this.vista.txtNumero.setText(String.valueOf(new MasterDao().autoIncrement("CE")));
        llenarTercero();
        llenarFormasPago();
        llenarConceptos();
        llenarOrdenesServicio();
    }

    private void llenarTercero() {
        this.vista.cmbTerceros.addItem("Seleccione..");
        for (Tercero t : new TerceroDao().getTodos(1)) {
            this.vista.cmbTerceros.addItem(t.getRazonSocial());
        }
    }

    private void llenarConceptos() {
        this.vista.cmbConceptos.addItem("Seleccione..");
        for (Concepto c : new ConceptoDao().getTodos(1)) {
            this.vista.cmbConceptos.addItem(c.getConDescripcion());
        }

    }

    private void llenarFormasPago() {
        this.vista.cmbFormasPago.addItem("Seleccione..");
        for (FormaPago fp : new FormaPagoDao().getTodos(1)) {
            this.vista.cmbFormasPago.addItem(fp.getFpDescripcion());
        }
    }

    private void llenarOrdenesServicio() {
        this.vista.cmbOrdenes.addItem("Seleccione..");
        for (OrdenServicio os : new OrdenServicioDao().getTodos(1)) {
            this.vista.cmbOrdenes.addItem(os.getOsId());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
