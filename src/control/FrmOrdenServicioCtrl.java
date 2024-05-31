package control;

import dao.*;
import model.*;
import view.OrdenesServicioFrm;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;

public class FrmOrdenServicioCtrl implements ActionListener {
    private OrdenesServicioFrm vista;

    public FrmOrdenServicioCtrl(OrdenesServicioFrm vista) {
        this.vista = vista;
        this.vista.btnAgregar.addActionListener(this);
        this.vista.btnAdicionar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
    }

    public void iniciar() {
        Font fontLabels = new Font("Verdana", Font.PLAIN, 16);
        Font fontButtons = new Font("Verdana", Font.PLAIN, 16);
        this.vista.btnAgregar.setFont(fontButtons);
        this.vista.btnAdicionar.setFont(fontButtons);
        this.vista.btnLimpiar.setFont(fontButtons);
        this.vista.lblNumero.setFont(fontLabels);
        this.vista.lblFecha.setFont(fontLabels);
        this.vista.lblMascota.setFont(fontLabels);
        this.vista.lblPropietario.setFont(fontLabels);
        this.vista.lblExamen.setFont(fontLabels);
        this.vista.lblObservaciones.setFont(fontLabels);
        this.vista.lblDetalleServicio.setFont(fontLabels);
        this.vista.lblTotal.setFont(fontLabels);
        this.vista.lblAbono.setFont(fontLabels);
        this.vista.lblSaldo.setFont(fontLabels);
        this.vista.txtNumero.setFont(fontButtons);
        this.vista.txtFecha.setFont(fontButtons);
        this.vista.txtPropietario.setFont(fontButtons);
        this.vista.cmbMascota.setFont(fontButtons);
        this.vista.cmbVeterinario.setFont(fontButtons);
        this.vista.cmbExamen.setFont(fontButtons);
        this.vista.tAreaObservaciones.setFont(fontButtons);
        this.vista.txtTotal.setFont(fontButtons);
        this.vista.txtAbono.setFont(fontButtons);
        this.vista.txtSaldo.setFont(fontButtons);
        this.vista.dataTable.setFont(fontButtons);

        this.vista.setTitle("Ordenes de Servicio");
        this.vista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.vista.setContentPane(this.vista.panel1);
        this.vista.pack();
        this.vista.setVisible(true);
        this.vista.setSize(1000, 650);
        this.vista.setLocationRelativeTo(null);
        this.vista.txtNumero.setEditable(false);
        this.vista.txtTotal.setEditable(false);
        this.vista.txtAbono.setEditable(false);
        this.vista.txtSaldo.setEditable(false);
        llenarMarcota();
        llenarVeterinario();
        llenarExame();
        limpiar();
        cargarConsecutivo();

        DefaultTableModel model = (DefaultTableModel) vista.dataTable.getModel();
        model.setRowCount(0); // Limpiar filas existentes
        model.setColumnIdentifiers(new Object[]{"Examen", "Valor", "Cantidad", "Total", "Observaciones"});
        model.addTableModelListener((TableModelEvent e) -> dataTableControlModif(e));
    }

    private void limpiar() {
        this.vista.txtNumero.setText("");
        this.vista.txtFecha.setText("");
        this.vista.cmbMascota.setSelectedIndex(0);
        this.vista.cmbVeterinario.setSelectedIndex(0);
        this.vista.cmbExamen.setSelectedIndex(0);
        this.vista.txtPropietario.setText("");
        this.vista.tAreaObservaciones.setText("");
        DefaultTableModel tableModel = (DefaultTableModel) this.vista.dataTable.getModel();
        tableModel.setRowCount(0);
        LocalDate cld = LocalDate.now();
        this.vista.txtFecha.setText(String.valueOf(cld));
    }

    private void cargarConsecutivo() {
        this.vista.txtNumero.setText(String.valueOf(new MasterDao().autoIncrement("OS") + 1));
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
        if (e.getSource() == vista.btnAgregar) {
            if (vista.cmbExamen.getSelectedIndex() < 1) {
                JOptionPane.showMessageDialog(null, "Seleccione un examen", "Validación", JOptionPane.WARNING_MESSAGE);
            } else {
                ExamenDao examenDao = new ExamenDao();
                Examen examen = new Examen();
                examen.setExaDescripcion(vista.cmbExamen.getSelectedItem().toString());
                if (examenDao.buscarByDescripcion(examen)) {
                    DefaultTableModel model = (DefaultTableModel) vista.dataTable.getModel();
                    model.addRow(new Object[]{examen.getExaDescripcion()
                            , examen.getExaValor()
                            , 1
                            , examen.getExaValor()
                            , ""
                    });
                    calcTotal();
                }
            }
        } else if (e.getSource() == this.vista.btnAdicionar) {
            String strNumeroOrden = this.vista.txtNumero.getText();
            String strFechaOrden = this.vista.txtFecha.getText();
            int itemSelMascota = this.vista.cmbMascota.getSelectedIndex();
            int itemSelVeterinario = this.vista.cmbVeterinario.getSelectedIndex();
            int itemSelExamen = this.vista.cmbExamen.getSelectedIndex();
            String strTotal = this.vista.txtTotal.toString();
            int rowsDataTable = this.vista.dataTable.getRowCount();
            if (strNumeroOrden.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Campo número se encuentra vacío", "Validación", JOptionPane.WARNING_MESSAGE);
            } else if (strFechaOrden.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Campo fecha se encuentra vacío", "Validación", JOptionPane.WARNING_MESSAGE);
            } else if (itemSelMascota < 1) {
                JOptionPane.showMessageDialog(null, "Seleccione una mascota", "Validación", JOptionPane.WARNING_MESSAGE);
            } else if (itemSelVeterinario < 1) {
                JOptionPane.showMessageDialog(null, "Seleccione un veterinario", "Validación", JOptionPane.WARNING_MESSAGE);
            } else if (itemSelExamen < 1) {
                JOptionPane.showMessageDialog(null, "Seleccione un examen", "Validación", JOptionPane.WARNING_MESSAGE);
            } else if (strTotal.isEmpty() || rowsDataTable < 1) {
                JOptionPane.showMessageDialog(null, "Seleccione y agregue un examen", "Validación", JOptionPane.WARNING_MESSAGE);
            } else {
                if (registrarOrdenServicio() && registrarOrdenServicioDetalle() && actualizarConsecutivo()) {
                    JOptionPane.showMessageDialog(null, "Orden de servicio registrada exitosamente", "Validación", JOptionPane.INFORMATION_MESSAGE);
                    limpiar();
                    cargarConsecutivo();
                }
            }
        } else if (e.getSource() == this.vista.btnLimpiar) {
            limpiar();
            cargarConsecutivo();
        }
    }

