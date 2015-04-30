/**
 * 
 */
package com.xscj.score.action;

import java.awt.Font;
import java.awt.geom.Rectangle2D;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.opensymphony.xwork2.ActionSupport;
import com.xscj.domain.ScorePart;
import com.xscj.service.ScoreService;

/**
 * @author leorain
 * @date 2014-4-3 上午8:06:13
 * 
 * 对某个学生的所有成绩进行整体分析
 *
 */
public class ScoreAnalyzeAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7975822669876422419L;
	
	@Autowired
	@Qualifier("scoreServiceImpl")
	private ScoreService scoreService;//学生成绩业务逻辑操作类
	
	private JFreeChart chart;
	
	@SuppressWarnings("deprecation")
	public JFreeChart getChart() {
		chart = ChartFactory.createLineChart(year+"届（"+classID+"）班"+stuName+"同学所有课程考试成绩整体分析结果", "时间", "成绩", getDataSet(), PlotOrientation.VERTICAL, true, false, false);
		chart.setTitle(new TextTitle(year+"届（"+classID+"）班"+stuName+"同学所有课程考试成绩整体分析结果", new Font("黑体", Font.BOLD, 22)));
		LegendTitle legend = chart.getLegend();
		legend.setItemFont(new Font("宋体", Font.BOLD, 14));
		CategoryPlot plot = (CategoryPlot)chart.getPlot();
		CategoryItemRenderer renderer =  plot.getRenderer();
		renderer.setItemLabelsVisible(true);
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());	
		LineAndShapeRenderer lineAndShapeRenderer = (LineAndShapeRenderer)plot.getRenderer();
		lineAndShapeRenderer.setShapesVisible(true);
		//lineAndShapeRenderer.setShape(new Ellipse2D.Double(-2,-2,4,4));
		lineAndShapeRenderer.setShape(new Rectangle2D.Double(-2, -2, 4, 4));
		
		CategoryAxis categoryAxis = plot.getDomainAxis();
		categoryAxis.setLabelFont(new Font("宋体", Font.BOLD, 22));
		categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
		categoryAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 18));
		NumberAxis numberAxis = (NumberAxis)plot.getRangeAxis();
		numberAxis.setLabelFont(new Font("宋体", Font.BOLD, 22));
		return chart;
	}


	private DefaultCategoryDataset getDataSet() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i = 0; i < scoreParts.size(); i++) {
			//dataset.addValue(scoreParts.get(i).getScore(), scoreParts.get(i).getCourseName(), scoreParts.get(i).getExamTime());
			dataset.addValue(scoreParts.get(i).getScore(), scoreParts.get(i).getCourseName(), scoreParts.get(i).getExamTime());
		}
		return dataset;
	}


	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}
	
	
	
	private int stuXueHao; //学号
	private String stuName;//学生姓名
	private int year;//届
	private int classID;//班级号


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}


	public int getClassID() {
		return classID;
	}


	public void setClassID(int classID) {
		this.classID = classID;
	}


	public String getStuName() {
		return stuName;
	}


	public void setStuName(String stuName) {
		this.stuName = stuName;
	}


	public int getStuXueHao() {
		return stuXueHao;
	}


	public void setStuXueHao(int stuXueHao) {
		this.stuXueHao = stuXueHao;
	}
	private List<ScorePart> scoreParts;//学生所有成绩的集合

	public List<ScorePart> getScoreParts() {
		return scoreParts;
	}


	public void setScoreParts(List<ScorePart> scoreParts) {
		this.scoreParts = scoreParts;
	}
	
	@Override
	public void validate() {
		scoreParts = scoreService.getScorePartsByXueHao(stuXueHao);
	}
	
	
}
