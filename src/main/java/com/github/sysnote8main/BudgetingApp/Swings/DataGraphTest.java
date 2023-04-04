package com.github.sysnote8main.BudgetingApp.Swings;

import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;

public class DataGraphTest {
    public static void main(String[] args) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(300, "米国", "2005年");
        dataset.addValue(500, "米国", "2006年");
        dataset.addValue(120, "米国", "2007年");
        JFreeChart subchart = ChartFactory.createLineChart("Title","TestX","TestableY",dataset, PlotOrientation.VERTICAL,true,false,false);
        ChartFrame frame = new ChartFrame("Title", subchart);
        frame.setBounds(10, 10, 400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
