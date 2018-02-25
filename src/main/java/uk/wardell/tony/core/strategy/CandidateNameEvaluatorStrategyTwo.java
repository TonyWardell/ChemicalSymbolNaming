package uk.wardell.tony.core.strategy;

import uk.wardell.tony.boundary.out.NamingResult;
import uk.wardell.tony.model.CandidateName;

public class CandidateNameEvaluatorStrategyTwo {

    NamingResult evaluate(CandidateName candidateName) {

        String lcName = candidateName.name.toLowerCase();
        String symbolLc = candidateName.symbol.toLowerCase();

        int j = lcName.indexOf(symbolLc.charAt(0));
        int k = lcName.lastIndexOf(symbolLc.charAt(1));

        return new NamingResult(j != -1 && k != -1 && j<k);
    }
}
