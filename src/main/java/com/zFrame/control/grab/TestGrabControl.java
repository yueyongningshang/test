package com.zFrame.control.grab;

import java.io.File;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class TestGrabControl {

    public static void main(String[] args) throws TesseractException {

        String file = "C:/Users/Gz/Desktop/screenImg/3.jpg";
        ITesseract iTesseract = new Tesseract();
        iTesseract.setDatapath("C:\\hk\\tesseract-1.03\\tessdata");
        // iTesseract.setLanguage("eng+chi_sim");// 中英文结合用 + 分隔
        long l = System.currentTimeMillis();
        System.out.println(iTesseract.doOCR(new File(file)));// 识别结果
        System.out.println("用时：" + (System.currentTimeMillis() - l) + "ms");

        File imageFile = new File("C:/Users/Gz/Desktop/screenImg/3.jpg");
        if (!imageFile.exists()) {
            System.out.println("图片不存在");
            ;
        }
        Tesseract tessreact = new Tesseract();
        tessreact.setDatapath("C:\\hk\\tesseract-1.03\\tessdata");

        String result;
        try {
            result = "测验结果：" + tessreact.doOCR(imageFile);
            System.out.println(result);
        } catch (TesseractException e) {
            e.printStackTrace();
        }
    }

}
