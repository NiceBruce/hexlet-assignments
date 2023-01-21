package exercise;

// BEGIN
class Circle {

    Point point;
    int radius;

    public int getRadius() {
        return radius;
    }

    public Circle(Point point, int radius) {
        this.point = point;
        this.radius = radius;
    }

    public double getSquare() throws NegativeRadiusException {

        double sqr = 3.141592 * (radius * radius);

        if (radius < 0) {
            throw new NegativeRadiusException("Не удалось посчитать площадь");
        }

        return sqr;
    }
}
// END
