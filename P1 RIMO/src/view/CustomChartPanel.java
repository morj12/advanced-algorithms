package view;

import controller.Complexity;
import model.Model;
import model.TimePoint;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class CustomChartPanel extends JPanel {

    protected final int FPS = 24;
    private JFreeChart chartInfo;
    private ChartPanel chartPanel;
    private XYSeries linearSeries;
    private XYSeries quadraticSeries;
    private XYSeries logarithmicSeries;
    private XYSeriesCollection dataset;
    private ArrayList<TimePoint>[] pointLists;


    public CustomChartPanel() {
        initComponents();
    }

    private void initComponents() {
        this.chartInfo = ChartFactory.createXYLineChart(
                null,
                "Iterations",
                "Time(ms)",
                null,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);
        this.chartPanel = new ChartPanel(chartInfo);
        this.add(chartPanel);
        this.chartPanel.setPopupMenu(null);
        this.linearSeries = new XYSeries(Complexity.LINEAR.toString());
        this.quadraticSeries = new XYSeries(Complexity.QUADRATIC.toString());
        this.logarithmicSeries = new XYSeries(Complexity.LOGARITHMIC.toString());
        this.dataset = new XYSeriesCollection();
        dataset.addSeries(linearSeries);
        dataset.addSeries(quadraticSeries);
        dataset.addSeries(logarithmicSeries);
    }

    public void setPointListsAndStart(ArrayList<TimePoint>[] pointLists) {
        this.pointLists = pointLists;
        startUpdate();
    }

    public void startUpdate() {
        Runnable r = () -> {
            Timer t = new Timer(1000 / FPS, (ActionEvent e) -> {
                repaint();
            });
            t.start();
        };
        new Thread(r).start();
    }

    @Override
    public void repaint() {
        if (this.getGraphics() != null) {
            paint(this.getGraphics());
        }
    }

    @Override
    public void paint(Graphics g) {
        for (int i = 0; i < pointLists.length; i++) {
            if (pointLists[i].isEmpty()) {
                dataset.getSeries(i).clear();
            }
            for (int j = 0; j < pointLists[i].size(); j++) {
                int x = pointLists[i].get(j).getIterations();
                long y = pointLists[i].get(j).getTime();
                switch (i) {
                    case 0 -> linearSeries.add(x, y);
                    case 1 -> quadraticSeries.add(x, y);
                    default -> logarithmicSeries.add(x, y);
                }
            }
        }
        ((XYPlot)chartInfo.getPlot()).setDataset(dataset);
    }
}
