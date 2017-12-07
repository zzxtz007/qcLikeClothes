package com.qcsj.service.impl;

import com.qcsj.dao.DaoFactory;
import com.qcsj.entity.Recruit;
import com.qcsj.service.RecruitService;
import com.qcsj.service.ServiceUtil.ServiceUtil;
import com.qcsj.service.ServiceUtil.SuperInfo;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Ice_Dog
 */
public class RecruitServiceImpl implements RecruitService {

	@Override
	public SuperInfo insertRecruit(String company, String job, String typeId, String recruitCount, String salary, String workPlace, String positionStatement, String jobRequirements, HttpSession session) {
		SuperInfo si = new SuperInfo();

		// 参数不完整时返回
		if (company == null || job == null || typeId == null || recruitCount == null || salary ==
				null || workPlace == null || positionStatement == null || jobRequirements == null) {
			si.setRet(6);
			return si;
		}
		// 未登录时返回 2
		if (!ServiceUtil.isLoggedIn(session)) {
			si.setRet(2);
			return si;
		}
		Integer typeid = 0;
		Integer recruitcount = 0;
		Integer uid = 0;
		// 类型转换异常 5
		try {
			typeid = Integer.parseInt(typeId);
			recruitcount = Integer.parseInt(recruitCount);
			uid = Integer.parseInt(session.getAttribute("uid").toString());
			System.out.println("uid----" + uid);
		} catch (Exception e) {
			si.setRet(5);
			return si;
		}
		try {
			Integer ret = DaoFactory.getRecruitDao().insertRecruit(company, job, typeid, recruitcount, salary, workPlace, positionStatement, jobRequirements, uid);

			if (ret == null) {
				// 查询到的返回值为空 4
				si.setRet(4);
				return si;
			}
			// 添加成功 0
			if (ret == 1) {
				si.setRet(0);
				return si;
			}
			// 添加失败 3
			si.setRet(3);
			return si;
		} catch (SQLException e) {
			//用户登录时发生异常 1
			si.setRet(1);
			return si;
		}
	}

	@Override
	public SuperInfo deleteRecruit(String id, HttpSession session) {
		SuperInfo si = new SuperInfo();
		//传入的值 为空 6
		if (id == null) {
			si.setRet(6);
			return si;
		}
		// 未登录时返回 2
		if (!ServiceUtil.isLoggedIn(session)) {
			si.setRet(2);
			return si;
		}
		Integer uid;
		Integer recruitId;

		try {
			uid = Integer.parseInt(session.getAttribute("uid").toString());
			recruitId = Integer.parseInt(id);
//			System.out.println("uid----" + uid);
		} catch (Exception e) {
			// 类型转换异常 5
			si.setRet(5);
			return si;
		}
		try {
			Integer ret = DaoFactory.getRecruitDao().deleteRecruit(recruitId, uid);
			if (ret == null) {
				// 查询到的返回值为空 4
				si.setRet(4);
				return si;
			}
			// 删除成功 0
			if (ret == 1) {
				si.setRet(0);
				return si;
			}
			// 删除失败 3
			si.setRet(3);
			return si;
		} catch (SQLException e) {
			//用户登录时发生异常 1
			si.setRet(1);
			return si;
		}
	}

	@Override
	public SuperInfo getTotalRecruitPage(int i, HttpSession session) {
		SuperInfo si = new SuperInfo();

		// 未登录时返回 2
		if (!ServiceUtil.isLoggedIn(session)) {
			si.setRet(2);
			return si;
		}
		try {
			Integer pageNum = DaoFactory.getRecruitDao().getRecruitPageNum(5);
			si.setO(pageNum);
			// 查询所有type
			si.setRet(0);
			return si;
		} catch (SQLException e) {
			//用户登录时发生异常 1
			si.setRet(1);
			return si;
		}
	}

	@Override
	public SuperInfo queryRecruit(String pageSize, String pageNum, HttpSession session) {
		SuperInfo si = new SuperInfo();
		int size = 0;
		int num = 0;
		if (pageSize == null || pageNum == null) {
			// 查询到的返回值为空 5
			si.setRet(5);
			return si;
		}
		// 类型转换异常 3
		try {
			size = Integer.parseInt(pageSize);
			num = Integer.parseInt(pageNum);
		} catch (Exception e) {
			si.setRet(3);
			return si;
		}

		// 未登录时返回 2
//		if (!ServiceUtil.isLoggedIn(session)) {
//			si.setRet(2);
//			return si;
//		}
		try {
			List<Recruit> list = DaoFactory.getRecruitDao().getRecruitByPage(size, num);

			if (list == null) {
				// 查询到的返回值为空
				si.setRet(4);
				return si;
			}
			si.setLists(list);
			// 查询所有type
			si.setRet(0);
			return si;
		} catch (SQLException e) {
			//用户登录时发生异常 1
			si.setRet(1);
			return si;
		}
	}

