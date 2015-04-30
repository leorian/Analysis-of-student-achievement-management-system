/**
 * 
 */
package com.xscj.score.action;

import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.opensymphony.xwork2.ActionSupport;
import com.xscj.domain.Course;
import com.xscj.service.CoursePlan;

/**
 * @author leorain
 *
 */
public class ScoreRankPieAction extends ActionSupport {

	/**
	 * @author leorain
	 * @date 2014-3-31 下午8:40:43
	 */
	private static final long serialVersionUID = 5628961410171908654L;
	
	@Autowired
	@Qualifier("coursePlanImpl")
	private CoursePlan coursePlan;
	
	private Course course;
	
	private int year;//届
	private int classID;//班级号
	private int xueqi;//学期
	private String courseID;//课程名称
	private String examType;//考试类型
	
	public String getExamType() {
		return examType;
	}
	public void setExamType(String examType) {
		this.examType = examType;
	}
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
	public int getXueqi() {
		return xueqi;
	}
	public void setXueqi(int xueqi) {
		this.xueqi = xueqi;
	}
	public String getCourseID() {
		return courseID;
	}
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

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
		course = coursePlan.getCourseByCourseID(courseID);
		chart = ChartFactory.createPieChart(year+"届（"+classID+")班第"+xueqi+"学期"+course.getName()+examType+course.getName()+"考试成绩等级分布饼图", getDataSet(), true, false, false);
		chart.setTitle(new TextTitle(year+"届（"+classID+")班第"+xueqi+"学期"+course.getName()+examType+"考试成绩等级分布饼图", new Font("黑体", Font.BOLD, 22)));
		LegendTitle legend = chart.getLegend(0);
		legend.setItemFont(new Font("宋体", Font.BOLD, 14));
		PiePlot plot = (PiePlot)chart.getPlot();
		plot.setLabelFont(new Font("隶书", Font.BOLD, 18));
		plot.setBackgroundAlpha(1f);
		return chart;
	}
	private DefaultPieDataset getDataSet() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("不及格", failCount);
		dataset.setValue("及格", sucCount);
		dataset.setValue("良好", goodCount);
		dataset.setValue("中等", secondaryCount);
		dataset.setValue("优秀", excellentCount);
		return dataset;
	}
	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}

}
