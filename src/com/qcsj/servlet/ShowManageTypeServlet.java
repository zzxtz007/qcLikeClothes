package com.qcsj.servlet;

import com.google.gson.Gson;
import com.qcsj.service.ServiceFactory;
import com.qcsj.service.ServiceUtil.SuperInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ShowManageTypeServlet",urlPatterns = {"/ShowManageType"})
public class ShowManageTypeServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=UTF-8");
		String pageSize = request.getParameter("pagesize");
		String pageNum = request.getParameter("pagenum");
		System.out.println(pageSize+""+pageNum);
		SuperInfo si = ServiceFactory.getRecruitTypeService().ShowManageType(pageSize,pageNum,request.getSession());
		System.out.println("ShowManageTypeServlet----"+si.getRet());
		switch (si.getRet()) {
			case 0:
				Gson g = new Gson();
				String jsonStr = g.toJson(si.getLists());
				System.out.println(jsonStr);
				response.getWriter().print(jsonStr);
				break;
			case 1:
				response.getWriter().print("<script >alert('未知错误！');location.href='manage/manageMain.html';</script>");
				break;
			case 2:
				response.getWriter().print("<script >alert('未登录！');location.href='manage/manageLogin.html';</script>");
				break;
			default:
				response.getWriter().print("<script >alert('未知错误！');location.href='manage/manageMain.html';</script>");
				break;
		}
	}
}
