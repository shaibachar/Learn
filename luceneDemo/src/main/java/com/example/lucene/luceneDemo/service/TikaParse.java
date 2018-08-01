package com.example.lucene.luceneDemo.service;

import java.io.File;
import java.io.FileNotFoundException;

import org.apache.tika.exception.TikaException;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.microsoft.OfficeParserConfig;
import org.apache.tika.parser.ocr.TesseractOCRConfig;
import org.apache.tika.parser.pdf.PDFParserConfig;
import org.apache.tika.sax.BodyContentHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

@Component
public class TikaParse {
	private static final Logger log = LoggerFactory.getLogger(TikaParse.class);
	
	public TikaParse() {
		
	}
	
	public String extractTheContentOfTheFile(File file) throws Exception {
		ContentHandler handler = new BodyContentHandler(Integer.MAX_VALUE);
		Metadata metadata = new Metadata();

		try (TikaInputStream fileStream = TikaInputStream.get(file, metadata)) {
			Parser parser = new AutoDetectParser();

			TesseractOCRConfig tesseractOcrConfig = new TesseractOCRConfig();

			PDFParserConfig pdfConfig = new PDFParserConfig();
			pdfConfig.setExtractInlineImages(false);

			OfficeParserConfig officeParserConfig = new OfficeParserConfig();
			officeParserConfig.setExtractMacros(false);
			officeParserConfig.setUseSAXDocxExtractor(true);
			officeParserConfig.setUseSAXPptxExtractor(true);

			ParseContext parseContext = new ParseContext();
			parseContext.set(TesseractOCRConfig.class, tesseractOcrConfig);
			parseContext.set(PDFParserConfig.class, pdfConfig);
			parseContext.set(OfficeParserConfig.class, officeParserConfig);
			parseContext.set(Parser.class, parser);
			parser.parse(fileStream, handler, metadata, parseContext);

		} catch (FileNotFoundException | TikaException | SAXException e) {
			log.error(e.getStackTrace().toString());
		}

		return handler.toString();
	}
}
