/**
 * 
 */
package com.xscj.score.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.opensymphony.xwork2.ActionSupport;
import com.xscj.domain.Grade;
import com.xscj.domain.ScoreBySXT;
import com.xscj.domain.SubStudent;
import com.xscj.service.GradeSetUp;
import com.xscj.service.ScoreService;
import com.xscj.service.StuService;
import com.xscj.util.Util;
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
public class ScorePrinterSucAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3406371248132575847L;
	@Autowired
	@Qualifier("scoreServiceImpl")
	private ScoreService scoreService;
	@Autowired
	@Qualifier("stuServiceImpl")
	private StuService stuService;
	@Autowired
	@Qualifier("gradeSetUpImpl")
	private GradeSetUp gradeSetUp;
	private String stuXueHao;
	private String xueqi;
	private String examType;
	private SubStudent subStudent;
	private Grade grade;
	private List<ScoreBySXT> scoreBySXTs;
	private double totalScore;
	private InputStream pdfStream;
	private String fileName;
	public String generateContract() throws Exception{
		if(stuXueHao==null || xueqi==null || examType==null || stuXueHao.trim().equals("") || xueqi.trim().equals("") || examType.trim().equals("") || !Util.isNumeric(stuXueHao) || !Util.isNumeric(xueqi))
			return INPUT;
		this.subStudent = stuService.getSubStudentByXueHao(Integer.parseInt(stuXueHao));
		this.grade = gradeSetUp.getGradeBystuXueHao(Integer.parseInt(stuXueHao));
		this.scoreBySXTs = scoreService.getScoreBySXTs(Integer.parseInt(stuXueHao), Integer.parseInt(xueqi), examType);
		fileName= new String((this.grade.getYear()+"届（"+this.grade.getClassID()+")班"+this.subStudent.getStuName()+"第"+this.xueqi+"学期"+this.examType+"考试成绩单"+".pdf").getBytes(),"ISO8859-1");
		Document document = new Document();
		
		BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		Font FontChinese = new Font(bfChinese); 
		
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		PdfWriter.getInstance(document, buffer);
		document.open();
		Paragraph logoText = new Paragraph();
		logoText.setFont(FontChinese);
		Chunk logo = new Chunk(Image.getInstance(ServletActionContext.getServletContext().getRealPath("/")+"logo.png"),0,-15);
		logoText.add(logo);
		logoText.add("洛天工作室");
		document.add(logoText);
		Paragraph p = new Paragraph("石牌高级中学"+this.grade.getYear()+"届("+this.grade.getClassID()+")班"+this.subStudent.getStuName()+"同学成绩单",FontChinese);
		p.setAlignment(Element.ALIGN_CENTER);
		document.add(p);
		Paragraph p2 = new Paragraph("第"+this.xueqi+"学期 "+this.examType+"考试",FontChinese);
		p2.setAlignment(Element.ALIGN_CENTER);
		document.add(p2);
		document.add(new Paragraph(" "));
		PdfPTable table = new PdfPTable(4);
		PdfPCell cell = new PdfPCell(new Paragraph("课程编号",FontChinese));
		cell.setBackgroundColor(BaseColor.CYAN);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Paragraph("课程名称",FontChinese));
		cell.setBackgroundColor(BaseColor.CYAN);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Paragraph("考试时间",FontChinese));
		cell.setBackgroundColor(BaseColor.CYAN);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Paragraph("最终成绩",FontChinese));
		cell.setBackgroundColor(BaseColor.CYAN);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		
		for(int i=0; i<this.scoreBySXTs.size(); i++)
		{
			table.addCell(new Paragraph(this.scoreBySXTs.get(i).getCourseID()));
			table.addCell(new Paragraph(this.scoreBySXTs.get(i).getCourseName(),FontChinese));
			table.addCell(new Paragraph(this.scoreBySXTs.get(i).getExamTime()));
			table.addCell(new Paragraph(this.scoreBySXTs.get(i).getScore()+"分",FontChinese));
			this.totalScore = this.totalScore + this.scoreBySXTs.get(i).getScore();
		}
		cell = new PdfPCell(new Paragraph("总分",FontChinese));
		cell.setBackgroundColor(BaseColor.CYAN);
		cell.setColspan(3);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		table.addCell(new Paragraph(this.totalScore+"分",FontChinese));
		document.add(table);
		document.close();

		this.pdfStream = new ByteArrayInputStream(buffer.toByteArray());
		buffer.close();
		return SUCCESS;
	}

public InputStream getPdfStream() {
		return pdfStream;
	}

public String getStuXueHao() {
	return stuXueHao;
}

public void setStuXueHao(String stuXueHao) {
	this.stuXueHao = stuXueHao;
}

public String getXueqi() {
	return xueqi;
}

public void setXueqi(String xueqi) {
	this.xueqi = xueqi;
}

public String getExamType() {
	return examType;
}

public void setExamType(String examType) {
	this.examType = examType;
}

public List<ScoreBySXT> getScoreBySXTs() {
	return scoreBySXTs;
}

public void setScoreBySXTs(List<ScoreBySXT> scoreBySXTs) {
	this.scoreBySXTs = scoreBySXTs;
}

public SubStudent getSubStudent() {
	return subStudent;
}

public void setSubStudent(SubStudent subStudent) {
	this.subStudent = subStudent;
}

public Grade getGrade() {
	return grade;
}

public void setGrade(Grade grade) {
	this.grade = grade;
}

public double getTotalScore() {
	return totalScore;
}

public void setTotalScore(double totalScore) {
	this.totalScore = totalScore;
}

public String getFileName() {
	return fileName;
}

public void setFileName(String fileName) {
	this.fileName = fileName;
}
	 

}
