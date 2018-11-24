package uk.wardell.tony.core;

import java.util.Optional;

import uk.wardell.tony.chemicalnaming.CandidateName;
import uk.wardell.tony.chemicalnaming.terse.NamingResult;
import uk.wardell.tony.core.strategy.logic.Finder;

public class CandidateNameEvaluatorWithOptional {

    NamingResult evaluate(CandidateName candidateName) {

        Optional<Integer> found1 = Finder.find(candidateName.getElement().toLowerCase(), candidateName.getSymbol().toLowerCase().charAt(0));
        Optional<Integer> found2 =
                Finder.findLast(candidateName.getElement().toLowerCase(), candidateName.getSymbol().toLowerCase().charAt(1));

        return new NamingResult(found1.isPresent() && found2.isPresent() && found1.get() < found2.get());

    }
}
