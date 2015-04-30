/**
 * 
 */
package com.xscj.score.action;

import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author leorain
 *
 */
public class ScoreRankBar3DAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8402075275505008727L;
	
	private int failCount;//不及格
	private int sucCount;//及格
	private int goodCount;//良好
	private int secondaryCount;//中等
	private int excellentCount;//优秀
	public int getFailCount() {
		return failCount;
	}
	public void setFailCount(int failCount) {
		this.failCount = failCount;
	}
	public int getSucCount() {
		return sucCount;
	}
	public void setSucCount(int sucCount) {
		this.sucCount = sucCount;
	}
	public int getGoodCount() {
		return goodCount;
	}
	public void setGoodCount(int goodCount) {
		this.goodCount = goodCount;
	}
	public int getSecondaryCount() {
		return secondaryCount;
	}
	public void setSecondaryCount(int secondaryCount) {
		this.secondaryCount = secondaryCount;
	}
	public int getExcellentCount() {
		return excellentCount;
	}
	public void setExcellentCount(int excellentCount) {
		this.excellentCount = excellentCount;
	}
	
	private JFreeChart chart;
	public JFreeChart getChart() {
		chart = ChartFactory.createBarChart3D("成绩等级分布直方图","等级","人数", getDataSet(), PlotOrientation.VERTICAL,false, false, false);
		chart.setTitle(new TextTitle("成绩等级分布直方图", new Font("黑体", Font.BOLD, 22)));
		CategoryPlot plot = (CategoryPlot)chart.getPlot();
		CategoryAxis categoryAxis = plot.getDomainAxis();
		categoryAxis.setLabelFont(new Font("宋体",Font.BOLD,22));
		categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
		categoryAxis.setTickLabelFont(new Font("宋体",Font.BOLD,22));
		NumberAxis numberAxis = (NumberAxis)plot.getRangeAxis();
		numberAxis.setLabelFont(new Font("宋体", Font.BOLD,22));
		return chart;
	}
	private DefaultCategoryDataset getDataSet() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(failCount,"","不及格");
		dataset.setValue(sucCount,"","及格" );
		dataset.setValue(goodCount,"","良好");
		dataset.setValue(secondaryCount,"","中等");
		dataset.setValue(excellentCount,"","优秀");
		return dataset;
	}
	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}

	

}
