package com.github.sysnote8main.BudgetingApp.Swings;

import com.github.sysnote8main.BudgetingApp.App;
import com.github.sysnote8main.BudgetingApp.Enums.CategoryTypes;
import com.github.sysnote8main.BudgetingApp.Modules.SimpleDate;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class NewDataForm extends JFrame {
    private JTextField NameField;
    private JTextField DetailField;
    private JButton SaveButton;
    private JButton SaveQuitButton;
    private JButton NoSaveQuitButton;
    private JComboBox CategoryComboBox;
    private JPanel InputPanel;
    private JPanel CalenderPanel;
    private JPanel ButtonPanel;
    public JPanel MainPanel;
    private JLabel NameLabel;
    private JLabel DetailLabel;
    private JLabel CategoryLabel;
    private JLabel DateLabel;
    private JSpinner MoneySpinner;
    private JComboBox<String> MoneyComboBox;
    private JComboBox DayCombo;
    private JComboBox MonthCombo;
    private JComboBox YearCombo;
    private final String[] MoneyComboBoxData = {"出金","入金"};
    private final HashMap<String, CategoryTypes> CategoryLang = App.lang.CategoryTranslations;
    private boolean isWithdraw = false;
    private static int now_year = SimpleDate.getYear();
    private static int getMoney() {
        return 0;
    }
    private static int toInt(Object obj) {
        try {
            return Integer.valueOf(obj.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void setup() {
        for(String s:MoneyComboBoxData) {
            MoneyComboBox.addItem(s);
        }
        for(String s:CategoryLang.keySet()) {
            CategoryComboBox.addItem(s);
        }
        for(int i=0;i<=10;i++) {
            YearCombo.addItem(now_year-i);
        }
        for(int i=1;i<=12;i++) {
            MonthCombo.addItem(i);
        }
        for(int i=1;i<=SimpleDate.getDaysOnMonth(1);i++) {
            DayCombo.addItem(i);
        }
    }

    private void Error_get() {
        throw new Error("Error while in getting data from form.");
    }

    private void Save() {
            String name = NameField.getText();
            String CategoryComboSelect = CategoryComboBox.getSelectedItem().toString();
            if(!name.equals("")) {
                int money = Integer.parseInt(MoneySpinner.getValue().toString());
                if(money!=0) {
                    CategoryTypes category = CategoryLang.get(CategoryComboSelect);
                    int deposit = 0, withdraw = 0;
                    if (isWithdraw) {
                        withdraw = money;
                    } else {
                        deposit = money;
                    }
                    String summary = DetailField.getText();
                    int year = toInt(YearCombo.getSelectedItem());
                    int month = toInt(MonthCombo.getSelectedItem());
                    int day = toInt(DayCombo.getSelectedItem());
                    App.insertData(name, category, summary, deposit, withdraw, year, month, day, SimpleDate.getDay_of_month());
                } else {
                    JOptionPane.showMessageDialog(this,"金額を入力してください。","No money action",JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this,"名前は必ず必要です。","Name is null",JOptionPane.WARNING_MESSAGE);
            }
    }

    private void Quit() {
        dispose();
    }

    public NewDataForm() {
        this.setContentPane(MainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setup();
        MoneyComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = MoneyComboBox.getSelectedItem().toString();
                if(s.equals("出金")) {
                    isWithdraw = true;
                } else {
                    isWithdraw = false;
                }
            }
        });
        SaveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Save();
            }
        });
        SaveQuitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Save();
                Quit();
            }
        });
        NoSaveQuitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Quit();
            }
        });
        MonthCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DayCombo.removeAllItems();
                for(int i=1;i<=SimpleDate.getDaysOnMonth(Integer.parseInt(MonthCombo.getSelectedItem().toString()));i++) {
                    DayCombo.addItem(i);
                }
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
