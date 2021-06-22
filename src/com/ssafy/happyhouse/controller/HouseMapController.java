package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ssafy.happyhouse.model.AptInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.VillaInfoDto;
import com.ssafy.happyhouse.model.service.AptServiceImpl;
import com.ssafy.happyhouse.model.service.VillaServiceImpl;

@WebServlet("/map")
public class HouseMapController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		process(request, response);
	}

	protected void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String act = request.getParameter("act");
		String AptorVilla = request.getParameter("AptorVilla");
		if ("sido".equals(act)) {
			PrintWriter out = response.getWriter();
			List<SidoGugunCodeDto> list = null;
			JSONArray arr = new JSONArray();
			try {
				if (AptorVilla.equals("apt")) {
					list = AptServiceImpl.getAptService().getSido();
				} else {
					list = VillaServiceImpl.getVillaMapService().getSido();
				}
				for (SidoGugunCodeDto dto : list) {
					JSONObject obj = new JSONObject();
					obj.put("sido_code", dto.getSidoCode());
					obj.put("sido_name", dto.getSidoName());
					arr.add(obj);
				}
			} catch (Exception e) {
				arr = new JSONArray();
				JSONObject obj = new JSONObject();
				obj.put("message_code", "-1");
				arr.add(obj);
				e.printStackTrace();
			} finally {
				out.print(arr.toJSONString());
				out.close();
			}
		} // sido
		else if ("gugun".equals(act)) {
			String sido = request.getParameter("sido");
			PrintWriter out = response.getWriter();
			List<SidoGugunCodeDto> list = null;
			JSONArray arr = new JSONArray();
			try {
				if (AptorVilla.equals("apt")) {
					list = AptServiceImpl.getAptService().getGugunInSido(sido);
				} else {
					list = VillaServiceImpl.getVillaMapService().getGugunInSido(sido);
				}
				for (SidoGugunCodeDto dto : list) {
					JSONObject obj = new JSONObject();
					obj.put("gugun_code", dto.getGugunCode());
					obj.put("gugun_name", dto.getGugunName());
					arr.add(obj);
				}
			} catch (Exception e) {
				arr = new JSONArray();
				JSONObject obj = new JSONObject();
				obj.put("message_code", "-1");
				arr.add(obj);
				e.printStackTrace();
			} finally {
				out.print(arr.toJSONString());
				out.close();
			}
		} // gugun
		else if ("dong".equals(act)) {
			String gugun = request.getParameter("gugun");
			PrintWriter out = response.getWriter();
			Object list = null;
			JSONArray arr = new JSONArray();
			try {
				if (AptorVilla.equals("apt")) {
					list = AptServiceImpl.getAptService().getDongInGugun(gugun);
					for (AptInfoDto dto : (List<AptInfoDto>) list) {
						JSONObject obj = new JSONObject();
						obj.put("dong", dto.getDong());
						arr.add(obj);
					}
				} else {
					list = VillaServiceImpl.getVillaMapService().getDongInGugun(gugun);
					for (VillaInfoDto dto : (List<VillaInfoDto>) list) {
						JSONObject obj = new JSONObject();
						obj.put("dong", dto.getDong());
						arr.add(obj);
					}
				}
			} catch (Exception e) {
				arr = new JSONArray();
				JSONObject obj = new JSONObject();
				obj.put("message_code", "-1");
				arr.add(obj);
				e.printStackTrace();
			} finally {
				out.print(arr.toJSONString());
				out.close();
			}
		} // dong
		else if ("apt".equals(act)) {
			String dong = request.getParameter("dong");
			PrintWriter out = response.getWriter();
			Object list = null;
			JSONArray arr = new JSONArray();
			try {
				if (AptorVilla.equals("apt")) {
					list = AptServiceImpl.getAptService().getAptInDong(dong);
					for (AptInfoDto dto : (List<AptInfoDto>) list) {
						JSONObject obj = new JSONObject();
						obj.put("villaid", dto.getAptid());
						obj.put("dong", dto.getDong());
						obj.put("bunzi", dto.getBunzi());
						obj.put("name", dto.getName());
						obj.put("floor", dto.getFloor());
						obj.put("area", dto.getArea());
						obj.put("amount", dto.getAmount());
						arr.add(obj);
					}
				} else {
					list = VillaServiceImpl.getVillaMapService().getVillaInDong(dong);
					for (VillaInfoDto dto : (List<VillaInfoDto>) list) {
						JSONObject obj = new JSONObject();
						obj.put("villaid", dto.getVillaid());
						obj.put("dong", dto.getDong());
						obj.put("bunzi", dto.getBunzi());
						obj.put("name", dto.getName());
						obj.put("floor", dto.getFloor());
						obj.put("area", dto.getArea());
						obj.put("amount", dto.getAmount());
						arr.add(obj);

					}
				}
				
			} catch (Exception e) {
				arr = new JSONArray();
				JSONObject obj = new JSONObject();
				obj.put("message_code", "-1");
				arr.add(obj);
				e.printStackTrace();
			} finally {
				out.print(arr.toJSONString());
				out.close();
			}
		} // dong
		else if ("allApt".equals(act)) {
			PrintWriter out = response.getWriter();
			Object list = null;
			JSONArray arr = new JSONArray();
			try {
				if (AptorVilla.equals("apt")) {
					list = AptServiceImpl.getAptService().getAllApt();
					for (AptInfoDto dto : (List<AptInfoDto>) list) {
						JSONObject obj = new JSONObject();
						obj.put("villaid", dto.getAptid());
						obj.put("dong", dto.getDong());
						obj.put("bunzi", dto.getBunzi());
						obj.put("name", dto.getName());
						obj.put("floor", dto.getFloor());
						obj.put("area", dto.getArea());
						obj.put("amount", dto.getAmount());
						arr.add(obj);
					}
				} else {
					list = VillaServiceImpl.getVillaMapService().getAllVilla();
					for (VillaInfoDto dto : (List<VillaInfoDto>) list) {
						JSONObject obj = new JSONObject();
						obj.put("villaid", dto.getVillaid());
						obj.put("dong", dto.getDong());
						obj.put("bunzi", dto.getBunzi());
						obj.put("name", dto.getName());
						obj.put("floor", dto.getFloor());
						obj.put("area", dto.getArea());
						obj.put("amount", dto.getAmount());
						arr.add(obj);

					}
				}
			} catch (Exception e) {
				arr = new JSONArray();
				JSONObject obj = new JSONObject();
				obj.put("message_code", "-1");
				arr.add(obj);
				e.printStackTrace();
			} finally {
				out.print(arr.toJSONString());
				out.close();
			}
		} // dong
		else if ("aptName".equals(act)) {
			String aptName = request.getParameter("aptName");
			PrintWriter out = response.getWriter();
			Object list = null;
			JSONArray arr = new JSONArray();
			try {
				if (AptorVilla.equals("apt")) {
					list = AptServiceImpl.getAptService().getAptName(aptName);
					for (AptInfoDto dto : (List<AptInfoDto>) list) {
						JSONObject obj = new JSONObject();
						obj.put("villaid", dto.getAptid());
						obj.put("dong", dto.getDong());
						obj.put("bunzi", dto.getBunzi());
						obj.put("name", dto.getName());
						obj.put("floor", dto.getFloor());
						obj.put("area", dto.getArea());
						obj.put("amount", dto.getAmount());
						arr.add(obj);
					}
				} else {
					list = VillaServiceImpl.getVillaMapService().getVillaName(aptName);
					for (VillaInfoDto dto : (List<VillaInfoDto>) list) {
						JSONObject obj = new JSONObject();
						obj.put("villaid", dto.getVillaid());
						obj.put("dong", dto.getDong());
						obj.put("bunzi", dto.getBunzi());
						obj.put("name", dto.getName());
						obj.put("floor", dto.getFloor());
						obj.put("area", dto.getArea());
						obj.put("amount", dto.getAmount());
						arr.add(obj);

					}
				}
			} catch (Exception e) {
				arr = new JSONArray();
				JSONObject obj = new JSONObject();
				obj.put("message_code", "-1");
				arr.add(obj);
				e.printStackTrace();
			} finally {
				out.print(arr.toJSONString());
				out.close();
			}
		} // dong
	}// process

}