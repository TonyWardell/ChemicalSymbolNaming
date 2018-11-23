package uk.wardell.tony.chemicalnaming.functional;

import uk.wardell.tony.patterned.EvaluationResponse;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static uk.wardell.tony.chemicalnaming.functional.ChemicalNamingEvaluations.*;

public class ChemicalNamingMainTest {

    ChemicalNamingMain main = new ChemicalNamingMain();

    @org.junit.jupiter.api.Test
    void evaluateSymbol0() {
        CandidateName candidateName = new CandidateName("Spenglerium","Ee");

        EvaluationResponse evaluationResponse = main.evaluateSymbol(candidateName);

        assertThat(evaluationResponse.description, is("VALID"));
        assertThat(evaluationResponse.isValid, is(true));
    }

    @org.junit.jupiter.api.Test
    void evaluateSymbol1() {
        CandidateName candidateName = new CandidateName("Zeddemorium","Zr");

        EvaluationResponse evaluationResponse = main.evaluateSymbol(candidateName);

        assertThat(evaluationResponse.description, is("VALID"));
        assertThat(evaluationResponse.isValid, is(true));
    }

    @org.junit.jupiter.api.Test
    void evaluateSymbol2() {
        CandidateName candidateName = new CandidateName("Venkmine","Kn");

        EvaluationResponse evaluationResponse = main.evaluateSymbol(candidateName);

        assertThat(evaluationResponse, is(EvaluationResponse.VALID));

    }

    @org.junit.jupiter.api.Test
    void evaluateSymbol3() {
        CandidateName candidateName = new CandidateName("Stantzon","Zt");

        EvaluationResponse evaluationResponse = main.evaluateSymbol(candidateName);

        assertThat(evaluationResponse, is(SYMBOL_LETTERS_NOT_IN_ORDER));

    }

    @org.junit.jupiter.api.Test
    void evaluateSymbol4() {
        CandidateName candidateName = new CandidateName("Melintzum","Nn");

        EvaluationResponse evaluationResponse = main.evaluateSymbol(candidateName);

        assertThat(evaluationResponse, is(IF_LETTERS_IN_SYMBOL_ARE_THE_SAME_THEN_THE_NAME_SHOULD_HAVE_THOSE_LETTERS_TWICE));
    }

    @org.junit.jupiter.api.Test
    void evaluateSymbol5() {
        CandidateName candidateName = new CandidateName("Tullium","Ty");

        EvaluationResponse evaluationResponse = main.evaluateSymbol(candidateName);

        assertThat(evaluationResponse, is(SYMBOL_LETTERS_NOT_IN_ORDER));
    }

    @org.junit.jupiter.api.Test
    void evaluateSymbol6() {
        CandidateName candidateName = new CandidateName("Tullium","tu");

        EvaluationResponse evaluationResponse = main.evaluateSymbol(candidateName);

        assertThat(evaluationResponse, is(FIRST_CHAR_OF_SYMBOL_NOT_UPPER_CASE));
    }

    @org.junit.jupiter.api.Test
    void evaluateSymbol7() {
        CandidateName candidateName = new CandidateName("Tullium","TU");

        EvaluationResponse evaluationResponse = main.evaluateSymbol(candidateName);

        assertThat(evaluationResponse, is(SECOND_CHAR_OF_SYMBOL_NOT_LOWERCASE));
    }
}
