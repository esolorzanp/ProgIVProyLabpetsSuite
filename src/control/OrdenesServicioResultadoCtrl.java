package control;

import dao.*;
import model.*;
import view.OrdenesServicioResultadoFrm;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class OrdenesServicioResultadoCtrl implements ActionListener {
    private OrdenesServicioResultadoFrm vista;

    public OrdenesServicioResultadoCtrl(OrdenesServicioResultadoFrm vista) {
        this.vista = vista;
        this.vista.btnBuscar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
        this.vista.btnAdicionar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vista.btnLimpiar) {
            limpiar();
        } else if (e.getSource() == this.vista.btnBuscar) {
            String strBuscar = this.vista.txtBuscar.getText().toString();
            if (strBuscar.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingresa un npymero de orden a buscar", "Validación", JOptionPane.WARNING_MESSAGE);
            } else {
                cargarOrdenServicio();
            }
        } else if (e.getSource() == this.vista.btnAdicionar) {
            DefaultTableModel dtm = (DefaultTableModel) this.vista.dataTable.getModel();
            int n = dtm.getRowCount();
            if (n < 1) {
                JOptionPane.showMessageDialog(null, "Debe cargar una orden de servicio para procesar", "Validación", JOptionPane.WARNING_MESSAGE);
            } else if (!validaTodosResultadosCapturados()) {
                JOptionPane.showMessageDialog(null, "Tiene resultados sin capturar", "Validación", JOptionPane.WARNING_MESSAGE);
            } else if (registrarOrdenServicioResultado()) {
                JOptionPane.showMessageDialog(null, "Registros guardados exitosamente", "Infomrmación", JOptionPane.INFORMATION_MESSAGE);
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Se presentó un inconveniente para guardar los registros", "Validación", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean cargarOrdenServicio() {
        String strBuscar = this.vista.txtBuscar.getText().toString();
        OrdenServicio os = new OrdenServicio();
        os.setOsId(Integer.parseInt(strBuscar));
        new OrdenServicioDao().buscarId(os);
        Mascota m = new Mascota();
        m.setMasId(os.getOsMasId());
        new MascotaDao().buscarId(m);
        Veterinario v = new Veterinario();
        v.setVetId(os.getOsVetId());
        new VeterinarioDao().buscarId(v);
        this.vista.txtNumero.setText(String.valueOf(os.getOsId()));
        this.vista.txtFecha.setText(String.valueOf(os.getOsFecha()));
        this.vista.txtMascota.setText(m.getMasNombre());
        this.vista.txtVeterinario.setText(v.getVetNombre());
        cargarExamenesParametros(os);
        return true;
    }

    private boolean cargarExamenesParametros(OrdenServicio os) {
        String strBuscar = this.vista.txtBuscar.getText().toString();

        OrdenServicioDetalleDao osdDao = new OrdenServicioDetalleDao();
        DefaultTableModel dtm = (DefaultTableModel) this.vista.dataTable.getModel();
        dtm.setRowCount(0);
        for (OrdenServicioDetalle osd : osdDao.getTodos(os)) {
            ExamenParametroDao epDao = new ExamenParametroDao();
            Examen ex = new Examen();
            ex.setExaId(osd.getDosExaId());
            new ExamenDao().buscarId(ex);
            for (ExamenParametro ep : epDao.getTodos(ex)) {
                Parametro p = new Parametro();
                p.setParaId(ep.getEpParaId());
                new ParametroDao().buscarId(p);
                dtm.addRow(new Object[]{
                        ex.getExaId(),
                        ex.getExaDescripcion(),
                        p.getParaId(),
                        p.getParaDescripcion(),
                        p.getParaFormula(),
                        p.getParaReferencia1(),
                        p.getParaReferencia2(),
                        ""
                });
            }
        }
        return true;
    }

    public void iniciar() {
        Font fontLabels = new Font("Verdana", Font.PLAIN, 16);
        Font fontButtons = new Font("Verdana", Font.PLAIN, 16);
        this.vista.btnBuscar.setFont(fontButtons);
        this.vista.btnLimpiar.setFont(fontButtons);
        this.vista.btnAdicionar.setFont(fontButtons);
        this.vista.lblOrdenServicio.setFont(fontLabels);
        this.vista.lblBuscar.setFont(fontLabels);
        this.vista.lblNumero.setFont(fontLabels);
        this.vista.lblFecha.setFont(fontLabels);
        this.vista.lblMascota.setFont(fontLabels);
        this.vista.lblVeterinario.setFont(fontLabels);
        this.vista.lblResultado.setFont(fontLabels);
        this.vista.txtBuscar.setFont(fontButtons);
        this.vista.txtNumero.setFont(fontButtons);
        this.vista.txtFecha.setFont(fontButtons);
        this.vista.txtMascota.setFont(fontButtons);
        this.vista.txtVeterinario.setFont(fontButtons);
        this.vista.dataTable.setFont(fontButtons);
        this.vista.txtNumero.setEditable(false);
        this.vista.txtFecha.setEditable(false);
        this.vista.txtMascota.setEditable(false);
        this.vista.txtVeterinario.setEditable(false);

        this.vista.setTitle("Ordenes de Servicio");
        this.vista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.vista.setContentPane(this.vista.panel1);
        this.vista.pack();
        this.vista.setVisible(true);
        this.vista.setSize(1000, 650);
        this.vista.setLocationRelativeTo(null);

        DefaultTableModel model = (DefaultTableModel) vista.dataTable.getModel();
        model.setRowCount(0); // Limpiar filas existentes
        model.setColumnIdentifiers(new Object[]{"Id Examen",
                "Descripción examen",
                "Id parámetro",
                "Descripción parámetro",
                "Formula",
                "Referencia 1",
                "Referencia 2",
                "Resultado"
        });
//        model.addTableModelListener((TableModelEvent e) -> dataTableControlModif(e));
    }

    private void limpiar() {
        this.vista.txtBuscar.setText("");
        this.vista.txtNumero.setText("");
        this.vista.txtFecha.setText("");
        this.vista.txtMascota.setText("");
        this.vista.txtVeterinario.setText("");
        DefaultTableModel model = (DefaultTableModel) this.vista.dataTable.getModel();
        model.setRowCount(0);
    }

    private boolean registrarOrdenServicioResultado() {
        DefaultTableModel dtm = (DefaultTableModel) this.vista.dataTable.getModel();
        int n = dtm.getRowCount();
        String strBuscar = this.vista.txtBuscar.getText().toString();
        OrdenServicio os = new OrdenServicio();
        os.setOsId(Integer.parseInt(strBuscar));
        new OrdenServicioDao().buscarId(os);
        OrdenesServicioResultadoDao dao = new OrdenesServicioResultadoDao();
        LocalDateTime cld = LocalDateTime.now();
        for (int i = 0; i < n; i++) {
            OrdenesServicioResultado osr = new OrdenesServicioResultado();
            osr.setOsId(os.getOsId());
            osr.setDosExaId((Integer) dtm.getValueAt(i, 0));
            osr.setParaId((Integer) dtm.getValueAt(i, 2));
            osr.setOsrResultado(dtm.getValueAt(i, 7).toString());
            osr.setUsuCrea("");
            osr.setFechaCrea(Timestamp.valueOf(cld));
            dao.adicionar(osr);
        }
        return true;
    }

    private boolean validaTodosResultadosCapturados() {
        DefaultTableModel dtm = (DefaultTableModel) this.vista.dataTable.getModel();
        int n = dtm.getRowCount();
        for (int i = 0; i < n; i++) {
            if (dtm.getValueAt(i, 7).toString().isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
