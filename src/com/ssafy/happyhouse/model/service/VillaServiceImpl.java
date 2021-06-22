package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.VillaInfoDto;
import com.ssafy.happyhouse.model.dao.VillaDaoImpl;

public class VillaServiceImpl implements VillaService {
	
	private static VillaService VillaService;
	
	private VillaServiceImpl() {}
	
	public static VillaService getVillaMapService() {
		if(VillaService == null)
			VillaService = new VillaServiceImpl();
		return VillaService;
	}

	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		return VillaDaoImpl.getVillaDao().getSido();
	}

	@Override
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
		return VillaDaoImpl.getVillaDao().getGugunInSido(sido);
	}

	@Override
	public List<VillaInfoDto> getDongInGugun(String gugun) throws Exception {
		return VillaDaoImpl.getVillaDao().getDongInGugun(gugun);
	}

	@Override
	public List<VillaInfoDto> getVillaInDong(String dong) throws Exception {
		return VillaDaoImpl.getVillaDao().getVillaInDong(dong);
	}
	@Override
	public List<VillaInfoDto> getAllVilla() throws Exception {
		return VillaDaoImpl.getVillaDao().getAllVilla();
	}
	@Override
	public List<VillaInfoDto> getVillaName(String AptName) throws Exception {
		return VillaDaoImpl.getVillaDao().getVillaName(AptName);
	}

}
