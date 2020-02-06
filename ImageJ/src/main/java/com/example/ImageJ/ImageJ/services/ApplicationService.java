package com.example.ImageJ.ImageJ.services;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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

    public int[] parse() throws IOException {
        BufferedImage img = ImageIO.read(new File("c:\\temp\\1.jpeg") );
        int width = img.getWidth();
        int height = img.getHeight();
        int[] res = new int[width];
        BufferedImage gray = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);

        for (int y = 0; y < gray.getHeight(); y++)
        {
            for (int x = 0; x < gray.getWidth(); x++)
            {
                res[x] = gray.getRGB(x, y)>0?1:0;
            }
        }
        return res;
    }


    public BufferedImage smeared(InputStream inputStream) throws IOException {
        BufferedImage imBuff = ImageIO.read(inputStream);
        BufferedImage dest = ImageIO.read(inputStream);
        float[] matrix = {
                0.111f, 0.111f, 0.111f,
                0.111f, 0.111f, 0.111f,
                0.111f, 0.111f, 0.111f,
        };

        BufferedImageOp op = new ConvolveOp(new Kernel(3, 3, matrix));
        return op.filter(imBuff, dest);
    }

    /**
     * @param imBuff
     * @return
     * @throws TesseractException
     * @throws IOException
     */
    public String doOcr(BufferedImage imBuff) throws TesseractException, IOException {
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("C:\\workspace\\Tests\\Learn1\\ImageJ\\src\\main\\resources");
        tesseract.setLanguage("heb");
        BufferedImage bufferedImage = processImage(imBuff);
        String res = tesseract.doOCR(bufferedImage);
        return res;
    }

    /**
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
     * @param inputStream
     * @return
     * @throws TesseractException
     * @throws IOException
     */
    public String doOcrLeftUpperCorner(InputStream inputStream) throws TesseractException, IOException {
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("C:\\workspace\\Tests\\Learn1\\ImageJ\\src\\main\\resources");
        tesseract.setLanguage("eng");
        BufferedImage imBuff = ImageIO.read(inputStream);
        BufferedImage bufferedImage = processLeftUpperCornerImage(imBuff, 2);
        GeneralUtils.saveBuggeredImageToFile("res.jpeg","jpg",bufferedImage);
        String res = tesseract.doOCR(bufferedImage);
        return res;
    }

    /**
     * @param inputImage
     * @return
     * @throws IOException
     */
    public BufferedImage processImage(BufferedImage inputImage) throws IOException {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int rgb = inputImage.getRGB(x, y);
                int blue = 0x0000ff & rgb;
                int green = 0x0000ff & (rgb >> 8);
                int red = 0x0000ff & (rgb >> 16);
                int lum = (int) (red * 0.299 + green * 0.587 + blue * 0.114);
                outputImage.setRGB(x, y, lum | (lum << 8) | (lum << 16));
            }
        }
        return outputImage;
    }


    /**
     * @param inputImage
     * @param parts
     * @return
     * @throws IOException
     */
    public BufferedImage processLeftUpperCornerImage(BufferedImage inputImage, int parts) throws IOException {
        int width = inputImage.getWidth() / parts/2;
        int height = inputImage.getHeight() / parts;
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int rgb = inputImage.getRGB(x, y);
                int blue = 0x0000ff & rgb;
                int green = 0x0000ff & (rgb >> 8);
                int red = 0x0000ff & (rgb >> 16);
                int lum = (int) (red * 0.299 + green * 0.587 + blue * 0.114);
                outputImage.setRGB(x, y, lum | (lum << 8) | (lum << 16));
            }
        }
        return outputImage;
    }

    /**
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
