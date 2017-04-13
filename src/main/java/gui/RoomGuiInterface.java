package gui;

import java.util.Vector;

/**
 * Created by jaeyoung on 2017. 4. 13..
 */
public interface RoomGuiInterface extends PanelInterface {
    int BLACK_STONE = 0;
    int WHITE_STONE = 1;

    void drawStone(int[] stoneLocation, boolean b);
    void backOneStep(int n);
    void setGameRoomInfo(String info);
    void newGame();
    void setRoomList(Vector<String> roomList);
    GameBoardCanvas getM_board();
}
