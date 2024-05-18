package control;

import dao.*;
import model.Examen;
import model.Mascota;
import model.OrdenServicio;
import model.Veterinario;
import view.OrdenesServicioFrm;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmOrdenServicioCtrl implements ActionListener {
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
        this.vista.btnAdicionar.addActionListener(this);
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
        this.vista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.vista.setContentPane(this.vista.panel1);
        this.vista.pack();
        this.vista.setVisible(true);
        this.vista.setSize(800, 550);
        this.vista.setLocationRelativeTo(null);
//        this.listar();
        this.vista.txtNumero.setText(String.valueOf(new MasterDao().autoIncrement("FV")));
        this.vista.txtTotal.setEditable(false);
        llenarMarcota();
        llenarVeterinario();
        llenarExame();

        DefaultTableModel model = (DefaultTableModel) vista.dataTable.getModel();
        model.setRowCount(0); // Limpiar filas existentes
        model.setColumnIdentifiers(new Object[]{"Examen", "Valor", "Cantidad", "Total", "Observaciones"});
        model.addTableModelListener((TableModelEvent e) -> dataTableControlModif(e));
    }

    private void llenarMarcota() {
        MascotaDao mascotaDao = new MascotaDao();
        vista.cmbMascota.addItem("Seleccione..");
        for (Mascota m : mascotaDao.getTodos(1)) {
            vista.cmbMascota.addItem(m.getMasNombre());
        }
    }

    private void llenarVeterinario() {
        VeterinarioDao veterinarioDao = new VeterinarioDao();
        vista.cmbVeterinario.addItem("Seleccione..");
        for (Veterinario v : veterinarioDao.getTodos(1)) {
            vista.cmbVeterinario.addItem(v.getVetNombre());
        }
    }

    private void llenarExame() {
        ExamenDao examenDao = new ExamenDao();
        vista.cmbExamen.addItem("Seleccione..");
        for (Examen e : examenDao.getTodos(1)) {
            vista.cmbExamen.addItem(e.getExaDescripcion());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnAdicionar) {
            if (vista.cmbExamen.getSelectedIndex() > 0) {
                ExamenDao examenDao = new ExamenDao();
                Examen examen = new Examen();
                examen.setExaDescripcion(vista.cmbExamen.getSelectedItem().toString());
                if (examenDao.buscarByDescripcion(examen)) {
                    DefaultTableModel model = (DefaultTableModel) vista.dataTable.getModel();
                    model.addRow(new Object[]{examen.getExaDescripcion()
                            , examen.getExaValor()
                            , 1
                            , examen.getExaValor()
                    });
//                    double tot = Double.parseDouble(this.vista.txtTotal.getText());
//                    this.vista.txtTotal.setText(String.valueOf(tot));
                    calcTotal();
                }
            }
        }
    }

    private void calcTotal() {
        double tot = 0.0;
        DefaultTableModel model = (DefaultTableModel) this.vista.dataTable.getModel();
        int row = model.getRowCount();
        for (int i = 0; i < row; i++) {
            double prec = model.getValueAt(i, 1).toString().isEmpty() ? 0 : Double.parseDouble(model.getValueAt(i, 1).toString());
            int cant = Integer.parseInt(model.getValueAt(i, 2).toString());
            tot += (prec * cant);
        }
        this.vista.txtTotal.setText(String.valueOf(tot));
    }

    private void dataTableControlModif(TableModelEvent e) {
        if (e.getType() == TableModelEvent.UPDATE && e.getColumn() == 2) {
            DefaultTableModel model = (DefaultTableModel) vista.dataTable.getModel();
            int row = e.getFirstRow();
            int cant = Integer.parseInt(model.getValueAt(row, 2).toString());
            double prec = Double.parseDouble(model.getValueAt(row, 1).toString());
            model.setValueAt(cant * prec, row, 3);
            calcTotal();
        }

    }
}
