package uk.wardell.tony.chemicalnaming.functional;

import uk.wardell.tony.chemicalnaming.CandidateName;
import uk.wardell.tony.patterned.Evaluation;
import uk.wardell.tony.patterned.SimpleEvaluation;
import uk.wardell.tony.patterned.response.Response;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static uk.wardell.tony.chemicalnaming.NamingEvaluationResponses.*;

class ChemicalNamingEvaluations {

    static Function<CandidateName,Character> firstChar = cn -> cn.getSymbol().toLowerCase().charAt(0);
    static Function<CandidateName,Character> secondChar = cn -> cn.getSymbol().toLowerCase().charAt(1);


    static Predicate<CandidateName> doesSymbolContainTwoLetters() {
        return candidateName -> candidateName.getSymbol().length() == 2;
    }

    private static Predicate<CandidateName> ifSymbolNameIsTwoOfTheSameLetterThenNameShouldHaveThatLetterTwice() {
        return cn -> {
            char low0 = firstChar.apply(cn);
            char low1 = secondChar.apply(cn);
            if(low0 != low1) return true;

            //Characters are the same
            String lower = cn.getElement().toLowerCase();
            int loc0 = lower.indexOf(low0);
            int loc1 = lower.lastIndexOf(low1);
            return loc0 != loc1;
        };
    }

    private static Predicate<CandidateName> areBothSymbolLettersInTheElementName() {
        return cn -> (cn.getElement().toLowerCase().contains("" + firstChar.apply(cn)) && cn
                .getElement().toLowerCase().contains(("" + secondChar.apply(cn))));
    }

    private static Predicate<CandidateName> areTheLettersInElementOrder() {
        return cn -> {
            int firstCharacterLocation = cn.getElement().indexOf(cn.getSymbol().toLowerCase().charAt(0));
            String restOfSequence = cn.getElement().substring(firstCharacterLocation + 1);
            return restOfSequence.contains("" + secondChar.apply(cn));
        };
    }

    private static Predicate<CandidateName> areAllTheLettersFromElementName() {
        return cn -> {
            int firstCharacterLocation = cn.getElement().indexOf(cn.getSymbol().charAt(0));
            String restOfSequence = cn.getElement().substring(firstCharacterLocation + 1);

            Character secondCharOfSymbol = secondChar.apply(cn);

            if (!restOfSequence.contains("" + secondCharOfSymbol)) {
                return !cn.getElement().contains("" + secondCharOfSymbol);
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
