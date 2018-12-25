package uk.wardell.tony.patterned.response;

import lombok.Data;
import uk.wardell.tony.core.Evaluator;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class GroupedResponses<T> {

    Collection<CandidateAndResponse> passed;
    Collection<CandidateAndResponse> failed;

    public GroupedResponses(Collection<CandidateAndResponse> passed, Collection<CandidateAndResponse> failed) {
        this.passed = passed;
        this.failed = failed;
    }


    /**
     * Create an instance of GroupedResponses
     * @param target
     * @return
     */
    public GroupedResponses checkValidity(T target, Evaluator<T> evaluator) {
        Response response = evaluator.checkValidity(target);
        CandidateAndResponse candidateAndResponse = new CandidateAndResponse<>(target, response);
        if (response.isValid()) {
            ArrayList addToPassed = new ArrayList(passed);
            addToPassed.add(candidateAndResponse);
            return new GroupedResponses(addToPassed, failed);
        }
        ArrayList addToFailed = new ArrayList(failed);
        return new GroupedResponses(passed, addToFailed);
    }

//    public GroupedResponses checkValidity(Collection<T> targets, Evaluator<T> evaluator){
//        targets.stream().map(each -> checkValidity(each, evaluator))
//    }

}
