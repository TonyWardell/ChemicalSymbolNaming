package uk.wardell.tony.core.strategy;


import uk.wardell.tony.boundary.out.NamingResult;
import uk.wardell.tony.model.CandidateName;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class CandidateNameEvaluatorTest {
    CandidateNameEvaluator candidateNameEvaluator;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        candidateNameEvaluator = new CandidateNameEvaluator();
    }

    @org.junit.jupiter.api.Test
    void evaluate() {

        CandidateName candidateName = new CandidateName("Spenglerium","Ee");
        NamingResult namingResult = candidateNameEvaluator.evaluate(candidateName);
        assertThat(namingResult.isResult(), is(true));

    }
}