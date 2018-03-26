package uk.wardell.tony.patterned;

public class EvaluationResponse {
    final boolean validity;
    final String description;

    public EvaluationResponse(boolean validity, String description) {
        this.validity = validity;
        this.description = description;
    }

}
