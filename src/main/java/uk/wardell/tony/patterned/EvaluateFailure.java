package uk.wardell.tony.patterned;

import uk.wardell.tony.patterned.Evaluation;

import java.util.function.Function;
import java.util.function.Predicate;

public class EvaluateFailure<T,R> implements Evaluation<T,R>{

    public Function<T, R> create(Predicate<T> predicate, R success, R failure){

        return target -> predicate.test(target) ? failure : success;
    }
}
