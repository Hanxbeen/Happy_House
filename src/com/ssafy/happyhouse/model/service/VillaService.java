package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.VillaInfoDto;

public interface VillaService {
	
	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<VillaInfoDto> getDongInGugun(String gugun) throws Exception;
	List<VillaInfoDto> getVillaInDong(String dong) throws Exception;
	List<VillaInfoDto> getAllVilla() throws Exception;
	List<VillaInfoDto> getVillaName(String AptName) throws Exception;
	
}
