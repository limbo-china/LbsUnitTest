package com.w.limbo.Group;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;

public class Group {

	private String groupId;
	private String groupName;
	private String source;
	private boolean isUse;
	private String startTime;
	private String endTime;
	private String createBy;
	private String createTime;
	private String updateBy;
	private String updateTime;

	protected static int incrGroupID = 0;

	protected Group() {
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public boolean isUse() {
		return isUse;
	}

	public void setUse(boolean isUse) {
		this.isUse = isUse;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "[groupId=" + groupId + ", groupName=" + groupName + ", source="
				+ source + ", isUse=" + isUse + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", createBy=" + createBy
				+ ", createTime=" + createTime + ", updateBy=" + updateBy
				+ ", updateTime=" + updateTime + "]";
	}

	public String toJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

	public static Group generateByGroupType(String groupType) {
		long aDayMillSeconds = 1000 * 86400;
		Group group = new Group();
		group.setGroupId(groupType + "_" + String.valueOf(incrGroupID++));
		group.setGroupName("testGroupName");
		group.setSource("testSource");
		group.setUse(true);
		group.setStartTime(stampToDate(System.currentTimeMillis()));
		group.setEndTime(stampToDate(System.currentTimeMillis()
				+ aDayMillSeconds));
		group.setCreateBy("testCreateBy");
		group.setCreateTime(stampToDate(System.currentTimeMillis()));
		group.setUpdateBy("testUpdateBy");
		group.setUpdateTime(stampToDate(System.currentTimeMillis()));

		return group;
	}

	private static String stampToDate(long stamp) {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		Date date = new Date(stamp);
		return simpleDateFormat.format(date);
	}

}
