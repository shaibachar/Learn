package com.example.lucene.luceneDemo.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.lucene.luceneDemo.domain.LuceneDocumentConstants;

@Service
public class SearchService {
	private static final Logger log = LoggerFactory.getLogger(SearchService.class);

	private final Analyzer analyzer;
	private final Directory directory;

	public SearchService(Analyzer analyzer, Directory directory) {
		this.analyzer = analyzer;
		this.directory = directory;
	}

	public List<String> Query(String querystr) {

		List<String> res = new ArrayList<>();

		try {
			Query q = new QueryParser(LuceneDocumentConstants.EXTRACTED_TEXT.getValue(), analyzer).parse(querystr);

			int hitsPerPage = 10;
			IndexReader reader = DirectoryReader.open(directory);
			IndexSearcher searcher = new IndexSearcher(reader);
			TopDocs docs = searcher.search(q, hitsPerPage);
			ScoreDoc[] hits = docs.scoreDocs;

			for (int i = 0; i < hits.length; ++i) {
				int docId = hits[i].doc;
				Document d = searcher.doc(docId);
				res.add(d.get(LuceneDocumentConstants.ABSOLUTE_PATH.getValue()));
			}
			reader.close();
		} catch (Exception e) {
			log.error("error", e);
		}
		return res;
	}

}
