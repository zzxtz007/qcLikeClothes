package com.qcsj.servlet.recruitType;

import com.google.gson.Gson;
import com.qcsj.service.ServiceFactory;
import com.qcsj.service.ServiceUtil.SuperInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GetAllFathersTypeServlet",urlPatterns = {"/GetAllFathersType"})
public class GetAllFathersTypeServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		SuperInfo si = ServiceFactory.getRecruitTypeService().getAllFathersType(request.getSession());
		System.out.println("GetAllFathersTypeServlet----" + si.getRet());
		switch (si.getRet()) {
			case 0:
				Gson g = new Gson();
				String jsonStr = g.toJson(si.getLists());
				response.getWriter().print(jsonStr);
				break;
			case 1:
				response.getWriter().print("<script >alert('未知错误！');location.href='manage/manageMain.html';</script>");
				break;
			case 2:
				response.getWriter().print("<script >alert('未登录！');location.href='manage/manageLogin.html';</script>");
				break;
			case 3:
			case 4:
			default:
				response.getWriter().print("<script >alert('未知错误！');location.href='manage/manageMain.html';</script>");
				break;
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
}
