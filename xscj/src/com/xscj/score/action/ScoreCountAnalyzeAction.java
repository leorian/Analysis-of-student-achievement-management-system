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
import com.xscj.domain.Grade;
import com.xscj.domain.StuScoreCount;
import com.xscj.service.GradeSetUp;
import com.xscj.service.ScoreService;

/**
 * @author leorain
 * @date 2014-4-3 上午8:06:13
 * 
 * 对某个学生的所有成绩进行整体分析
 *
 */
public class ScoreCountAnalyzeAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7975822669876422419L;
	
	@Autowired
	@Qualifier("scoreServiceImpl")
	private ScoreService scoreService;//学生成绩业务逻辑操作类
	
	@Autowired
	@Qualifier("gradeSetUpImpl")
	private GradeSetUp gradeSetUp;
	
	private String gradeID;
	private Grade grade;
	private int xueqi;
	private String examType;
	
	private List<StuScoreCount> stuScoreCounts;
	
	private JFreeChart chart;
	
	@SuppressWarnings("deprecation")
	public JFreeChart getChart() {
		chart = ChartFactory.createLineChart(grade.getYear()+"届（"+grade.getClassID()+"）班第"+xueqi+"学期"+examType+"考试成绩总分统计排名", "学生排名", "总分", getDataSet(), PlotOrientation.VERTICAL, true, false, false);
		chart.setTitle(new TextTitle(grade.getYear()+"届（"+grade.getClassID()+"）班第"+xueqi+"学期"+examType+"考试成绩总分统计排名", new Font("黑体", Font.BOLD, 22)));
		LegendTitle legend = chart.getLegend();
	//	legend.setItemFont(new Font("宋体", Font.BOLD, 14));
		legend.setVisible(false);
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
		for (int i = 0; i < stuScoreCounts.size(); i++) {
			dataset.addValue(stuScoreCounts.get(i).getScoreSum(), "",(i+1)+"、"+stuScoreCounts.get(i).getStuNameString());
		}
		return dataset;
	}


	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}
 
	@Override
	public void validate() {
		 stuScoreCounts = scoreService.getStuScoreCounts(gradeID, xueqi, examType);
		/* for (int i = 0; i < stuScoreCounts.size(); i++) {
			System.out.println(stuScoreCounts.get(i).getScoreSum());
		}*/
		 grade = gradeSetUp.getGradeBybianHao(gradeID);
	}


	public String getGradeID() {
		return gradeID;
	}


	public void setGradeID(String gradeID) {
		this.gradeID = gradeID;
	}


	public int getXueqi() {
		return xueqi;
	}


	public void setXueqi(int xueqi) {
		this.xueqi = xueqi;
	}


	public String getExamType() {
		return examType;
	}


	public void setExamType(String examType) {
		this.examType = examType;
	}


	public List<StuScoreCount> getStuScoreCounts() {
		return stuScoreCounts;
	}


	public void setStuScoreCounts(List<StuScoreCount> stuScoreCounts) {
		this.stuScoreCounts = stuScoreCounts;
	}


	public Grade getGrade() {
		return grade;
	}


	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	
	
}
