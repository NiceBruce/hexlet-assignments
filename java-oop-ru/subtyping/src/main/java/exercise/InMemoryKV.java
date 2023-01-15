package exercise;

import java.util.Map;
import java.util.HashMap;

// BEGIN
class InMemoryKV implements KeyValueStorage {

    Map<String, String> localStorage = new HashMap<>();

    InMemoryKV (Map<String, String> map) {
        this.localStorage.putAll(map);
    }

    @Override
    public void set(String key, String value) {
        if (localStorage.containsKey(key)) {
            localStorage.replace(key, value);
        } else {
            localStorage.put(key, value);
        }
    }

    @Override
    public void unset(String key) {
        localStorage.remove(key);
    }

    @Override
    public String get(String key, String defaultValue) {
        if (localStorage.containsKey(key)) {
            return localStorage.get(key);
        }
        return defaultValue;
    }

    @Override
    public Map<String, String> toMap() {
        Map <String, String> res = new HashMap<>(localStorage);
        return res;
    }
}
// END
