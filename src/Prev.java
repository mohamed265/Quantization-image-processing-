package Quantizer_MY;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class Prev  extends JFrame{
	
	Prev(BufferedImage bac , String title){
		super(title);
		setVisible(true);
		int width = bac.getWidth(), height = bac.getHeight();
		setBounds((1300 - width) / 2, (760 - height) / 2, width +30, height + 50);
		//setResizable(false);
		setContentPane(new ImagePanel(bac));
	}

}

class ImagePanel extends JComponent {
    private Image image;
    public ImagePanel(Image image) {
        this.image = image;
    }
    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, null);
    }
}