	@Override
	public SuperInfo updateRecruitById(String company, String job, String typeId, String recruitCount, String salary, String workPlace, String positionStatement, String jobRequirements, String hits, String hotFlag, String verifyFlag, HttpSession session, String recruitId) {
		SuperInfo si = new SuperInfo();
		// 参数不完整时返回 6
		if (company == null || job == null || typeId == null || recruitCount == null || salary ==
				null || workPlace == null || positionStatement == null || jobRequirements == null || hits == null || hotFlag == null || verifyFlag == null) {
			si.setRet(6);
			return si;
		}
		// 未登录时返回 2
		if (!ServiceUtil.isLoggedIn(session)) {
			si.setRet(2);
			return si;
		}
		Integer recruitid = 0;
		Integer typeid = 0;
		Integer recruitcount = 0;
		Integer uid = 0;
		Integer hitss = 0;
		Integer hotflag = 0;
		Integer verifyflag = 0;
		// 类型转换异常 5
		try {
			typeid = Integer.parseInt(typeId);
			System.out.println("typeId"+typeid);
			uid = Integer.parseInt(session.getAttribute("uid").toString());
			recruitid = Integer.parseInt(recruitId);
			System.out.println("recruitid"+recruitid);
			recruitcount = Integer.parseInt(recruitCount);
			System.out.println("recruitcount"+recruitcount);
			hitss = Integer.parseInt(hits);
			System.out.println("hitss"+hitss);
			hotflag = Integer.parseInt(hotFlag);
			System.out.println("hotflag"+hotflag);
			verifyflag = Integer.parseInt(verifyFlag);
			System.out.println("verifyflag"+verifyflag);
		} catch (Exception e) {
			si.setRet(5);
			return si;
		}
		try {
			Integer ret = DaoFactory.getRecruitDao().updateRecruit(company, job, typeid, recruitcount, salary, workPlace, positionStatement, jobRequirements, hitss, hotflag, verifyflag, uid, recruitid);

			if (ret == null) {
				// 查询到的返回值为空
				si.setRet(4);
				return si;
			}
			// 查询所有type
			if (ret == 1) {
				si.setRet(0);
				return si;
			}
			si.setRet(3);
			return si;
		} catch (SQLException e) {
			//用户登录时发生异常 1
			si.setRet(1);
			return si;
		}
	}

	@Override
	public SuperInfo queryRecruitByTypeId(String pageSize, String pageNum, String typeId, HttpSession session) {
		SuperInfo si = new SuperInfo();
		Integer id = 0;
		int size = 0;
		int num = 0;
		if (pageSize == null || pageNum == null||typeId ==null) {
			// 查询到的返回值为空 5
			si.setRet(5);
			return si;
		}
		// 类型转换异常 3
		try {
			size = Integer.parseInt(pageSize);
			num = Integer.parseInt(pageNum);
			id = Integer.parseInt(typeId);
		} catch (Exception e) {
			si.setRet(3);
			return si;
		}

		// 未登录时返回 2
//		if (!ServiceUtil.isLoggedIn(session)) {
//			si.setRet(2);
//			return si;
//		}
		try {
			List<Recruit> list = DaoFactory.getRecruitDao().getRecruitByTypeId(size, num,id);

			if (list == null) {
				// 查询到的返回值为空
				si.setRet(4);
				return si;
			}
			si.setLists(list);
			// 查询所有type
			si.setRet(0);
			return si;
		} catch (SQLException e) {
			//用户登录时发生异常 1
			si.setRet(1);
			return si;
		}
	}

	@Override
	public SuperInfo queryAllRecruitByHotFlag(String pageSize, String pageNum, HttpSession session) {
		SuperInfo si = new SuperInfo();
		int size = 0;
		int num = 0;
		if (pageSize == null || pageNum == null) {
			// 查询到的返回值为空 5
			si.setRet(5);
			return si;
		}
		// 类型转换异常 3
		try {
			size = Integer.parseInt(pageSize);
			num = Integer.parseInt(pageNum);
		} catch (Exception e) {
			si.setRet(3);
			return si;
		}

		// 未登录时返回 2
//		if (!ServiceUtil.isLoggedIn(session)) {
//			si.setRet(2);
//			return si;
//		}
		try {
			List<Recruit> list = DaoFactory.getRecruitDao().queryAllRecruitByHotFlag(size, num);

			if (list == null) {
				// 查询到的返回值为空
				si.setRet(4);
				return si;
			}
			si.setLists(list);
			// 查询所有type
			si.setRet(0);
			return si;
		} catch (SQLException e) {
			//用户登录时发生异常 1
			si.setRet(1);
			return si;
		}
	}

	@Override
	public SuperInfo queryRecruitById(String typeId, HttpSession session) {
		SuperInfo si = new SuperInfo();
		int id = 0;
		// 类型转换异常 3
		try {
			id = Integer.parseInt(typeId);
		} catch (Exception e) {
			si.setRet(3);
			return si;
		}

		// 未登录时返回 2
//		if (!ServiceUtil.isLoggedIn(session)) {
//			si.setRet(2);
//			return si;
//		}
		try {
			List<Recruit> list = DaoFactory.getRecruitDao().queryRecruitById(id);

			if (list == null) {
				// 查询到的返回值为空
				si.setRet(4);
				return si;
			}
			si.setLists(list);
			// 查询所有type
			si.setRet(0);
			return si;
		} catch (SQLException e) {
			//用户登录时发生异常 1
			si.setRet(1);
			return si;
		}
	}
}
