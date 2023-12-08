/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package doancuahangthuoc;

import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import java.awt.Color;
/**
 *
 * @author sonco
 */
public class Test_Chart{
    public static void main(String[]args){
        JFrame frame=new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 200, 200);
        frame.setLayout(null);
        JPanel pn_main=new JPanel();
        pn_main.setLayout(null);
        pn_main.setBounds(10, 10, 180, 180);
        pn_main.setBackground(new Color(255,255,255));
        DefaultCategoryDataset dataset=new DefaultCategoryDataset();
        for(int i = 0;i<12;i++) {
        	dataset.addValue(i, "VNĐ", i+1+"");
        }
        JFreeChart barChart = ChartFactory.createBarChart(
                "BIỂU ĐỒ TỔNG THU THEO THÁNG",
                "Tháng", "VNĐ",
                dataset, PlotOrientation.VERTICAL, false, false, false);

        ChartPanel chartPanel=new ChartPanel(barChart);
        chartPanel.setBounds(10, 10, 160, 160);
        pn_main.add(chartPanel);
        frame.add(pn_main);
        frame.setVisible(true);
        frame.pack();
    }
    
}
