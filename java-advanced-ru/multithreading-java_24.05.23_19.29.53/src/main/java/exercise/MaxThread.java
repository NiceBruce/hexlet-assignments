package exercise;

// BEGIN
class MaxThread extends Thread {
    private final int[] numbers;
    private int max;

    public MaxThread(int[] numbers) {
        this.numbers = numbers;
    }

    public int getMax() {
        return max;
    }

    @Override
    public void run() {
        for (int num : numbers) {
            max = Math.max(num, max);
        }
    }
}
// END
