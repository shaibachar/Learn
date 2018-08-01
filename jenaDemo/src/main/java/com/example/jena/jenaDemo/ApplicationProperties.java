package com.example.jena.jenaDemo;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

	private String tdbPath;
	private String lucenePath;
	private String indexedProperty;
	private String dataFileName;
	private String dataPath;

	public String getDataFileName() {
		return dataFileName;
	}

	public void setDataFileName(String dataFileName) {
		this.dataFileName = dataFileName;
	}

	public String getTdbPath() {
		return tdbPath;
	}

	public String getLucenePath() {
		return lucenePath;
	}

	public String getIndexedProperty() {
		return indexedProperty;
	}

	public void setTdbPath(String tdbPath) {
		this.tdbPath = tdbPath;
	}

	public void setLucenePath(String lucenePath) {
		this.lucenePath = lucenePath;
	}

	public void setIndexedProperty(String indexedProperty) {
		this.indexedProperty = indexedProperty;
	}

	public String getDataPath() {
		return dataPath;
	}

	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}

}
