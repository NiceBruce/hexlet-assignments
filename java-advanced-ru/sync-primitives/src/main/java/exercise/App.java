package exercise;

class App {

    public static void main(String[] args) {
        // BEGIN
        SafetyList safetyList = new SafetyList();

        Thread threadFirst = new Thread(new ListThread(safetyList));
        Thread threadSecond = new Thread(new ListThread(safetyList));

        threadFirst.start();
        threadSecond.start();

        try {
            threadFirst.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            threadSecond.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // END
    }
}

