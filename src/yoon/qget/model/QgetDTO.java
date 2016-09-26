package yoon.qget.model;

import java.sql.Date;

public class QgetDTO {
	private int num;
	private String id;
	private String content;
	private int good;
	private int bad;
	private Date rdate;
	private String nick;
	private String ctime;
	public QgetDTO(int num, String id, String content, int good, int bad, Date rdate, String nick) {
		super();
		this.num = num;
		this.id = id;
		this.content = content;
		this.good = good;
		this.bad = bad;
		this.rdate = rdate;
		this.nick = nick;
	}
	public QgetDTO(String ctime, int num, String id, String content, int good, int bad, Date rdate, String nick) {
		super();
		this.ctime = ctime;
		this.num = num;
		this.id = id;
		this.content = content;
		this.good = good;
		this.bad = bad;
		this.rdate = rdate;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getGood() {
		return good;
	}
	public void setGood(int good) {
		this.good = good;
	}
	public int getBad() {
		return bad;
	}
	public void setBad(int bad) {
		this.bad = bad;
	}
	public Date getRdate() {
		return rdate;
	}
	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getCtime() {
		return ctime;
	}
	public void setCtime(String ctime) {
		this.ctime = ctime;
	}
}
