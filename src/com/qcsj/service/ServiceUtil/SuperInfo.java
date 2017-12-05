package com.qcsj.service.ServiceUtil;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/**
 * @author ICE_DOG
 * 超级信息
 * 存放sevrice需要传输的数据
 * ret 为sevrice的工作状态
 * list set map 为可能传输的数据
 * 获取的流程为 先判断ret 再判断需要获取的值的size 最后获取值
 */
public class SuperInfo {
	/**
	 * ret
	 * 0 正常
	 * 1 null
	 */
	private int ret = 0;
	private ArrayList lists;
	private Set set;
	private Map map;
	private Object o;

	public int getOSize() {
		if (o == null) {
			return -1;
		}
		return 1;
	}

	public int getListSize() {
		if (lists == null) {
			return -1;
		}
		return lists.size();
	}

	public int getSetSize() {
		if (set == null) {
			return -1;
		}
		return set.size();
	}

	public int getMapSize() {
		if (map == null) {
			return -1;
		}
		return map.size();
	}

	public int getRet() {
		return ret;
	}

	public void setRet(int ret) {
		this.ret = ret;
	}

	public Object getO() {
		return o;
	}

	public void setO(Object o) {
		this.o = o;
	}


	public ArrayList getLists() {
		return lists;
	}

	public void setLists(ArrayList lists) {
		this.lists = lists;
	}

	public Set getSet() {
		return set;
	}

	public void setSet(Set set) {
		this.set = set;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}
}
