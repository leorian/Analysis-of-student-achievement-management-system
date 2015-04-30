/**
 * 
 */
package com.xscj.score.action;

/*import java.awt.geom.Rectangle2D;*/
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.struts2.ServletActionContext;
/*import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.opensymphony.xwork2.ActionSupport;
import com.xscj.domain.Course;
import com.xscj.domain.Grade;
import com.xscj.domain.ScoreCount;
import com.xscj.domain.ScoreSmall;
import com.xscj.domain.ScoreTotal;
/*import com.xscj.domain.StuScoreCount;*/
import com.xscj.service.GradeSetUp;
import com.xscj.service.ScoreService;
import com.xscj.service.TeachingArrange;
import com.xscj.util.XscjComparator;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * @author leorain
 * 
 */
public class ScorePrinterGradeSucAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3406371248132575847L;
	@Autowired
	@Qualifier("scoreServiceImpl")
	private ScoreService scoreService;

	@Autowired
	@Qualifier("gradeSetUpImpl")
	private GradeSetUp gradeSetUp;

	@Autowired
	@Qualifier("teachingArrangeImpl")
	private TeachingArrange teachingArrange;

	private String gradeID;
	private int xueqi;
	private String examType;
	private List<ScoreCount> scoreCounts;
	private List<Course> courses;
	private List<ScoreTotal> scoreTotals;
	private Grade grade;
	private InputStream pdfStream;
	private String fileName;

