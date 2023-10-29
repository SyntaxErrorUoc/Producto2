package vista;




import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class GestionOSTest {

    private GestionOS gestion;

    @BeforeEach
    public void setup(){
        this.gestion = new GestionOS();
    }

    @Test
    void validaEmilioValido() {
        assertTrue(gestion.validaEmilio("pepe@gmail.com"));
        assertTrue(gestion.validaEmilio("gestion.test@emilio.com"));
    }

    @Test
    void validaEmilioNoValido() {
        assertFalse(gestion.validaEmilio("pepegmail.com"));
        assertFalse(gestion.validaEmilio("casas@mail"));
        ;
    }

}