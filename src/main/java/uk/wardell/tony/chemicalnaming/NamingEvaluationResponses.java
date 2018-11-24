package uk.wardell.tony.chemicalnaming;

import uk.wardell.tony.patterned.response.InvalidResponse;
import uk.wardell.tony.patterned.response.Response;
import uk.wardell.tony.patterned.response.ValidResponse;

import static uk.wardell.tony.chemicalnaming.NamingEvaluationResponses.ResponseValue.PASSED;

public class NamingEvaluationResponses {

    public static final Response VALID = new ValidResponse(PASSED);

    public static final Response SYMBOL_LETTERS_NOT_IN_ORDER = new InvalidResponse(ResponseValue.NOT_IN_ORDER);

    public static final Response SYMBOL_LETTERS_ARE_MISSING_FROM_THE_NAME =
            new InvalidResponse(ResponseValue.LETTERS_ARE_MISSING);

    public static final Response SYMBOL_IS_NOT_TWO_LETTERS =
            new InvalidResponse(ResponseValue.NOT_TWO_LETTERS);

    public static final Response IF_LETTERS_IN_SYMBOL_ARE_THE_SAME_THEN_THE_NAME_SHOULD_HAVE_THOSE_LETTERS_TWICE =
            new InvalidResponse(ResponseValue.TWICE_CHECK_FAILED);

    public static final Response FIRST_CHAR_OF_SYMBOL_NOT_UPPER_CASE =
            new InvalidResponse(ResponseValue.FIRST_NOT_UPPER_CASE);

    public static final Response SECOND_CHAR_OF_SYMBOL_NOT_LOWERCASE =
            new InvalidResponse(ResponseValue.SECOND_NOT_LOWERCASE);

    enum ResponseValue {
        PASSED("Symbol passes criteria"),
        NOT_TWO_LETTERS("Symbol Must Contain 2 Letters"),
        LETTERS_ARE_MISSING("Symbol letters are missing from the name"),
        TWICE_CHECK_FAILED("If a letter occurs twice then the symbol must be that letter twice"),
        NOT_IN_ORDER("Symbol letters must be in same order as element"),
        FIRST_NOT_UPPER_CASE("First symbol character must be upper case"),
        SECOND_NOT_LOWERCASE("Second symbol character must be lower case");

        private final String description;

        ResponseValue(String s) {
            description = s;
        }

        public String getDescription() {
            return description;
        }
    }

    /**
     * A test for java 12 switch statements
     */

//    public boolean isWhat(ResponseValue responseValue){
//        switch (responseValue) {
//            case PASSED -> true;
//            case NOT_TWO_LETTERS -> false;
//        }
//    };
}
