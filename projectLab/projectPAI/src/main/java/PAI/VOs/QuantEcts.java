package PAI.VOs;

import PAI.ddd.ValueObject;

public class QuantEcts implements ValueObject  {

    private final int _quantEcts;

    public QuantEcts(int quantityOfEcts) {
        if (!isQuantEctsValid(quantityOfEcts)) {
            throw new IllegalArgumentException("Insert a valid number of ECTS");
        }
        _quantEcts = quantityOfEcts;
    }

    private boolean isQuantEctsValid (int quantityOfEcts){
        return quantityOfEcts > 0 && quantityOfEcts <= 30;
    }

    public int getQuantEcts() {
        return _quantEcts;
    }
}

