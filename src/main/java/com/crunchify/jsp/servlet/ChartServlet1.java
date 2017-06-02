/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crunchify.jsp.servlet;

import edu.co.sergio.mundo.dao.Visitas_tecnicasDao;
import edu.co.sergio.mundo.vo.Visitas_Tecnicas;
import edu.co.sergio.mundo.vo.Recoleccion;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Paint;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.TextAnchor;

public class ChartServlet1 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

           response.setContentType("image/png");
		OutputStream outputStream = response.getOutputStream();
		JFreeChart chart = null;
            try {
                chart = getChart();
            } catch (URISyntaxException ex) {
                Logger.getLogger(ChartServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
		int width = 500;
		int height = 350;
		ChartUtilities.writeChartAsPNG(outputStream, chart, width, height);
		

	}
        
        
        
//    public JFreeChart getChart() throws URISyntaxException {
//
//        List<Recoleccion> arr = new LinkedList();
//       Visitas_tecnicasDao vis = new Visitas_tecnicasDao();
//        arr =   vis.findAll2();
//        double[][] data = new double[1][arr.size()];
//        int j=0;
//        for (int i = 0; i < arr.size(); i++) {
//            data[0][j] = arr.get(i).getKilosdeiel() ;
//            j++;
//        }
//
//        CategoryDataset category = DatasetUtilities.createCategoryDataset(
//                "Series",       
//                "Colmena",
//                data
//        );
//
//        JFreeChart chart = ChartFactory.createBarChart(
//                "Kilos de miel por Colmena", // chart title
//                "Area", // domain axis label
//                "Elementos", // range axis label
//                category, // data
//                PlotOrientation.VERTICAL, // the plot orientation
//                false, // include legend
//                true,
//                false
//        );
//
//        chart.setBackgroundPaint(Color.lightGray);
//
//        // get a reference to the plot for further customisation...
//        CategoryPlot plot = chart.getCategoryPlot();
//        plot.setNoDataMessage("NO DATA!");
//
//        CategoryItemRenderer renderer = new CustomRenderer(
//                new Paint[]{Color.red, Color.blue, Color.green,
//                    Color.yellow, Color.WHITE, Color.cyan,
//                    Color.magenta, Color.blue}
//        );
//
//        renderer.setItemLabelsVisible(true);
//        ItemLabelPosition p = new ItemLabelPosition(
//                ItemLabelAnchor.CENTER, TextAnchor.CENTER, TextAnchor.CENTER, 45.0
//        );
//        renderer.setPositiveItemLabelPosition(p);
//        plot.setRenderer(renderer);
//
//        // change the margin at the top of the range axis...
//       org.jfree.chart.axis.ValueAxis rangAxis = plot.getRangeAxis();
//        rangAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
//        rangAxis.setLowerMargin(0.15);
//        rangAxis.setUpperMargin(0.15);
//
//        return chart;
//
//    }
        
        
        
        

	public JFreeChart getChart() throws URISyntaxException {
		
                DefaultPieDataset dataset = new DefaultPieDataset();
	        //Crear la capa de servicios que se enlace con el DAO
                List <Visitas_Tecnicas>c=new LinkedList<Visitas_Tecnicas>();
                Visitas_tecnicasDao dep=new Visitas_tecnicasDao();
                c= dep.findAll();
               
            
               
                
            for (int i = 0; i < c.size(); i++) {
                dataset.setValue(c.get(i).getTecnico(),c.get(i).getPanalesconaimento());
            }
            
		boolean legend = true;
		boolean tooltips = false;
		boolean urls = false;

		JFreeChart chart = ChartFactory.createPieChart("Informacion Panal Colmena", dataset, legend, tooltips, urls);

		chart.setBorderPaint(Color.GREEN);
		chart.setBorderStroke(new BasicStroke(5.0f));
		chart.setBorderVisible(true);

		return chart;
	}

}
