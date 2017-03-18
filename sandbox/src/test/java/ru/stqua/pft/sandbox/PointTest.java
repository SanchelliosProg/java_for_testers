package ru.stqua.pft.sandbox;

import org.testng.annotations.Test;
import path_finding.Point;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by Александр on 18.03.2017.
 */

public class PointTest {
    final double ANSWER = 44.04543109109048;
    @Test
    public void testStaticMethod(){
        Point p1 = new Point(1, 1);
        Point p2 = new Point(3, 45);
        double answer = Point.getDistance(p1, p2);
        assertThat(answer, is(ANSWER));
    }

    @Test
    public void testInsideMethod(){
        Point p1 = new Point(1, 1);
        Point p2 = new Point(3, 45);
        double answer = p1.getDistance(p2);
        assertThat(answer, is(ANSWER));
    }
}
