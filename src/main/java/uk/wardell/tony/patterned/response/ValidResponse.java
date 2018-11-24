package uk.wardell.tony.patterned.response;

import lombok.Data;

@Data
public class ValidResponse implements Response{

    Enum description;

    public ValidResponse(Enum description) {
        this.description = description;
    }

    public boolean isValid(){
        return true;
    }
}
