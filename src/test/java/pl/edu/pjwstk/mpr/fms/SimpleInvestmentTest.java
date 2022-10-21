package pl.edu.pjwstk.mpr.fms;

import org.junit.Test;
import pl.edu.pjwstk.mpr.fms.exceptions.DesignSuggestionException;
import pl.edu.pjwstk.mpr.fms.exceptions.WrongInvestmentStateException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class SimpleInvestmentTest {

    @Test
    public void when_investment_created_state_should_be_building_concept() {

        var investment = new SimpleInvestment();

        assertThat(investment.currentInvestmentState).isEqualTo(InvestmentState.BUILDING_CONCEPT);
    }

    @Test
    public void when_state_other_than_building_concept_can_not_buy_parcel() {
        var investment = new SimpleInvestment();

        // tests can be written to check all the other wrong states
        investment.setCurrentInvestmentState(InvestmentState.DESIGN_PHASE);

        assertThatThrownBy(investment::buyParcel).isInstanceOf(WrongInvestmentStateException.class)
                .hasMessageContaining("To buy parcel, investment has to be in BUILDING_CONCEPT state, current state is:");
    }

    @Test
    public void when_state_is_building_concept_can_buy_parcel() {
        // don't have to set current state, because after creating, it will already be BUILDING_CONCEPT
        var investment = new SimpleInvestment();

        investment.buyParcel();

        assertThat(investment.currentInvestmentState).isEqualTo(InvestmentState.PARCEL_BOUGHT);
    }

    @Test
    public void when_state_is_building_concept_can_resign_from_investment() {
        var investment = new SimpleInvestment();

        investment.resign();

        assertThat(investment.currentInvestmentState).isEqualTo(InvestmentState.INVESTMENT_RESIGNED);
    }

    @Test
    public void when_state_other_than_parcel_bought_can_not_sell_parcel() {
        var investment = new SimpleInvestment();

        // tests can be written to check all the other wrong states
        investment.setCurrentInvestmentState(InvestmentState.DESIGN_PHASE);

        assertThatThrownBy(investment::sellParcel).isInstanceOf(WrongInvestmentStateException.class)
                .hasMessageContaining("To sell parcel, investment has to be in PARCEL_BOUGHT state, current state is:");
    }

    @Test
    public void when_state_is_parcel_bought_can_sell_parcel() {
        var investment = new SimpleInvestment();

        investment.setCurrentInvestmentState(InvestmentState.PARCEL_BOUGHT);

        investment.sellParcel();

        assertThat(investment.currentInvestmentState).isEqualTo(InvestmentState.PARCEL_SOLD);
    }

    @Test
    public void when_state_is_parcel_sold_can_resign() {
        var investment = new SimpleInvestment();

        investment.setCurrentInvestmentState(InvestmentState.PARCEL_SOLD);

        investment.resign();

        assertThat(investment.currentInvestmentState).isEqualTo(InvestmentState.INVESTMENT_RESIGNED);
    }

    @Test
    public void when_state_other_than_parcel_bought_can_not_start_designing() {
        var investment = new SimpleInvestment();

        // tests can be written to check all the other wrong states
        investment.setCurrentInvestmentState(InvestmentState.BUILDING_CONCEPT);

        assertThatThrownBy(investment::startProjectingBuilding).isInstanceOf(WrongInvestmentStateException.class)
                .hasMessageContaining("To start designing, investment has to be in PARCEL_BOUGHT state, current state is:");
    }

    @Test
    public void when_state_is_parcel_bought_can_start_designing() {
        var investment = new SimpleInvestment();

        investment.setCurrentInvestmentState(InvestmentState.PARCEL_BOUGHT);

        investment.startProjectingBuilding();

        assertThat(investment.currentInvestmentState).isEqualTo(InvestmentState.DESIGN_PHASE);
    }

    @Test
    public void when_state_other_than_design_phase_can_not_make_design_suggestion() {
        var investment = new SimpleInvestment();

        // tests can be written to check all the other wrong states
        investment.setCurrentInvestmentState(InvestmentState.PARCEL_BOUGHT);

        assertThatThrownBy(() -> {
            investment.makeDesignSuggestion("Suggestion 1");
        }).isInstanceOf(WrongInvestmentStateException.class)
                .hasMessageContaining("To make design suggestion, investment has to be in DESIGN_PHASE state, current state is: ");
    }

    @Test
    public void when_state_is_design_phase_can_make_design_suggestion() {
        var investment = new SimpleInvestment();

        investment.setCurrentInvestmentState(InvestmentState.DESIGN_PHASE);

        investment.makeDesignSuggestion("Suggestion 1");

        assertThat(investment.getNumberOfUnsolvedDesignSuggestions()).isEqualTo(1);
    }

    @Test
    public void when_state_is_design_phase_can_make_multiple_design_suggestions() {
        var investment = new SimpleInvestment();

        investment.setCurrentInvestmentState(InvestmentState.DESIGN_PHASE);

        investment.makeDesignSuggestion("Suggestion 1");
        investment.makeDesignSuggestion("Suggestion 2");
        investment.makeDesignSuggestion("Suggestion 3");

        assertThat(investment.getNumberOfUnsolvedDesignSuggestions()).isEqualTo(3);
    }

    @Test
    public void when_there_is_no_suggestion_can_not_solve_it() {
        var investment = new SimpleInvestment();

        investment.setCurrentInvestmentState(InvestmentState.DESIGN_PHASE);

        var suggestionToResolve = new DesignSuggestion("Suggestion 1");

        assertThatThrownBy(() -> {
            investment.resolveDesignSuggestion(suggestionToResolve);
        }).isInstanceOf(DesignSuggestionException.class)
                .hasMessage("There is no such suggestion to resolve");
    }

    @Test
    public void when_two_equal_suggestions_are_made_one_is_omitted() {
        var investment = new SimpleInvestment();

        investment.setCurrentInvestmentState(InvestmentState.DESIGN_PHASE);

        investment.makeDesignSuggestion("Suggestion 1");
        investment.makeDesignSuggestion("Suggestion 1");

        assertThat(investment.getNumberOfUnsolvedDesignSuggestions()).isEqualTo(1);
    }

    @Test
    public void when_there_is_created_design_suggestion_can_resolve_it() {
        var investment = new SimpleInvestment();

        investment.setCurrentInvestmentState(InvestmentState.DESIGN_PHASE);

        var currentSuggestion = investment.makeDesignSuggestion("Suggestion 1");

        investment.resolveDesignSuggestion(currentSuggestion);

        assertThat(investment.getNumberOfUnsolvedDesignSuggestions()).isEqualTo(0);
    }

    @Test
    public void when_there_are_unsolved_suggestions_can_not_build_building_case_1() {
        var investment = new SimpleInvestment();

        investment.setCurrentInvestmentState(InvestmentState.DESIGN_PHASE);

        investment.makeDesignSuggestion("Suggestion 1");

        assertThatThrownBy(investment::buildBuilding).isInstanceOf(DesignSuggestionException.class)
                .hasMessageContaining("To build the building, all the design suggestions have to be resolved. Number of suggestion left:");
    }

    @Test
    public void when_there_are_unsolved_suggestions_can_not_build_building_case_2() {
        var investment = new SimpleInvestment();

        investment.setCurrentInvestmentState(InvestmentState.DESIGN_PHASE);

        investment.makeDesignSuggestion("Suggestion 1");

        var currentSuggestion = investment.makeDesignSuggestion("Suggestion 2");

        investment.resolveDesignSuggestion(currentSuggestion);

        assertThatThrownBy(investment::buildBuilding).isInstanceOf(DesignSuggestionException.class)
                .hasMessageContaining("To build the building, all the design suggestions have to be resolved. Number of suggestion left:");
    }

    @Test
    public void when_there_are_no_suggestion_and_state_is_design_phase_can_build_building() {
        var investment = new SimpleInvestment();

        investment.setCurrentInvestmentState(InvestmentState.DESIGN_PHASE);

        investment.buildBuilding();

        assertThat(investment.currentInvestmentState).isEqualTo(InvestmentState.BUILDING_FINISHED);
    }

    @Test
    public void when_all_suggestions_are_resolved_and_state_is_design_phase_can_build_building() {
        var investment = new SimpleInvestment();

        investment.setCurrentInvestmentState(InvestmentState.DESIGN_PHASE);

        var suggestion1 = investment.makeDesignSuggestion("Suggestion 1");
        var suggestion2 = investment.makeDesignSuggestion("Suggestion 2");
        investment.resolveDesignSuggestion(suggestion1);
        investment.resolveDesignSuggestion(suggestion2);

        investment.buildBuilding();

        assertThat(investment.currentInvestmentState).isEqualTo(InvestmentState.BUILDING_FINISHED);
    }

    @Test
    public void when_state_other_than_building_finished_can_not_give_to_client() {
        var investment = new SimpleInvestment();

        // tests can be written to check all the other wrong states
        investment.setCurrentInvestmentState(InvestmentState.PARCEL_BOUGHT);

        assertThatThrownBy(investment::giveToClient).isInstanceOf(WrongInvestmentStateException.class)
                .hasMessageContaining("To give investment to client, investment has to be in BUILDING_FINISHED state, current state is:");
    }

    @Test
    public void when_state_is_building_finished_can_give_to_client() {
        var investment = new SimpleInvestment();

        investment.setCurrentInvestmentState(InvestmentState.BUILDING_FINISHED);

        investment.giveToClient();

        assertThat(investment.currentInvestmentState).isEqualTo(InvestmentState.INVESTMENT_FINISHED);
    }
}