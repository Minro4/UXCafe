package src.Vue;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class VueUtils {
	public static JPanel getGenericPanel(JPanel[] composantes) {
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new FlowLayout());
		jPanel.setBackground(Color.white);
		for (JPanel composante : composantes) {
			jPanel.add(composante);
		}

		return jPanel;
	}

	public static JPanel getMultiplePanel(JPanel[]... composantes) {
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new GridLayout(composantes.length, 1));
		jPanel.setBackground(Color.white);

		for (int i = 0; i < composantes.length; i++) {
			jPanel.add(getGenericPanel(composantes[i]));
		}

		return jPanel;
	}


	
	public static ImageIcon setIcon(String path, int resizeX) {
		
		ImageIcon imageI = new ImageIcon(path);
		
		java.awt.Image oof = imageI.getImage();
		java.awt.Image resized = oof.getScaledInstance(resizeX, resizeX, java.awt.Image.SCALE_SMOOTH);
		imageI.setImage(resized);		
		
		return imageI;
		
	}
}
