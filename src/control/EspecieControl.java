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
        System.out.println("Button Search selected");
    }

    private void onAdd() {
        System.out.println("Button Add selected");
    }

    private void onUpdate() {
        System.out.println("Button Update selected");
    }

    private void onDelete() {
        System.out.println("Button Delete selected");
    }

    private void onClearForm() {
        this.view.especieTextField.setText("");
        this.view.searchTextField.setText("");
        this.view.estadoComboBox.setSelectedIndex(-1);
        System.out.println("Button clear selected");
    }

    public void iniciar() {
        this.view.setTitle("Especies");
        this.view.setSize(500, 300);
        this.view.setContentPane(this.view.panel1);
        this.view.setLocationRelativeTo(null);
        this.view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.view.pack();
        this.view.setVisible(true);
    }

}
