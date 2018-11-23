package uk.wardell.tony.chemicalnaming.functional;

import uk.wardell.tony.patterned.Evaluation;
import uk.wardell.tony.patterned.EvaluationResponse;

import java.util.function.Function;
import java.util.function.Predicate;

public class ChemicalNameEvaluation implements Evaluation {
    public static Function<CandidateName, EvaluationResponse> create(Predicate predicate,
                                                                     EvaluationResponse success,
                                                                     EvaluationResponse failure){

        return candidateName -> predicate.test(candidateName) ? success : failure;
    }
}
