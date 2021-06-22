package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.AptInfoDto;
import com.ssafy.happyhouse.model.dao.AptDaoImpl;

public class AptServiceImpl implements AptService {
	
	private static AptService AptService;
	
	private AptServiceImpl() {}
	
	public static AptService getAptService() {
		if(AptService == null)
			AptService = new AptServiceImpl();
		return AptService;
	}

	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		return AptDaoImpl.getAptDao().getSido();
	}

	@Override
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
		return AptDaoImpl.getAptDao().getGugunInSido(sido);
	}

	@Override
	public List<AptInfoDto> getDongInGugun(String gugun) throws Exception {
		return AptDaoImpl.getAptDao().getDongInGugun(gugun);
	}

	@Override
	public List<AptInfoDto> getAptInDong(String dong) throws Exception {
		return AptDaoImpl.getAptDao().getAptInDong(dong);
	}
	@Override
	public List<AptInfoDto> getAllApt() throws Exception {
		return AptDaoImpl.getAptDao().getAllApt();
	}
	@Override
	public List<AptInfoDto> getAptName(String AptName) throws Exception {
		return AptDaoImpl.getAptDao().getAptName(AptName);
	}

}
