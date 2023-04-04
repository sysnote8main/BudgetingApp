package com.github.sysnote8main.BudgetingApp.Swings;

import com.github.sysnote8main.BudgetingApp.App;
import com.github.sysnote8main.BudgetingApp.Enums.DataType;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class DataGraph {
    private static DefaultCategoryDataset ChartData = new DefaultCategoryDataset();
    private static JFreeChart chart = null;
    private static int baseBalance = 0;
    private static String Data_type = null, ChartTitle = null, CategoryLabel = null, ValueLabel = null;
    private static int[] Withdraws, Deposits;
    private final HashMap<DataType, String> DatatypeTranslations = App.lang.DatatypeTranslations;
    private static ChartFrame MainFrame;
    private void createChartData() {
        int nowBalance = 0, nowWithdraw, nowDeposit, OutputBalance;
        for(int i=0;i<Withdraws.length;i++) {
            nowWithdraw = Withdraws[i];
            nowDeposit = Deposits[i];
            nowBalance+=nowDeposit-nowWithdraw;
            if(nowBalance<0) {
                OutputBalance = 0;
            } else {
                OutputBalance = nowBalance;
            }
            String nowColumn = String.valueOf(i+1);
            ChartData.addValue(nowWithdraw,"出金",nowColumn);
            ChartData.addValue(nowDeposit,"入金",nowColumn);
            ChartData.addValue(OutputBalance,"現在の残高",nowColumn);
        }
    }

    private void createChart() {
        chart = ChartFactory.createLineChart(ChartTitle,CategoryLabel,ValueLabel,ChartData,PlotOrientation.VERTICAL,true,true,false);
    }
    public DataGraph(String title, DataType datatype, int BaseBalance, int[] Withdraw_list, int[] Deposit_list) {
        if(Withdraw_list.length!=Deposit_list.length) {
            throw new Error("Not match these list length.(List: Withdraw, Deposit)");
        }
        Data_type = DatatypeTranslations.get(datatype);
        baseBalance = BaseBalance;
        Withdraws = Withdraw_list;
        Deposits = Deposit_list;
        ChartTitle = Data_type + "ごとの収支についてのデータ";
        CategoryLabel = Data_type;
        ValueLabel = "金額(円)";
        createChartData();
        createChart();
        MainFrame = new ChartFrame(title,chart);
        MainFrame.setBounds(10,10,400,300);
        MainFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                MainFrame.dispose();
            }
        });
    }

    void setVisible(boolean isVisible) {
        MainFrame.setVisible(isVisible);
    }
    public static void main(String[] args) {
        int[] Withdraw_list = {110,110}, Deposit_list = {120,140};
        DataGraph dtgraph = new DataGraph("データグラフ", DataType.month,0,Withdraw_list,Deposit_list);
        dtgraph.setVisible(true);
    }
}
