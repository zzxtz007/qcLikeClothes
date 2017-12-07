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

/**
 * @author Ice_Dog
 */
@WebServlet(name = "GetAllSonByFatherIdServlet", urlPatterns = {"/GetAllSonByFatherId"})
public class GetAllSonByFatherIdServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=UTF-8");
		String id = request.getParameter("id");
		System.out.println("GetAllSonByFatherIdServlet----id____"+id);
		SuperInfo si = ServiceFactory.getRecruitTypeService().getAllSonByFatherId(id, request.getSession());
		System.out.println("GetAllSonByFatherIdServlet----" + si.getRet());
		switch (si.getRet()) {
			case 0:
				Gson g = new Gson();
				String jsonStr = g.toJson(si.getLists());
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

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
