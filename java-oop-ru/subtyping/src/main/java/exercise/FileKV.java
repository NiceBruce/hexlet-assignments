package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
class FileKV implements KeyValueStorage {

    static String filePath;
    static Map<String, String> dataFromFile = new HashMap<>();
    static String cache;

    FileKV(String filePath, Map<String, String> map) {
        this.filePath = filePath;
        this.cache = Utils.serialize(map);
        Utils.writeFile(this.filePath, this.cache);
    }

    public static void writeToFile() {
        cache = Utils.serialize(dataFromFile);
        Utils.writeFile(filePath, cache);
    }

    public static void readFromFile() {
        cache = Utils.readFile(filePath);
        dataFromFile = Utils.unserialize(cache);
    }

    @Override
    public void set(String key, String value) {
        readFromFile();
        if (dataFromFile.containsKey(key)) {
            dataFromFile.replace(key, value);
        } else {
            dataFromFile.put(key, value);
        }
        writeToFile();
    }

    @Override
    public void unset(String key) {
        readFromFile();
        dataFromFile.remove(key);
        writeToFile();
    }

    @Override
    public String get(String key, String defaultValue) {
        readFromFile();
        if (dataFromFile.containsKey(key)) {
            return dataFromFile.get(key);
        }
        return defaultValue;
    }

    @Override
    public Map<String, String> toMap() {
        Map<String, String> res = new HashMap<>(dataFromFile);
        return res;
    }
}
// END
