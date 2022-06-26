package ksr.grupa3.ling;

public class SummaryBuilder {
    
    public static Summary buildSummary(Quantifier quantifier, Subject subject, Subject secondSubject, Agregator qualifier, Agregator summarizer, SummaryType summaryType) {

        if (qualifier == null) {
            throw new IllegalArgumentException("Qualifier cannot be null");
        }
        if (summarizer == null || summarizer.getSetList().size() == 0) {
            throw new IllegalArgumentException("Valid summarizer must be given");
        }
        if (subject == null) {
            throw new IllegalArgumentException("First subject must be given");
        }
        if (quantifier.getIsForthForm() && summaryType != SummaryType.FOURTH_FORM) {
            throw new IllegalArgumentException("This quantifier is only for fourth form summary");
        }
        if (summaryType == SummaryType.FOURTH_FORM && qualifier.getSetList().size() != 0) {
            throw new IllegalArgumentException("Fourth form summary cannot have a qualifier");
        }
        if (secondSubject != null && summaryType == SummaryType.SINGLE_SUBJECT) {
            throw new IllegalArgumentException("Single subject summary cannot have more than one subject");
        }
        if (summaryType  == SummaryType.FIRST_FORM && qualifier.getSetList().size() != 0) {
            throw new IllegalArgumentException("First form summary cannot have qualifier");
        }
        

        return new Summary(quantifier, subject, secondSubject, qualifier, summarizer, summaryType);
    }

}
