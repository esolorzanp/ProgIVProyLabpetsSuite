package control;

import dao.EspecieDao;
import model.Especie;
import view.EspecieView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class EspecieControl implements ActionListener, KeyListener, MouseListener {
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
        this.view.dataTable.addKeyListener(this);
        this.view.dataTable.addMouseListener(this);
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
                JOptionPane.showMessageDialog(null, "Registro creado exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
                this.limpiar();
            }
        }
        if (e.getSource() == this.view.updateButton) {
            this.model.setEspId(Integer.parseInt(this.view.idTextField.getText()));
            this.model.setEspDescripcion(this.view.especieTextField.getText());
            this.model.setEspEstado(String.valueOf(this.view.estadoComboBox.getSelectedItem()));
            if (dao.update(this.model)) {
                listar();
                JOptionPane.showMessageDialog(null, "Registro modificado exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
                this.limpiar();
            }
        }
        if (e.getSource() == this.view.deleteButton) {
            this.model.setEspId(Integer.parseInt(this.view.idTextField.getText()));
            if (dao.delete(this.model)) {
                listar();
                JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
                this.limpiar();
            }
        }

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 38 || e.getKeyCode() == 40) {
            this.getRowSelected();
        }

    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1) {
            this.getRowSelected();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

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

    private void getRowSelected() {
        int row = this.view.dataTable.getSelectedRow();
        this.model.setEspId((Integer) this.view.dataTable.getModel().getValueAt(row, 0));
        this.model = dao.getById(this.model);
        this.view.idTextField.setText(String.valueOf(this.model.getEspId()));
        this.view.especieTextField.setText(this.model.getEspDescripcion());
        this.view.estadoComboBox.setSelectedItem(this.model.getEspEstado());
    }
}
