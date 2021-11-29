package tests;

import api.GeoLocation;
import api.GeoLocationImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeoLocationImplTest {

    private final double EPSILON = .0001;

    // point 1//
    GeoLocation p = new GeoLocationImpl(8,5,4);

    // point 2//
    GeoLocation p2 = new GeoLocationImpl(64,32,35);

    // point 3//
    GeoLocation p3 = new GeoLocationImpl(22,12,53);

    // point 4//
    GeoLocation p4 = new GeoLocationImpl(176,26,16);

    // point 5//
    GeoLocation p5 = new GeoLocationImpl(86,49,26);

    // point 6//
    GeoLocation p6 = new GeoLocationImpl(15,63,34);


    @Test
    void x() {

        assertEquals(p.x(),8);
        assertNotEquals(p.x(),12);

        assertEquals(p2.x(),64);
        assertNotEquals(p.x(),25);

        assertEquals(p3.x(),22);
        assertNotEquals(p.x(),53);

        assertEquals(p4.x(),176);
        assertNotEquals(p.x(),125);

        assertEquals(p5.x(),86);
        assertNotEquals(p.x(),73);

        assertEquals(p6.x(),15);
        assertNotEquals(p.x(),1);
    }

    @Test
    void y() {

        assertEquals(p.y(),5);
        assertNotEquals(p.y(),12);

        assertEquals(p2.y(),32);
        assertNotEquals(p.y(),25);

        assertEquals(p3.y(),12);
        assertNotEquals(p.y(),53);

        assertEquals(p4.y(),26);
        assertNotEquals(p.y(),125.1456);

        assertEquals(p5.y(),49);
        assertNotEquals(p.y(),73.00111111);

        assertEquals(p6.y(),63);
        assertNotEquals(p.y(),62.999);

    }

    @Test
    void z() {

        assertEquals(p.z(),4);
        assertNotEquals(p.z(),3.054216);

        assertEquals(p2.z(),35);
        assertNotEquals(p.z(),23.15415);

        assertEquals(p3.z(),53);
        assertNotEquals(p.z(),343.65102);

        assertEquals(p4.z(),16);
        assertNotEquals(p.z(),15.14510);

        assertEquals(p5.z(),26);
        assertNotEquals(p.z(),26.6654);

        assertEquals(p6.z(),34);
        assertNotEquals(p.z(),34.01);

    }
    /**
     * This tester following the distance formula for 3D points: sqrt((x2 - x1)^2 + (y2 - y1)^2 + (z2 - z1)^2).
     */
    @Test
    void distance() {

        // distance of p1 to p6:
        double d = p.distance(p6);
        assertEquals(d,65.6734,EPSILON);

        // distance of p1 to p1:
        d = p.distance(p);
        assertEquals(d,0.0,EPSILON);

        // distance of p2 to p3:
        d = p2.distance(p3);
        assertEquals(d,49.8798,EPSILON);

        // distance of p5 to p4:
        d = p5.distance(p4);
        assertEquals(d,93.4291,EPSILON);

        // distance of p2 to p4:
        d = p2.distance(p4);
        assertEquals(d,113.7585,EPSILON);

        // distance of p6 to p3:
        d = p6.distance(p3);
        assertEquals(d,54.8725,EPSILON);

        // other tests:
        assertEquals(p6.distance(p3),p3.distance(p6));
        assertEquals(p.distance(p6),p6.distance(p));
        assertEquals(p2.distance(p5),p5.distance(p2));
        assertEquals(p2.distance(p),p.distance(p2));
        assertEquals(p4.distance(p3),p3.distance(p4));

    }
}