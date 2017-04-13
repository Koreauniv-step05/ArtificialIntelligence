package gui;

import javax.swing.*;
import java.util.Vector;

/**
 * Created by jaeyoung on 2017. 4. 13..
 */
public interface PanelInterface extends GuiInterface {
    public void setTextToLogWindow(String str);
    public JList getJList();
    public void unShow();
    public String getInputText();
    public void setTextToInput(String string);
    public int[] getFrameSize();
}
