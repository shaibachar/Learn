package com.example.ImageJ.ImageJ.services;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Service
public class ApplicationService {

    public String getHello(String name) {
        return String.format("hello %s", name);
    }

    public String doOcr(InputStream inputStream) throws TesseractException, IOException {
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("C:\\workspace\\Tests\\Learn1\\ImageJ\\src\\main\\resources");
        tesseract.setLanguage("ocr1");
        BufferedImage imBuff = ImageIO.read(inputStream);
        String res = tesseract.doOCR(imBuff);
        return res;
    }
    public byte[] processImage(byte[] bytes, int parts) {
        try {
            InputStream inputStream = new ByteArrayInputStream(bytes);
            BufferedImage inputImage = ImageIO.read(inputStream);

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
//                    System.out.println("image w:"+outputImage.getWidth()+" h:"+outputImage.getHeight()+" x:"+(x-i)+" y:"+y);
                    outputImage.setRGB(x-i, y, lum | (lum << 8) | (lum << 16));
                }
            }
            ImageIO.write(outputImage, "jpg", new File("output1.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }
}
