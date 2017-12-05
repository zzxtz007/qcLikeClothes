package com.qcsj.servlet;

import com.qcsj.entity.User;
import com.qcsj.service.ServiceFactory;
import com.qcsj.service.ServiceUtil.SuperInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author IceDog
 */
@WebServlet(name = "ManageLoginServlet", urlPatterns = {"/ManageLogin"})
public class ManageLoginServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		String phone = request.getParameter("phone");
		String pwd = request.getParameter("phone");
		SuperInfo si = ServiceFactory.getUserService().loginManage(phone, pwd, request.getSession());
		System.out.println("ManageLoginServlet----"+si.getRet());
		switch (si.getRet()) {
			case 0:
				response.getWriter().print("<script >alert('登录成功！');location.href='manage/manageMain.html';</script>");
				break;
			case 1:
				response.getWriter().print("<script >alert('登录失败！1');location.href='manage/manageLogin.html';</script>");
				break;
			case 2:
				response.getWriter().print("<script >alert('登录失败！2');location.href='manage/manageLogin.html';</script>");
				break;
			case 3:
				response.getWriter().print("<script >alert('登录失败！3');location.href='manage/manageLogin.html';</script>");
				break;
			case 4:
				response.getWriter().print("<script >alert('登录失败！4');location.href='manage/manageLogin.html';</script>");
				break;
			case 5:
				response.getWriter().print("<script >alert('登录失败！5');location.href='manage/manageLogin.html';</script>");
				break;
			default:
				response.getWriter().print("<script >alert('未知错误！');location.href='manage/manageLogin.html';</script>");
		}
	}

}
