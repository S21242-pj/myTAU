package pl.edu.pjwstk.mpr.fms;

public interface Investment {
    void initializeConcept();

    void resign();

    void buyParcel();

    void sellParcel();

    void startProjectingBuilding();

    DesignSuggestion makeDesignSuggestion(String suggestion);

    void resolveDesignSuggestion(DesignSuggestion resolvedSuggestion);

    void buildBuilding();

    void giveToClient();
}
