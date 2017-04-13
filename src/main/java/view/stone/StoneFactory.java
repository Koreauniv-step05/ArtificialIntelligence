package view.stone;

import java.awt.*;

import static consts.Consts.BLACK_STONE;

/**
 * Created by jaeyoung on 2017. 4. 13..
 */
public class StoneFactory {
	/*
	 *  FlyWeight  Design Patten....
	 */

    public static StoneFactory singleton = new StoneFactory();

    private Toolkit toolkit = Toolkit.getDefaultToolkit();

    private Image blackstone = toolkit.createImage(URLGetter.getResource("image/BlackStone.gif"));
    private Image whitestone = toolkit.createImage(URLGetter.getResource("image/WhiteStone.gif"));

    private StoneFactory() {}

    public static StoneFactory getInstance() {
        return singleton;
    }

    public Image getStone(int index) {
        if (index == BLACK_STONE) {
            return blackstone;
        }
        else {
            return whitestone;
        }
    }
}
