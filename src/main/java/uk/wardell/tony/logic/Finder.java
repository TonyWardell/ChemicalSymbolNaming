package uk.wardell.tony.logic;

import java.util.Optional;

/**
 * @author Tony Wardell
 * Date: 26/02/2018
 * Time: 09:36
 * Created with IntelliJ IDEA.
 */
public class Finder {

    public static Optional<Integer> find(String target, char lookFor){
       int location = target.indexOf(lookFor);
       return location == -1 ? Optional.empty() : Optional.of(location);
    }

    public static Optional<Integer> findLast(String target, char lookFor){
        int location = target.lastIndexOf(lookFor);
        return location == -1 ? Optional.empty() : Optional.of(location);
    }
}
