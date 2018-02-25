package uk.wardell.tony.core.strategy;

import uk.wardell.tony.boundary.out.NamingResult;
import uk.wardell.tony.model.CandidateName;


public class CandidateNameEvaluator {

    NamingResult evaluate(CandidateName candidateName) {

        String fullNameLc = candidateName.name.toLowerCase();
        String symbolLc = candidateName.symbol.toLowerCase();

        char symChar1 = symbolLc.charAt(0);
        char symChar2 = symbolLc.charAt(1);


        int c1 = fullNameLc.indexOf(symChar1);

        if (c1 == fullNameLc.length() - 1) {
            return new NamingResult(false);
        }

        int c2 = fullNameLc.indexOf(symChar2, c1 + 1);
        boolean c1b = c1 > -1;
        boolean c2b = c2 > -1;
        boolean different = c1 != c2;

        return new NamingResult(c1b && c2b && different);
    }
}
