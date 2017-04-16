package ai.algorithm;

/**
 * Created by jaeyoung on 2017. 4. 16..
 */
public class TimeListenerImpl implements TimeListener {
    public void onTimeLimited() {
        IterativeDeepeningSearch.onTimeLimited();
        Alphabeta.onTimeLimited();
    }
}
