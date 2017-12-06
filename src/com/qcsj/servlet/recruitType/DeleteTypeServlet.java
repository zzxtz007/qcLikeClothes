package com.qcsj.servlet.recruitType;

import com.qcsj.service.ServiceFactory;
import com.qcsj.service.ServiceUtil.SuperInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteTypeServlet", urlPatterns = {"/DeleteType"})
public class DeleteTypeServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("id");
		SuperInfo si = ServiceFactory.getRecruitTypeService().deleteType(id,request.getSession());
		System.out.println("deleteTypeServlet----" + si.getRet());
		switch (si.getRet()) {
			case 0:
				response.getWriter().print(true);
				break;
			case 1:
				response.getWriter().print("<script >alert('未知错误！');location.href='manage/manageMain.html';</script>");
				break;
			case 2:
				response.getWriter().print("<script >alert('未知错误！');location.href='manage/manageMain.html';</script>");
				break;
			case 3:
				response.getWriter().print(false);
				break;
			case 5:
				response.getWriter().print(5);
				break;
			case 4:
			default:
				response.getWriter().print("<script >alert('未知错误！');location.href='manage/manageMain.html';</script>");
				break;
		}
	}
}
