package ksr.grupa3.ling;

import java.util.List;

public class SummaryBuilder {
    
    public static Summary buildSummary(Quantifier quantifier, List<Subject> subjects, Agregator qualifier, Agregator summarizer, SummaryType summaryType) {

        if (qualifier == null) {
            throw new IllegalArgumentException("Qualifier cannot be null");
        }
        if (summarizer == null || summarizer.getSetList().size() == 0) {
            throw new IllegalArgumentException("Valid summarizer must be given");
        }
        if (quantifier.getIsForthForm() && summaryType != SummaryType.FOURTH_FORM) {
            throw new IllegalArgumentException("This quantifier is only for fourth form summary");
        }
        if (summaryType == SummaryType.FOURTH_FORM && qualifier.getSetList().size() != 0) {
            throw new IllegalArgumentException("Fourth form summary cannot have a qualifier");
        }
        if (subjects.size() == 1 && summaryType != SummaryType.SINGLE_SUBJECT) {
            throw new IllegalArgumentException("Single subject summary cannot have more than one subject");
        }
        if (subjects.size() > 1 && summaryType == SummaryType.SINGLE_SUBJECT) {
            throw new IllegalArgumentException("Single subject summary cannot have more than one subject");
        }
        if (subjects.size() > 2) {
            throw new IllegalArgumentException("Summaries cannot have more than two subjects");
        }
        if (summaryType  == SummaryType.FIRST_FORM && qualifier.getSetList().size() != 0) {
            throw new IllegalArgumentException("First form summary cannot have qualifier");
        }
        

        return new Summary(quantifier, subjects, qualifier, summarizer, summaryType);
    }

}
