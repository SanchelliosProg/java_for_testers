/**
 * Created by Александр on 11.03.2017.
 */
public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getDistance(Point point){
        return calculateDistance(this, point);
    }

    public static double getDistance(Point p1, Point p2){
        return calculateDistance(p1, p2);
    }

    private static double calculateDistance(Point p1, Point p2) {
        double x = p1.getX() - p2.getX();
        double y = p1.getY() - p2.getY();
        return Math.sqrt(x*x + y*y);
    }
}
