package uk.wardell.tony.patterned.response;

import lombok.Data;

@Data
public class InvalidResponse implements Response{

    Enum description;

    public InvalidResponse(Enum description) {
        this.description = description;
    }

    public boolean isValid(){
        return false;
    }
}
