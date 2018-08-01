package com.example.jena.jenaDemo.services;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.KeepOnlyLastCommitDeletionPolicy;
import org.apache.lucene.store.Directory;

import com.example.jena.jenaDemo.ApplicationProperties;
import com.example.jena.jenaDemo.utils.DefaultDocumentToTextFile;

public class IndexWriterForTextFileService {
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(IndexWriterForTextFileService.class);

	private IndexWriter indexWriter;
	private final TikaParse tikaParse;
	private final ApplicationProperties applicationProperties;

	public IndexWriterForTextFileService(Directory directory, TikaParse tikaParse,
			ApplicationProperties applicationProperties) {
		this.tikaParse = tikaParse;
		this.applicationProperties = applicationProperties;
		try {
			indexWriter = new IndexWriter(directory, getIndexWriterConfig());
		} catch (IOException e) {
			log.error("error", e);
		}
	}

	public void loadDocuments(String folderPath) throws Exception {
		String folder = (folderPath == null || folderPath.isEmpty()) ? applicationProperties.getDataPath() : folderPath;

		Set<File> files = new HashSet<>();
		listFilesForFolder(files, new File(folder));
		for (File file : files) {
			DefaultDocumentToTextFile defaultDocumentToTextFile = new DefaultDocumentToTextFile(file.getName(),
					tikaParse.extractTheContentOfTheFile(file.getPath()), file.getAbsolutePath());
			indexWriter.addDocument(defaultDocumentToTextFile.getDocument());
		}
	}

	public void listFilesForFolder(Set<File> files, final File folder) {
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(files, fileEntry);
			} else {
				files.add(fileEntry);
			}
		}
	}

	private IndexWriterConfig getIndexWriterConfig() {
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig();
		indexWriterConfig.setCommitOnClose(true);
		indexWriterConfig.setIndexDeletionPolicy(new KeepOnlyLastCommitDeletionPolicy());
		return indexWriterConfig;
	}
}