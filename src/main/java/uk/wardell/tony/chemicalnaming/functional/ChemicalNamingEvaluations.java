package uk.wardell.tony.chemicalnaming.functional;

import uk.wardell.tony.patterned.EvaluationResponse;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static uk.wardell.tony.patterned.EvaluationResponse.VALID;

public class ChemicalNamingEvaluations {

    //tests
    static final Predicate<CandidateName> doesSymbolContainTwoLetters() {
        return candidateName -> candidateName.symbol.length() == 2;
    }

    private static Predicate<CandidateName> ifSymbolNameIsTwoOfTheSameLetterThenNameShouldHaveThatLetterTwice() {
        return candidateName -> {
            char c0 = candidateName.symbol.charAt(0);
            char c1 = candidateName.symbol.charAt(1);
            char low0 = Character.toLowerCase(c0);
            char low1 = Character.toLowerCase(c1);
            if(low0 != low1) return true;

            //Characters are the same
            String lower = candidateName.element.toLowerCase();
            int loc0 = lower.indexOf(low0);
            int loc1 = lower.lastIndexOf(low1);
            return loc0 != loc1;
        };
    }

    private static Predicate<CandidateName> areBothSymbolLettersInTheElementName() {
        return candidateName -> {
            char c1 = candidateName.symbol.toLowerCase().charAt(0);
            char c2 = candidateName.symbol.toLowerCase().charAt(1);
            return (candidateName.element.toLowerCase().contains("" + c1) && candidateName
                    .element.toLowerCase().contains(("" + c2)));
        };
    }

    private static Predicate<CandidateName> areTheLettersInElementOrder() {
        return candidateName -> {
            int firstCharacterLocation = candidateName.element.indexOf(candidateName.symbol.toLowerCase().charAt(0));
            String restOfSequence = candidateName.element.substring(firstCharacterLocation + 1);

            char secondCharOfSymbol = candidateName.symbol.toLowerCase().charAt(1);

            return restOfSequence.contains("" + secondCharOfSymbol);
        };
    }

    private static Predicate<CandidateName> areAllTheLettersFromElementName() {
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

    private static Predicate<CandidateName> isFirstCharacterInUpperCase(){
        return candidateName -> {
            char c0 = candidateName.symbol.charAt(0);
            return Character.toUpperCase(c0) == c0;
        };
    }

    private static Predicate<CandidateName> isSecondCharacterInLowerCase(){
        return candidateName -> {
            char c1 = candidateName.symbol.charAt(1);
            return Character.toLowerCase(c1) == c1;
        };
    }

    //responses

    public static final EvaluationResponse SYMBOL_LETTERS_NOT_IN_ORDER =
            new EvaluationResponse(false, "Symbol letters must be in same order as element");

    public static final EvaluationResponse SYMBOL_LETTERS_ARE_MISSING_FROM_THE_NAME =
            new EvaluationResponse(false, "Symbol letters are missing from the name");

    public static final EvaluationResponse SYMBOL_IS_NOT_TWO_LETTERS =
            new EvaluationResponse(false, "Symbol Must Contain 2 Letters");

    public static final EvaluationResponse SYMBOL_LETTERS_NOT_ALL_FROM_CHEM_NAME =
            new EvaluationResponse(false, "Symbol may only contain letters in element");

    public static final EvaluationResponse IF_LETTERS_IN_SYMBOL_ARE_THE_SAME_THEN_THE_NAME_SHOULD_HAVE_THOSE_LETTERS_TWICE =
            new EvaluationResponse(false, "If a letter occurs twice " +
                    "then the symbol must be that letter twice");

    public static final EvaluationResponse FIRST_CHAR_OF_SYMBOL_NOT_UPPER_CASE =
            new EvaluationResponse(false, "First symbol character must be upper case");

    public static final EvaluationResponse SECOND_CHAR_OF_SYMBOL_NOT_LOWERCASE =
            new EvaluationResponse(false, "Second symbol character must be lower case");

    static List<Function<CandidateName, EvaluationResponse>> evaluations(){
        return Arrays.asList(
                ChemicalNameEvaluation.create(ifSymbolNameIsTwoOfTheSameLetterThenNameShouldHaveThatLetterTwice(), VALID, IF_LETTERS_IN_SYMBOL_ARE_THE_SAME_THEN_THE_NAME_SHOULD_HAVE_THOSE_LETTERS_TWICE),
                ChemicalNameEvaluation.create(areTheLettersInElementOrder(), VALID, SYMBOL_LETTERS_NOT_IN_ORDER),
                ChemicalNameEvaluation.create(areBothSymbolLettersInTheElementName(), VALID, SYMBOL_LETTERS_ARE_MISSING_FROM_THE_NAME),
                ChemicalNameEvaluation.create(doesSymbolContainTwoLetters(), VALID, SYMBOL_IS_NOT_TWO_LETTERS),
                ChemicalNameEvaluation.create(areAllTheLettersFromElementName(), VALID, SYMBOL_LETTERS_NOT_ALL_FROM_CHEM_NAME),
                ChemicalNameEvaluation.create(isFirstCharacterInUpperCase(), VALID, FIRST_CHAR_OF_SYMBOL_NOT_UPPER_CASE),
                ChemicalNameEvaluation.create(isSecondCharacterInLowerCase(), VALID, SECOND_CHAR_OF_SYMBOL_NOT_LOWERCASE)
        );
    }

}
