/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pengolahan.citra.digital;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

/**
 *
 * @author MBP
 */
public class Main implements ActionListener{
    
    private final gui v = new gui();
        
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
                Image img1 = img0.getImage();
                Image img2 = img1.getScaledInstance(v.getGambar().getWidth(), v.getGambar().getHeight(), Image.SCALE_SMOOTH);
                ImageIcon img = new ImageIcon(img2);
                v.getGambar().setIcon(img);
            }
                    
            
        }
    }
}
    
 

