package uk.wardell.tony.chemicalnaming.terse;

import uk.wardell.tony.chemicalnaming.model.CandidateName;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class CandidateNameEvaluatorStrategyTwoTest {
    private CandidateNameEvaluatorStrategyTwo candidateNameEvaluator;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        candidateNameEvaluator = new CandidateNameEvaluatorStrategyTwo();

    }

    @org.junit.jupiter.api.Test
    void evaluate0() {
        CandidateName candidateName = new CandidateName("Spenglerium","Ee");
        NamingResult namingResult = candidateNameEvaluator.evaluate(candidateName);
        assertThat(namingResult.isResult(), is(true));
    }

    @org.junit.jupiter.api.Test
    void evaluate1() {
        CandidateName candidateName = new CandidateName("Zeddemorium","Zr");
        NamingResult namingResult = candidateNameEvaluator.evaluate(candidateName);
        assertThat(namingResult.isResult(), is(true));
    }

    @org.junit.jupiter.api.Test
    void evaluate2() {
        CandidateName candidateName = new CandidateName("Venkmine","Kn");
        NamingResult namingResult = candidateNameEvaluator.evaluate(candidateName);
        assertThat(namingResult.isResult(), is(true));
    }

    @org.junit.jupiter.api.Test
    void evaluate3() {
        CandidateName candidateName = new CandidateName("Stantzon","Zt");
        NamingResult namingResult = candidateNameEvaluator.evaluate(candidateName);
        assertThat(namingResult.isResult(), is(false));
    }

    @org.junit.jupiter.api.Test
    void evaluate4() {
        CandidateName candidateName = new CandidateName("Melintzum","Nn");
        NamingResult namingResult = candidateNameEvaluator.evaluate(candidateName);
        assertThat(namingResult.isResult(), is(false));
    }

    @org.junit.jupiter.api.Test
    void evaluate5() {
        CandidateName candidateName = new CandidateName("Tullium","Ty");
        NamingResult namingResult = candidateNameEvaluator.evaluate(candidateName);
        assertThat(namingResult.isResult(), is(false));
    }
}