/*	private List<StuScoreCount> stuScoreCounts;
	private JFreeChart chart;*/

	/*@SuppressWarnings("deprecation")*/
	public String generateContract() throws Exception {
		if(gradeID == null || examType==null)
			return INPUT;
		scoreCounts = scoreService.getScoreCounts(gradeID, xueqi, examType);
		courses = teachingArrange.getCourses(gradeID, xueqi);
		grade = gradeSetUp.getGradeBybianHao(gradeID);

	/*	stuScoreCounts = scoreService.getStuScoreCounts(gradeID, xueqi,
				examType);
*/
		/*chart = ChartFactory.createLineChart(
				grade.getYear() + "届（" + grade.getClassID() + "）班第" + xueqi
						+ "学期" + examType + "考试成绩总分统计排名", "学生排名", "总分",
				getDataSet(), PlotOrientation.VERTICAL, true, false, false);
		chart.setTitle(new TextTitle(grade.getYear() + "届（"
				+ grade.getClassID() + "）班第" + xueqi + "学期" + examType
				+ "考试成绩总分统计排名", new java.awt.Font("黑体", Font.BOLD, 11)));
		LegendTitle legend = chart.getLegend();
		// legend.setItemFont(new Font("宋体", Font.BOLD, 14));
		legend.setVisible(false);
		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		CategoryItemRenderer renderer = plot.getRenderer();
		renderer.setItemLabelsVisible(true);
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		LineAndShapeRenderer lineAndShapeRenderer = (LineAndShapeRenderer) plot
				.getRenderer();
		lineAndShapeRenderer.setShapesVisible(true);
		// lineAndShapeRenderer.setShape(new Ellipse2D.Double(-2,-2,4,4));
		lineAndShapeRenderer.setShape(new Rectangle2D.Double(-2, -2, 4, 4));

		CategoryAxis categoryAxis = plot.getDomainAxis();
		categoryAxis.setLabelFont(new java.awt.Font("宋体", Font.BOLD, 11));
		categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
		categoryAxis.setTickLabelFont(new java.awt.Font("宋体", Font.BOLD, 9));
		NumberAxis numberAxis = (NumberAxis) plot.getRangeAxis();
		numberAxis.setLabelFont(new java.awt.Font("宋体", Font.BOLD, 11));
		String pictureName = ServletUtilities.saveChartAsPNG(chart, 1200, 600,  ServletActionContext.getRequest().getSession());
		*/
		int n = scoreCounts.size() / courses.size();
		scoreTotals = new ArrayList<ScoreTotal>();
		for (int i = 0; i < n; i++) {
			int xuehao = scoreCounts.get(i * courses.size()).getStuXueHao();
			String name = scoreCounts.get(i * courses.size()).getStuName();
			ScoreTotal scoreTotal = new ScoreTotal();
			scoreTotal.setXuehao(xuehao);
			scoreTotal.setName(name);
			double totalScore = 0;
			List<ScoreSmall> scoreSmalls = new ArrayList<ScoreSmall>();
			for (int j = 0; j < courses.size(); j++) {
				totalScore = totalScore
						+ scoreCounts.get(i * courses.size() + j).getScore();
				ScoreSmall scoreSmall = new ScoreSmall();
				scoreSmall.setCourseID(scoreCounts.get(i * courses.size() + j)
						.getCourseID());
				scoreSmall.setCourseName(scoreCounts
						.get(i * courses.size() + j).getCourseName());
				scoreSmall.setScore(scoreCounts.get(i * courses.size() + j)
						.getScore());
				scoreSmalls.add(scoreSmall);
			}
			scoreTotal.setScoreSmalls(scoreSmalls);
			scoreTotal.setTotalScore(totalScore);
			scoreTotals.add(scoreTotal);
		}
		Collections.sort(scoreTotals, new XscjComparator());
		fileName = new String((grade.getYear() + "届（" + grade.getClassID()
				+ ")班第" + xueqi + "学期" + examType + "考试成绩单").getBytes(),
				"ISO8859-1");
		Document document = new Document();

		BaseFont bfChinese = BaseFont.createFont("STSong-Light",
				"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		Font FontChinese = new Font(bfChinese);

		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		PdfWriter.getInstance(document, buffer);
		document.open();
		Paragraph logoText = new Paragraph();
		logoText.setFont(FontChinese);
		Chunk logo = new Chunk(Image.getInstance(ServletActionContext
				.getServletContext().getRealPath("/") +"logo.png"), 0, -15);
		
		logoText.add(logo);
		logoText.add("洛天工作室");
		document.add(logoText);

		Paragraph p = new Paragraph(grade.getYear() + "届（" + grade.getClassID()
				+ ")班第" + xueqi + "学期" + examType + "考试成绩公示", FontChinese);
		p.setAlignment(Element.ALIGN_CENTER);
		document.add(p);
		p = new Paragraph(" ");
		document.add(p);

		PdfPTable table = new PdfPTable(2 + courses.size());
		/*
		 * PdfPCell cell = new PdfPCell(new Paragraph("学号",FontChinese));
		 * cell.setBackgroundColor(BaseColor.CYAN); table.addCell(cell);
		 */

		PdfPCell cell = new PdfPCell(new Paragraph("姓名", FontChinese));
		cell.setBackgroundColor(BaseColor.CYAN);
		table.addCell(cell);

		for (int i = 0; i < courses.size(); i++) {
			cell = new PdfPCell(new Paragraph(courses.get(i).getName(),
					FontChinese));
			cell.setBackgroundColor(BaseColor.CYAN);
			table.addCell(cell);
		}
		cell = new PdfPCell(new Paragraph("总分", FontChinese));
		cell.setBackgroundColor(BaseColor.CYAN);
		table.addCell(cell);
		for (int i = 0; i < scoreTotals.size(); i++) {
			/*
			 * cell = new PdfPCell(new
			 * Paragraph(scoreTotals.get(i).getXuehao()+""));
			 * table.addCell(cell);
			 */
			cell = new PdfPCell(new Paragraph(scoreTotals.get(i).getName(),
					FontChinese));
			table.addCell(cell);
			for (int j = 0; j < scoreTotals.get(i).getScoreSmalls().size(); j++) {
				cell = new PdfPCell(new Paragraph(scoreTotals.get(i)
						.getScoreSmalls().get(j).getScore()
						+ ""));
				table.addCell(cell);
			}
			cell = new PdfPCell(new Paragraph(scoreTotals.get(i)
					.getTotalScore() + ""));
			table.addCell(cell);
		}
		float[] widths = new float[2 + courses.size()];
		Arrays.fill(widths, 1.2f);
		widths[0] = 1.8f;
		table.setWidths(widths);
		document.add(table);
	/*	Image image = Image.getInstance("C:/Users/leorain/AppData/Local/Temp/"+pictureName);
		image.scaleAbsolute(image.getWidth()*(5/12), image.getHeight()*(5/12));
	 
		System.out.println(image.getHeight());
		System.out.println(image.getWidth());
		Chunk picture = new Chunk(image,0,-300);
		System.out.println(pictureName);
	 
		Paragraph picturePara = new Paragraph();
		picturePara.setFont(FontChinese);
		picturePara.add("排名");
		picturePara.add(picture);
		document.add(picturePara);*/
		document.close();

		this.pdfStream = new ByteArrayInputStream(buffer.toByteArray());
		buffer.close();
		return SUCCESS;
	}

	public InputStream getPdfStream() {
		return pdfStream;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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

	public List<ScoreCount> getScoreCounts() {
		return scoreCounts;
	}

	public void setScoreCounts(List<ScoreCount> scoreCounts) {
		this.scoreCounts = scoreCounts;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public List<ScoreTotal> getScoreTotals() {
		return scoreTotals;
	}

	public void setScoreTotals(List<ScoreTotal> scoreTotals) {
		this.scoreTotals = scoreTotals;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}
/*
	public List<StuScoreCount> getStuScoreCounts() {
		return stuScoreCounts;
	}

	public void setStuScoreCounts(List<StuScoreCount> stuScoreCounts) {
		this.stuScoreCounts = stuScoreCounts;
	}

	private DefaultCategoryDataset getDataSet() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i = 0; i < stuScoreCounts.size(); i++) {
			dataset.addValue(stuScoreCounts.get(i).getScoreSum(), "", (i + 1)
					+ "、" + stuScoreCounts.get(i).getStuNameString());
		}
		return dataset;
	}

	public JFreeChart getChart() {
		return chart;
	}

	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}
*/
}
