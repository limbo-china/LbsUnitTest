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

	public AddGroupConfig(int totalGroup, int personPerGroup) {
		super();
		this.totalGroup = totalGroup;
		this.personPerGroup = personPerGroup;
	}

	public void addToRedis(String ip, int port) {
		Jedis jedis = new Jedis(ip, port);
		Pipeline pipeline = jedis.pipelined();
		jcGroupList = new ArrayList<String>();
		lbsGroupList = new ArrayList<String>();

		for (int i = 0; i < totalGroup; i++) {
			Group group = Group.generateByGroupType("jcgroup");
			jcGroupList.add(group.getGroupId());
			pipeline.hset("JCGroup", group.getGroupId(), group.toJson());
		}
		for (int i = 0; i < totalGroup; i++) {
			Group group = Group.generateByGroupType("lbsgroup");
			lbsGroupList.add(group.getGroupId());
			pipeline.hset("LBSGroup", group.getGroupId(), group.toJson());
		}
		pipeline.sync();

		for (String groupId : jcGroupList) {
			for (int i = 0; i < personPerGroup; i++) {
				String personJson = JCPerson.generateOnePersonJson();
				pipeline.rpush(groupId, personJson);
			}
		}
		for (String groupId : lbsGroupList) {
			for (int i = 0; i < personPerGroup; i++) {
				String personJson = LBSPerson.generateOnePersonJson();
				pipeline.rpush(groupId, personJson);
			}
		}
		pipeline.sync();
		jedis.close();
	}

	public static void main(String[] args) {
		AddGroupConfig jcConfig = new AddGroupConfig(20, 20);
		jcConfig.addToRedis("172.16.18.34", 6398);
	}
}
