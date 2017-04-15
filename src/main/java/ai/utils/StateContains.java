package ai.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

/**
 * Created by jaeyoung on 2017. 4. 16..
 */
public class StateContains {
    public static int numOfContains(ArrayList<String> strArr, String stoneSequences)
    {
        int count = 0;

        for (String str:
             strArr) {
            count += StringUtils.countMatches(str, stoneSequences);
//            System.out.println(str+" "+stoneSequences+" "+count+" "+StringUtils.countMatches(str, stoneSequences));
        }
        return count;
    }
}
