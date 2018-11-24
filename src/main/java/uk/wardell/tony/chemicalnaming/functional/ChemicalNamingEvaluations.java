package uk.wardell.tony.chemicalnaming.functional;

import uk.wardell.tony.patterned.Evaluation;
import uk.wardell.tony.patterned.SimpleEvaluation;
import uk.wardell.tony.patterned.response.Response;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static uk.wardell.tony.chemicalnaming.NamingEvaluationResponses.*;

public class ChemicalNamingEvaluations {

    public static Predicate<CandidateName> doesSymbolContainTwoLetters() {
        return candidateName -> candidateName.getSymbol().length() == 2;
    }

    private static Predicate<CandidateName> ifSymbolNameIsTwoOfTheSameLetterThenNameShouldHaveThatLetterTwice() {
        return candidateName -> {
            char c0 = candidateName.getSymbol().charAt(0);
            char c1 = candidateName.getSymbol().charAt(1);
            char low0 = Character.toLowerCase(c0);
            char low1 = Character.toLowerCase(c1);
            if(low0 != low1) return true;

            //Characters are the same
            String lower = candidateName.getElement().toLowerCase();
            int loc0 = lower.indexOf(low0);
            int loc1 = lower.lastIndexOf(low1);
            return loc0 != loc1;
        };
    }

    private static Predicate<CandidateName> areBothSymbolLettersInTheElementName() {
        return candidateName -> {
            char c1 = candidateName.getSymbol().toLowerCase().charAt(0);
            char c2 = candidateName.getSymbol().toLowerCase().charAt(1);
            return (candidateName.getElement().toLowerCase().contains("" + c1) && candidateName
                    .getElement().toLowerCase().contains(("" + c2)));
        };
    }

    private static Predicate<CandidateName> areTheLettersInElementOrder() {
        return candidateName -> {
            int firstCharacterLocation = candidateName.getElement().indexOf(candidateName.getSymbol().toLowerCase().charAt(0));
            String restOfSequence = candidateName.getElement().substring(firstCharacterLocation + 1);

            char secondCharOfSymbol = candidateName.getSymbol().toLowerCase().charAt(1);

            return restOfSequence.contains("" + secondCharOfSymbol);
        };
    }

    private static Predicate<CandidateName> areAllTheLettersFromElementName() {
        return candidateName -> {
            int firstCharacterLocation = candidateName.getElement().indexOf(candidateName.getSymbol().charAt(0));
            String restOfSequence = candidateName.getElement().substring(firstCharacterLocation + 1);

            char secondCharOfSymbol = candidateName.getSymbol().charAt(1);

            if (!restOfSequence.contains("" + secondCharOfSymbol)) {
                return !candidateName.getElement().contains("" + secondCharOfSymbol);
            }

            return true;
        };
    }

    private static Predicate<CandidateName> isFirstCharacterInUpperCase(){
        return candidateName -> {
            char c0 = candidateName.getSymbol().charAt(0);
            return Character.toUpperCase(c0) == c0;
        };
    }

    private static Predicate<CandidateName> isSecondCharacterInLowerCase(){
        return candidateName -> {
            char c1 = candidateName.getSymbol().charAt(1);
            return Character.toLowerCase(c1) == c1;
        };
    }


    static List<Function<CandidateName, Response>> evaluations(){
        Evaluation<CandidateName, Response> simpleEvaluation = new SimpleEvaluation<>();
        return Arrays.asList(
                simpleEvaluation.create(ifSymbolNameIsTwoOfTheSameLetterThenNameShouldHaveThatLetterTwice(), VALID, IF_LETTERS_IN_SYMBOL_ARE_THE_SAME_THEN_THE_NAME_SHOULD_HAVE_THOSE_LETTERS_TWICE),
                simpleEvaluation.create(areTheLettersInElementOrder(), VALID, SYMBOL_LETTERS_NOT_IN_ORDER),
                simpleEvaluation.create(areBothSymbolLettersInTheElementName(), VALID, SYMBOL_LETTERS_ARE_MISSING_FROM_THE_NAME),
                simpleEvaluation.create(doesSymbolContainTwoLetters(), VALID, SYMBOL_IS_NOT_TWO_LETTERS),
                simpleEvaluation.create(areAllTheLettersFromElementName(), VALID, SYMBOL_LETTERS_NOT_ALL_FROM_CHEM_NAME),
                simpleEvaluation.create(isFirstCharacterInUpperCase(), VALID, FIRST_CHAR_OF_SYMBOL_NOT_UPPER_CASE),
                simpleEvaluation.create(isSecondCharacterInLowerCase(), VALID, SECOND_CHAR_OF_SYMBOL_NOT_LOWERCASE)
        );
    }

}
