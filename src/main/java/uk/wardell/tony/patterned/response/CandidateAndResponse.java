package uk.wardell.tony.patterned.response;

import lombok.Data;

@Data
class CandidateAndResponse<T> {

    T target;
    Response response;

    CandidateAndResponse(T target, Response response) {
        this.target = target;
        this.response = response;
    }
}
