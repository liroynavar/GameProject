package gameUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

public class StartPageFrame extends JFrame {
    private JLabel _imageLabel; 
    private JLabel _textLabel;
    private JButton _pvpButton;
    private JButton _pvcButton;
    private JButton _rulesButton;

    public StartPageFrame() {
        super("Start Page Gomoku");

        // Create a panel with FlowLayout for side-by-side buttons
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));

        // Load the GIF image
        ImageIcon helloGif = new ImageIcon("C:\\Users\\97250\\OneDrive - ORT365Schools\\YodGimal\\פרוייקט\\Project\\Img\\hello.gif");
        _imageLabel = new JLabel(helloGif);

        _textLabel = new JLabel("\tWelcome to Gomoku!");
        _textLabel.setFont(new Font("Arial", Font.BOLD, 20));
        _textLabel.setBackground(new Color(230, 230, 230));
        _textLabel.setOpaque(true);
        _textLabel.setHorizontalAlignment(SwingConstants.CENTER);


        // Style the buttons
        _pvpButton = new JButton("Player VS Player");
        styleButton(_pvpButton);
        _pvpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OuterFrame oFrame = new OuterFrame();
                dispose();
            }
        });
        buttonsPanel.add(_pvpButton);

        _pvcButton = new JButton("Player VS Computer");
        styleButton(_pvcButton);
        _pvcButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OuterFrame oFrame = new OuterFrame();
                dispose();
            }
        });
        buttonsPanel.add(_pvcButton);
        
        _rulesButton = new JButton("Rules");
        styleButton(_rulesButton);
        _rulesButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		RulesFrame rFrame = new RulesFrame();
        		dispose();
        	}
        });
        buttonsPanel.add(_rulesButton);

        // Set wooden background for buttons panel
        buttonsPanel.setBackground(new Color(139, 69, 19));

        // Add components to the frame using BorderLayout
        add(_imageLabel, BorderLayout.WEST); // Add the image to the left
        add(_textLabel, BorderLayout.NORTH);
        add(buttonsPanel, BorderLayout.CENTER);

        // Set frame properties
        setSize(800, 400); // Adjusted width to accommodate the image
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Method to style the buttons
    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setBackground(new Color(0, 153, 51));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
    }

}
