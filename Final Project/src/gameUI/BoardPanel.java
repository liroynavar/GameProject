package gameUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class BoardPanel extends JPanel {
    private Square[][] panels;
    public static final int NUMBER_OF_ROWS =15;
    private OuterFrame _oFrame;
    
    public BoardPanel(OuterFrame oFrame) {
        super();
        _oFrame = oFrame;
        panels = new Square[NUMBER_OF_ROWS][NUMBER_OF_ROWS];
        setLayout(new GridLayout(NUMBER_OF_ROWS, NUMBER_OF_ROWS));
        setBackground(new Color(161, 121, 51));
        for (int i = 0; i < panels.length; i++) {
            for (int j = 0; j < panels[i].length; j++) {
                panels[i][j] = new Square(i,j);
                panels[i][j].setoFrame(oFrame);
                add(panels[i][j]);
            }
        }
        setPreferredSize(new Dimension(600, 600));
    }
    public void clearBoard() {
        for (int i = 0; i < panels.length; i++) {
            for (int j = 0; j < panels[i].length; j++) {
                panels[i][j].clearSquare();
            }
        }
    }
	public Square[][] getPanels() {
		return panels;
	}
	public Square getSquare(int i,int j) {
		return panels[i][j];
	}
    
}
