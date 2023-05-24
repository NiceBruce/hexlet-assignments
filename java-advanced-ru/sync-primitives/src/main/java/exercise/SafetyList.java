package exercise;

import java.util.ArrayList;
import java.util.List;

class SafetyList {
    // BEGIN
    private final List<Integer> container = new ArrayList<>();

    public synchronized void add(int num) {
        container.add(num);
    }

    public int get(int index) {
        return  container.get(index);
    }

    public int getSize() {
        return container.size();
    }
    // END
}
