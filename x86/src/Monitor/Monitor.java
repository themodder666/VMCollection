package Monitor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Monitor extends JPanel {

	/**
	 * @param args
	 */
	int width,  height;
	BufferedImage canvas;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Byte.MAX_VALUE);
		JFrame f=new JFrame();
		f.add(new Monitor(512, 512, new byte[1024*1024]));
		f.pack();
		f.setResizable(false);
		f.setVisible(true);
		f.setSize(512, 512);
		

	}
	public Monitor(int width, int height, byte[] memory){
		
		this.width=width;
		this.height=height;
		canvas=new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				canvas.setRGB(i,j, Color.BLACK.getRGB());
			}
		}
	}
	
	public void paint(Graphics arg0) {
		// TODO Auto-generated method stub
		setSize(width, height);
		arg0.drawImage(canvas,0,0,null);
	}

}