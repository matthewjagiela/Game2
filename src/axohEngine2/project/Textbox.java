package axohEngine2.project;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.JFrame;

public class Textbox {
	String s7 = " ";
	String s1 = "", s2 = "", s3 = "", s4 = "", s5 = "", s6 = "";
	boolean s1IsFull = false, s2IsFull = false, s3IsFull = false, s4IsFull = false, s5IsFull = false, s6IsFull = false;
	int progress = 0;

	// draws textbox
	public void renderTextBox(JFrame frame, Graphics2D g2d) {
		g2d.setColor(Color.black);
		g2d.drawRect(500, 560, 320, 100);
		g2d.setColor(new Color(255, 255, 255, 100));
		g2d.fillRect(500, 560, 320, 100);
		g2d.setColor(Color.black);
		g2d.setFont(new Font("Times New Roman", Font.BOLD, 14));
		handleString();
		g2d.drawString(s1, 505, 575);
		g2d.drawString(s2, 505, 590);
		g2d.drawString(s3, 505, 605);
		g2d.drawString(s4, 505, 620);
		g2d.drawString(s5, 505, 635);
		g2d.drawString(s6, 505, 650);
	}

	public void setTextBox(String string) {
		progress = 0;
		s1 = " ";
		s2 = " ";
		s3 = " ";
		s4 = " ";
		s5 = " ";
		s6 = " ";
		s1IsFull = false;
		s2IsFull = false;
		s3IsFull = false;
		s4IsFull = false;
		s5IsFull = false;
		s6IsFull = false;
		s7 = string;

	}

	public void handleString() {

		String[] parts = s7.split(" ");
		int stringLength = 0;
		while (!s1IsFull && progress != parts.length) {
			for (int i = 0; i < parts.length; i++) {
				stringLength = stringLength + parts[i].length();

				if (stringLength < 45) {
					s1 += parts[i] + " ";
					stringLength++;
					progress++;
				} else
					s1IsFull = true;
			}
		}
		while (!s2IsFull && progress != parts.length) {
			stringLength = 0;
			for (int i = progress; i < parts.length; i++) {
				stringLength = stringLength + parts[i].length();

				if (stringLength < 45) {
					s2 += parts[i] + " ";
					stringLength++;
					progress++;
				} else
					s2IsFull = true;
			}
		}
		while (!s3IsFull && progress != parts.length) {
			stringLength = 0;
			for (int i = progress; i < parts.length; i++) {
				stringLength = stringLength + parts[i].length();

				if (stringLength < 45) {
					s3 += parts[i] + " ";
					stringLength++;
					progress++;
				} else
					s3IsFull = true;
			}
		}
		while (!s4IsFull && progress != parts.length) {
			stringLength = 0;
			for (int i = progress; i < parts.length; i++) {
				stringLength = stringLength + parts[i].length();

				if (stringLength < 45) {
					s4 += parts[i] + " ";
					stringLength++;
					progress++;
				} else
					s4IsFull = true;
			}
		}
		while (!s5IsFull && progress != parts.length) {
			stringLength = 0;
			for (int i = progress; i < parts.length; i++) {
				stringLength = stringLength + parts[i].length();

				if (stringLength < 45) {
					s5 += parts[i] + " ";
					stringLength++;
					progress++;
				} else
					s5IsFull = true;
			}
		}
		while (!s6IsFull && progress != parts.length) {
			stringLength = 0;
			for (int i = progress; i < parts.length; i++) {
				stringLength = stringLength + parts[i].length();
				if (stringLength < 45) {
					s6 += parts[i] + " ";
					stringLength++;
					progress++;
				} else
					s6IsFull = true;
			}
		}
	}

}

// package axohEngine2.project;
//
// import java.awt.BasicStroke;
// import java.awt.Color;
// import java.awt.Font;
// import java.awt.Graphics2D;
// import java.awt.Shape;
// import java.awt.font.TextLayout;
//
// import javax.swing.JFrame;
//
// public class Textbox
// {
// // draws textbox
// public void renderTextBox(JFrame frame, Graphics2D g2d)
// {
// g2d.setColor(Color.white);
// g2d.drawRect(42, 440, 1100, 210);
// g2d.setColor(Color.black);
// g2d.fillRect(42, 440, 1100, 210);
// }
//
// int startX;
// int startY;
// // draws text in textbox
// public void renderText(String text, Graphics2D g2d, JFrame frame)
// {
//
//
// Font f;
// Shape shape;
// TextLayout t1;
//
// startX = 160;
// startY = 500;
// for (int i = 0; i < text.length() - 1; i++)
// {
// // System.out.println(startX + ", " + startY + ", " + i);
//
// f = new Font("Courier New", Font.PLAIN, 62);
// t1 = new TextLayout(text.substring(i, i+1), f, g2d.getFontRenderContext());
// shape = t1.getOutline(null);
// g2d.setColor(Color.black);
// g2d.setStroke(new BasicStroke(4, BasicStroke.CAP_ROUND,
// BasicStroke.JOIN_ROUND));
// g2d.translate(startX, startY);
// g2d.draw(shape);
// g2d.translate(startX * -1, startY * -1);
// g2d.setColor(Color.white);
// g2d.translate(startX, startY);
// g2d.fill(shape);
// g2d.translate(startX * -1, startY * -1);
//
// startX += 160;
//
// if (i == 15)
// {
// startX = 160;
// startY += 160;
// }
//
// }
//
//
//
// }
//
// }
