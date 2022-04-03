package Modele;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class CaseTest {
    @Test
    void caseInitOrigine() {
        new Case(0, 0, false, false);
    }

    @Test
    void caseInitInvalid() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Case(-1, -1, false, false));
    }
}
