package edu.ycp.cs201.disks;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * The DisksApp class is a "frame", which is a top-level window.
 * Its only purpose in this program is as a container for the
 * DisksPanel, which is where all of the gameplay and
 * GUI operations occur.
 * 
 * You won't need to change this class.
 */
public class DisksApp extends JFrame {
	private static final long serialVersionUID = 1L;
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				DisksApp app = new DisksApp();
				DisksPanel panel = new DisksPanel();
				app.getContentPane().add(panel, BorderLayout.CENTER);
				app.pack();
				app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				app.setVisible(true);
				
				
			}
		});
	}
}
