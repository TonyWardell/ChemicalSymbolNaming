package uk.wardell.tony.chemicalnaming.functional;

import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.partition.list.PartitionMutableList;
import org.eclipse.collections.impl.factory.Lists;
import uk.wardell.tony.chemicalnaming.model.CandidateName;

class MainEvaluateUsingEclipseCollections {

    private MutableList<CandidateName> candidateNames;
    private Predicate<CandidateName> candidateNamePredicate = name -> Evaluations.checkValidity(name).isValid();

    public static void main(String[] args) {
        MainEvaluateUsingEclipseCollections mainEvaluateList = new MainEvaluateUsingEclipseCollections();
        mainEvaluateList.candidateNames = mainEvaluateList.getCandidateNames();

        mainEvaluateList.printValidSizeWithSelect();
        mainEvaluateList.printBothSizesWithPartition();
    }

    private void printValidSizeWithSelect() {
        MutableList<CandidateName> validNames = candidateNames.select(candidateNamePredicate);
        System.out.println("Valid names size is " + validNames.size());
    }

    private void printBothSizesWithPartition() {
        PartitionMutableList<CandidateName> partitionedNames = candidateNames.partition(candidateNamePredicate);
        System.out.println("Valid names size is "   + partitionedNames.getSelected().size());
        System.out.println("Invalid names size is " + partitionedNames.getRejected().size());

    }

    private MutableList<CandidateName> getCandidateNames() {
        //Valid
        CandidateName candidateName0 = new CandidateName("Spenglerium","Ee");
        CandidateName candidateName1 = new CandidateName("Zeddemorium","Zr");
        CandidateName candidateName2 = new CandidateName("Venkmine","Kn");

        //Not Valid
        CandidateName candidateName3 = new CandidateName("Melintzum","M");
        CandidateName candidateName4 = new CandidateName("Melintzum","Nn");
        CandidateName candidateName5 = new CandidateName("Tullium","uT");
        CandidateName candidateName6 = new CandidateName("Tullium","tu");
        CandidateName candidateName7 = new CandidateName("Tullium","TU");

        return Lists.mutable.of(candidateName0, candidateName1, candidateName2,
                candidateName3, candidateName4, candidateName5, candidateName6, candidateName7);
    }
}
