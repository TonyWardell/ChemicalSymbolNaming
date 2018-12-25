package uk.wardell.tony.core;

import uk.wardell.tony.patterned.response.Response;

public interface Evaluator<T>{

    Response checkValidity(T target);
}
