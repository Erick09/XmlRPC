package br.ifce.teste;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Tela extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela frame = new Tela();
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
	public Tela() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 341, 154);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnIniciaServidor = new JButton("Inicia servidor");
		btnIniciaServidor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnIniciaServidor.setBounds(10, 27, 127, 23);
		contentPane.add(btnIniciaServidor);
		
		JButton btnPausaServidor = new JButton("Pausa Servidor");
		btnPausaServidor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPausaServidor.setBounds(10, 80, 127, 23);
		contentPane.add(btnPausaServidor);
		
		JButton btnCapturaQrcode = new JButton("Captura QRCode");
		btnCapturaQrcode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCapturaQrcode.setBounds(174, 27, 134, 23);
		contentPane.add(btnCapturaQrcode);
	}
}
