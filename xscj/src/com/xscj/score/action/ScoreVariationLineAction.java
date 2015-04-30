/**
 * 
 */
package com.xscj.score.action;

import java.awt.Font;
import java.awt.geom.Ellipse2D;
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
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.opensymphony.xwork2.ActionSupport;
import com.xscj.domain.Course;
import com.xscj.domain.SimpleScore;
import com.xscj.service.CoursePlan;
import com.xscj.service.ScoreService;

/**
 * @author leorain
 *
 */
public class ScoreVariationLineAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2768584559582844524L;
	
	@Autowired
	@Qualifier("scoreServiceImpl")
	private ScoreService scoreService;
	
	@Autowired
	@Qualifier("coursePlanImpl")
	private CoursePlan coursePlan;
	private Course course;
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	private List<SimpleScore> simpleScores;
	public List<SimpleScore> getSimpleScores() {
		return simpleScores;
	}
	public void setSimpleScores(List<SimpleScore> simpleScores) {
		this.simpleScores = simpleScores;
	}
	private String gradeID;//班级编号
	private int year;
	private int classID;
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
	private String courseID;//课程编号
	private String examType;//考试类型
	private String examTime;//考试时间
	private int xueqi;//学期
	public String getGradeID() {
		return gradeID;
	}
	public void setGradeID(String gradeID) {
		this.gradeID = gradeID;
	}
	public String getCourseID() {
		return courseID;
	}
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	public String getExamType() {
		return examType;
	}
	public void setExamType(String examType) {
		this.examType = examType;
	}
	public String getExamTime() {
		return examTime;
	}
	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}
	public int getXueqi() {
		return xueqi;
	}
	public void setXueqi(int xueqi) {
		this.xueqi = xueqi;
	}
	@Override
	public void validate() {
		simpleScores = scoreService.getSimpleScores(gradeID, courseID, examType, examTime, xueqi);
		course = coursePlan.getCourseByCourseID(courseID);
	}
	private JFreeChart chart;
	@SuppressWarnings("deprecation")
	public JFreeChart getChart() {
		chart = ChartFactory.createLineChart(year+"届（"+classID+"）班第"+xueqi+"学期"+course.getName()+examType+"成绩统计结果折线图","学号","成绩", getDataSet(),PlotOrientation.VERTICAL,false,false,false);
		chart.setTitle(new TextTitle(year+"届（"+classID+"）班第"+xueqi+"学期"+course.getName()+examType+"成绩统计结果折线图", new Font("黑体", Font.BOLD, 22)));
		CategoryPlot plot = (CategoryPlot)chart.getPlot();
		CategoryItemRenderer renderer =  plot.getRenderer();
		renderer.setBaseShape(new Ellipse2D.Double(-2, -2, 4, 4));
		renderer.setItemLabelsVisible(true);
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		LineAndShapeRenderer lineAndShapeRenderer = (LineAndShapeRenderer)plot.getRenderer();
		 lineAndShapeRenderer.setShapesVisible(true);
		 lineAndShapeRenderer.setShape(new Ellipse2D.Double(-2,-2,4,4));
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
		 for(int i=0; i<simpleScores.size(); i++)
		 {
			 dataset.addValue(simpleScores.get(i).getScore(), "", simpleScores.get(i).getXuehao()+"");
		 }
		return dataset;
	}
	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}

}
