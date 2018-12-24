package uk.wardell.tony.core;

import java.util.Optional;

import uk.wardell.tony.chemicalnaming.model.CandidateName;
import uk.wardell.tony.chemicalnaming.terse.NamingResult;
import uk.wardell.tony.core.strategy.logic.Finder;

class CandidateNameEvaluatorWithOptional {

    NamingResult evaluate(CandidateName candidateName) {

        String asLowerCase = candidateName.getElement().toLowerCase();
        char element1 = candidateName.getSymbol().toLowerCase().charAt(1);
        char element0 = candidateName.getSymbol().toLowerCase().charAt(0);

        Optional<Integer> found1 = Finder.find(asLowerCase, element0);
        Optional<Integer> found2 = Finder.findLast(asLowerCase, element1);

        return new NamingResult(found1.isPresent() && found2.isPresent() && found1.get() < found2.get());

    }
}
