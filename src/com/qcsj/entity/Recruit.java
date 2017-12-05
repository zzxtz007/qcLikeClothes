package com.qcsj.entity;

public class Recruit {
	/**
	 * 招聘信息ID
	 */
	private int id;
	/**
	 * 公司
	 */
	private String company;

	/**
	 * 职位名称
	 */
	private String job;

	/**
	 * 类型ID
	 */
	private int typeId;

	/**
	 * 招聘人数
	 */
	private int recruitCount;

	/**
	 * 工资
	 */
	private int salary;

	/**
	 * 工作地点
	 */
	private String workPlace;

	/**
	 * 岗位职责
	 */
	private String positionStatement;

	/**
	 * 任职要求
	 */
	private String jobRequirements;

	/**
	 * 点击数
	 */
	private int hits;

	/**
	 * 热度标记
	 */
	private int hotFlag;

	/**
	 * 验证标记
	 */
	private int verifyFlag;


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

	public Recruit() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public int getRecruitCount() {
		return recruitCount;
	}

	public void setRecruitCount(int recruitCount) {
		this.recruitCount = recruitCount;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getWorkPlace() {
		return workPlace;
	}

	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}

	public String getPositionStatement() {
		return positionStatement;
	}

	public void setPositionStatement(String positionStatement) {
		this.positionStatement = positionStatement;
	}

	public String getJobRequirements() {
		return jobRequirements;
	}

	public void setJobRequirements(String jobRequirements) {
		this.jobRequirements = jobRequirements;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public int getHotFlag() {
		return hotFlag;
	}

	public void setHotFlag(int hotFlag) {
		this.hotFlag = hotFlag;
	}

	public int getVerifyFlag() {
		return verifyFlag;
	}

	public void setVerifyFlag(int verifyFlag) {
		this.verifyFlag = verifyFlag;
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
