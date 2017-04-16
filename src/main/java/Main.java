import game.controller.GameController;

import static consts.Consts.AI_PLAYER;
import static consts.Consts.HUMAN_PLAYER;

/**
 * Created by jaeyoung on 2017. 4. 16..
 */
public class Main {
    public static void main(String[] args) {

        // 게임시작
        new GameController(AI_PLAYER,HUMAN_PLAYER);
        //new GameController(new Game(HUMAN_PLAYER,HUMAN_PLAYER));
    }
}
