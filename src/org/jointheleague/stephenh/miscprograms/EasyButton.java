package org.jointheleague.stephenh.miscprograms;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class EasyButton extends MouseAdapter {

	/*
	 * Use the methods below to make an easy button show on the screen. 
	 * When clicked, it says some very annoying words.
	 */

	JLabel easyButtonImage;
	List<String> annoying;
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new EasyButton().showEasyButton();
			}
		});
	}

	private void showEasyButton() {
		JFrame quizWindow = new JFrame();
		quizWindow.setVisible(true);
		URL url = null;
		try {
			url = new URL(
					"https://github.com/joonspoon/league-jars/blob/master/easy_button.jpg?raw=true");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		Icon icon = new ImageIcon(url);
		this.easyButtonImage = new JLabel(icon);
		quizWindow.add(easyButtonImage);
		easyButtonImage.addMouseListener(this);
		quizWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		quizWindow.pack();
		createAnnoyingList();
	}
	
	private void createAnnoyingList() {
		annoying = Arrays.asList(new String[]{"My spoon is too big!", "I am a banana!", "Hey! Hey apple!", "Mr. Z requires shiny objects.",
				"I'm just sayin'..."});
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		speak(annoying.get(new Random().nextInt(annoying.size())));
	}

	private void speak(String words) {
		try {
			Runtime.getRuntime().exec("say " + words).waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}