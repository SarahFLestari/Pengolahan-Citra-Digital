/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pengolahan.citra.digital;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

/**
 *
 * @author MBP
 */
public class Main implements ActionListener{
    
    private final gui v = new gui();
    private Image image;
    private Color c;
    BufferedImage img;
    ImageIcon img3;
    Image img2;
    public Main() {
        v.setVisible(true);
        v.setActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(v.getBrowse())){
            JFileChooser chooser = new JFileChooser();
            int returnValue = chooser.showOpenDialog(null);
            if (returnValue == 0){
                File f = chooser.getSelectedFile();
                ImageIcon img0 = new ImageIcon(f.getAbsolutePath());
                image = img0.getImage();
                img2 = image.getScaledInstance(v.getGambar().getWidth(), v.getGambar().getHeight(), Image.SCALE_SMOOTH);
                img3 = new ImageIcon(img2);
                v.getGambar().setIcon(img3);
            }        
        }
        else if (source.equals(v.getGrayscale())){
            System.out.println("masuk");
            img2 = grayScale(image).getScaledInstance(v.getGambar().getWidth(), v.getGambar().getHeight(), Image.SCALE_SMOOTH);
            img3 = new ImageIcon(img2);
            v.getGambar().setIcon(img3);
            System.out.println("selesai");
        }
    }
    public Image grayScale(Image image){
        try{
            img = toBufferedimage(image);
            int H = img.getHeight(v);
            int W = img.getWidth(v);
            int count = 0;
            for (int i = 0;i<H;i++){
                for (int j = 0;j<W;j++){
                    count ++;
                    c = new Color(img.getRGB(j, i));
                    int merah = (int) (c.getRed() * 0.25);
                    int biru = (int) (c.getBlue() * 0.25);
                    int hijau = (int) (c.getGreen() * 0.5);
                    Color newColor = new Color(merah+biru+hijau,merah+biru+hijau,merah+biru+hijau);
                    img.setRGB(j, i, newColor.getRGB());
                    }
                }
        } catch (Exception e) {
            System.out.println("error");
        }
        image = img;
        return image;
    }

    private BufferedImage toBufferedimage(Image image) {
        if (image instanceof BufferedImage){
            return (BufferedImage) image;
        }
        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(image, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }
    
}
    
 

