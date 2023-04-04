package com.github.sysnote8main.BudgetingApp.Swings;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

public class Startup extends JFrame {

    private JPanel AppPanel;
    private JButton ExitButton;
    private JButton NewDataButton;
    private JButton ShowGraphButton;
    private JButton ShowAllDataButton;
    private JLabel WelcomeMessageLabel;
    private JComboBox DataTypeCombo;
    private JPanel GraphPanel;
    private JComboBox ShowTypeCombo;
    private static final NewDataForm NewData_window = new NewDataForm();
    // Window Close Function
    private void Quit() {
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
    // initializer
    public Startup() {
        setContentPane(AppPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        ShowGraphButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        ShowAllDataButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        NewDataButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                NewData_window.Enable();
            }
        });
        ExitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Quit();
            }
        });

        ShowGraphButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                DataGraph.main(new String[]{""});
            }
        });
    }

    public void Enable() {
        setVisible(true);
    }
    public void Disable() {
        setVisible(false);
    }
}
