package uk.wardell.tony.patterned;

public class EvaluationResponse {
    public final boolean isValid;
    public final String description;

    public static EvaluationResponse VALID = new EvaluationResponse(true, "VALID");

    public EvaluationResponse(boolean isValid, String description) {
        this.isValid = isValid;
        this.description = description;
    }

    @Override
    public String toString() {
        return "EvaluationResponse{" +
                "isValid=" + isValid +
                ", description='" + description + '\'' +
                '}';
    }
}
