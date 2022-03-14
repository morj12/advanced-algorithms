package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.*;

import controller.Complexity;
import main.ErrorWriter;
import model.Model;
import model.TimePoint;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class CustomChartPanel extends JPanel {

    private final Model model;
    private final View view;
    protected final int FPS = 24;
    private final JFreeChart chartInfo;
    private final XYSeries linearSeries;
    private final XYSeries quadraticSeries;
    private final XYSeries logarithmicSeries;
    private final ArrayList<TimePoint>[] pointLists;
    private final XYSeriesCollection dataset;
    private ChartPanel chartPanel;

    public CustomChartPanel(int width, int height, Model model, View view) {
        this.model = model;
        this.view = view;
        this.setPreferredSize(new Dimension(width, height));
        this.chartInfo = ChartFactory.createXYLineChart(null, "Iterations", "Time(ms)", null, PlotOrientation.VERTICAL, true, true, false);
        this.pointLists = this.model.getPointLists();
        this.linearSeries = new XYSeries(Complexity.LINEAR.toString());
        this.quadraticSeries = new XYSeries(Complexity.QUADRATIC.toString());
        this.logarithmicSeries = new XYSeries(Complexity.LOGARITHMIC.toString());
        this.dataset = new XYSeriesCollection();
        dataset.addSeries(linearSeries);
        dataset.addSeries(quadraticSeries);
        dataset.addSeries(logarithmicSeries);

        this.chartPanel = new ChartPanel(chartInfo);
        this.chartPanel.setPopupMenu(null);
        this.add(chartPanel);

        Runnable r = () -> {
            Timer t = new Timer(1000/FPS, (ActionEvent e) -> {
                repaint();
            });
            t.start();
        };
        new Thread(r).start();

    }

    public void repaint() {
        if (this.getGraphics() != null) {
            paint(this.getGraphics());
        }
    }
    
    public void paint(Graphics gr) {
        for(int i = 0; i < pointLists.length; i++) {
            if(pointLists[i].isEmpty()) {
                dataset.getSeries(i).clear();
            }
            for(int j = 0; j < pointLists[i].size(); j++) {
                switch (i) {
                    case 0:
                        linearSeries.add(pointLists[i].get(j).getStopNumber(), pointLists[i].get(j).getTime());
                        break;
                    case 1:
                        quadraticSeries.add(pointLists[i].get(j).getStopNumber(), pointLists[i].get(j).getTime());
                        break;
                    default:
                        logarithmicSeries.add(pointLists[i].get(j).getStopNumber(), pointLists[i].get(j).getTime());
                        break;
                }
            }
        }
        ((XYPlot) chartInfo.getPlot()).setDataset(dataset);
    }
}

