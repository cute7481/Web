package yoon.board.model;

import java.sql.*;

public class BoardDTO {
	private int num;
	private String id;
	private int readnum;
	private String subject;
	private String content;
	private Date rdate;
	private int replycnt;
	private String nick;
	public BoardDTO(int num, String id, String subject, String content,int readnum, Date rdate, int replycnt, String nick) {
		super();
		this.num = num;
		this.id = id;
		this.readnum = readnum;
		this.subject = subject;
		this.content = content;
		this.rdate = rdate;
		this.replycnt = replycnt;
		this.nick = nick;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getReadnum() {
		return readnum;
	}
	public void setReadnum(int readnum) {
		this.readnum = readnum;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRdate() {
		return rdate;
	}
	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}
	public int getReplycnt(){
		return replycnt;
	}
	public void setReplycnt(int replycnt){
		this.replycnt = replycnt;
	}
	public String getNick(){
		return nick;
	}
	public void setNick(String nick){
		this.nick = nick;
	}
}
