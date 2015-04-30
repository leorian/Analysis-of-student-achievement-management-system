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
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.opensymphony.xwork2.ActionSupport;
import com.xscj.domain.Course;
import com.xscj.domain.Grade;
import com.xscj.domain.ScoreByGidCid;
import com.xscj.service.CoursePlan;
import com.xscj.service.GradeSetUp;
import com.xscj.service.ScoreService;

/**
 * @author leorain
 *
 */
public class ScoreAnalyzeThirdAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2216699496015066134L;
	
	@Autowired
	@Qualifier("gradeSetUpImpl")
	private GradeSetUp gradeSetUp;
	
	@Autowired
	@Qualifier("coursePlanImpl")
	private CoursePlan coursePlan;
	
	@Autowired
	@Qualifier("scoreServiceImpl")
	private ScoreService scoreService;
	
	private String gradeID;//班级编号
	private String courseID;//课程编号
	private JFreeChart chart;//图标对象
	private Grade grade;//班级信息
	private Course course;//课程信息
	private List<ScoreByGidCid> scoreByGidCids;//成绩信息
	
	@SuppressWarnings("deprecation")
	public JFreeChart getChart() {
		chart = ChartFactory.createLineChart(grade.getYear()+"届（"+grade.getClassID()+"）班"+course.getName()+"课程所有同学考试成绩整体分析结果", "姓名", "成绩", getDataSet(), PlotOrientation.VERTICAL, true, false, false);
		chart.setTitle(new TextTitle(grade.getYear()+"届（"+grade.getClassID()+"）班"+course.getName()+"课程所有同学考试成绩整体分析结果", new Font("黑体", Font.BOLD, 22)));
		LegendTitle legend = chart.getLegend();
		legend.setItemFont(new Font("宋体", Font.BOLD, 14));
		CategoryPlot plot = (CategoryPlot)chart.getPlot();
		CategoryItemRenderer renderer =  plot.getRenderer();
		renderer.setItemLabelsVisible(true);
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());	
		LineAndShapeRenderer lineAndShapeRenderer = (LineAndShapeRenderer)plot.getRenderer();
		lineAndShapeRenderer.setShapesVisible(true);
		lineAndShapeRenderer.setShape(new Ellipse2D.Double(-2,-2,4,4));
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
		for (int i = 0; i < scoreByGidCids.size(); i++) {
			dataset.addValue(scoreByGidCids.get(i).getScore(), scoreByGidCids.get(i).getExamTime(), scoreByGidCids.get(i).getStuName());
		}
		return dataset;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}
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

	public List<ScoreByGidCid> getScoreByGidCids() {
		return scoreByGidCids;
	}

	public void setScoreByGidCids(List<ScoreByGidCid> scoreByGidCids) {
		this.scoreByGidCids = scoreByGidCids;
	}

	@Override
	public void validate() {
		grade = gradeSetUp.getGradeBybianHao(gradeID);
		course = coursePlan.getCourseByCourseID(courseID);
		scoreByGidCids = scoreService.getScoreByGidCids(gradeID, courseID);
	}
	

}
