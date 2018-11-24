package uk.wardell.tony.chemicalnaming.functional;

import uk.wardell.tony.chemicalnaming.CandidateName;
import uk.wardell.tony.patterned.Evaluation;
import uk.wardell.tony.patterned.EvaluateSuccess;
import uk.wardell.tony.patterned.EvaluateFailure;
import uk.wardell.tony.patterned.response.Response;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static uk.wardell.tony.chemicalnaming.NamingEvaluationResponses.*;

class ChemicalNamingEvaluations {

    private static final Function<CandidateName,String> firstChar = cn -> String.valueOf(cn.getSymbol().toLowerCase().charAt(0));
    private static final Function<CandidateName,String> secondChar = cn -> String.valueOf(cn.getSymbol().toLowerCase().charAt(1));


    static Predicate<CandidateName> doesSymbolContainTwoLetters() {
        return cn -> cn.getSymbol().length() == 2;
    }

    private static Predicate<CandidateName> charFrequencyInSymbolShouldMatchThatInElement() {
        return cn -> {
            var charLocations = getCharLocations(cn);
            return charLocations.loc0 != charLocations.loc1;
        };
    }

    private static Predicate<CandidateName> areBothSymbolLettersInTheElementName() {
        return cn -> {
            var charLocations = getCharLocations(cn);
            return charLocations.loc0 != -1 && charLocations.loc1 != -1;
        };
    }

    private static Predicate<CandidateName> areTheLettersInElementOrder() {
        return cn -> {
            var charLocations = getCharLocations(cn);
            return charLocations.loc0 < charLocations.loc1;
        };
    }

    private static Predicate<CandidateName> isFirstCharacterInUpperCase(){
        return cn -> {
            char c0 = cn.getSymbol().charAt(0);
            return Character.toUpperCase(c0) == c0;
        };
    }

    private static Predicate<CandidateName> isSecondCharacterInLowerCase(){
        return cn -> {
            var c1 = String.valueOf(cn.getSymbol().charAt(1));
            return secondChar.apply(cn).equals(c1);
        };
    }


    static List<Function<CandidateName, Response>> evaluations(){
        Evaluation<CandidateName, Response> requireTrue = new EvaluateSuccess<>();
        Evaluation<CandidateName, Response> requireFalse = new EvaluateFailure<>();

        return Arrays.asList(
                requireTrue.create(doesSymbolContainTwoLetters(), VALID, SYMBOL_IS_NOT_TWO_LETTERS),
                requireTrue.create(areBothSymbolLettersInTheElementName(), VALID, SYMBOL_LETTERS_ARE_MISSING_FROM_THE_NAME),
                requireTrue.create(charFrequencyInSymbolShouldMatchThatInElement(), VALID, IF_LETTERS_IN_SYMBOL_ARE_THE_SAME_THEN_THE_NAME_SHOULD_HAVE_THOSE_LETTERS_TWICE),
                requireTrue.create(areTheLettersInElementOrder(), VALID, SYMBOL_LETTERS_NOT_IN_ORDER),
                requireTrue.create(isFirstCharacterInUpperCase(), VALID, FIRST_CHAR_OF_SYMBOL_NOT_UPPER_CASE),
                requireTrue.create(isSecondCharacterInLowerCase(), VALID, SECOND_CHAR_OF_SYMBOL_NOT_LOWERCASE)
        );
    }

    private static CharLocations getCharLocations(CandidateName cn) {
        String lower = cn.getElement().toLowerCase();
        String low0 = firstChar.apply(cn);
        String low1 = secondChar.apply(cn);
        return new CharLocations(lower.indexOf(low0), lower.lastIndexOf(low1));
    }

    static class CharLocations {
        final int loc0;
        final int loc1;

        CharLocations(int loc0, int loc1) {
            this.loc0 = loc0;
            this.loc1 = loc1;
        }
    }
}
