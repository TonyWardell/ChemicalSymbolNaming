package uk.wardell.tony.chemicalnaming.functional;

import uk.wardell.tony.chemicalnaming.CandidateName;
import uk.wardell.tony.patterned.response.Response;

import java.util.List;
import java.util.function.Function;

import static uk.wardell.tony.chemicalnaming.NamingEvaluationResponses.*;

class ChemicalNamingMain {

    private final List<Function<CandidateName, Response>> evaluations;

    public ChemicalNamingMain() {
        evaluations = ChemicalNamingEvaluations.evaluations();
    }

    Response evaluateSymbol(CandidateName candidateName){

        return evaluations.stream()
                .map(eval -> eval.apply(candidateName))
                .filter(resp -> !resp.isValid())
                .findFirst()
                .orElse(VALID);
    }
}
