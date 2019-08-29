package collector.gui;



import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SplashPanelUserEnd extends JPanel {

	 JProgressBar progressBar;

	public SplashPanelUserEnd() {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createLineBorder(new Color(255,0,0), 1));
		Icon icon = new ImageIcon(getClass().getResource("/images/jansevalogo1.png"));
		add(new JLabel(icon), BorderLayout.CENTER);
		progressBar = new JProgressBar(1, 100);
		  progressBar.setPreferredSize(new Dimension(20,20));
		progressBar.setForeground(new Color(255,0,0));
		
                
                add(progressBar, BorderLayout.SOUTH);
	}

	public void setProgress(int progress) {
		progressBar.setValue(progress);
	}

	public static void main(String args[]) {
		SplashPanelUserEnd sp = new SplashPanelUserEnd();
		JWindow win = new JWindow();
		win.setContentPane(sp);
		win.pack();
		win.setLocationRelativeTo(null);
		win.setVisible(true);
		try {
			for (int i = 1; i <= 100; i++) {
                           
				Thread.sleep(20);
				sp.setProgress(i);
                                
			}
		} 
                catch (Exception e) {
                    e.printStackTrace();
		}
		win.setVisible(false);
		win.dispose();
                new UserEndWelcome().setVisible(true);
	}
}
