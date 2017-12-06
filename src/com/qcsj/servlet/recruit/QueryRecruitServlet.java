package com.qcsj.servlet.recruit;

import com.google.gson.Gson;
import com.qcsj.service.ServiceFactory;
import com.qcsj.service.ServiceUtil.SuperInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Ice_Dog
 */
@WebServlet(name = "QueryRecruitServlet", urlPatterns = {"/QueryRecruit"})
public class QueryRecruitServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=UTF-8");
		String pageSize = request.getParameter("pagesize");
		String pageNum = request.getParameter("pagenum");
		System.out.println(pageSize + "" + pageNum);
		SuperInfo si = ServiceFactory.getRecruitService().queryRecruit(pageSize, pageNum, request.getSession());
		System.out.println("QueryRecruitServlet----" + si.getRet());
		switch (si.getRet()) {
			case 0:
				Gson g = new Gson();
				String jsonStr = g.toJson(si.getLists());
				System.out.println(jsonStr);
				response.getWriter().print(jsonStr);
				break;
			case 1:
				response.getWriter().print(1);
				break;
			case 2:
				response.getWriter().print(2);
				break;
			case 3:
			case 4:
			default:
				response.getWriter().print(false);
				break;
		}
	}
}
