package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] numbers) {
        Map<String, Integer> res = new HashMap<>();

        MinThread minThread = new MinThread(numbers);
        minThread.start();
        LOGGER.info("Thread: " + minThread + " started");

        MaxThread maxThread = new MaxThread(numbers);
        maxThread.start();
        LOGGER.info("Thread: " + maxThread + " started!");

        try {
            minThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        LOGGER.info("Thread: " + minThread + " finished!");

        try {
            maxThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        LOGGER.info("Thread: " + maxThread + " finished!");

        res.put("min", minThread.getMin());
        res.put("max", maxThread.getMax());

        return res;
    }
    // END
}
