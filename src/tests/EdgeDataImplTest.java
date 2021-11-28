package tests;

import api.EdgeData;
import api.EdgeDataImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EdgeDataImplTest {

    // edge 1 //
    int src = 0, dest = 1, tag = 0;
    double weight = 1.2465;
    String info = "info_test";
    EdgeData e = new EdgeDataImpl(src,dest,tag, weight, info);

    // edge 2 //
    int src_2 = 1, dest_2 = 0, tag_2 = 2;
    double weight_2 = 0.345;
    String info_2 = "info_test2";
    EdgeData e2 = new EdgeDataImpl(src_2,dest_2,tag_2, weight_2, info_2);

    // edge 3 //
    int src_3 = 3, dest_3 = 5, tag_3 = 3;
    double weight_3 = 2.534578;
    String info_3 = "info_test3";
    EdgeData e3 = new EdgeDataImpl(src_3,dest_3,tag_3, weight_3, info_3);


    @Test
    void getSrc() {

        assertEquals(e.getSrc(),src);
        assertNotEquals(e.getSrc(),4);

        assertEquals(e2.getSrc(),src_2);
        assertNotEquals(e2.getSrc(),2);

        assertEquals(e3.getSrc(),src_3);
        assertNotEquals(e3.getSrc(),7);

    }

    @Test
    void getDest() {

        assertEquals(e.getDest(),dest);
        assertNotEquals(e.getDest(),9);

        assertEquals(e2.getDest(),dest_2);
        assertNotEquals(e2.getDest(),3);

        assertEquals(e3.getDest(),dest_3);
        assertNotEquals(e3.getDest(),11);

    }

    @Test
    void getWeight() {

        assertEquals(e.getWeight(),weight);
        assertNotEquals(e.getWeight(),1.212);

        assertEquals(e2.getWeight(),weight_2);
        assertNotEquals(e2.getWeight(),0.3456878);

        assertEquals(e3.getWeight(),weight_3);
        assertNotEquals(e3.getWeight(),1.5);

    }

    @Test
    void getInfo() {

        assertEquals(e.getInfo(),info);
        assertNotEquals(e.getInfo(),info_2);

        assertEquals(e2.getInfo(),info_2);
        assertNotEquals(e2.getInfo(),"blabla");

        assertEquals(e3.getInfo(),info_3);
        assertNotEquals(e3.getInfo(),info);

    }

    @Test
    void setInfo() {
        e.setInfo("new_info");
        assertEquals(e.getInfo(),"new_info");
        assertNotEquals(e.getInfo(),info);

        e2.setInfo("new_info_2");
        assertEquals(e2.getInfo(),"new_info_2");
        assertNotEquals(e2.getInfo(),info_2);

        e3.setInfo("new_info_3");
        assertEquals(e3.getInfo(),"new_info_3");
        assertNotEquals(e3.getInfo(),info_3);

    }

    @Test
    void getTag() {

        assertEquals(e.getTag(),tag);
        assertNotEquals(e.getTag(),4);

        assertEquals(e2.getTag(),tag_2);
        assertNotEquals(e2.getTag(),8);

        assertEquals(e3.getTag(),tag_3);
        assertNotEquals(e3.getTag(),7);

    }

    @Test
    void setTag() {

        e.setTag(4);
        assertEquals(e.getTag(),4);
        assertNotEquals(e.getTag(),tag);

        e2.setTag(6);
        assertEquals(e2.getTag(),6);
        assertNotEquals(e2.getTag(),tag_2);

        e3.setTag(7);
        assertEquals(e3.getTag(),7);
        assertNotEquals(e3.getTag(),tag_3);
    }
}