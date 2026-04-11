package registration;

import common.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationControllerTest {
    private RegistrationController reg;

    @BeforeEach
    void setUp() {
        reg = new RegistrationController(
                new ServiceArea(new String[]{"1"}),
                new SpecialtyCatalog(new String[]{"1"}),
                new ProfileCollection(new Profile[]{new Profile("2")}),
                new Office()
        );
        reg.newCreation();
    }

    @Test
    void testCreate() {
        RegInput[] inputs = new RegInput[]{
                new RegInput("2", new Passport("1", LocalDate.now(), "1","1"), "1", 1),
                new RegInput("1", new Passport("1", LocalDate.now(), "2","1"), "1", 1),
                new RegInput("1", new Passport("1", LocalDate.now(), "1","1"), "2", 1),
                new RegInput("1", new Passport("1", LocalDate.now(), "1","1"), "1", -1),
                new RegInput("1", new Passport("1", LocalDate.now(), "1","1"), "1", 1)
        };
        boolean[] shouldPass = new boolean[]{false,false,false,false,true};

        for (int i = 0; i < shouldPass.length; i++) {
            int finalI = i;
            if (shouldPass[i]) {
                assertDoesNotThrow(() -> reg.create(inputs[finalI]));
            } else {
                assertThrows(Exception.class, () -> reg.create(inputs[finalI]));
            }
        }
    }
}
