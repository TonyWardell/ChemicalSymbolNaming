package uk.wardell.tony.chemicalnaming.functional;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ChemicalNamingEvaluationsTest {

    @Test
    public void doesSymbolContainTwoLettersIsTrue(){
        CandidateName candidateName = new CandidateName(null, "xn");

        assertThat(ChemicalNamingEvaluations.doesSymbolContainTwoLetters().test(candidateName), is(true));
    }

    @Test
    public void doesSymbolContainTwoLettersIsFalse(){
        CandidateName candidateName = new CandidateName(null, "sss");

        assertThat(ChemicalNamingEvaluations.doesSymbolContainTwoLetters().test(candidateName), is(false));
    }
}
