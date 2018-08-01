package com.example.jena.jenaDemo.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.MessageFormat;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.DatasetFactory;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.query.text.EntityDefinition;
import org.apache.jena.query.text.TextDatasetFactory;
import org.apache.jena.query.text.TextQuery;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.tdb.TDBFactory;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.springframework.stereotype.Service;

import com.example.jena.jenaDemo.ApplicationProperties;

@Service
public class IndexService {

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(IndexService.class);
	private Dataset dataset;

	private final ApplicationProperties applicationProperties;
	
	public IndexService(ApplicationProperties applicationProperties) {
		this.applicationProperties = applicationProperties;
		TextQuery.init();
		dataset = createIndexedDataset();
		
		loadData(applicationProperties.getDataFileName());
	}
	
	public Dataset getDataset() {
		return dataset;
	}

	public void loadData(String file) {
		log.info("Load data ...");
		long startTime = System.currentTimeMillis();
		dataset.begin(ReadWrite.WRITE);
		try {
			Model m = dataset.getDefaultModel();
			RDFDataMgr.read(m, file);
			dataset.commit();
		} finally {
			dataset.end();
		}

		long finishTime = System.currentTimeMillis();
		long time = finishTime - startTime;
		log.debug(MessageFormat.format("Loading finished after {0}ms", time));
	}


	private Dataset createIndexedDataset() {
		String tdbPath = applicationProperties.getTdbPath();
		String lucenePath = applicationProperties.getLucenePath();
		String indexedProperty = applicationProperties.getIndexedProperty();
		return createIndexedDataset(tdbPath, lucenePath, indexedProperty);
	}
	
	public Dataset createIndexedDataset(String tdbPath, String lucenePath, String indexedProperty) {
		Dataset graphDS = null;

		if (tdbPath == null) {
			graphDS = DatasetFactory.createGeneral();
		} else {
			log.debug("Construct a persistant TDB based dataset to: " + tdbPath);
			graphDS = TDBFactory.createDataset(tdbPath);
		}

		// Define the index mapping
		EntityDefinition entDef = new EntityDefinition("uri", "text");
		Directory luceneDir = null;

		// check for in memory or file based (persistant) index
		if (lucenePath == null) {
			log.debug("Construct an in-memory lucene index");
			luceneDir = new RAMDirectory();
		} else {
			try {
				log.debug("Construct a persistant lucene index to: " + lucenePath);
				File file = new File(lucenePath);
				luceneDir = new SimpleFSDirectory(Paths.get(file.getAbsolutePath()));
			} catch (IOException e) {
				log.error("error", e);
			}
		}

		// Create new indexed dataset: Insert operations are automatically indexed with
		// lucene
		Dataset ds = TextDatasetFactory.createLucene(graphDS, luceneDir, entDef, null);

		return ds;
	}

}
