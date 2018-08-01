package com.example.lucene.luceneDemo.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.lucene.index.IndexWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.example.lucene.luceneDemo.config.LuceneDemoProperties;
import com.example.lucene.luceneDemo.domain.DefaultDocumentToTextFile;

@Service
public class IndexWriterService {
	private static final Logger log = LoggerFactory.getLogger(IndexWriterService.class);

	private final IndexWriter indexWriter;
	private final LuceneDemoProperties luceneDemoProperties;
	private final TikaParse tikaParse;

	public IndexWriterService(LuceneDemoProperties luceneDemoProperties, TikaParse tikaParse, IndexWriter indexWriter)
			throws IOException {
		this.luceneDemoProperties = luceneDemoProperties;
		this.tikaParse = tikaParse;
		this.indexWriter = indexWriter;

		loadDocuments(null);

	}

	public void loadDocuments(String folderPath) throws IOException {
		String folder = (folderPath == null || folderPath.isEmpty()) ? luceneDemoProperties.getDataPath() : folderPath;
		File dir = new ClassPathResource(folder).getFile();
		List<File> files = (List<File>) FileUtils.listFiles(dir, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
		try {
			for (File file : files) {
				String extractTheContentOfTheFile = tikaParse.extractTheContentOfTheFile(file);
				DefaultDocumentToTextFile defaultDocumentToTextFile = new DefaultDocumentToTextFile(file.getName(),
						extractTheContentOfTheFile, file.getAbsolutePath());
				indexWriter.addDocument(defaultDocumentToTextFile.getDocument());
			}
			indexWriter.close();
		} catch (Exception e) {
			log.error("Error", e);
		}
	}

}
