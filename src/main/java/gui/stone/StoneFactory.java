package gui.stone;

import java.awt.*;

/**
 * Created by jaeyoung on 2017. 4. 13..
 */
public class StoneFactory {
	/*
	 *  FlyWeight  Design Patten....
	 */

    public static StoneFactory singleton = new StoneFactory();

    private Toolkit toolkit = Toolkit.getDefaultToolkit();

    private Image[] stones = { toolkit.createImage(URLGetter.getResource("image/BlackStone.gif")),
            toolkit.createImage(URLGetter.getResource("image/WhiteStone.gif"))	};

    private StoneFactory() {}

    public static StoneFactory getInstance() {
        return singleton;
    }

    public Image getStone(int index) {
        return stones[index];
    }
}
