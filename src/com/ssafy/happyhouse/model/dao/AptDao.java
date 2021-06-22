package com.ssafy.happyhouse.model.dao;

import java.util.List;

import com.ssafy.happyhouse.model.AptInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;

public interface AptDao {

	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<AptInfoDto> getDongInGugun(String gugun) throws Exception;
	List<AptInfoDto> getAllApt() throws Exception;
	List<AptInfoDto> getAptInDong(String dong) throws Exception;
	List<AptInfoDto> getAptName(String AptName) throws Exception;
	
}
