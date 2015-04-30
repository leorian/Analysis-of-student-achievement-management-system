/**
 * 
 */
package com.xscj.score.action;

import java.awt.Font;
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
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.opensymphony.xwork2.ActionSupport;
import com.xscj.domain.ScoreXueqi;
import com.xscj.service.ScoreService;

/**
 * @author leorain
 *@date 2014-4-3 上午11:16:59
 */
public class ScoreAnalyzeSecondAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7908989197851956370L;
	
	@Autowired
	@Qualifier("scoreServiceImpl")
	private ScoreService scoreService;//成绩操作业务逻辑处理类
	
	private List<ScoreXueqi> scoreXueqis;
	
	private JFreeChart chart;
	
	@SuppressWarnings("deprecation")
	public JFreeChart getChart() {
		chart = ChartFactory.createBarChart3D(gradeNameString+stuName+"同学"+courseName+"所有考试成绩整体分析结果", "时间", "成绩", getDataSet(), PlotOrientation.VERTICAL, false, false, false);
		chart.setTitle(new TextTitle(gradeNameString+stuName+"同学"+courseName+"所有考试成绩整体分析结果", new Font("黑体", Font.BOLD, 22)));
		CategoryPlot plot = (CategoryPlot)chart.getPlot();
		
		CategoryItemRenderer renderer =  plot.getRenderer();
		renderer.setItemLabelsVisible(true);
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		
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
		for (int i = 0; i < scoreXueqis.size(); i++) {
			dataset.addValue(scoreXueqis.get(i).getScore(), "", scoreXueqis.get(i).getExamTime());
		}
		return dataset;
	}
	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}
	private String gradeNameString;//班级名称
	private int stuXueHao;//学号
	private String stuName;//学生姓名
	private String courseID;//课程编号
	private String courseName;//课程名称
	public String getGradeNameString() {
		return gradeNameString;
	}
	public void setGradeNameString(String gradeNameString) {
		this.gradeNameString = gradeNameString;
	}
	public int getStuXueHao() {
		return stuXueHao;
	}
	public void setStuXueHao(int stuXueHao) {
		this.stuXueHao = stuXueHao;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getCourseID() {
		return courseID;
	}
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	@Override
	public void validate() {
		scoreXueqis = scoreService.getScoreXueqis(stuXueHao, courseID);
	}
	
	

}
