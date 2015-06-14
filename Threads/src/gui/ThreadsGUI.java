package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Dimension;

import javax.swing.JButton;

import music.Instrumental;
import music.Performance;
import music.Singer;
import music.Song;
import music.Synchronizer;
import music.Voice;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class ThreadsGUI extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private static JTextArea textArea;
	private JPanel panel;
	private JButton btnStart;
	private JButton btnStop;
	private Song song;
    private Performance performance;
    private Singer bbk;
    private Singer bono;
    private Instrumental inst;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThreadsGUI frame = new ThreadsGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ThreadsGUI() {
		setTitle("Threads");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getScrollPane(), BorderLayout.CENTER);
		contentPane.add(getPanel(), BorderLayout.SOUTH);
		getTA().setText("\t\t**WARNING**\n"+"when Stop button is pressed it takes a while until threads are safely stopped");
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTextArea());
		}
		return scrollPane;
	}
	private static JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setEditable(false);
		}
		return textArea;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setPreferredSize(new Dimension(10, 60));
			panel.setLayout(null);
			panel.add(getBtnStart());
			panel.add(getBtnStop());
		}
		return panel;
	}
	private JButton getBtnStart() {
		if (btnStart == null) {
			btnStart = new JButton("Start");
			btnStart.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					getTA().setText(null);
					btnStart.setVisible(false);
					btnStop.setVisible(true);
					 initialize();
				     bbk.start();
				     bono.start();
				     inst.start();
				}
			});
			btnStart.setBounds(220, 15, 89, 23);
		}
		return btnStart;
	}
	private JButton getBtnStop() {
		if (btnStop == null) {
			btnStop = new JButton("Stop");
			btnStop.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					
					bbk.setStopIt(true);
				    bono.setStopIt(true);
				    inst.setStopIt(true);
				    while(bbk.isAlive()||bono.isAlive()||inst.isAlive()){
				    	getTA().setText(null);
				    	if(!bono.isAlive()&&!bbk.isAlive()&&inst.isAlive()) inst.getSynch().endOfSinging();
				    }
				    btnStop.setVisible(false);
					btnStart.setVisible(true);
				}
			});
			btnStop.setVisible(false);
			btnStop.setBounds(220, 15, 89, 23);
		}
		return btnStop;
	}
	private void initialize() {
		List<String> lyrics = new ArrayList<String>();
        lyrics.add("Here I lay");
        lyrics.add("Still and breathless");
        lyrics.add("Just like always");
        lyrics.add("Still your passenger");
        
        song = new Song("Passenger", lyrics);
        performance = new Performance(song, 3000,10000);
        Synchronizer synch = new Synchronizer(true,false);
        boolean stopIt = false;
        
        bbk = new Singer("B.B. King", Voice.LEAD, performance, stopIt, synch);
        bono = new Singer("Bono", Voice.BACKING, performance, stopIt, synch);
        inst = new Instrumental("(AWESOME INSTRUMENTAL)", stopIt, performance, synch);
	}
	public static JTextArea getTA() {
		return getTextArea();
	}
}
