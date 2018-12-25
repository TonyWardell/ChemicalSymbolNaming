package uk.wardell.tony.patterned.response;

import lombok.Data;
import uk.wardell.tony.core.Evaluator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class GroupedResponses<T> {

    Collection<CandidateAndResponse> passed;
    Collection<CandidateAndResponse> failed;

    private GroupedResponses(Collection<CandidateAndResponse> passed, Collection<CandidateAndResponse> failed) {
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
            List<CandidateAndResponse> addToPassed = new ArrayList<>(passed);
            addToPassed.add(candidateAndResponse);
            return new GroupedResponses(addToPassed, failed);
        }
        List<CandidateAndResponse> addToFailed = new ArrayList<>(failed);
        return new GroupedResponses(passed, addToFailed);
    }

//    public GroupedResponses checkValidity(Collection<T> targets, Evaluator<T> evaluator){
//        targets.stream().map(each -> checkValidity(each, evaluator))
//    }

}
