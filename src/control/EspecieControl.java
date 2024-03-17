package control;

import dao.EspecieDao;
import model.Especie;
import view.EspecieView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EspecieControl implements ActionListener {
    private final Especie model;
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
        if (e.getSource() == this.view.clearButton)
            onClearForm();
        if (e.getSource() == this.view.searchButton)
            onSearch();
        if (e.getSource() == this.view.addButton)
            onAdd();
        if (e.getSource() == this.view.updateButton)
            onUpdate();
        if (e.getSource() == this.view.deleteButton)
            onDelete();
    }

    private void onSearch() {
        String strSeasrch = view.searchTextField.getText();
        if (strSeasrch.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese una especie a buscar", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (!dao.exist(strSeasrch)) {
            JOptionPane.showMessageDialog(null, "Especie no existe", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            Especie e = dao.getByEspecie(strSeasrch);
            setFieldsForm(e);
        }
    }

    private void onAdd() {
        if (validateFieldsForm()) {
            Especie e = this.getFieldsForm();
            if (dao.exist(e.getEspDescripcion())) {
                JOptionPane.showMessageDialog(null, "Especie ya existe", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!dao.insert(e)) {
                JOptionPane.showMessageDialog(null, "Registro no guardado", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Registro grabado exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private boolean validateFieldsForm() {
        String strEspecie = view.especieTextField.getText();
        int intItem = view.estadoComboBox.getSelectedIndex();
        String strEstado = String.valueOf(view.estadoComboBox.getSelectedItem());
        if (strEspecie.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campo Especie no puede estar vacio", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (intItem == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione un estado", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (strEstado.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un estado", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private Especie getFieldsForm() {
        String strEspecie = view.especieTextField.getText();
        String strEstado = String.valueOf(view.estadoComboBox.getSelectedItem());
        int intId = dao.exist(strEspecie) ? dao.getByEspecie(strEspecie).getEspId() : -1;
        this.eForm = new Especie(intId, strEspecie, strEstado);
        return eForm;
    }

    private void setFieldsForm(Especie e) {
        this.view.especieTextField.setText(e.getEspDescripcion());
        this.view.estadoComboBox.setSelectedItem(e.getEspEstado());
    }

    private void onUpdate() {
        if (validateFieldsForm()) {
            Especie e = this.getFieldsForm();
            if (!dao.exist(e.getEspDescripcion())) {
                JOptionPane.showMessageDialog(null, "Especie no existe", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!dao.update(e)) {
                JOptionPane.showMessageDialog(null, "Registro no guardado", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Registro grabado exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void onDelete() {
        if (validateFieldsForm()) {
            Especie e = this.getFieldsForm();
            if (!dao.exist(e.getEspDescripcion())) {
                JOptionPane.showMessageDialog(null, "Especie no existe", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!dao.delete(e.getEspId())) {
                JOptionPane.showMessageDialog(null, "Registro no eliminado", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void onClearForm() {
        this.view.especieTextField.setText("");
        this.view.searchTextField.setText("");
        this.view.estadoComboBox.setSelectedIndex(-1);
//        System.out.println("Button clear selected");
    }

    public void iniciar() {
        this.view.estadoComboBox.addItem("Activo");
        this.view.estadoComboBox.addItem("Inactivo");
        this.view.estadoComboBox.setSelectedIndex(-1);

        this.view.setTitle("Especies");
        this.view.setSize(500, 300);
        this.view.setContentPane(this.view.panel1);
        this.view.setLocationRelativeTo(null);
        this.view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.view.pack();
        this.view.setVisible(true);
    }

}
