package gameUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.MessageFormat;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import gameLogic.GameManager;

public class OuterFrame extends JFrame implements ActionListener {
	static JButton playerButton;
	private JButton restart;
	private JButton back;
	private BoardPanel boardPanel;
	private Timer timer;
	private static Timer turnTimer;
	private static Integer turnTimeCount;
	private Integer timeCount;
	private GameManager _Manager;
	private Square _currSquare;
	private static JFrame _frame;

	public OuterFrame() {
		super("Gomoku");
		_currSquare = new Square(-1, -1);
		timer = new Timer(1000, this);
		turnTimeCount = 30;
		turnTimer = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (turnTimeCount == 0) {
					Main.togglePlayer();
					updateButton();
					JOptionPane.showMessageDialog(rootPane, "Your Turn Has Ended");
					turnTimeCount = 30;
				}
				turnTimeCount--;
				repaint();

			}
		});
		_frame = this;
		timer.start();
		turnTimer.start();
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		JPanel panel5 = new JPanel(new GridBagLayout());
		boardPanel = new BoardPanel(this);
		_Manager = new GameManager(boardPanel);
		playerButton = new JButton();
		playerButton.setText((Main.currentPlayer == Player.black ? "White " : "Black ") + "Is Playing");
		playerButton.setEnabled(false);
		restart = new JButton("Restart");
		restart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				restartGame();
			}
		});
		back = new JButton("back to start");
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StartPageFrame st = new StartPageFrame();
				dispose();
				turnTimer.stop();
			}
		});

		timeCount = 0;
		panel1.setBackground(Color.BLACK);
		panel2.setBackground(Color.BLACK);
		panel3.setBackground(Color.BLACK);
		panel4.setBackground(Color.BLACK);
		panel1.add(playerButton);
		panel5.add(boardPanel);
		panel4.add(restart);
		panel4.add(back);
		JButton end = new JButton("End Game");
		end.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				endGame(_frame);
			}
		});
		panel4.add(end);
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(panel1, BorderLayout.NORTH);
		getContentPane().add(panel2, BorderLayout.WEST);
		getContentPane().add(panel3, BorderLayout.EAST);
		getContentPane().add(panel4, BorderLayout.SOUTH);
		getContentPane().add(panel5, BorderLayout.CENTER);
		_Manager.startGame(_currSquare.get_i(), _currSquare.get_j());
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void updateButton() {
		playerButton.setText((Main.currentPlayer == Player.black ? "White " : "Black ") + "Is Playing");
	}

	private void restartGame() {
		boardPanel.clearBoard();
		Main.togglePlayer();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.white);
		g.drawString("Time Played: " + timeCount + " seconds", 50, 50);
		g.drawString("Your Turn End In: " + turnTimeCount + " seconds", 450, 50);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timeCount++;
		repaint();
	}

	public static void turnTimerRestart() {
		turnTimeCount = 30;
	}

	public static void endGame(JFrame frame) {
		//EndPageFrame st = new EndPageFrame();
		frame.dispose();
		turnTimer.stop();
		if (Main.GAME_STATUS == gameLogic.GameStatus.black_player_win) {
			ImageIcon icon = new ImageIcon(
					"C:\\Users\\97250\\OneDrive - ORT365Schools\\YodGimal\\Project\\Project\\Img\\black.png");
			Image img = icon.getImage();
			Image scaledImage = img.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
			ImageIcon scaledIcon = new ImageIcon(scaledImage);
			JOptionPane.showMessageDialog(null, "Black Player Wins!", "Game Ended", JOptionPane.INFORMATION_MESSAGE,
					scaledIcon);
		} else if (Main.GAME_STATUS == gameLogic.GameStatus.white_player_win) {
			ImageIcon icon = new ImageIcon(
					"C:\\Users\\97250\\OneDrive - ORT365Schools\\YodGimal\\Project\\Project\\Img\\white.png");
			Image img = icon.getImage();
			Image scaledImage = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			ImageIcon scaledIcon = new ImageIcon(scaledImage);
			JOptionPane.showMessageDialog(null, "White Player Wins!", "Game Ended", JOptionPane.INFORMATION_MESSAGE,
					scaledIcon);
		} else if (Main.GAME_STATUS == gameLogic.GameStatus.draw) {
			ImageIcon icon = new ImageIcon(
					"C:\\Users\\97250\\OneDrive - ORT365Schools\\YodGimal\\Project\\Project\\Img\\draw.png");
			Image img = icon.getImage();
			Image scaledImage = img.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
			ImageIcon scaledIcon = new ImageIcon(scaledImage);
			JOptionPane.showMessageDialog(null, "It's A Draw!", "Game Ended", JOptionPane.INFORMATION_MESSAGE,
					scaledIcon);
		}
	}
	public static void endGameWrapper() {
		endGame(_frame);
	}
	public Square get_currSquare() {
		return _currSquare;
	}

	public void set_currSquare(Square _currSquare) {
		this._currSquare = _currSquare;
	}

	public GameManager get_Manager() {
		return _Manager;
	}

	public void set_Manager(GameManager _Manager) {
		this._Manager = _Manager;
	}
	
	
	
}
