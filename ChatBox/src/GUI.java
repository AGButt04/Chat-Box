import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GUI extends JFrame implements ActionListener {

	private static JTextArea text;
	private static JTextField tf;
		
	public static void main(String[] args) {
		JFrame frame = new JFrame("Chat Frame");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400,400);
		
		JMenuBar menu = new JMenuBar();
		JMenu m1 = new JMenu("File");
		JMenu m2 = new JMenu("Help");
		menu.add(m1);
		menu.add(m2);
		JMenuItem mt1 = new JMenuItem("Open");
		JMenuItem mt2 = new JMenuItem("Save as");
		m1.add(mt1);
		m1.add(mt2);
		JMenuItem mt3 = new JMenuItem("Welcome");
		JMenuItem mt4 = new JMenuItem("Help Contents");
		m2.add(mt3);
		m2.add(mt4);
		
		JPanel panel = new JPanel();
		
		JLabel label = new JLabel("Enter text");
		tf = new JTextField(10);
		JButton send = new JButton("Send");
		JButton reset = new JButton("Reset");
		
		panel.add(label);
		panel.add(tf);
		panel.add(send);
		panel.add(reset);
		
		text = new JTextArea();
		
		frame.add(BorderLayout.SOUTH, panel);
		frame.add(BorderLayout.CENTER, text);
		frame.add(BorderLayout.NORTH, menu);
		frame.setVisible(true);
		
		GUI listener = new GUI();
		send.addActionListener((ActionListener) listener);
		reset.addActionListener((ActionListener) listener);
	}
	
	public void actionPerformed(ActionEvent event) {
		JButton b = (JButton) event.getSource();
		if (b.getText().equals("Send")) {
			text.append(tf.getText() + "\n");
			tf.setText("");
		} else {
			text.setText("");
			tf.setText("");
		}
	}

}
