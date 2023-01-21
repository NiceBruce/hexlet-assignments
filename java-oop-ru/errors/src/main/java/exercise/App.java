package exercise;

// BEGIN
class  App {
    public static void printSquare(Circle circle) {
        try {
            double sqrt = circle.getSquare();
            System.out.println((int) Math.ceil(sqrt));
        } catch(NegativeRadiusException e){
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Вычисление окончено");
        }
    }
}
// END
