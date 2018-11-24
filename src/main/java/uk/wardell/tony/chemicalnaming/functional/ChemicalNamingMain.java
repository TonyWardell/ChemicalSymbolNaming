package uk.wardell.tony.chemicalnaming.functional;

import uk.wardell.tony.patterned.EvaluationResponse;

import java.util.List;
import java.util.function.Function;

class ChemicalNamingMain {

    List<Function<CandidateName, EvaluationResponse>> evaluations;

    public ChemicalNamingMain() {
        evaluations = ChemicalNamingEvaluations.evaluations();
    }

    EvaluationResponse evaluateSymbol(CandidateName candidateName){

        return evaluations.stream()
                .map(eval -> eval.apply(candidateName))
                .filter(resp -> !resp.isValid)
                .findFirst()
                .orElse(EvaluationResponse.VALID);
    }
}
