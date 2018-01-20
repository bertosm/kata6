package kata6.view;


import java.awt.Dimension;
import javax.swing.JPanel;
import kata6.model.Histogram;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Usuario
 */
public class HistogramDisplay<T> extends ApplicationFrame{
    private final Histogram<T> histogram;
    private String nameEjeX;

    public HistogramDisplay(Histogram<T> histogram, String nameEjeX) {
        super("HISTOGRAMA");
        this.histogram = histogram;
        this.nameEjeX = nameEjeX;
        setContentPane(createPanel());
        pack();
    }

    
    public void execute() {
        setVisible(true);
    }
    
    private JPanel createPanel(){
        ChartPanel chartPanel = new ChartPanel(createChart(createDataset()));
        setPreferredSize(new Dimension(500,400));
        return chartPanel;
    }
    
    private JFreeChart createChart(DefaultCategoryDataset dataSet){
        JFreeChart chart = ChartFactory.createBarChart(
                "Histograma JFreeChart",
                "caracteristica deseada",
                "número de elementos",
                dataSet,
                PlotOrientation.VERTICAL,
                false,
                true,
                true);
        return chart;
    }
    
    private DefaultCategoryDataset createDataset(){
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        for (T key : histogram.keySet()){
            dataSet.addValue(histogram.get(key),"",(Comparable)key);
        }
        return dataSet;
    }
    
    
}
