package com.hello.Hellotesseract;

import net.sourceforge.tess4j.Tesseract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.File;


@Component
@ConditionalOnProperty(prefix = "job.autorun", name = "enabled", havingValue = "true", matchIfMissing = true)
public class StartupRunner implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(StartupRunner.class);


    public StartupRunner() {
    }

    @Override
    public void run(String... args) throws Exception {

        Tesseract tesseract = getTesseract();
        File file = new File("/home/shaibachar/Downloads/report-0.jpg");
        String result = tesseract.doOCR(file);
        System.out.println(result);
    }

    private static Tesseract getTesseract() {
        Tesseract instance = new Tesseract();
        instance.setDatapath("/home/shaibachar/.m2/repository/net/sourceforge/tess4j/tess4j/4.4.1/data/tessdata");
        instance.setLanguage("heb");
        instance.setHocr(true);
        return instance;
    }
}
