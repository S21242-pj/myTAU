package pl.edu.pjwstk.mpr.fms;

public interface Investment {

    void createNewConcept() throws WrongStateException;

    void signIt() throws WrongStateException;

    void buyParcel() throws WrongStateException;

    void sellParcel() throws WrongStateException;

    void makeANewBuildProject() throws WrongStateException;

    void makeNewDesignProjectSuggestion() throws WrongStateException;

    void resolveDesignProjectSuggestion() throws WrongStateException;

    void buildABuildFromProjectSuggestion() throws WrongStateException;

    void giveANewBuildToClient() throws WrongStateException;
 }
