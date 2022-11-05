package chainOfResponsibility.validators;

abstract class StringEmptinessValidator extends BaseMessageValidatorChainComponent {

    boolean isStringPopulated(String string) {
        return string != null && !string.isBlank();
    }
}