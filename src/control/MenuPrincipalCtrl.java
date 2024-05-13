package control;

import dao.EspecieDao;
import dao.ExamenDao;
import model.Especie;
import model.Examen;
import view.EspecieView;
import view.ExamenFrm;
import view.MenuPrincipalFrm;
import view.OrdenesServicioFrm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipalCtrl implements ActionListener {
    MenuPrincipalFrm vista;

    public MenuPrincipalCtrl(MenuPrincipalFrm vista) {
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

        this.vista.jMenuBar = new JMenuBar();
        this.vista.setJMenuBar(this.vista.jMenuBar);

        this.vista.jmMaestros = new JMenu("Maestros");
        this.vista.jmMaestros.setFont(fontLabels);
        this.vista.jMenuBar.add(this.vista.jmMaestros);

        this.vista.miEspecies = new JMenuItem("Especies");
        this.vista.miEspecies.setFont(fontLabels);
        this.vista.miEspecies.addActionListener(this);
        this.vista.jmMaestros.add(this.vista.miEspecies);

        this.vista.miExamenes = new JMenuItem("Exámenes");
        this.vista.miExamenes.setFont(fontLabels);
        this.vista.miExamenes.addActionListener(this);
        this.vista.jmMaestros.add(this.vista.miExamenes);

        this.vista.miSalir = new JMenuItem("Salir");
        this.vista.miSalir.setFont(fontLabels);
        this.vista.miSalir.addActionListener(this);
        this.vista.jmMaestros.add(this.vista.miSalir);

        this.vista.jmMovimientos = new JMenu("Movimientos");
        this.vista.jmMovimientos.setFont(fontLabels);
        this.vista.jMenuBar.add(this.vista.jmMovimientos);

        this.vista.miOrdenesServicio = new JMenuItem("Ordenes de servicio");
        this.vista.miOrdenesServicio.setFont(fontLabels);
        this.vista.miOrdenesServicio.addActionListener(this);
        this.vista.jmMovimientos.add(this.vista.miOrdenesServicio);

        this.vista.setTitle("LabPets App");
        this.vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.vista.setContentPane(this.vista.panel1);
        this.vista.pack();
        this.vista.setVisible(true);
        this.vista.setSize(600, 450);
//        this.vista.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.vista.setLocationRelativeTo(null);
        this.vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vista.miEspecies) {
            Especie model = new Especie();
            EspecieDao dao = new EspecieDao();
            EspecieView vista = new EspecieView();
            EspecieCtrl ctrl = new EspecieCtrl(model,dao,vista);
            ctrl.iniciar();
        }
        if (e.getSource() == this.vista.miExamenes) {
            Examen model = new Examen();
            ExamenDao dao = new ExamenDao();
            ExamenFrm vista = new ExamenFrm();
            ExamenCtrl ctrl = new ExamenCtrl(model,dao,vista);
            ctrl.iniciar();
        }
        if (e.getSource() == this.vista.miOrdenesServicio) {
            OrdenesServicioFrm vista = new OrdenesServicioFrm();
            FrmOrdenServicioCtrl ctrl = new FrmOrdenServicioCtrl(vista);
            ctrl.iniciar();
        }
        if (e.getSource() == this.vista.miSalir) {
            System.exit(0);
        }
    }
}
