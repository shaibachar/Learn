package com.example.jena.jenaDemo.services;

import java.text.MessageFormat;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.sparql.util.QueryExecUtils;
import org.springframework.stereotype.Service;

@Service
public class QueryService {
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(IndexService.class);

	private final IndexService indexService;

	public QueryService(IndexService indexService) {
		this.indexService = indexService;
	}

	// https://jena.apache.org/documentation/query/text-query.html
	public void queryData() {
		Dataset dataset = indexService.getDataset();

		String query = "SELECT * WHERE { ?s text:query (ta:hasLongText 'g?eat') .  ?s ta:hasLongText ?text . }";
		log.info(MessageFormat.format("Query data with:{0}", query) );

		long startTime = System.currentTimeMillis();

		dataset.begin(ReadWrite.READ);
		try {
			Query q = QueryFactory.create(query);
			QueryExecution qexec = QueryExecutionFactory.create(q, dataset);
			QueryExecUtils.executeQuery(q, qexec);
		} finally {
			dataset.end();
		}

		long finishTime = System.currentTimeMillis();

		long time = finishTime - startTime;
		log.debug("Query finished  after " + time + "ms");

	}
}
