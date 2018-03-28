/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Lan
 */
public class Project1 {

    /**
     * @param args the command line arguments
     */
    
    public static void join(String inputPath, String outputPath, int size) throws IOException {
        File directory = new File(inputPath);
        File[] files = directory.listFiles();

        BufferedImage sprite = ImageIO.read(files[0]);

        int CR = sprite.getWidth();
        int CD = sprite.getHeight();

        for (File file : files) {
            BufferedImage bufferedImage = ImageIO.read(file);
            if (bufferedImage.getWidth() > CR) {
                CR = bufferedImage.getWidth();
            }
            if (bufferedImage.getHeight() > CD) {
                CD = bufferedImage.getHeight();
            }
        }

        int TCR = CR * size;
        int TCD = CD * size;
        BufferedImage spritemap = new BufferedImage(TCR, TCD, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = spritemap.createGraphics();

        int x = 0;
        int y = 0;
        int i = 0;
        for (File file : files) {
            sprite = ImageIO.read(file);
            g2d.drawImage(sprite, null, x, y);
            x += CR;
            i++;
            if (i % size == 0) {
                y += CD;
                x = 0;
            }
            if (i > size * size) {
                break;
            }
        }

        ImageIO.write(spritemap, "png", new File(outputPath));
    }
    
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            Project1.join("D:\\Project1\\Project1\\src\\project1\\input","D:\\Project1\\Project1\\src\\project1\\output.png", 2);
        } catch (IOException ex) {
            Logger.getLogger(Project1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
