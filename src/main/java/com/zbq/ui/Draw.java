package com.zbq.ui;

import com.zbq.semantic.DrawingData;
import com.zbq.semantic.Point;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * @author zbq
 * @date 2022/12/20 17:32
 */
public class Draw {
    static int num=1;
    private XYSeriesCollection dataset=new XYSeriesCollection();
    public void addData(DrawingData drawingData){
        XYSeries data=new XYSeries("Func: "+num);
        num++;
        for(Point p:drawingData.getPoints()){
            data.add(p.getX(),p.getY());
        }
        dataset.addSeries(data);
    }
    public void show(){
        JFreeChart chart= ChartFactory.createScatterPlot(
                "test",
                "X",
                "Y",
                dataset,
                PlotOrientation.VERTICAL,
                true,true,false);
        ChartFrame frame=new ChartFrame("Test",chart);
        frame.pack();
        frame.setVisible(true);
    }


}
