/**
 * 
 */
package com.protocol.safemon.sys;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * @author xizhonghuai
 * @date 2018��8��22��
 * @readme
 */
public class ImageProcess {

	private File inFile;

	private BufferedImage image;

	/**
	 * @param filename
	 * @throws IOException
	 */
	public ImageProcess(String filename) throws IOException {
		super();
		try {

			inFile = new File(filename);

			image = ImageIO.read(this.inFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int getWidth() {
		
		return image.getWidth();

	}

	public int getheight() {
		
		return image.getHeight();

	}

	public void drawRect(int x, int y, int xIncrement, int yIncrement, Color color) throws IOException {

		Graphics g = image.getGraphics();
		g.setColor(color);// ������ɫ
		g.drawRect(y, x, yIncrement,xIncrement); 
	}

	public void drawString(int x, int y, String text, Color color, Font font) throws IOException {

		Graphics g = image.getGraphics();
		g.setColor(color);// ������ɫ
		g.setFont(font); // ���塢���͡��ֺ�
		g.drawString(text, y, x);
	}

	public void save(String filename) throws IOException {
		
		File file = new File(filename);
		File fileParent = file.getParentFile();
		if (!fileParent.exists()) {
			fileParent.mkdirs();
		}
		file.createNewFile();
		
		FileOutputStream out = new FileOutputStream(filename);// ���ͼƬ�ĵ�ַ
		ImageIO.write(image, "jpeg", out);
		
		out.flush();
		out.close();
		
 
		
	}

}
