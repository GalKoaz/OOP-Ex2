package tests;

import api.GeoLocation;
import api.GeoLocationImpl;
import api.NodeData;
import api.NodeDataImpl;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class NodeDataImplTest {

    // vertex 1 //
    int node_id = 0, tag = 2;
    String info = "info_v";
    double weight = 1.42321;
    GeoLocation p = new GeoLocationImpl(8,5,4);
    NodeData v = new NodeDataImpl(node_id,tag,info, weight,p);

    // vertex 2 //
    int node_id_2 = 1, tag_2 = 14;
    String info_2 = "info_v2";
    double weight_2 = 5.112;
    GeoLocation p2 = new GeoLocationImpl(64,32,35);
    NodeData v2 = new NodeDataImpl(node_id_2,tag_2,info_2, weight_2,p2);

    // vertex 3 //
    int node_id_3 = 2, tag_3 = 23;
    String info_3 = "info_v3";
    double weight_3 = 2.45321;
    GeoLocation p3 = new GeoLocationImpl(22,12,53);
    NodeData v3 = new NodeDataImpl(node_id_3,tag_3,info_3, weight_3,p3);

    @Test
    void getKey() {
        assertEquals(v.getKey(),node_id);
        assertNotEquals(v.getKey(),4);

        assertEquals(v2.getKey(),node_id_2);
        assertNotEquals(v2.getKey(),9);

        assertEquals(v3.getKey(),node_id_3);
        assertNotEquals(v3.getKey(),7);

    }

    @Test
    void getLocation() {

        assertEquals(v.getLocation().x(),8);
        assertEquals(v.getLocation().y(),5);
        assertEquals(v.getLocation().z(),4);


        assertEquals(v2.getLocation().x(),64);
        assertEquals(v2.getLocation().y(),32);
        assertEquals(v2.getLocation().z(),35);


        assertEquals(v3.getLocation().x(),22);
        assertEquals(v3.getLocation().y(),12);
        assertEquals(v3.getLocation().z(),53);
    }

    /**
     * Checking if this is a deep copy.
     */
    @Test
    void setLocation() {
        // Checks to see if the nodes' locations are a deep copy of the new locations.
        GeoLocation p = new GeoLocationImpl(1,2,3);
        v.setLocation(p);
        assertNotEquals(v.getLocation(),p); //should be different because it is a deep copy.

        GeoLocation p2 = new GeoLocationImpl(43,23,36);
        v2.setLocation(p2);
        assertNotEquals(v2.getLocation(),p2); //should be different because it is a deep copy.

        GeoLocation p3 = new GeoLocationImpl(1,2,3);
        v3.setLocation(p3);
        assertNotEquals(v3.getLocation(),p3); //should be different because it is a deep copy.

        //checks to see if it has changed a's 3D point coordinate.
        assertEquals(p.x(),v.getLocation().x());
        assertEquals(p.y(),v.getLocation().y());
        assertEquals(p.z(),v.getLocation().z());

        assertEquals(p2.x(),v2.getLocation().x());
        assertEquals(p2.y(),v2.getLocation().y());
        assertEquals(p2.z(),v2.getLocation().z());

        assertEquals(p3.x(),v3.getLocation().x());
        assertEquals(p3.y(),v3.getLocation().y());
        assertEquals(p3.z(),v3.getLocation().z());
    }

    @Test
    void getWeight() {
        assertEquals(v.getWeight(),weight);
        assertNotEquals(v.getWeight(),1.212);

        assertEquals(v2.getWeight(),weight_2);
        assertNotEquals(v2.getWeight(),0.3456878);

        assertEquals(v3.getWeight(),weight_3);
        assertNotEquals(v3.getWeight(),1.5);
    }

    @Test
    void setWeight() {
        v.setWeight(1.999);
        assertEquals(v.getWeight(),1.999);
        assertNotEquals(v.getWeight(),weight);


        v2.setWeight(4.45);
        assertEquals(v2.getWeight(),4.45);
        assertNotEquals(v2.getWeight(),weight_2);


        v3.setWeight(2.16651);
        assertEquals(v3.getWeight(),2.16651);
        assertNotEquals(v3.getWeight(),weight_3);
    }

    @Test
    void getInfo() {


        assertEquals(v.getInfo(),info);
        assertNotEquals(v.getInfo(),info_2);

        assertEquals(v2.getInfo(),info_2);
        assertNotEquals(v2.getInfo(),"bla--bla");

        assertEquals(v3.getInfo(),info_3);
        assertNotEquals(v3.getInfo(),info);
    }

    @Test
    void setInfo() {
        v.setInfo("new_info_v");
        assertEquals(v.getInfo(),"new_info_v");
        assertNotEquals(v.getInfo(),info);

        v2.setInfo("new_info_v2");
        assertEquals(v2.getInfo(),"new_info_v2");
        assertNotEquals(v2.getInfo(),info_2);

        v3.setInfo("new_info_v3");
        assertEquals(v3.getInfo(),"new_info_v3");
        assertNotEquals(v3.getInfo(),info_3);
    }

    @Test
    void getTag() {
        assertEquals(v.getTag(),tag);
        assertNotEquals(v.getTag(),3);

        assertEquals(v2.getTag(),tag_2);
        assertNotEquals(v2.getTag(),16);

        assertEquals(v3.getTag(),tag_3);
        assertNotEquals(v3.getTag(),35);
    }

    @Test
    void setTag() {
        v.setTag(93);
        assertEquals(v.getTag(),93);
        assertNotEquals(v.getTag(),tag);

        v2.setTag(77);
        assertEquals(v2.getTag(),77);
        assertNotEquals(v2.getTag(),tag_2);

        v3.setTag(11);
        assertEquals(v3.getTag(),11);
        assertNotEquals(v3.getTag(),tag_3);
    }
}