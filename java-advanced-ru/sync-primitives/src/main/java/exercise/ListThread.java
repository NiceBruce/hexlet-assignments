package exercise;

// BEGIN
class ListThread extends Thread {
    private final SafetyList list;

    public ListThread(SafetyList list) {
        this.list = list;
    }

    @Override
    public void run() {
        int cnt = 0;
        while (cnt < 1000) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            list.add(++cnt);
        }
    }
}
// END
