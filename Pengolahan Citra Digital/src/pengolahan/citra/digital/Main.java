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
import javax.swing.filechooser.FileNameExtensionFilter;

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
            chooser.setFileFilter(new FileNameExtensionFilter("jpg|png","jpg","png"));
            int returnValue = chooser.showOpenDialog(null);
            if (returnValue == 0){
                File f = chooser.getSelectedFile();
                ImageIcon img0 = new ImageIcon(f.getAbsolutePath());
                image = img0.getImage();
                img2 = image.getScaledInstance(v.getGambar().getWidth(), v.getGambar().getHeight(), Image.SCALE_SMOOTH);
                img3 = new ImageIcon(img2);
                v.getGambar().setIcon(img3);
            }        
        } else if (source.equals(v.getGrayscale())){
            System.out.println("gray");
            img2 = grayScale(image).getScaledInstance(v.getGambar().getWidth(), v.getGambar().getHeight(), Image.SCALE_SMOOTH);
            img3 = new ImageIcon(img2);
            v.getGambar().setIcon(img3);
            System.out.println("selesai");
        } else if (source.equals(v.getPerbesar())){
            System.out.println("Perbesar");
            img2 = Perbesar(image);
            img3 = new ImageIcon(img2);
            v.getGambar().setIcon(img3);
            System.out.println("selesai");
               
        } else if (source.equals(v.getTerangTambah())){
            System.out.println("terang");
            img2 = TerangTambah(image).getScaledInstance(v.getGambar().getWidth(), v.getGambar().getHeight(), Image.SCALE_SMOOTH);
            img3 = new ImageIcon(img2);
            v.getGambar().setIcon(img3);
            System.out.println("selesai");
        } else if (source.equals(v.getTerangKali())){
            System.out.println("terang");
            img2 = TerangKali(image).getScaledInstance(v.getGambar().getWidth(), v.getGambar().getHeight(), Image.SCALE_SMOOTH);
            img3 = new ImageIcon(img2);
            v.getGambar().setIcon(img3);
            System.out.println("selesai");
        } else if (source.equals(v.getGelapBagi())){
            System.out.println("Gelap");
            img2 = GelapBagi(image).getScaledInstance(v.getGambar().getWidth(), v.getGambar().getHeight(), Image.SCALE_SMOOTH);
            img3 = new ImageIcon(img2);
            v.getGambar().setIcon(img3);
            
            System.out.println("selesai");
        }
    }
    public Image grayScale(Image image){
        try{
            img = toBufferedimage(image);
            int H = img.getHeight();
            int W = img.getWidth();
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
    
    private Image Perbesar(Image image){
        try {
            img = toBufferedimage(image);
            int W = img.getWidth()*2;
            int H = img.getHeight()*2;
            int count = 0;
            for (int i = 0;i<W;i++){
                for (int j = 0;j<H;j++){
                    count ++;
                    c = new Color(img.getRGB(j, i));
                    int merah = c.getRed();
                    int biru = c.getBlue();
                    int hijau = c.getGreen();
                    Color newColor = new Color(merah,biru,hijau);
                    for (int k = 0;k<2;k++)
                        for (int l = 0;l<2;l++){
                            int baris = (i-1)*2+k;
                            int kolom = (j-1)*2+l;
                            int P = img.getRGB(k, l);
                            img.setRGB(baris, kolom, newColor.getRGB());
                        }
                }
            }
            
        } catch (Exception e){
            System.out.println("error");
        }
        image = img;
        return image;
    }
    
    public Image TerangTambah(Image image){
        try{
            img = toBufferedimage(image);
            int H = img.getHeight();
            int W = img.getWidth();
//            System.out.println("width,height: "+W+", "+H);
//            int count = 0;
//            for (int i=0;i<H;i++){
//                for (int j=0;j<W;j++){
//                    count ++;
//                    System.out.println("X,Y: "+j+", "+i);
//                    int pixel = img.getRGB(j, i);
//                    printPixelARGB(pixel);
//                    System.out.println("masuk");
//                    int alpha = (pixel >> 24) & 0xff + 10;
//                    int red = (pixel >> 16) & 0xff + 10;
//                    int green = (pixel >> 8) & 0xff + 10;
//                    int blue = (pixel) & 0xff + 10;
//                    System.out.println("masuk2");
//                    Color c = new Color(red,green,blue);
//                    img.setRGB(j, i, c.getRGB());
//                    System.out.println("");
//                }
//            }
            int factor = 80;
            for (int y=0;y<H;y++){
                for (int x=0;x<W;x++){
                    Color c = new Color(img.getRGB(x, y));
                    int merah = c.getRed()+factor;
                    int hijau = c.getGreen()+factor;
                    int biru = c.getBlue()+factor;
                    if (merah >= 255){
                        merah=255;
                    } else if (merah<0){
                        merah = 0;
                    }
                    if (hijau >= 255){
                        hijau=255;
                    } else if (hijau<0){
                        hijau = 0;
                    }
                    if (biru >= 255){
                        biru=255;
                    } else if (biru<0){
                        biru = 0;
                    }
                    img.setRGB(x, y, new Color(merah,hijau,biru).getRGB());
                }
            }
        } catch (Exception e) {
            System.out.println("kok error");
        }
        image = img;
        return image;
    }
    public void printPixelARGB(int pixel) {
        int alpha = (pixel >> 24) & 0xff + 10;
        int red = (pixel >> 16) & 0xff + 10;
        int green = (pixel >> 8) & 0xff+ 10;
        int blue = (pixel) & 0xff +10;
        System.out.println("argb: " + alpha + ", " + red + ", " + green + ", " + blue);
    }
        public Image TerangKali(Image image){
        try{
            img = toBufferedimage(image);
            int H = img.getHeight();
            int W = img.getWidth();
            int factor = 10;
            for (int y=0;y<H;y++){
                for (int x=0;x<W;x++){
                    Color c = new Color(img.getRGB(x, y));
                    int merah = c.getRed()*factor;
                    int hijau = c.getGreen()*factor;
                    int biru = c.getBlue()*factor;
                    if (merah >= 255){
                        merah=255;
                    } else if (merah<0){
                        merah = 0;
                    }
                    if (hijau >= 255){
                        hijau=255;
                    } else if (hijau<0){
                        hijau = 0;
                    }
                    if (biru >= 255){
                        biru=255;
                    } else if (biru<0){
                        biru = 0;
                    }
                    img.setRGB(x, y, new Color(merah,hijau,biru).getRGB());
                }
            }
        } catch (Exception e) {
            System.out.println("kok error");
        }
        image = img;
        return image;
    }
        
    public Image GelapBagi(Image image){
        try{
            img = toBufferedimage(image);
            int H = img.getHeight();
            int W = img.getWidth();
            int factor = 2;
            for (int y=0;y<H;y++){
                for (int x=0;x<W;x++){
                    Color c = new Color(img.getRGB(x, y));
                    int merah = c.getRed()/factor;
                    int hijau = c.getGreen()/factor;
                    int biru = c.getBlue()/factor;
                    if (merah >= 255){
                        merah=255;
                    } else if (merah<0){
                        merah = 0;
                    }
                    if (hijau >= 255){
                        hijau=255;
                    } else if (hijau<0){
                        hijau = 0;
                    }
                    if (biru >= 255){
                        biru=255;
                    } else if (biru<0){
                        biru = 0;
                    }
                    img.setRGB(x, y, new Color(merah,hijau,biru).getRGB());
                }
            }
        } catch (Exception e) {
            System.out.println("kok error");
        }
        image = img;
        return image;    
    }
    public Image GelapKurang (Image image){
        
    }
  
}
    
 

