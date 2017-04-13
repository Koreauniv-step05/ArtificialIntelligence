package gui;

import gui.stone.URLGetter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * Created by jaeyoung on 2017. 4. 13..
 */
public class GameRoomGui extends JPanel implements RoomGuiInterface {
    /*
     *  Left Panel
     */
    private JLabel m_titleLabel;
    private GameBoardCanvas m_board = new GameBoardCanvas();
    private JTextField m_input;
    private JPanel m_canvasPanel;

    /*
     * Right Panel
     */
    private JPanel m_rightPanel;
    private JTextArea m_log;
    private JList m_userList;
    private JLabel m_gamer1, m_gamer2;
    private JScrollPane m_logScrollPan;
    private JScrollBar m_vScroll;

    public GameRoomGui() {
        execute();

    }

    public void paint(Graphics g) {
        ImageIcon icon = new ImageIcon(
                URLGetter.getResource("image/gameRoomBg.jpg"));
        g.drawImage(icon.getImage(), 0, 0, null, null);
        this.paintComponents(g);
    }

    private void execute() {
        addCanvas();
    }

    private void addCanvas() {
        m_canvasPanel = new JPanel() {
            public void paint(Graphics g) {
                this.paintComponents(g);
            }
        };

        m_canvasPanel.add(m_board);
        m_canvasPanel.setLayout(null);
        m_board.setBounds(5, 5, 590, 593);

        add(m_canvasPanel);
        m_canvasPanel.setBounds(5, 5, 600, 700);
//		m_canvasPanel.setBorder(new TitledBorder("sdf"));

        setLayout(null);


    }

    public void setTextToLogWindow(String str) {
        m_log.append(str);
    }

    public JList getJList() {
        return m_userList;
    }

    public void unShow() {
        this.setVisible(false);
    }

    public String getInputText() {
        return m_input.getText();
    }


    public void setTextToInput(String string) {
        m_input.setText(string);
    }

    public void setRoomList(Vector<String> roomList) {
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Network FIve Eyes Ver. 1.0");
        Container cp = frame.getContentPane();
        cp.add(new GameRoomGui());

        frame.setSize(650, 650);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        System.out.println("Exit");
    }

    public void drawStone(int[] stoneLocation, boolean isBlack) {
        if (isBlack)
            m_board.addHistory(stoneLocation, BLACK_STONE);
        else m_board.addHistory(stoneLocation, WHITE_STONE);

    }

    public void backOneStep(int n) {
        m_board.subHistory(n);

        m_board.setVisible(false);
        m_board.setVisible(true);

        m_board.drawHistory();
    }

    public void setGameRoomInfo(String info) {
        StringTokenizer token = new StringTokenizer(info, "|");

        m_titleLabel.setText(token.nextToken());
        m_gamer1.setText(token.nextToken());
        m_gamer2.setText(token.nextToken());
    }

    public void newGame() {
        m_board.removeStones();
        m_board.lastStone = null;
        m_board.lastPoint = new int[2];

        m_board.setVisible(false);
        m_board.setVisible(true);
    }

    public int[] getFrameSize() {
        int[] size = {850, 650};
        return size;
    }

    public GameBoardCanvas getM_board() {
        return m_board;
    }
}