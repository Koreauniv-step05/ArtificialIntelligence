package ai.algorithm;

import java.util.ArrayList;

/**
 * Created by jaeyoung on 2017. 4. 16..
 */
public class TimeLimiter {
    private static ArrayList<TimeListener> mTimeListeners = new ArrayList<TimeListener>();
    private static TimeLimiter mInstance;
    private static long mLimitedSecs=60;
    private static Thread curTimeLimiterThread;

    private TimeLimiter() {
    }

    public static TimeLimiter getInstance() {
        if (mInstance == null) {
            mInstance = new TimeLimiter();
        }

        return mInstance;
    }

    public static void start() {
        curTimeLimiterThread = new Thread() {

            @Override
            public void run() {
                long limitedMillis = mLimitedSecs*1000;
                long passedMillis = 0;
                long startMillis = System.currentTimeMillis();

                long displayMillis = 0;

                while(limitedMillis > passedMillis) {

                    passedMillis = System.currentTimeMillis() - startMillis;
                    try {
                        Thread.sleep(100);
                    } catch(InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (passedMillis - displayMillis > 1000) {
                        displayMillis = passedMillis;
                        System.out.println((int)(displayMillis/1000)+"초 경과");
                    }
                }

                onTimeLimited();
            }
        };
        curTimeLimiterThread.start();
    }

    public static void stop() {
        // curTimeLimiterThread.interrupt();
        curTimeLimiterThread.stop();
    }

    public static void setLimitedSecs(long limitedSecs) {
        mLimitedSecs = limitedSecs;
    }

    private static void onTimeLimited() {
        for (TimeListener listener:
                mTimeListeners) {
            listener.onTimeLimited();
        }
    }

    public static void addTimeListener(TimeListener listener) {
        mTimeListeners.add(listener);
    }
}
