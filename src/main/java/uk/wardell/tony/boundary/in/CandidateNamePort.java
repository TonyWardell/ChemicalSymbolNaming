package uk.wardell.tony.boundary.in;

import uk.wardell.tony.model.CandidateName;

public interface CandidateNamePort {
    void tryName(CandidateName candidateName);
}
