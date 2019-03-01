package com.cache.HelloSpringCache.model;

import java.util.List;
import java.util.Map;

public class CacheClients {

	private Map<String,List<Client>> clientList;

	public Map<String, List<Client>> getClientList() {
		return clientList;
	}

	public void setClientList(Map<String, List<Client>> clientList) {
		this.clientList = clientList;
	}

	

}
