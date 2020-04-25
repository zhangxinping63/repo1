package test;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @Author: Zhangxp
 * @Date: 2020-04-25 11:49
 */
public class ImageTest {
    @Test
    void test(){
        try {
           BufferedImage image = ImageIO.read(new File("D:\\zxp\\开发学习资料\\马士兵教育\\tank\\tank\\src\\images\\bulletD.gif"));
           assertNotNull(image);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   /* private void fail(String not_yet_implemented) {
    }*/
}
