package yoon.member.model;

public class MemberManager {
	MemberDAO dao;
	private static MemberManager instance = new MemberManager();
	private MemberManager(){
		dao = new MemberDAO();
	}
	public static MemberManager getInstance(){
		return instance;
	}
	public MemberDTO loginB(String id, String pwd){
		return dao.login(id, pwd);
	}
	public boolean joinB(MemberDTO dto){
		return dao.join(dto);
	}
	public String idSearchB(String name, String email){
		return dao.idSearch(name, email);
	}
	public boolean pwdUpdateB(String pwd, String id){
		return dao.pwdUpdate(pwd, id);
	}
	public String pwdCheckB(String id){
		return dao.pwdCheck(id);
	}
	public String idCheck2B(String nick){
		return dao.idCheck2(nick);
	}
	public boolean myinfoB(MemberDTO dto){
		return dao.myinfo(dto);
	}
	public void picuploadB(String id, String picpath){
		dao.picupload(id, picpath);
	}
}
