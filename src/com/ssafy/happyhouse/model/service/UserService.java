package com.ssafy.happyhouse.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.ssafy.happyhouse.model.dao.Member;
import com.ssafy.happyhouse.model.dao.UserDao;
import com.ssafy.util.DBUtil;

public class UserService {
	private UserDao dao = UserDao.getDao();

	private static UserService service;

	public static UserService getUserService() {
		if (service == null)
			service = new UserService();
		return service;
	}

	// 서비스 : Transaction 처리의 책임, Connection 생성 후 TX 관리
	public boolean login(String memName, String memPwd) throws SQLException {
		try (Connection con = DBUtil.getConnection()) {
			Member member = dao.select(con, memName);
			System.out.println("조회결과 : " + member);
			if (member != null && member.getMemPwd().equals(memPwd)) {
				return true;
			} else {
				return false;
			}
		}
		// return id.equals("ssafy") && pass.equals("1111");
	}
	public List<Member> search() {
		List<Member> result = new LinkedList<>();
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			result = dao.selectList(con);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con);
		}
		
		return result;

	}
	// 트랜잭션 처리하기 : 에러떴는데 가입이 되버림.
	// 우리의 가입 업무 :
	// 1. 회원 db에 저장한다.
	// 2. 가입된 회원을 조회해서 콘솔에 출력한다.
	public int join(Member member) throws SQLException {
		int result = -1;
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			result = dao.insert(con, member);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (con != null) {//con이 실행안됐을때 널포인터익셉션 가능성
				con.close();
			}
		}
		return result;
	}
	
	
	public int delete(String memName) {
		
		int result = -1;
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			result = dao.delete(con, memName);
		} catch (Exception e) {
			result = -1;
			e.printStackTrace();
		} finally {
			DBUtil.close(con);
		}
		
		return result;

	}

}