package control;

import dao.UsuarioDao;
import model.Usuario;
import view.LoginFrm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginCtrl implements ActionListener {
    private Usuario model;
    private UsuarioDao dao;
    private LoginFrm vista;

    public LoginCtrl(Usuario model, UsuarioDao dao, LoginFrm vista) {
        this.model = model;
        this.dao = dao;
        this.vista = vista;
        this.vista.btnIngresar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vista.btnIngresar) {
            this.ingresar();
        }
        if (e.getSource() == this.vista.btnLimpiar) {
            this.limpiar();
        }

    }

    private void ingresar() {
        this.model.setUsuLogin(String.valueOf(this.vista.txtUsuario.getText()));
        this.model.setUsuPass(String.valueOf(this.vista.txtContrasena.getText()));
        boolean b = false;
        if (this.dao.buscarLogin(this.model))
            if (this.model.getUsuLogin().equals(this.vista.txtUsuario.getText())
                    && this.model.getUsuPass().equals(this.vista.txtContrasena.getText()))
                b = true;
        if (b)
            JOptionPane.showMessageDialog(null, "Bienvenido... ingreso a la aplicación");
        else
            JOptionPane.showMessageDialog(null, "Usuario y/o contraseña incorrectos");
    }

    public void iniciar() {
        Font fontLabels = new Font("Verdana", Font.PLAIN, 16);
        Font fontButtons = new Font("Verdana", Font.PLAIN, 16);
        this.vista.lblUsuario.setFont(fontLabels);
        this.vista.lblContrasena.setFont(fontLabels);
        this.vista.btnIngresar.setFont(fontButtons);
        this.vista.btnLimpiar.setFont(fontButtons);

        this.vista.setTitle("Login");
        this.vista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.vista.setContentPane(this.vista.panel1);
        this.vista.pack();
        this.vista.setVisible(true);
        this.vista.setSize(300, 300);
        this.vista.setLocationRelativeTo(null);
//        this.listar();
    }

    private void limpiar() {
        this.vista.txtUsuario.setText("");
        this.vista.txtContrasena.setText("");
    }
}
