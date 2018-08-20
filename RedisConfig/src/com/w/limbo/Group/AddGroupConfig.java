package com.w.limbo.Group;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

public class AddGroupConfig {

	private int totalGroup;
	private int personPerGroup;
	private List<String> jcGroupList;
	private List<String> lbsGroupList;
	private List<String> gjGroupList;

	public AddGroupConfig(int totalGroup, int personPerGroup) {
		super();
		this.totalGroup = totalGroup;
		this.personPerGroup = personPerGroup;
	}

	public void addJCToRedis(String ip, int port) {
		Jedis jedis = new Jedis(ip, port);
		Pipeline pipeline = jedis.pipelined();
		jcGroupList = new ArrayList<String>();

		for (int i = 0; i < totalGroup; i++) {
			Group group = Group.generateByGroupType("jcgroup");
			jcGroupList.add(group.getGroupId());
			pipeline.hset("JCGroup", group.getGroupId(), group.toJson());
		}
		pipeline.sync();

		for (String groupId : jcGroupList) {
			for (int i = 0; i < personPerGroup; i++) {
				String personJson = JCPerson.generateOnePersonJson(groupId);
				pipeline.rpush(groupId, personJson);
			}
		}
		pipeline.sync();
		jedis.close();
	}

	public void addGJToRedis(String ip, int port) {
		Jedis jedis = new Jedis(ip, port);
		Pipeline pipeline = jedis.pipelined();

		int ruleId = 0;
		for (int i = 0; i < totalGroup; i++) {
			String ruleIdStr = String.valueOf(ruleId++);
			for (int j = 0; j < personPerGroup; j++) {
				String ruleJson = GJRule.generateOneRuleJson();
				pipeline.rpush("gjrule_" + ruleIdStr, ruleJson);
			}
		}
		pipeline.sync();
		jedis.close();
	}

	public static void main(String[] args) {
		AddGroupConfig jcConfig = new AddGroupConfig(20, 200);
		jcConfig.addJCToRedis("172.16.18.34", 6398);
		// jcConfig.addGJToRedis("172.16.18.34", 6399);
	}
}
