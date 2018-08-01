package com.example.lucene.luceneDemo.domain;

public enum LuceneDocumentConstants {
	DOCUMENT_ID("documentId"), ABSOLUTE_PATH("absolutePath"), EXTRACTED_TEXT("extractedText");
	private String value;

	private LuceneDocumentConstants(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}