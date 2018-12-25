package uk.wardell.tony.chemicalnaming.functional;

import uk.wardell.tony.chemicalnaming.model.CandidateName;
import uk.wardell.tony.patterned.response.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static uk.wardell.tony.chemicalnaming.model.NamingEvaluationResponses.*;

class ChemicalNamingEvaluatorTest {

     private final ChemicalNamingEvaluator chemicalNamingEvaluator = new ChemicalNamingEvaluator();

    @org.junit.jupiter.api.Test
    void evaluateSymbol0() {
        CandidateName candidateName = new CandidateName("Spenglerium","Ee");

        Response response = chemicalNamingEvaluator.checkValidity(candidateName);

        assertThat(response, is(VALID));
    }

    @org.junit.jupiter.api.Test
    void evaluateSymbol1() {
        CandidateName candidateName = new CandidateName("Zeddemorium","Zr");

        Response response = chemicalNamingEvaluator.checkValidity(candidateName);

        assertThat(response, is(VALID));    }

    @org.junit.jupiter.api.Test
    void evaluateSymbol2() {
        CandidateName candidateName = new CandidateName("Venkmine","Kn");

        Response response = chemicalNamingEvaluator.checkValidity(candidateName);

        assertThat(response, is(VALID));

    }

    @org.junit.jupiter.api.Test
    void evaluateSymbol3() {
        CandidateName candidateName = new CandidateName("Melintzum","M");

        Response response = chemicalNamingEvaluator.checkValidity(candidateName);

        assertThat(response, is(SYMBOL_IS_NOT_TWO_LETTERS));
    }

    @org.junit.jupiter.api.Test
    void evaluateSymbol4() {
        CandidateName candidateName = new CandidateName("Melintzum","Nn");

        Response response = chemicalNamingEvaluator.checkValidity(candidateName);

        assertThat(response, is(IF_LETTERS_IN_SYMBOL_ARE_THE_SAME_THEN_THE_NAME_SHOULD_HAVE_THOSE_LETTERS_TWICE));
    }

    @org.junit.jupiter.api.Test
    void evaluateSymbol5() {
        CandidateName candidateName = new CandidateName("Tullium","uT");

        Response response = chemicalNamingEvaluator.checkValidity(candidateName);

        assertThat(response, is(SYMBOL_LETTERS_NOT_IN_ORDER));
    }

    @org.junit.jupiter.api.Test
    void evaluateSymbol6() {
        CandidateName candidateName = new CandidateName("Tullium","tu");

        Response response = chemicalNamingEvaluator.checkValidity(candidateName);

        assertThat(response, is(FIRST_CHAR_OF_SYMBOL_NOT_UPPER_CASE));
    }

    @org.junit.jupiter.api.Test
    void evaluateSymbol7() {
        CandidateName candidateName = new CandidateName("Tullium","TU");

        Response response = chemicalNamingEvaluator.checkValidity(candidateName);

        assertThat(response, is(SECOND_CHAR_OF_SYMBOL_NOT_LOWERCASE));
    }
}
