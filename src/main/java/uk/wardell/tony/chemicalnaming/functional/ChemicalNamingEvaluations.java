package uk.wardell.tony.chemicalnaming.functional;

import uk.wardell.tony.patterned.EvaluationResponse;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class ChemicalNamingEvaluations {


    //tests
    static final Predicate<CandidateName> doesSymbolContainTwoLetters() {
        return candidateName -> candidateName.symbol.length() == 2;
    }


    private static final Predicate<CandidateName> doesLetterOccurTwiceMustBeSymbolName() {
        return candidateName -> {
            for (int i = 0; i < candidateName.element.length() - 1; i++) {
                char elementUnderInspection = candidateName.element.charAt(i);
                if (candidateName.element.substring(i + 1).contains("" + elementUnderInspection)) {
                    return candidateName.symbol.equals(elementUnderInspection + "" + elementUnderInspection);
                }
            }
            return false;
        };
    }

    private final Predicate<CandidateName> areTheLettersInElementOrder() {
        return candidateName -> {
            int firstCharacterLocation = candidateName.element.indexOf(candidateName.symbol.charAt(0));
            String restOfSequence = candidateName.element.substring(firstCharacterLocation + 1);

            char secondCharOfSymbol = candidateName.symbol.charAt(1);

            if (!restOfSequence.contains("" + secondCharOfSymbol)) {
                return candidateName.element.contains("" + secondCharOfSymbol);
            }

            return true;
        };
    }

    private final Predicate<CandidateName> areAllTheLettersFromElementName() {
        return candidateName -> {
            int firstCharacterLocation = candidateName.element.indexOf(candidateName.symbol.charAt(0));
            String restOfSequence = candidateName.element.substring(firstCharacterLocation + 1);

            char secondCharOfSymbol = candidateName.symbol.charAt(1);

            if (!restOfSequence.contains("" + secondCharOfSymbol)) {
                return !candidateName.element.contains("" + secondCharOfSymbol);
            }

            return true;
        };
    }

    //responses

    public static final EvaluationResponse SYMBOL_LETTERS_NOT_IN_ORDER =
            new EvaluationResponse(false, "Symbol letters must be in same order as element");

    public static final EvaluationResponse SYMBOL_IS_NOT_TWO_LETTERS =
            new EvaluationResponse(false, "Symbol Must Contain 2 Letters");

    public static final EvaluationResponse SYMBOL_LETTERS_NOT_ALL_FROM_CHEM_NAME =
            new EvaluationResponse(false, "Symbol may only contain letters in element");

    public static final EvaluationResponse IF_LETTER_OCCURS_TWICE_MUST_BE_SYMBOL_NAME =
            new EvaluationResponse(false, "If a letter occurs twice" +
                    "then the symbol must be that letter twice");

    public static final EvaluationResponse VALID = new EvaluationResponse(true, "Fine");


    List<Function<CandidateName, EvaluationResponse>> evaluations(){
        return Arrays.asList(
                ChemicalNameEvaluation.create(areTheLettersInElementOrder(), VALID, SYMBOL_LETTERS_NOT_IN_ORDER),
                ChemicalNameEvaluation.create(doesSymbolContainTwoLetters(), VALID, SYMBOL_IS_NOT_TWO_LETTERS),
                ChemicalNameEvaluation.create(doesLetterOccurTwiceMustBeSymbolName(), VALID, IF_LETTER_OCCURS_TWICE_MUST_BE_SYMBOL_NAME),
                ChemicalNameEvaluation.create(areAllTheLettersFromElementName(), VALID, SYMBOL_LETTERS_NOT_ALL_FROM_CHEM_NAME)
        );
    }

}
