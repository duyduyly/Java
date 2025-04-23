package java9;

import utils.LogUtils;

import java.util.List;
import java.util.Map;

public class ImmutableListSetMapAndMapEntry {

    /**
     * Oracle Corp has introduced some convenient factory methods to create Immutable List, Set, Map and Map.Entry objects.
     * These utility methods are used to create empty or non-empty Collection objects
     *
     * can read more:
     * link source: https://www.digitalocean.com/community/tutorials/java-9-features-with-examples#factory-methods-for-immutable-list-set-map-and-map-entry
     * */

    public static void main(String[] args) {

        //it is immutable because we can not assign after initial

        //Empty List
        List immutableListEmpty = List.of();
        LogUtils.log("immutableListEmpty", immutableListEmpty);

        //non-Empty List
        List immutableListNotEmpty = List.of("one","two","three");
        LogUtils.log("immutableListNotEmpty", immutableListNotEmpty);

        //empty map
        Map emptyImmutableMap = Map.of();
        LogUtils.log("emptyImmutableMap", emptyImmutableMap);

        //non-empty Map
        Map nonemptyImmutableMap = Map.of(1, "one", 2, "two", 3, "three");
        LogUtils.log("nonemptyImmutableMap", nonemptyImmutableMap);
    }
}
