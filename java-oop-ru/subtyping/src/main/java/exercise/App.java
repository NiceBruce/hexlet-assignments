package exercise;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

// BEGIN
class App {
    public static void swapKeyValue(KeyValueStorage kv) {
        Set<Entry<String, String>> s = kv.toMap().entrySet();
        for (Entry<String, String> e: s) {
            kv.unset(e.getKey());
            kv.set(e.getValue(), e.getKey());
        }
    }

    public static void main(String[] args) {
        KeyValueStorage kv = new InMemoryKV(Map.of("key1", "value1", "key2", "value2"));

        swapKeyValue(kv);
    }
}
// END
