package vista;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GestionOSTest {

    private GestionOS gestion;
    @BeforeEach
    void setUp() {
        gestion = new GestionOS();
    }

    @Test
   public void testValidaEmilioValido() {
        assertTrue(gestion.validaEmilio("Emilios@emilio.comk"));
        assertTrue(gestion.validaEmilio("Juan.emilio@emilio.com"));
    }

    @Test
    public void testValidaEmilioNoValido(){
        assertFalse(gestion.validaEmilio("Emiliosemilio.com"));

    }
}