package yoon.questions.model;

import java.sql.Date;

public class QuestionsDTO {
	private int num;
	private String pic;
	private String subject;
	private String author;
	private Date rdate;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getRdate() {
		return rdate;
	}
	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}
	public QuestionsDTO(int num, String pic, String subject, String author, Date rdate) {
		super();
		this.num = num;
		this.pic = pic;
		this.subject = subject;
		this.author = author;
		this.rdate = rdate;
	}
	
}
