package memopad;

import io_text.Pass;
import io_text.Restore;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;

public class MemoMain extends JFrame {

	private JPanel contentPane;
	Pass md = new Pass();
	public final String filename = md.SHGetFolderPath() + "\\memo.txt";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemoMain frame = new MemoMain();
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
	public MemoMain() {
		setTitle("\u7C21\u6613\u30E1\u30E2\u5E33");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane);
		
		final JTextPane textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		
		Restore restore = new Restore();
		textPane.setText(restore.getRestore());
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JButton btnNewButton = new JButton("\u4FDD\u5B58");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO 保存処理
				try{
					BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
					bw.write(textPane.getText().replace("\n", "\r\n"));
					bw.flush();
					bw.close();
				}catch(IOException ex){
					System.out.println("ファイルエラー");
				}
			}
		});
		panel.add(btnNewButton);
		
		JButton button = new JButton("\u5FA9\u5143");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Restore restore = new Restore();
				textPane.setText(restore.getRestore());	//TODO 復元処理
			}
		});
		panel.add(button);
		
		JButton button_1 = new JButton("\u30AF\u30EA\u30A2");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textPane.setText("");	//TODO テキストクリア
			}
		});
		panel.add(button_1);
	}

}
