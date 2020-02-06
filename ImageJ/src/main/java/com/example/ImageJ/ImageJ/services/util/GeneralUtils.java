package com.example.ImageJ.ImageJ.services.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class GeneralUtils {

    public static BufferedImage byteArrayToBufferedImage(byte[] bytes) throws IOException {
        InputStream inputStream = new ByteArrayInputStream(bytes);
        BufferedImage image = ImageIO.read(inputStream);
        return image;
    }

    public static void saveBuggeredImageToFile(String filePath,String fileFormat,BufferedImage bufferedImage) throws IOException {
        File outputfile = new File(filePath);
        ImageIO.write(bufferedImage, fileFormat, outputfile);
    }
}
