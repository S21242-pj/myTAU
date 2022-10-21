package pl.edu.pjwstk.mpr.fms;

public class fmsMain {

    public static void main(String[] args) {
        var investement = new SimpleInvestment();

        investement.buyParcel();
        investement.startProjectingBuilding();
        var suggestion1 = investement.makeDesignSuggestion("foo");
        var suggestion2 = investement.makeDesignSuggestion("baz");
        investement.resolveDesignSuggestion(suggestion2);
        investement.resolveDesignSuggestion(suggestion1);
        investement.buildBuilding();
        investement.giveToClient();
    }
}
