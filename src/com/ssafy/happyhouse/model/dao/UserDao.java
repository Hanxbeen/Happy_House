package com.ssafy.happyhouse.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.ssafy.util.DBUtil;

//dao는 커넥션을 만들지않고 공급받아서 사용한다.!!!!!
public class UserDao {
	// singleton
	private static UserDao dao = new UserDao();

	public static UserDao getDao() {
		return dao;
	}

	private UserDao() {
	}

	// DAO는 개별 SQL 처리가 목적
	// 트랜잭션을 처리해야하니까..
	// service에서 전달받은 connection을 얻어서 쓸뿐.... 자기가 만들거나 클로즈하면 안된다.
	// 예외 처리 :
	public List<Member> selectList(Connection con) throws SQLException {
		
		List<Member> member = new LinkedList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = "select * from member";

		try {
			pstmt = con.prepareStatement(sql);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				String memName = rset.getString(1);
				String memEmail = rset.getString(2);
				String memPwd = rset.getString(3);
				
				member.add(new Member(memName, memEmail, memPwd));
			}
		} finally {
			DBUtil.close(pstmt, rset);
		}

		return member;
	}
	public Member select(Connection con, String memName) throws SQLException {
		// 할일 작성 - Query
		Member member = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "select * from member where memName = ?";
		try {
			// 질의 준비
			pstmt = con.prepareStatement(sql);
			// 파라미터 세팅
			System.out.println(memName);
			pstmt.setString(1, memName);
			// 쿼리 실행 - 쿼리의 타입은???
			rset = pstmt.executeQuery();
			// 레절트셋은 기본적으로 어떻게 데이터를 관리하고 있냐면
			if (rset.next()) {
				System.out.println(1111);
				String memEmail = rset.getString(2);
				String memPwd = rset.getString(3);

				member = new Member(memName, memEmail, memPwd);
			}
		} finally {
			DBUtil.close(pstmt, rset);
		}
		return member;
	}
	// auto closeable : try with resource에서 선언된 자원을 try 끝난 후 자동으로 close처리해준다.
//	public int insert2(Connection con, Member member) throws SQLException {
//		int result = 0;
//		String sql = "insert into ssafy_member(userid, username, userpwd, email, address) values(?, ?, ?, ?, ?)";
//		try (PreparedStatement pstmt = con.prepareStatement(sql);) {
//			pstmt.setString(1, member.getUserId());
//			pstmt.setString(2, member.getUserName());
//			pstmt.setString(3, member.getUserpwd());
//			pstmt.setString(4, member.getEmail());
//			pstmt.setString(5, member.getAddress());
//			result = pstmt.executeUpdate();
//		}
//		return result;
//	}

	public int insert(Connection con, Member member) throws SQLException {
		PreparedStatement pstmt = null;
		int result = 0;
		// 할일 작성 - Query
		String sql = "insert into member(memName,memEmail,memPwd) values(?, ?, ?)";
		try {
			// 질의 준비
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getMemName());
			pstmt.setString(2, member.getMemEmail());
			pstmt.setString(3, member.getMemPwd());
			// 쿼리 실행 - 쿼리의 타입은??
			result = pstmt.executeUpdate();
		} finally {
			DBUtil.close(pstmt,null);
		}
		return result;
	}

	public int delete(Connection con, String memName) throws SQLException {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = "delete from member where memName = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memName);
			result = pstmt.executeUpdate();

		} finally {
			DBUtil.close(pstmt, null);
		}
		return result;
	}
}