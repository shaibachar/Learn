package com.example.ImageJ.ImageJ.services;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

@Service
public class ApplicationService {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationService.class);

    private final ResourceLoader resourceLoader;

    public ApplicationService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public String getHello(String name) {
        return String.format("hello %s", name);
    }

    /**
     *
     * @param inputStream
     * @return
     * @throws TesseractException
     * @throws IOException
     */
    public String doOcrRightUpperCorner(InputStream inputStream) throws TesseractException, IOException {
        Tesseract tesseract = new Tesseract();
        String path = resourceLoader.getResource("classpath:tessdata/heb.traineddata").getFile().getPath().replace("heb.traineddata", "");
        tesseract.setDatapath(path);//classpath:
        tesseract.setLanguage("heb");
        BufferedImage imBuff = ImageIO.read(inputStream);
        BufferedImage bufferedImage = processRightUpperCornerImage(imBuff, 3);
        String res = tesseract.doOCR(bufferedImage);
        return res;
    }

    /**
     *
     * @param inputImage
     * @param parts
     * @return
     * @throws IOException
     */
    public BufferedImage processRightUpperCornerImage(BufferedImage inputImage, int parts) throws IOException {
        int width = inputImage.getWidth() / parts;
        int height = inputImage.getHeight() / parts;
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        int i = width * (parts - 1);
        for (int x = i; x < (width * parts); x++) {
            for (int y = 0; y < height; y++) {
                int rgb = inputImage.getRGB(x, y);
                int blue = 0x0000ff & rgb;
                int green = 0x0000ff & (rgb >> 8);
                int red = 0x0000ff & (rgb >> 16);
                int lum = (int) (red * 0.299 + green * 0.587 + blue * 0.114);
                outputImage.setRGB(x - i, y, lum | (lum << 8) | (lum << 16));
            }
        }
//
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        ImageIO.write(outputImage, "jpg", out);

        return outputImage;
    }
}
