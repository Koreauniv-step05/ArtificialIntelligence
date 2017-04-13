package gui;

import gui.stone.StoneFactory;
import gui.stone.StoneHistory;
import gui.stone.URLGetter;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by jaeyoung on 2017. 4. 13..
 */
public class GameBoardCanvas extends Canvas {

		/*
		 * Image Size is 590,593
		 */

    private static final long serialVersionUID = 1L;

    private ArrayList<StoneHistory> historyOfStone = new ArrayList<StoneHistory>();

    private Toolkit toolkit = Toolkit.getDefaultToolkit();

    private MediaTracker tracker;

    public Image image, lastStone;

    private BufferedImage bi;

    private int width, height;

    private int[] points;

    public int[] lastPoint = new int[2];

    private StoneHistory aStoneInfo;

    private boolean isRedraw = false;

    private int whatStone = 0;

    public GameBoardCanvas() {
        imageLoad();
        traker();

			/*
			 * Mouse Click! Event catch!!
			 *
			 */
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int[] point = new int[2];

                if ((16 < e.getX() && e.getX() < 555) && 20 < e.getY()
                        && e.getY() < 560) {

                    System.out.println("mouse click!");
                    point = FindCrossPoint.find(e.getX(), e.getY());

                    if(!isStoneDraw(point)){}
//                        client.sendMessage(point);

//                    public void sendMessage(int[] location) {
//                        this.data = new GameData(location, GameData.SEND_STONE_LOCATION);
//
//                        try {
//                            out.writeObject(this.data);
//                        } catch (IOException e) {
//                            System.out.println("Exception : 362라인.");
//                            e.printStackTrace();
//                        }
//                    }
                }
            }
        });
    }

    public void addHistory(int[] points, int kindOfStone) {
        aStoneInfo = new StoneHistory(points, StoneFactory.getInstance().getStone(kindOfStone));
        historyOfStone.add(aStoneInfo);

        repaint();
    }

    // Version 1.01 update
    protected boolean isStoneDraw(int[] points) {

        for (StoneHistory temp : historyOfStone) {
            if(temp.points[0] == points[0] && temp.points[1] == points[1])
                return true;
        }
        return false;
    }

    public void subHistory(int count) {
        historyOfStone.remove(historyOfStone.size()-1);

        if (count == 2) subHistory(1);
    }

    public void drawHistory() {
        isRedraw = true;
        repaint();
    }

    public void paint(Graphics g) {
        g.drawImage(bi, 0, 0, this);
//			System.out.println("paint()!");
//			update(g);
    }

    /*
     * when mouse click, stone is draw.
     */
    public void update(Graphics g) {

//			System.out.println("update()");

        drawStones(g);
    }


    /*
     * private Methid~!
     *
     */
    private void drawStones(Graphics g) {

        if (!isRedraw) {
            if (lastStone != null)
                g.drawImage(lastStone, lastPoint[0], lastPoint[1], this);

            System.out.println("add()" + historyOfStone);
            lastPoint[0] = aStoneInfo.points[0];
            lastPoint[1] = aStoneInfo.points[1];
            lastStone = aStoneInfo.getStone();

            g.drawImage(aStoneInfo.getStone(), lastPoint[0], lastPoint[1], this);

        } else {
            for (StoneHistory temp : historyOfStone) {
                g.drawImage(temp.getStone(), temp.points[0], temp.points[1], this);
                lastPoint[0] = temp.points[0];
                lastPoint[1] = temp.points[1];
            }

            isRedraw = false;
        }

        if (lastStone != null) {
            g.setColor(Color.RED);
            g.fillRect(lastPoint[0]+8, lastPoint[1]+8, 7,7);
        }
    }

    private StoneHistory getLastStone() {
        return historyOfStone.get(historyOfStone.size() - 1);
    }

    private void traker() {
        try {
            tracker = new MediaTracker(this);
            tracker.addImage(image, 0);
            tracker.waitForID(0);
            width = image.getWidth(null);
            height = image.getHeight(null);
            bi = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics gg = bi.getGraphics();
            gg.drawImage(image, 0, 0, this);
            gg.dispose();
            repaint();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void imageLoad() {
        image = toolkit.createImage(URLGetter.getResource("image/badook_board.jpg"));
    }

    public void removeStones() {
        historyOfStone = new ArrayList<StoneHistory>();
    }
}