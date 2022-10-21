package pl.edu.pjwstk.mpr.fms;

public class DesignSuggestion {

    String suggestion;

    DesignSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DesignSuggestion) {
            return ((DesignSuggestion) obj).suggestion.equals(this.suggestion);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return suggestion == null ? 0 : suggestion.hashCode();
    }
}
