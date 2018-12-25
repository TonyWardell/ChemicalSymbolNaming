package uk.wardell.tony.patterned.response;

import lombok.Data;

@Data
public class CandidateAndResponse<T> {

    T target;
    Response response;

    public CandidateAndResponse(T target, Response response) {
        this.target = target;
        this.response = response;
    }
}
