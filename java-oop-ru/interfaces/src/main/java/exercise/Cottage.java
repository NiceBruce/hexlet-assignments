package exercise;

// BEGIN
public class Cottage implements Home {
    private double area;
    private int floorCount;

    Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }

    @Override
    public double getArea() {
        return this.area;
    }

    @Override
    public int compareTo(Home another) {
        if (this.getArea() == another.getArea()) {
            return 0;
        }
        return (this.getArea() > another.getArea()) ? 1 : -1;
    }

    public String toString() {
        return String.format("%s этажный коттедж площадью %s метров", this.floorCount, this.getArea());
    }
}
// END
