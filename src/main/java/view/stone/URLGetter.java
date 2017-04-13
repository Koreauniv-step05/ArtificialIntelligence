package view.stone;

import java.net.URL;

/**
 * Created by jaeyoung on 2017. 4. 13..
 */
public class URLGetter {

    private URLGetter() {}

    public static URL getResource(String filename) {
        URL url = ClassLoader.getSystemResource(filename);

        if (url == null) {
            try {
                url = new URL("file", "localhost", filename);
            } catch (Exception urlException) {} // ignore
        }
        return url;
    }
}
