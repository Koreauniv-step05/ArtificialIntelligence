package view;

import user.controller.UserControllerImpl;
import view.stone.StoneFactory;
import view.stone.StoneHistory;
import view.stone.URLGetter;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static consts.Consts.*;

/**
 * Created by jaeyoung on 2017. 4. 13..
 */
public class GameBoardCanvas extends Canvas {
    public interface GameBoardCanvasUIListener {
        void onNewStone(int[] ingamePoint);
    }

		/*
		 * Image Size is 590,593
		 */

    private static final long serialVersionUID = 1L;

    private ArrayList<StoneHistory> historyOfStone;

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

    private MouseAdapter mMouseAdapter;


    private UserControllerImpl mUserController;

    public GameBoardCanvas() {
        resetHistoryOfStone();
        imageLoad();
        traker();
    }

    public void setGameBoardCanvasUIListener(final GameBoardCanvasUIListener listener){
        if( mMouseAdapter == null) {
            mMouseAdapter = new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    int[] point = new int[2];

                    if ((16 < e.getX() && e.getX() < 555) && 20 < e.getY()
                            && e.getY() < 560) {


                        point = FindCrossPoint.find(e.getX(), e.getY());
                        // System.out.println("click x:"+point[0]+" y:" + point[1]);

                        int[] ingamePoint = findIngamePoint(point);
                        // System.out.println("ingame x:"+ingamePoint[0]+" y:"+ingamePoint[1]);

                        listener.onNewStone(ingamePoint);
                    }
                }
            };
        }
        addMouseListener(mMouseAdapter);
    }

    public void removeGameBoardCanvasUIListener() {
        removeMouseListener(mMouseAdapter);
    }


    public int[] findIngamePoint(int[] point) {
        int[] ingamePoint= { point[X]/29+1, point[Y]/29+1};
        return ingamePoint;
    }

    public void addHistory(int[] points, int kindOfStone) {
        aStoneInfo = new StoneHistory(points, StoneFactory.getInstance().getStone(kindOfStone));
        historyOfStone.add(aStoneInfo);

        repaint();
    }

    public void resetHistoryOfStone() {
        this.historyOfStone = new ArrayList<StoneHistory>();
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
//			// System.out.println("paint()!");
//			update(g);
    }

    /*
     * when mouse click, stone is draw.
     */
    public void update(Graphics g) {

//			// System.out.println("update()");

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

            // System.out.println("history : " + historyOfStone);
            // points = (int)((point + XSTART -10) / CELLSIZE) * CELLSIZE - 9;
            lastPoint[0] = aStoneInfo.points[0] * 29 - 29 + XSTART;
            lastPoint[1] = aStoneInfo.points[1] * 29 - 29 + YSTART;
            // // System.out.println(lastPoint[0]+" "+lastPoint[1]);
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