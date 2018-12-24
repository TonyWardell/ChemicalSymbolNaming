package uk.wardell.tony.chemicalnaming.functional;

import org.junit.Test;
import uk.wardell.tony.chemicalnaming.model.CandidateName;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static uk.wardell.tony.chemicalnaming.functional.Evaluations.*;

public class EvaluationsTest {

    @Test
    public void doesSymbolContainTwoLettersIsTrue(){
        CandidateName candidateName = new CandidateName(null, "xn");

        assertThat(doesSymbolContainTwoLetters().test(candidateName), is(true));
    }

    @Test
    public void doesSymbolContainTwoLettersIsFalse(){
        CandidateName candidateName = new CandidateName(null, "sss");

        assertThat(doesSymbolContainTwoLetters().test(candidateName), is(false));
    }
}
