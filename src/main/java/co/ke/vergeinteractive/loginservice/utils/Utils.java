package co.ke.vergeinteractive.loginservice.utils;

import java.util.HashSet;
import java.util.Set;

public class Utils {

    public static <T> Set<T> createSet (T... items) {
        Set<T> genSet = new HashSet<>();
        for (T item : items) {
            try {
                genSet.add(item);
            } catch (Exception e) {} // ignore repetitions
        }
        return genSet;
    }
}
