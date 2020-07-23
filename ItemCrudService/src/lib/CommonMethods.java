package lib;

import java.util.List;

public class CommonMethods {
    public static boolean isEmpty(Object obj) {
        return obj == null || obj.equals("");
    }
    public static boolean isEmpty(List list) {
        return list == null || list.size() == 0;
    }

}
