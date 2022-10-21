package pl.edu.pjwstk.mpr.fms;

import pl.edu.pjwstk.mpr.fms.exceptions.DesignSuggestionException;
import pl.edu.pjwstk.mpr.fms.exceptions.WrongInvestmentStateException;

import java.util.HashSet;
import java.util.Set;

public class SimpleInvestment implements Investment {

    InvestmentState currentInvestmentState;

    Set<DesignSuggestion> unsolvedSuggestions = new HashSet<DesignSuggestion>();

    public SimpleInvestment() {
        initializeConcept();
    }

    public void setCurrentInvestmentState(InvestmentState state) {
        this.currentInvestmentState = state;
    }

    public InvestmentState getCurrentInvestmentState() {
        return this.currentInvestmentState;
    }

    public int getNumberOfUnsolvedDesignSuggestions() {
        return unsolvedSuggestions.size();
    }

    @Override
    public void initializeConcept() {
        currentInvestmentState = InvestmentState.BUILDING_CONCEPT;
    }

    @Override
    public void resign() {
        if (currentInvestmentState == InvestmentState.PARCEL_SOLD || currentInvestmentState == InvestmentState.BUILDING_CONCEPT) {
            currentInvestmentState = InvestmentState.INVESTMENT_RESIGNED;
        } else {
            throw new WrongInvestmentStateException(
                    "To resign from investment, it has to be in PARCEL_SOLD or BUILDING_CONCEPT state, current state is: "
                            + currentInvestmentState
            );
        }
    }

    @Override
    public void buyParcel() {
        if (currentInvestmentState != InvestmentState.BUILDING_CONCEPT) {
            throw new WrongInvestmentStateException(
                    "To buy parcel, investment has to be in BUILDING_CONCEPT state, current state is: "
                            + currentInvestmentState
            );
        } else {
            currentInvestmentState = InvestmentState.PARCEL_BOUGHT;
        }
    }

    @Override
    public void sellParcel() {
        if (currentInvestmentState != InvestmentState.PARCEL_BOUGHT) {
            throw new WrongInvestmentStateException(
                    "To sell parcel, investment has to be in PARCEL_BOUGHT state, current state is: "
                            + currentInvestmentState
            );
        } else {
            currentInvestmentState = InvestmentState.PARCEL_SOLD;
        }
    }

    @Override
    public void startProjectingBuilding() {
        if (currentInvestmentState != InvestmentState.PARCEL_BOUGHT) {
            throw new WrongInvestmentStateException(
                    "To start designing, investment has to be in PARCEL_BOUGHT state, current state is: "
                            + currentInvestmentState
            );
        } else {
            currentInvestmentState = InvestmentState.DESIGN_PHASE;
        }
    }

    @Override
    public DesignSuggestion makeDesignSuggestion(String suggestion) {
        if (currentInvestmentState != InvestmentState.DESIGN_PHASE) {
            throw new WrongInvestmentStateException(
                    "To make design suggestion, investment has to be in DESIGN_PHASE state, current state is: "
                            + currentInvestmentState
            );
        } else {
            var designSuggestion = new DesignSuggestion(suggestion);
            unsolvedSuggestions.add(designSuggestion);
            return designSuggestion;
        }
    }

    @Override
    public void resolveDesignSuggestion(DesignSuggestion resolvedSuggestion) {
        if (currentInvestmentState != InvestmentState.DESIGN_PHASE) {
            throw new WrongInvestmentStateException(
                    "To resolve design suggestion, investment has to be in DESIGN_PHASE state, current state is: "
                            + currentInvestmentState
            );
        } else if (!unsolvedSuggestions.contains(resolvedSuggestion)) {
            throw new DesignSuggestionException(
                    "There is no such suggestion to resolve"
            );
        } else {
            unsolvedSuggestions.remove(resolvedSuggestion);
        }
    }

    @Override
    public void buildBuilding() {
        if (currentInvestmentState != InvestmentState.DESIGN_PHASE) {
            throw new WrongInvestmentStateException(
                    "To build the building, investment has to be in DESIGN_PHASE state, current state is: "
                            + currentInvestmentState
            );
        } else if (!unsolvedSuggestions.isEmpty()) {
            throw new DesignSuggestionException(
                    "To build the building, all the design suggestions have to be resolved. Number of suggestion left: " + unsolvedSuggestions.size()
            );
        } else {
            currentInvestmentState = InvestmentState.BUILDING_FINISHED;
        }
    }

    @Override
    public void giveToClient() {
        if (currentInvestmentState != InvestmentState.BUILDING_FINISHED) {
            throw new WrongInvestmentStateException(
                    "To give investment to client, investment has to be in BUILDING_FINISHED state, current state is: "
                            + currentInvestmentState
            );
        } else {
            currentInvestmentState = InvestmentState.INVESTMENT_FINISHED;
        }
    }
}
