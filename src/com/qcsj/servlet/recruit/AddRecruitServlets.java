package com.qcsj.servlet.recruit;

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
@WebServlet(name = "AddRecruitServlets", urlPatterns = {"/AddRecruit"})
public class AddRecruitServlets extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		String company = request.getParameter("company");
		String job = request.getParameter("job");
		String typeId = request.getParameter("typeId");
		String recruitCount = request.getParameter("recruitCount");
		String salary = request.getParameter("salary");
		String workPlace = request.getParameter("workPlace");
		String positionStatement = request.getParameter("positionStatement");
		String jobRequirements = request.getParameter("jobRequirements");
		SuperInfo si = ServiceFactory.getRecruitService().insertRecruit(company, job,typeId, recruitCount, salary, workPlace, positionStatement, jobRequirements, request.getSession());
		System.out.println("AddRecruitServlets----" + si.getRet());
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
			case 6:
				response.getWriter().print(6);
				break;
			case 4:
			default:
				response.getWriter().print("<script >alert('未知错误！');location.href='manage/manageMain.html';</script>");
				break;
		}
	}
}
