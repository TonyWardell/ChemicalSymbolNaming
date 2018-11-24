package uk.wardell.tony.chemicalnaming.terse;


import uk.wardell.tony.chemicalnaming.CandidateName;

public class CandidateNameEvaluatorStrategyTwo {

    NamingResult evaluate(CandidateName candidateName) {

        String lcName = candidateName.getElement().toLowerCase();
        String symbolLc = candidateName.getSymbol().toLowerCase();

        int j = lcName.indexOf(symbolLc.charAt(0));
        int k = lcName.lastIndexOf(symbolLc.charAt(1));

        return new NamingResult(j != -1 && k != -1 && j<k);
    }
}
