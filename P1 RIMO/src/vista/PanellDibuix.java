package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JPanel;
import principal.MeuError;
import model.Model;
import model.Punt;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import static vista.Vista.FUNCION_0;
import static vista.Vista.FUNCION_1;
import static vista.Vista.FUNCION_2;

public class PanellDibuix extends JPanel {

    private int w;
    private int h;
    private Model mod;
    private Vista vis;
    protected final int FPS = 24;  // 24 frames per segon
    private final ProcesPintat procpin;
    private BufferedImage bima;
    private JFreeChart chart;
    private XYSeries l0;
    private XYSeries l1;
    private XYSeries l2;
    private ArrayList<Punt>[] listas;
    private XYSeriesCollection dataset;



    public PanellDibuix(int x, int y, Model m, Vista v) {
        w = x;
        h = y;
        mod = m;
        vis = v;
        bima = null;
        this.setPreferredSize(new Dimension(w, h));
        procpin = new ProcesPintat(this);
        procpin.start();
        this.chart = ChartFactory.createXYLineChart(null, "N-Iteraciones", "Tiempo(ms)", null, PlotOrientation.VERTICAL, true, true, false);
        this.listas = mod.getLists();
        this.l0 = new XYSeries(FUNCION_0);
        this.l1 = new XYSeries(FUNCION_1);
        this.l2 = new XYSeries(FUNCION_2);
        this.dataset = new XYSeriesCollection();
        dataset.addSeries(l0);
        dataset.addSeries(l1);
        dataset.addSeries(l2);
    }

    public void repaint() {
        if (this.getGraphics() != null) {
            paint(this.getGraphics());
        }
    }
    
    public void paint(Graphics gr) {
        if (bima == null) {
            if (this.getWidth() > 0) {
                bima = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
                bima.getGraphics().setColor(Color.white);
                bima.getGraphics().fillRect(0, 0, bima.getWidth(), bima.getHeight());
            }
        }

        for(int i = 0; i < listas.length; i++)
        {
            if(listas[i].isEmpty())
            {
                dataset.getSeries(i).clear();
            }
            for(int j = 0; j < listas[i].size(); j++)
            {
                switch (i) {
                    case 0:
                        l0.add(listas[i].get(j).getN(), listas[i].get(j).getTemps());
                        break;
                    case 1:
                        l1.add(listas[i].get(j).getN(), listas[i].get(j).getTemps());
                        break;
                    default:
                        l2.add(listas[i].get(j).getN(), listas[i].get(j).getTemps());
                        break;
                }
            }
        }
        
        ((XYPlot)chart.getPlot()).setDataset(dataset);
        vis.add(new ChartPanel(chart));
    }
}

class ProcesPintat extends Thread {

    private PanellDibuix pan;

    public ProcesPintat(PanellDibuix pd) {
        pan = pd;
    }

    public void run() {
        long temps = System.nanoTime();
        long tram = 1000000000L / pan.FPS;
        while (true) {
            if ((System.nanoTime() - temps) > tram) {
                pan.repaint();
                temps = System.nanoTime();
                espera((long) (tram / 2000000));
            }
        }
    }

    private void espera(long t) {
        try {
            Thread.sleep(t);
        } catch (Exception e) {
            MeuError.informaError(e);
        }
    }
}
