package exercise;

import java.util.*;


// BEGIN
class App {
    public static LinkedHashMap<String, String> genDiff(Map<String, Object> data1, Map<String, Object> data2) {

        Map<String, String> result = new TreeMap<String, String>();

        for (var e : data2.entrySet()) {
            if (data1.containsKey(e.getKey())) {
                if (data1.get(e.getKey()).equals(e.getValue())) {
                    result.put(e.getKey(), "unchanged");
                } else {
                    result.put(e.getKey(), "changed");
                }
            } else {
                result.put(e.getKey(), "added");
            }
        }

        for (var e : data1.entrySet()) {
            if (!data2.containsKey(e.getKey())) {
                result.put(e.getKey(), "deleted");
            }
        }

        LinkedHashMap<String, String> sortedMap = new LinkedHashMap<>(result);

        return  sortedMap;
    }
}
//END
