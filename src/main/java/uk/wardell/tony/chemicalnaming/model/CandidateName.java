package uk.wardell.tony.chemicalnaming.model;

import lombok.Data;

@Data
public class CandidateName {
    private final String element;
    private final String symbol;

    public CandidateName(String name, String symbol) {
        this.element = name;
        this.symbol = symbol;
    }
}
