package control;

import dao.EspecieDao;
import model.Especie;
import view.EspecieView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EspecieControl implements ActionListener {
    private Especie model;
    private final EspecieDao dao;
    private final EspecieView view;
    private Especie eForm;

    public EspecieControl(Especie model, EspecieDao dao, EspecieView view) {
        this.model = model;
        this.dao = dao;
        this.view = view;
        this.view.clearButton.addActionListener(this);
        this.view.searchButton.addActionListener(this);
        this.view.addButton.addActionListener(this);
        this.view.updateButton.addActionListener(this);
        this.view.deleteButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.view.searchButton) {
            this.model.setEspId(Integer.parseInt(this.view.searchTextField.getText()));
            if (dao.exist(this.model)) {
                this.limpiar();
                this.model = dao.getById(this.model);
                this.view.idTextField.setText(String.valueOf(this.model.getEspId()));
                this.view.especieTextField.setText(this.model.getEspDescripcion());
                this.view.estadoComboBox.setSelectedItem(this.model.getEspEstado());
            }
        }
        if (e.getSource() == this.view.clearButton)
            this.limpiar();
        if (e.getSource() == this.view.addButton) {
            this.model.setEspDescripcion(this.view.especieTextField.getText());
            this.model.setEspEstado(String.valueOf(this.view.estadoComboBox.getSelectedItem()));
            if (dao.insert(this.model)) {
                listar();
                JOptionPane.showMessageDialog(null, "Registro grabado exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
                this.limpiar();
            }
        }
        if (e.getSource() == this.view.updateButton) {
            this.model.setEspId(Integer.parseInt(this.view.idTextField.getText()));
            this.model.setEspDescripcion(this.view.especieTextField.getText());
            this.model.setEspEstado(String.valueOf(this.view.estadoComboBox.getSelectedItem()));
            if (dao.update(this.model)) {
                listar();
                JOptionPane.showMessageDialog(null, "Registro grabado exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
                this.limpiar();
            }
        }
    }

    private void limpiar() {
        this.view.especieTextField.setText("");
        this.view.idTextField.setText("");
        this.view.searchTextField.setText("");
        this.view.estadoComboBox.setSelectedIndex(-1);
    }

    private void listar() {
        DefaultTableModel model = (DefaultTableModel) view.dataTable.getModel();
        model.setColumnIdentifiers(new Object[]{"Id", "Especie", "Estado"});
        model.setRowCount(0);
        for (Especie e : dao.getAll(1)) {
            model.addRow(new Object[]{e.getEspId(), e.getEspDescripcion(), e.getEspEstado()});
        }
    }

    public void iniciar() {
        this.view.estadoComboBox.addItem("Activo");
        this.view.estadoComboBox.addItem("Inactivo");
        this.view.estadoComboBox.setSelectedIndex(-1);

        this.view.setTitle("Especies");
        this.view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.view.setContentPane(this.view.panel1);
        this.view.pack();
        this.view.setVisible(true);
        this.view.setSize(600, 450);
        this.view.setLocationRelativeTo(null);
        this.listar();
    }

}
