package uk.wardell.tony.core.strategy;

import uk.wardell.tony.boundary.out.NamingResult;
import uk.wardell.tony.model.CandidateName;


public class CandidateNameEvaluator {

    NamingResult evaluate(CandidateName candidateName) {

        String fullNameLc = candidateName.name.toLowerCase();
        String symbolLc = candidateName.symbol.toLowerCase();

        char symChar1 = symbolLc.charAt(0);
        char symChar2 = symbolLc.charAt(1);

        int c1Location = fullNameLc.indexOf(symChar1);

        if (c1Location == fullNameLc.length() - 1) {
            return new NamingResult(false);
        }

        int c2Location = fullNameLc.indexOf(symChar2, c1Location + 1);
        boolean c1Found = c1Location > -1;
        boolean c2Found = c2Location > -1;
        boolean different = c1Location != c2Location;

        return new NamingResult(c1Found && c2Found && different);
    }
}
