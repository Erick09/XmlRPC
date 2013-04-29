package br.ifce.teste;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class QRCodeGenerator extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QRCodeGenerator frame = new QRCodeGenerator();
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
	public QRCodeGenerator() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JTextArea txtaQRCodeSource = new JTextArea();
		txtaQRCodeSource.setBounds(10, 11, 414, 91);
		contentPane.add(txtaQRCodeSource);
		
		JButton btnQRCGen = new JButton("Gerar QRCode");
		btnQRCGen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnQRCGen.setBounds(80, 113, 108, 23);
		contentPane.add(btnQRCGen);
		
		JButton btnLimpa = new JButton("Limpa Texto");
		btnLimpa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtaQRCodeSource.setText(null);
			}
		});
		btnLimpa.setBounds(248, 113, 115, 23);
		contentPane.add(btnLimpa);
	}
}
