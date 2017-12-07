package com.qcsj.service.impl;

import com.qcsj.dao.DaoFactory;
import com.qcsj.entity.RecruitType;
import com.qcsj.service.RecruitTypeService;
import com.qcsj.service.ServiceUtil.ServiceUtil;
import com.qcsj.service.ServiceUtil.SuperInfo;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ice_Dog
 */
public class RecruitTypeServiceImpl implements RecruitTypeService {
	@Override
	public SuperInfo showManageType(String pageSize, String pageNum, HttpSession session) {
		SuperInfo si = new SuperInfo();
		int size = 0;
		int num = 0;
		// 类型转换异常 3
		try {
			size = Integer.parseInt(pageSize);
			num = Integer.parseInt(pageNum);
		} catch (Exception e) {
			si.setRet(3);
			return si;
		}

		// 未登录时返回 2
		if (!ServiceUtil.isLoggedIn(session)) {
			si.setRet(2);
			return si;
		}
		try {
			List<RecruitType> list = DaoFactory.getRecruitTypeDao().getRecruitTypeByPage(size, num);

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
	public SuperInfo getTotalRecruitTypePage(Integer pageSize, HttpSession session) {
		SuperInfo si = new SuperInfo();

		// 未登录时返回 2
		if (!ServiceUtil.isLoggedIn(session)) {
			si.setRet(2);
			return si;
		}
		try {
			Integer pageNum = DaoFactory.getRecruitTypeDao().getRecruitTypePageNum(5);
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
	public SuperInfo getAllFathersType(HttpSession session) {
		SuperInfo si = new SuperInfo();

//		// 未登录时返回 2
//		if (!ServiceUtil.isLoggedIn(session)) {
//			si.setRet(2);
//			return si;
//		}
		try {
			List<RecruitType> list = DaoFactory.getRecruitTypeDao().getRecruitTypeFathers();

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
	public SuperInfo updateTypeById(String id, String name, String description, String supId, HttpSession session) {
		SuperInfo si = new SuperInfo();

		// 未登录时返回 2
		if (!ServiceUtil.isLoggedIn(session)) {
			si.setRet(2);
			return si;
		}
		Integer typeid = 0;
		Integer uid = 0;
		Integer sid = 0;
		// 类型转换异常 5
		try {
			typeid = Integer.parseInt(id);
			uid = Integer.parseInt(session.getAttribute("uid").toString());
//			System.out.println("uid----"+uid);
			sid = Integer.parseInt(supId);
		} catch (Exception e) {
			si.setRet(5);
			return si;
		}
		try {
			Integer ret = DaoFactory.getRecruitTypeDao().updateRecruitType(name, description, sid, uid, typeid);

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
	public SuperInfo insertType(String id, String name, String description, String supId, HttpSession session) {
		SuperInfo si = new SuperInfo();

		// 未登录时返回 2
		if (!ServiceUtil.isLoggedIn(session)) {
			si.setRet(2);
			return si;
		}
		Integer typeid = 0;
		Integer uid = 0;
		Integer sid = 0;
		// 类型转换异常 5
		try {
			uid = Integer.parseInt(session.getAttribute("uid").toString());
			System.out.println("uid----" + uid);
			sid = Integer.parseInt(supId);
		} catch (Exception e) {
			si.setRet(5);
			return si;
		}
		try {
			Integer ret = DaoFactory.getRecruitTypeDao().insertRecruitType(name, description, sid, uid);

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
	public SuperInfo deleteType(String id, HttpSession session) {
		SuperInfo si = new SuperInfo();

		// 未登录时返回 2
		if (!ServiceUtil.isLoggedIn(session)) {
			si.setRet(2);
			return si;
		}
		Integer uid = 0;
		Integer typeId = 0;
		// 类型转换异常 5
		try {
			uid = Integer.parseInt(session.getAttribute("uid").toString());
			typeId = Integer.parseInt(id);
			System.out.println("uid----" + uid);
		} catch (Exception e) {
			si.setRet(5);
			return si;
		}
		try {
			Integer ret = DaoFactory.getRecruitTypeDao().deleteRecruitType(typeId, uid);

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
	public SuperInfo getAllSonByFatherId(String id, HttpSession session) {
		SuperInfo si = new SuperInfo();
		Integer supId = 0;
//		// 未登录时返回 2
//		if (!ServiceUtil.isLoggedIn(session)) {
//			si.setRet(2);
//			return si;
//		}
		
		try {
			supId = Integer.parseInt(id);
		} catch (Exception e) {
			// 类型转换异常 5
			si.setRet(5);
			return si;
		}
		try {
			List<RecruitType> list = DaoFactory.getRecruitTypeDao().getAllSonByFatherId(supId);

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
			//用户查询时发生异常 1
			si.setRet(1);
			e.printStackTrace();
			return si;
		}
	}
}
