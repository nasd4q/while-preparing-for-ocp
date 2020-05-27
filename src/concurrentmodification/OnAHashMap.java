/*
    ConcurrentModificationException is thrown when removing an entry from a HashMap while iterating on the
    HashMap's keySet() (except if doing it righteously - using iterator.remove()).

    This doesn't happen with a ConcurrentHashMap.
 */

package concurrentmodification;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class OnAHashMap {
    public static void main(String[] args) {
        Map<String, Object> foodData = new HashMap<String, Object>();
        foodData.put("penguin", 1);
        foodData.put("flamingo", 2);
        foodData.put("ape", 2);
        //for(String key: foodData.keySet()) // throws java.util.ConcurrentModificationException
        //      Exception is not thrown when only one key in the map
        //foodData.remove(key);

        Set<String> keys = foodData.keySet();

        /* for (Iterator<String> iterator = keys.iterator(); iterator.hasNext(); ) {
            String next = iterator.next(); // throws java.util.ConcurrentModificationException
                // after penguin has been removed
            System.out.println(next);
            if ("penguin".equals(next)) {
                foodData.remove(next);
                System.out.println("removed penguin :" + foodData);
            }
        } */

        //      This is the right version on a regular (non concurrent) HashMap
        // Using iterator remove allows to remove an element from the HashMap while iterating
        for (Iterator<String> iterator = keys.iterator(); iterator.hasNext(); ) {
            String next = iterator.next();
            System.out.println(next);
            if ("penguin".equals(next)) {
                iterator.remove();
                System.out.println("removed penguin :" + foodData);
            }
        }




        Map<String, Object> foodData2 = new ConcurrentHashMap<String, Object>();
        foodData2.put("penguin", 1);
        foodData2.put("flamingo", 2);
        foodData2.put("ape", 3);

        System.out.println(foodData2); // {ape=3, penguin=1, flamingo=2}

        for (String key : foodData2.keySet())
            foodData2.remove(key);

        System.out.println(foodData2); // {}


        // This too works like a charm.
        Map<String, Object> foodData3 = new ConcurrentHashMap<String, Object>();
        foodData3.put("penguin", 1);
        foodData3.put("flamingo", 2);
        foodData3.put("ape", 3);

        System.out.println(foodData3); // {ape=3, penguin=1, flamingo=2}


        for (Iterator<String> iterator = foodData3.keySet().iterator(); iterator.hasNext();)
            foodData2.remove(iterator.next());

        System.out.println(foodData2); // {}
    }
}
