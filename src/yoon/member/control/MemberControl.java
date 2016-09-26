package yoon.member.control;

import java.math.BigInteger;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.Enumeration;

import javax.crypto.Cipher;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.bouncycastle.jcajce.provider.digest.Keccak.DigestKeccak;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import yoon.member.model.MemberDTO;
import yoon.member.model.MemberManager;

public class MemberControl extends HttpServlet {
	public static final int KEY_SIZE = 1024;

	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String m = req.getParameter("method");
		System.out.println(m);
		if (m != null) {
			m = m.trim();
			if (m.equals("loginform")) {
				// 로그인창
				loginform(req, res);
			} else if (m.equals("login")) {
				// 로그인
				login(req, res);
			} else if (m.equals("logout")) {
				// 로그아웃
				logout(req, res);
			} else if (m.equals("joinform")) {
				// 회원가입창
				joinform(req, res);
			} else if (m.equals("join")) {// 회원가입
				join(req, res);
			} else if (m.equals("mypage")) {// 마이페이지가기
				mypage(req, res);
			} else if (m.equals("myinfo")) {// 정보수정
				myinfo(req, res);
			} else if ( m.equals("mypic")){
				mypic(req, res);
			} else if ( m.equals("picupload")){
				picupload(req, res);
			} else {
				// res.sendRedirect("main.do");
			}
		} else {

			// res.sendRedirect("main.do");
		}
	}

	private void loginform(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		try {
			KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
			generator.initialize(KEY_SIZE);

			KeyPair keyPair = generator.genKeyPair();
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");

			PublicKey publicKey = keyPair.getPublic();
			PrivateKey privateKey = keyPair.getPrivate();

			HttpSession session = req.getSession();
			// 세션에 공개키의 문자열을 키로하여 개인키를 저장한다.
			session.setAttribute("__rsaPrivateKey__", privateKey);

			// 공개키를 문자열로 변환하여 JavaScript RSA 라이브러리 넘겨준다.
			RSAPublicKeySpec publicSpec = (RSAPublicKeySpec) keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);

			String publicKeyModulus = publicSpec.getModulus().toString(16);
			String publicKeyExponent = publicSpec.getPublicExponent().toString(16);
			req.setAttribute("publicKeyModulus", publicKeyModulus);
			req.setAttribute("publicKeyExponent", publicKeyExponent);
			
			req.setAttribute("rpage",req.getHeader("referer"));
			String view = "login/login.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(view);
			rd.forward(req, res);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	private void login(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		MemberManager manager = MemberManager.getInstance();
		String id = req.getParameter("securedId");
		String pwd = req.getParameter("securedPwd");
		String rpage = req.getParameter("rpage");
;
		if (id != null)
			id = id.trim();
		// if(pwd != null)pwd = pwd.trim();

		HttpSession session = req.getSession();

		PrivateKey privateKey = (PrivateKey) session.getAttribute("__rsaPrivateKey__");
		
		if (privateKey == null) {
			System.out.println("private error!!");
			String view = "login/loginFailed.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(view);
			rd.forward(req, res);
		}
		try {
			String password = CryptoSHA3(decryptRsa(privateKey, pwd), 256);
			System.out.println(password);
			MemberDTO dto = manager.loginB(id, password);
			
			if (dto != null) {// 로그인 성공
				session.setAttribute("loginOkUser", dto);
				if(rpage.contains("member.do"))rpage="main.do";
				res.sendRedirect(rpage);
				session.removeAttribute("__rsaPrivateKey__");
			} else { // 로그인 실패 혹은 서버에러
				String view = "login/loginFailed.jsp";
				RequestDispatcher rd = req.getRequestDispatcher(view);
				rd.forward(req, res);
			}
		} catch (Exception e) {
			System.out.println("e on login : " + e);
		}

	}

	private void logout(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		session.removeAttribute("loginOkUser");

		res.sendRedirect("main.do");
	}

	private void joinform(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		try {
			KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
			generator.initialize(KEY_SIZE);

			KeyPair keyPair = generator.genKeyPair();
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");

			PublicKey publicKey = keyPair.getPublic();
			PrivateKey privateKey = keyPair.getPrivate();

			HttpSession session = req.getSession();
			// 세션에 공개키의 문자열을 키로하여 개인키를 저장한다.
			session.setAttribute("__rsaPrivateKey__", privateKey);

			// 공개키를 문자열로 변환하여 JavaScript RSA 라이브러리 넘겨준다.
			RSAPublicKeySpec publicSpec = (RSAPublicKeySpec) keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);

			String publicKeyModulus = publicSpec.getModulus().toString(16);
			String publicKeyExponent = publicSpec.getPublicExponent().toString(16);
			req.setAttribute("publicKeyModulus", publicKeyModulus);
			req.setAttribute("publicKeyExponent", publicKeyExponent);

			String view = "join/join.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(view);
			rd.forward(req, res);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void join(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		MemberManager manager = MemberManager.getInstance();
		String id = req.getParameter("securedId");
		String pwd = req.getParameter("securedPwd");
		String name = req.getParameter("securedName");
		String nick = req.getParameter("securedNick");
		String email = req.getParameter("securedEmail");
		HttpSession session = req.getSession();

		PrivateKey privateKey = (PrivateKey) session.getAttribute("__rsaPrivateKey__");

		
		if (privateKey == null) {
			System.out.println("private error!!");
		}
		try {

			String password = CryptoSHA3(decryptRsa(privateKey, pwd), 256);
			MemberDTO dto = new MemberDTO(-1, id, password, name, nick, email, "", -1, null);
			boolean check = manager.joinB(dto);
			String view = "";
			if(check) {
				view = "join/joinOk.jsp";
				RequestDispatcher rd = req.getRequestDispatcher(view);
				rd.forward(req, res);
				session.removeAttribute("__rsaPrivateKey__");
			}else {
				view = "join/joinOk.jsp";
				req.setAttribute("check", "fail");
				RequestDispatcher rd = req.getRequestDispatcher(view);
				rd.forward(req, res);
			}
		} catch (Exception e) {
			System.out.println("e on login : " + e);
		}

	}

	private void mypage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
			generator.initialize(KEY_SIZE);

			KeyPair keyPair = generator.genKeyPair();
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");

			PublicKey publicKey = keyPair.getPublic();
			PrivateKey privateKey = keyPair.getPrivate();

			HttpSession session = req.getSession();
			// 세션에 공개키의 문자열을 키로하여 개인키를 저장한다.
			session.setAttribute("__rsaPrivateKey__", privateKey);

			// 공개키를 문자열로 변환하여 JavaScript RSA 라이브러리 넘겨준다.
			RSAPublicKeySpec publicSpec = (RSAPublicKeySpec) keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);

			String publicKeyModulus = publicSpec.getModulus().toString(16);
			String publicKeyExponent = publicSpec.getPublicExponent().toString(16);
			req.setAttribute("publicKeyModulus", publicKeyModulus);
			req.setAttribute("publicKeyExponent", publicKeyExponent);

			String view = "join/mypage.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(view);
			rd.forward(req, res);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void myinfo(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		MemberManager manager = MemberManager.getInstance();
		String id = req.getParameter("securedId");
		if (id != null)
			id = id.trim();
		String pwd = req.getParameter("securedPwd");
		String name = req.getParameter("securedName");
		if (name != null)
			name = name.trim();
		String nick = req.getParameter("securedNick");
		if (nick != null)
			nick = nick.trim();
		String email = req.getParameter("securedEmail");
		if (email != null)
			email = email.trim();

		HttpSession session = req.getSession();

		PrivateKey privateKey = (PrivateKey) session.getAttribute("__rsaPrivateKey__");

		
		if (privateKey == null) {
			System.out.println("private error!!");
		}
		try {

			String password = CryptoSHA3(decryptRsa(privateKey, pwd), 256);
			MemberDTO dto = new MemberDTO(-1, id, password, name, nick, email, "", -1, null);

			boolean check = manager.myinfoB(dto);
			String view = "";
			if (check) {
				view = "join/mypageOk.jsp";
				req.setAttribute("update", check);
				RequestDispatcher rd = req.getRequestDispatcher(view);
				rd.forward(req, res);
				session.removeAttribute("__rsaPrivateKey__");
			} else {
				view = "join/mypageOk.jsp";
				req.setAttribute("update", check);
				RequestDispatcher rd = req.getRequestDispatcher(view);
				rd.forward(req, res);
			}
		} catch (Exception e) {
			System.out.println("e on login : " + e);
		}
	}
	private void mypic(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String view ="join/mypic.jsp";
		RequestDispatcher rd = req.getRequestDispatcher(view);
		rd.forward(req, res);
	}
	private void picupload(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String fileName = "";
		File file = null;
		String id = req.getParameter("id");
		if(id!=null)id=id.trim();
		
		String tmpPath = getServletContext().getRealPath("/img");
		File dir = new File(tmpPath,id);
		if(!dir.exists())dir.mkdirs();
		
		File[] destroy = dir.listFiles();
		for(File f : destroy)f.delete();
		
		String savePath = getServletContext().getRealPath("/img/"+id);
	
		Enumeration files = null;
		int maxSize = 1 * 1024 * 1024;
		try{
			MultipartRequest multi = new MultipartRequest(req, savePath, maxSize, "utf-8", new DefaultFileRenamePolicy());// new DefaultFileRenamePolicy()는 저장폴더에 같은 이름의 파일이 있을경우 자동적으로 뒤에 숫자1
			fileName = multi.getFilesystemName("uploadFile");
			System.out.println("filename : " +fileName);
			
			files = multi.getFileNames();
			String name = (String)files.nextElement();
			file = multi.getFile(name);
			if(fileName == null){//파일업로드되지 않았을 경우
				System.out.println("file upload failed");
			}else{
				System.out.println("filename : " + fileName);
			}
		}catch(Exception e){
			System.out.println("exception : " + e);
		}
		MemberManager manager = MemberManager.getInstance();
		HttpSession session = req.getSession();
		MemberDTO dto = (MemberDTO)session.getAttribute("loginOkUser");
		dto.setPic("img/"+id+ "/"+ fileName);
		session.setAttribute("loginOkUser", dto);
		manager.picuploadB(id, "img/"+id+ "/"+ fileName);
		String view ="main.do";
		RequestDispatcher rd = req.getRequestDispatcher(view);
		res.sendRedirect(view);
	
	}
	private String decryptRsa(PrivateKey privateKey, String securedValue) throws Exception {
		// System.out.println("will decrypt : " + securedValue);
		Cipher cipher = Cipher.getInstance("RSA");
		byte[] encryptedBytes = hexToByteArray(securedValue);
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
		String decryptedValue = new String(decryptedBytes, "utf-8"); // 문자 인코딩
																		// 주의.
		return decryptedValue;
	}

	public static byte[] hexToByteArray(String hex) {
		if (hex == null || hex.length() % 2 != 0) {
			return new byte[] {};
		}

		byte[] bytes = new byte[hex.length() / 2];
		for (int i = 0; i < hex.length(); i += 2) {
			byte value = (byte) Integer.parseInt(hex.substring(i, i + 2), 16);
			bytes[(int) Math.floor(i / 2)] = value;
		}
		return bytes;
	}

	public String CryptoSHA3(String key, int hash) {

		// 1.x 버전
		// DigestSHA3 md = new DigestSHA3(hash);
		// 2.x 이상 부터
		DigestKeccak md = new DigestKeccak(hash);
		try {
			md.update(key.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		byte[] digest = md.digest();

		return org.bouncycastle.util.encoders.Hex.toHexString(digest);
	}
}
