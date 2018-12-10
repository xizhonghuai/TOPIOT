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
 * @date 2018年8月22日
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
		g.setColor(color);// 画笔颜色
		g.drawRect(y, x, yIncrement,xIncrement); 
	}

	public void drawString(int x, int y, String text, Color color, Font font) throws IOException {

		Graphics g = image.getGraphics();
		g.setColor(color);// 画笔颜色
		g.setFont(font); // 字体、字型、字号
		g.drawString(text, y, x);
	}

	public void save(String filename) throws IOException {
		
		File file = new File(filename);
		File fileParent = file.getParentFile();
		if (!fileParent.exists()) {
			fileParent.mkdirs();
		}
		file.createNewFile();
		
		FileOutputStream out = new FileOutputStream(filename);// 输出图片的地址
		ImageIO.write(image, "jpeg", out);
		
		out.flush();
		out.close();
		
 
		
	}

}