    private boolean registrarOrdenServicio() {
        String strNumeroOrden = this.vista.txtNumero.getText();
        String strFechaOrden = this.vista.txtFecha.getText();
        String strTotal = this.vista.txtTotal.getText().toString();
        LocalDate cld = LocalDate.now();
        String fecha[] = this.vista.txtFecha.getText().split("-");
        int total = Integer.parseInt(strTotal);
        Veterinario ve = new Veterinario();
        Mascota ma = new Mascota();
        Examen ex = new Examen();
        ve.setVetNombre(this.vista.cmbVeterinario.getSelectedItem().toString());
        ma.setMasNombre(this.vista.cmbMascota.getSelectedItem().toString());
        ex.setExaDescripcion(this.vista.cmbExamen.getSelectedItem().toString());
        new VeterinarioDao().buscarByNombre(ve);
        new MascotaDao().buscarByNombre(ma);
        new ExamenDao().buscarByDescripcion(ex);
        OrdenServicioDao dao = new OrdenServicioDao();
        OrdenServicio os = new OrdenServicio();
        os.setOsId(Integer.parseInt(strNumeroOrden));
        os.setOsFecha(new Date(
                Integer.parseInt(fecha[0]) - 1900,
                Integer.parseInt(fecha[1]) - 1,
                Integer.parseInt(fecha[2])
        ));
        os.setOsVetId(ve.getVetId());
        os.setOsMasId(ma.getMasId());
        os.setOsMasPropietario("");
        os.setOsEstado("Activo");
        os.setOsValorTotal(total);
        os.setOsAbono(0);
        os.setOsObservaciones("");
        os.setOsObservacionesResultados("");
        os.setOsUsuCrea("");
        os.setOsFechaCrea(Date.valueOf(cld));
        os.setOsUsuAnula("");
        os.setOsFechaAnula(null);
        os.setOsRazonAnula("");
        os.setOsUsuResultadoCrea("");
        return dao.adicionar(os);
    }

    private boolean registrarOrdenServicioDetalle() {
        boolean b = false;
        DefaultTableModel dtm = (DefaultTableModel) this.vista.dataTable.getModel();
        int numeroOrden = Integer.parseInt(this.vista.txtNumero.getText().toString());
        int n = dtm.getRowCount();
        for (int i = 0; i < n; i++) {
//            model.setColumnIdentifiers(new Object[]{"Examen", "Valor", "Cantidad", "Total", "Observaciones"});
            Examen e = new Examen();
            e.setExaDescripcion(dtm.getValueAt(i, 0).toString());
            new ExamenDao().buscarByDescripcion(e);
            OrdenServicioDetalle osd = new OrdenServicioDetalle();
            osd.setDosOsId(numeroOrden);
            osd.setDosExaId(e.getExaId());
            osd.setDosExaValor(Integer.parseInt(dtm.getValueAt(i, 1).toString()));
            osd.setDosExaCantidad(dtm.getValueAt(i, 2).toString());
            osd.setDosExaObservaciones(dtm.getValueAt(i, 4).toString());
            OrdenServicioDetalleDao dao = new OrdenServicioDetalleDao();
            b = dao.adicionar(osd);
        }
        return b;
    }

    private boolean actualizarConsecutivo() {
        int consec = Integer.parseInt(this.vista.txtNumero.getText().toString());
        boolean b = false;
        ConsecutivoDocumento cd = new ConsecutivoDocumento();
        cd.setTdSigla("OS");
        ConsecutivoDocumentosDao dao = new ConsecutivoDocumentosDao();
        if (!dao.buscarId(cd)) {
            cd.setTdSigla("OS");
            cd.setNitEmpresa("");
            cd.setUltimoCreado(String.valueOf(consec));
            b = dao.adicionar(cd);
        } else {
            cd.setUltimoCreado(String.valueOf(consec));
            b = dao.modificar(cd);
        }
        return b;
    }

    private void calcTotal() {
        int tot = 0;
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
