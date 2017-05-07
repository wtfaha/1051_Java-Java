package com;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.swing.*;

public class Func {
	public static GridBagConstraints makeGrid(int x, int y, int w, int h) {
		return makeGrid(x, y, w, h, 1, 1, GridBagConstraints.BOTH, GridBagConstraints.WEST);
	}
	
	public static GridBagConstraints makeGrid(int x, int y, int w, int h, double wx, double wy) {
        return makeGrid(x, y, w, h, wx, wy, GridBagConstraints.BOTH, GridBagConstraints.WEST);
	}
	
	public static GridBagConstraints makeGrid(int x, int y, int w, int h, double wx, double wy, int fill, int anchor) {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = x; 
		c.gridy = y;
        c.gridwidth = w;
        c.gridheight = h;
        c.weightx = wx;
        c.weighty = wy;
        c.fill = fill;
        c.anchor = anchor;
        return c;
	}
	
	public static String getFileToString(File file) {
		String sFile = null;
		try {
			FileInputStream fin = new FileInputStream(file.getPath());
			byte ba[] = new byte[fin.available()];
            fin.read(ba);
            sFile = new String(ba, "UTF-8");
            sFile = sFile.replace("\uFEFF", "");
            fin.close();
		} catch(IOException e) { }
		return sFile;
	}
	
	public static ImageIcon getImg(String path) {
		return new ImageIcon(Func.class.getResource(path));
	}
	
	public static ImageIcon getScaleImg(String path, int w, int h) {
		return new ImageIcon(Func.getImg(path).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH));
	}
	
	public static ImageIcon getGrayImg(Icon icon) {
	    final int w = icon.getIconWidth();
	    final int h = icon.getIconHeight();
	    GraphicsEnvironment ge =
	        GraphicsEnvironment.getLocalGraphicsEnvironment();
	    GraphicsDevice gd = ge.getDefaultScreenDevice();
	    GraphicsConfiguration gc = gd.getDefaultConfiguration();
	    BufferedImage image = gc.createCompatibleImage(w, h);
	    Graphics2D g2d = image.createGraphics();
	    icon.paintIcon(null, g2d, 0, 0);
	    Image gray = GrayFilter.createDisabledImage(image);
	    return new ImageIcon(gray);
	}
}
