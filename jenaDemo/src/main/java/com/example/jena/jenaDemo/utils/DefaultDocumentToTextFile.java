package com.example.jena.jenaDemo.utils;

import static com.example.jena.jenaDemo.domain.LuceneDocumentConstants.ABSOLUTE_PATH;
import static com.example.jena.jenaDemo.domain.LuceneDocumentConstants.DOCUMENT_ID;
import static com.example.jena.jenaDemo.domain.LuceneDocumentConstants.EXTRACTED_TEXT;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;

public class DefaultDocumentToTextFile {

	private Document document;
	private String id;
	private String extractedText;
	private String absolutePath;

	public DefaultDocumentToTextFile(String id, String extractedText, String absolutePath) {
		this.id = id;
		this.extractedText = extractedText;
		this.absolutePath = absolutePath;

		updateDocument(id, extractedText, absolutePath);
	}

	private void updateDocument(String id, String extractedText, String absolutePath) {
		Document document = new Document();
		document.add(new TextField(DOCUMENT_ID.getValue(), id, Field.Store.YES));
		document.add(new TextField(EXTRACTED_TEXT.getValue(), extractedText, Field.Store.YES));
		document.add(new TextField(ABSOLUTE_PATH.getValue(), absolutePath, Field.Store.YES));
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getExtractedText() {
		return extractedText;
	}

	public void setExtractedText(String extractedText) {
		this.extractedText = extractedText;
	}

	public String getAbsolutePath() {
		return absolutePath;
	}

	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}

}