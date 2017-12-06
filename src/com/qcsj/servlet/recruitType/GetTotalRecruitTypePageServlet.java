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

@WebServlet(name = "GetTotalRecruitTypePageServlet", urlPatterns = {"/GetTotalRecruitTypePage"})
public class GetTotalRecruitTypePageServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SuperInfo si = ServiceFactory.getRecruitTypeService().getTotalRecruitTypePage(5, request.getSession());
		System.out.println("GetTotalRecruitTypePageServlet----"+si.getRet());
		switch (si.getRet()) {
			case 0:
				Gson g = new Gson();
				String jsonStr = g.toJson(si.getO());
				System.out.println("jsonStr----"+jsonStr);
				response.getWriter().print(jsonStr);
				break;
			case 2:
				response.getWriter().print("<script >alert('未登录！');location.href='manage/manageLogin.html';</script>");
				break;
			case 1:
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
