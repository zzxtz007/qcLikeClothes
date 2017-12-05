package com.qcsj.entity;

public class RecruitType {
	/**
	 * 职位分类ID
	 */
	private int id;
	/**
	 * 职位名称
	 */
	private String name;
	/**
	 * 职位描述
	 */
	private String description;
	/**
	 * 上级职位ID
	 */
	private int supId;
	/**
	 * 上级职位名字
	 */
	private String supName;
	/**
	 * 创建人
	 */
	private int createUserId;
	/**
	 * 最后修改人
	 */
	private int updateUserId;
	/**
	 * 数据创建时间
	 */
	private String creataDate;
	/**
	 * 数据更新时间
	 */
	private String updateDate;
	/**
	 * 数据删除标记
	 */
	private int deleteFlag;

	public RecruitType() {
	}

	public int getId() {
		return id;
	}

	public String getSupName() {
		return supName;
	}

	public void setSupName(String supName) {
		this.supName = supName;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSupId() {
		return supId;
	}

	public void setSupId(int supId) {
		this.supId = supId;
	}

	public int getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(int createUserId) {
		this.createUserId = createUserId;
	}

	public int getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(int updateUserId) {
		this.updateUserId = updateUserId;
	}

	public String getCreataDate() {
		return creataDate;
	}

	public void setCreataDate(String creataDate) {
		this.creataDate = creataDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public int getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
}
