package yoon.member.model;

import java.sql.Date;

public class MemberDTO {
	private int num;
	private String id;
	private String pwd;
	private String name;
	private String nick;
	private String email;
	private String pic;
	private int birth;
	private Date rdate;
	public MemberDTO(int num, String id, String pwd, String name, String nick, String email, String pic, int birth, Date rdate) {
		super();
		this.num = num;
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.nick = nick;
		this.email = email;
		this.pic = pic;
		this.birth = birth;
		this.rdate = rdate;
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
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.name = nick;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public int getBirth() {
		return birth;
	}
	public void setBirth(int birth) {
		this.birth = birth;
	}
	public Date getRdate() {
		return rdate;
	}
	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}
	
}
