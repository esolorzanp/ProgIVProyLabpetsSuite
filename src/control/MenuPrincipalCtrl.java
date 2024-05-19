package control;

import dao.*;
import model.*;
import view.*;

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

        /*
         * Menú maestros
         */
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

        this.vista.miParametros = new JMenuItem("Parámetros");
        this.vista.miParametros.setFont(fontLabels);
        this.vista.miParametros.addActionListener(this);
        this.vista.jmMaestros.add(this.vista.miParametros);

        this.vista.miRazas = new JMenuItem("Razas");
        this.vista.miRazas.setFont(fontLabels);
        this.vista.miRazas.addActionListener(this);
        this.vista.jmMaestros.add(this.vista.miRazas);

        this.vista.miMascotas = new JMenuItem("Mascotas");
        this.vista.miMascotas.setFont(fontLabels);
        this.vista.miMascotas.addActionListener(this);
        this.vista.miMascotas.setEnabled(false);
        this.vista.jmMaestros.add(this.vista.miMascotas);

        this.vista.miTerceros = new JMenuItem("Terceros");
        this.vista.miTerceros.setFont(fontLabels);
        this.vista.miTerceros.addActionListener(this);
        this.vista.jmMaestros.add(this.vista.miTerceros);

        this.vista.miFormasPago = new JMenuItem("Formas de pago");
        this.vista.miFormasPago.setFont(fontLabels);
        this.vista.miFormasPago.addActionListener(this);
        this.vista.jmMaestros.add(this.vista.miFormasPago);

        this.vista.miVeterinarios = new JMenuItem("Veterinarios");
        this.vista.miVeterinarios.setFont(fontLabels);
        this.vista.miVeterinarios.addActionListener(this);
        this.vista.miVeterinarios.setEnabled(false);
        this.vista.jmMaestros.add(this.vista.miVeterinarios);

        this.vista.miSalir = new JMenuItem("Salir");
        this.vista.miSalir.setFont(fontLabels);
        this.vista.miSalir.addActionListener(this);
        this.vista.jmMaestros.add(this.vista.miSalir);

        /*
         * Menú Movimientos
         */
        this.vista.jmMovimientos = new JMenu("Movimientos");
        this.vista.jmMovimientos.setFont(fontLabels);
        this.vista.jMenuBar.add(this.vista.jmMovimientos);

        this.vista.miOrdenesServicio = new JMenuItem("Ordenes de servicio");
        this.vista.miOrdenesServicio.setFont(fontLabels);
        this.vista.miOrdenesServicio.addActionListener(this);
        this.vista.jmMovimientos.add(this.vista.miOrdenesServicio);

        this.vista.miResultados = new JMenuItem("Resultados");
        this.vista.miResultados.setFont(fontLabels);
        this.vista.miResultados.addActionListener(this);
        this.vista.miResultados.setEnabled(false);
        this.vista.jmMovimientos.add(this.vista.miResultados);

        this.vista.miEgresos = new JMenuItem("Egresos");
        this.vista.miEgresos.setFont(fontLabels);
        this.vista.miEgresos.addActionListener(this);
        this.vista.miEgresos.setEnabled(false);
        this.vista.jmMovimientos.add(this.vista.miEgresos);

        /*
         * Menú seguridades
         */
        this.vista.jmMSeguridades = new JMenu("Seguridades");
        this.vista.jmMSeguridades.setFont(fontLabels);
        this.vista.jMenuBar.add(this.vista.jmMSeguridades);

        this.vista.miPerfiles = new JMenuItem("Perfiles");
        this.vista.miPerfiles.setFont(fontLabels);
        this.vista.miPerfiles.addActionListener(this);
        this.vista.jmMSeguridades.add(this.vista.miPerfiles);

        this.vista.miUsuarios = new JMenuItem("Usuarios");
        this.vista.miUsuarios.setFont(fontLabels);
        this.vista.miUsuarios.addActionListener(this);
        this.vista.miUsuarios.setEnabled(false);
        this.vista.jmMSeguridades.add(this.vista.miUsuarios);

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
            EspecieCtrl ctrl = new EspecieCtrl(model, dao, vista);
            ctrl.iniciar();
        }
        if (e.getSource() == this.vista.miExamenes) {
            Examen model = new Examen();
            ExamenDao dao = new ExamenDao();
            ExamenFrm vista = new ExamenFrm();
            ExamenCtrl ctrl = new ExamenCtrl(model, dao, vista);
            ctrl.iniciar();
        }
        if (e.getSource() == this.vista.miParametros) {
            Parametro model = new Parametro();
            ParametroDao dao = new ParametroDao();
            ParametroFrm vista = new ParametroFrm();
            ParametroCtrl ctrl = new ParametroCtrl(model, dao, vista);
            ctrl.iniciar();
        }
        if (e.getSource() == this.vista.miRazas) {
            Raza model = new Raza();
            RazaDao dao = new RazaDao();
            RazaFrm vista = new RazaFrm();
            RazaCtrl ctrl = new RazaCtrl(model, dao, vista);
            ctrl.iniciar();
        }
        if (e.getSource() == this.vista.miMascotas) {
            Mascota model = new Mascota();
            MascotaDao dao = new MascotaDao();
            MascotaFrm vista = new MascotaFrm();
//            MascotaCtrl ctrl = new MascotaCtrl(model, dao, vista);
//            ctrl.iniciar();
        }
        if (e.getSource() == this.vista.miTerceros) {
            Tercero model = new Tercero();
            TerceroDao dao = new TerceroDao();
            TerceroFrm vista = new TerceroFrm();
            TerceroCtrl ctrl = new TerceroCtrl(model, dao, vista);
            ctrl.iniciar();
        }
        if (e.getSource() == this.vista.miVeterinarios) {
            Veterinario model = new Veterinario();
            VeterinarioDao dao = new VeterinarioDao();
//            VeterinarioFrm vista = new VeterinarioFrm();
//            VeterinarioCtrl ctrl = new VeterinarioCtrl(model, dao, vista);
//            ctrl.iniciar();
        }
        if (e.getSource() == this.vista.miFormasPago) {
            FormaPago model = new FormaPago();
            FormaPagoDao dao = new FormaPagoDao();
            FormaPagoFrm vista = new FormaPagoFrm();
            FormaPagoCtrl ctrl = new FormaPagoCtrl(model, dao, vista);
            ctrl.iniciar();
        }
        if (e.getSource() == this.vista.miOrdenesServicio) {
            OrdenesServicioFrm vista = new OrdenesServicioFrm();
            FrmOrdenServicioCtrl ctrl = new FrmOrdenServicioCtrl(vista);
            ctrl.iniciar();
        }
        if (e.getSource() == this.vista.miPerfiles) {
            Perfil model = new Perfil();
            PerfilDao dao = new PerfilDao();
            PerfilFrm vista = new PerfilFrm();
            PerfilCtrl ctrl = new PerfilCtrl(model, dao, vista);
            ctrl.iniciar();
        }
        if (e.getSource() == this.vista.miUsuarios) {
            Usuario model = new Usuario();
            UsuarioDao dao = new UsuarioDao();
//            UsuarioFrm vista = new UsuarioFrm();
//            UsuarioCtrl ctrl = new UsuarioCtrl(model, dao, vista);
//            ctrl.iniciar();
        }
        if (e.getSource() == this.vista.miResultados) {
//            Usuario model = new Usuario();
//            UsuarioDao dao = new UsuarioDao();
//            UsuarioFrm vista = new UsuarioFrm();
//            UsuarioCtrl ctrl = new UsuarioCtrl(model, dao, vista);
//            ctrl.iniciar();
        }
        if (e.getSource() == this.vista.miEgresos) {
//            Usuario model = new Usuario();
//            UsuarioDao dao = new UsuarioDao();
//            UsuarioFrm vista = new UsuarioFrm();
//            UsuarioCtrl ctrl = new UsuarioCtrl(model, dao, vista);
//            ctrl.iniciar();
        }
        if (e.getSource() == this.vista.miSalir) {
            System.exit(0);
        }
    }
}
