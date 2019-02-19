package com.qunar.fintech.plat.admin.support.util;

import org.springframework.util.Base64Utils;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.MemoryCacheImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by xiqun.song on 2017/8/23.
 */
public class ImageUtil {

    /**
     * 图片裁剪
     *
     * @param base64Image 图片base64编码字符串
     * @param x           x坐标
     * @param y           y坐标
     * @param w           宽度
     * @param h           高度
     * @return base64 裁剪图片
     */
    public static String cutImage(String base64Image, int x, int y, int w, int h) throws Exception {

        byte[] b = Base64Utils.decodeFromString(base64Image);
        for (int i = 0; i < b.length; ++i) {

            if (b[i] < 0) {//调整异常数据
                b[i] += 256;
            }
        }
        BufferedImage bi = readMemoryImage1(b);
        int srcWidth = bi.getWidth(); // 源图宽度
        int srcHeight = bi.getHeight(); // 源图高度
        Image image = bi.getScaledInstance(srcWidth, srcHeight, Image.SCALE_DEFAULT);

        ImageFilter cropFilter = new CropImageFilter(x, y, w, h);
        Image img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), cropFilter));
        BufferedImage tag = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics g = tag.getGraphics();
        g.drawImage(img, 0, 0, null); // 绘制缩小后的图
        g.dispose();


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(tag, "jpeg", baos);


        return Base64Utils.encodeToString(baos.toByteArray());
    }

    public static final BufferedImage readMemoryImage1(byte[] imgBytes) throws IOException {
        if (null == imgBytes || 0 == imgBytes.length)
            throw new NullPointerException("the argument 'imgBytes' must not be null or empty");
        // 将字节数组转为InputStream，再转为MemoryCacheImageInputStream
        ImageInputStream imageInputstream = new MemoryCacheImageInputStream(new ByteArrayInputStream(imgBytes));
        // 直接调用ImageIO.read方法解码
        BufferedImage bufImg = ImageIO.read(imageInputstream);
        if (null == bufImg)
            // 没有能识别此数据的图像ImageReader对象，抛出异常
            throw new IOException("unsupported image format");
        return bufImg;
    }

    /**
     * 将base64编码的字符串转换为inputStream
     *
     * @param base64Image base64编码图片
     * @return
     */
    public static InputStream convertStrToStream(String base64Image) {

        byte[] b = Base64Utils.decodeFromString(base64Image);
        for (int i = 0; i < b.length; ++i) {

            if (b[i] < 0) {//调整异常数据
                b[i] += 256;
            }
        }
        ByteArrayInputStream ins = new ByteArrayInputStream(b);
        return ins;
    }

    public static void main(String[] args) {
        System.out.println(convertStrToStream("3/face/txt/uz0bcn105gedpsl5s520B.txt"));
    }
}
