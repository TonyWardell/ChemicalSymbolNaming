package uk.wardell.tony.core.strategy;

import java.util.Optional;

import uk.wardell.tony.chemicalnaming.terse.CandidateName;
import uk.wardell.tony.chemicalnaming.terse.NamingResult;
import uk.wardell.tony.logic.Finder;

public class CandidateNameEvaluatorWithOptional {

    NamingResult evaluate(CandidateName candidateName) {

        Optional<Integer> found1 = Finder.find(candidateName.name.toLowerCase(), candidateName.symbol.toLowerCase().charAt(0));
        Optional<Integer> found2 =
                Finder.findLast(candidateName.name.toLowerCase(), candidateName.symbol.toLowerCase().charAt(1));

        return new NamingResult(found1.isPresent() && found2.isPresent() && found1.get() < found2.get());

    }
}
