public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        Point p1 = new Point(23, 56);
        Point p2 = new Point(8, 87);
        System.out.println(String.valueOf(calculateDistanceBetween(p1, p2)));
    }

    private static double calculateDistanceBetween(Point a, Point b){
        double x = b.getX() - a.getX();
        double y = b.getY() - a.getY();
        return Math.sqrt(x*x + y*y);
    }
}