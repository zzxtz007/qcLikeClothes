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
@WebServlet(name = "QueryRecruitByTypeIdServlet", urlPatterns = {"/QueryRecruitByTypeId"})
public class QueryRecruitByTypeIdServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=UTF-8");
		String pageSize = request.getParameter("pagesize");
		String pageNum = request.getParameter("pagenum");
		String typeId = request.getParameter("typeId");
		System.out.println("size--" + pageSize + "--num--" + pageNum + "--id--" + typeId);
		SuperInfo si = ServiceFactory.getRecruitService().queryRecruitByTypeId(pageSize, pageNum, typeId, request.getSession());
		System.out.println("QueryRecruitByTypeIdServlet----" + si.getRet());
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
