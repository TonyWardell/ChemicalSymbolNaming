package uk.wardell.tony.patterned;

import java.util.function.Function;
import java.util.function.Predicate;

public interface Evaluation<T,R> {

    Function<T, R> create(Predicate<T> predicate, R success, R failure);

}
