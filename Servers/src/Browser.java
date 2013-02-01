import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jnetpcap.winpcap.WinPcap;


public class Browser {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println(WinPcap.);
		JFileChooser f=new JFileChooser();
		f.showOpenDialog(new JFrame());
		File fe=f.getSelectedFile();
		JFrame fr=new JFrame();
		JPanel p=new JPanel();
		BufferedReader r=new BufferedReader(new FileReader(fe));
		StringBuilder b=new StringBuilder();
		while (r.ready()) {
			b.append((char)r.read());
			
		}
		
		JLabel l=new JLabel();
		l.setText(b.toString());
		p.add(l);
		fr.add(p);
		fr.setSize(500, 500);
		fr.pack();
		fr.setVisible(true);
	}

}
