package com.ssafy.happyhouse.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.AptInfoDto;
import com.ssafy.util.DBUtil;

public class AptDaoImpl implements AptDao {
	
	private static AptDao AptDao;
	
	private AptDaoImpl() {}
	
	public static AptDao getAptDao() {
		if(AptDao == null)
			AptDao = new AptDaoImpl();
		return AptDao;
	}

	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SidoGugunCodeDto> list = new ArrayList<SidoGugunCodeDto>();
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT left(sido_code,2) sido_code, sido_name FROM sidocode \n");
			sql.append("ORDER BY sido_code");
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				SidoGugunCodeDto dto = new SidoGugunCodeDto();
				dto.setSidoCode(rs.getString("sido_code"));
				dto.setSidoName(rs.getString("sido_name"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return list;
	}

	@Override
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SidoGugunCodeDto> list = new ArrayList<SidoGugunCodeDto>();
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT left(gugun_code,5) gugun_code, gugun_name FROM guguncode \n");
			sql.append("where left(gugun_code,2) = ?");
			sql.append("ORDER BY gugun_code");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, sido);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				SidoGugunCodeDto dto = new SidoGugunCodeDto();
				dto.setGugunCode(rs.getString("gugun_code"));
				dto.setGugunName(rs.getString("gugun_name"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return list;
	}

	@Override
	public List<AptInfoDto> getDongInGugun(String gugun) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AptInfoDto> list = new ArrayList<AptInfoDto>();
		System.out.println(gugun);
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT distinct dong FROM aptdeal \n");
			sql.append("where gugun = ? \n");
			sql.append("ORDER BY dong");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, gugun);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				AptInfoDto dto = new AptInfoDto();	
				dto.setDong(rs.getString("dong"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return list;
	}

	@Override
	public List<AptInfoDto> getAptInDong(String dong) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AptInfoDto> list = new ArrayList<AptInfoDto>();
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT amount,area,bunzi,dong,floor,name,aptid FROM aptdeal WHERE dong = ? \n");
			sql.append("ORDER BY name");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, dong);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				AptInfoDto dto = new AptInfoDto();
				dto.setAmount(rs.getString("amount"));
				dto.setArea(rs.getDouble("area"));
				dto.setBunzi(rs.getString("bunzi"));
				dto.setDong(rs.getString("dong"));
				dto.setFloor(rs.getInt("floor"));
				dto.setName(rs.getString("name"));
				dto.setAptid(rs.getInt("aptid"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		System.out.println(list.size());
		return list;
	}
	@Override
	public List<AptInfoDto> getAptName(String AptName) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AptInfoDto> list = new ArrayList<AptInfoDto>();
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT amount,area,bunzi,dong,floor,name,aptid FROM aptdeal WHERE name Like ? \n");
			sql.append("ORDER BY name");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1,"%"+ AptName + "%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				AptInfoDto dto = new AptInfoDto();
				dto.setAmount(rs.getString("amount"));
				dto.setArea(rs.getDouble("area"));
				dto.setBunzi(rs.getString("bunzi"));
				dto.setDong(rs.getString("dong"));
				dto.setFloor(rs.getInt("floor"));
				dto.setName(rs.getString("name"));
				dto.setAptid(rs.getInt("aptid"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return list;
	}
	
	@Override
	public List<AptInfoDto> getAllApt() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AptInfoDto> list = new ArrayList<AptInfoDto>();
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT amount,area,bunzi,dong,floor,name,aptid FROM aptdeal \n");
			sql.append("ORDER BY name");
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				AptInfoDto dto = new AptInfoDto();
				dto.setAmount(rs.getString("amount"));
				dto.setArea(rs.getDouble("area"));
				dto.setBunzi(rs.getString("bunzi"));
				dto.setDong(rs.getString("dong"));
				dto.setFloor(rs.getInt("floor"));
				dto.setName(rs.getString("name"));
				dto.setAptid(rs.getInt("aptid"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return list;
	}

}
