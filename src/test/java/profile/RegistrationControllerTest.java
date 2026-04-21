package profile;

import common.TestSpecialtyCatalog;
import logic.common.Passport;
import logic.profile.RegInput;
import logic.profile.RegistrationController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RegistrationControllerTest {
    private RegistrationController reg;

    @BeforeEach
    void setUp() {
        reg = new RegistrationController(
                new TestServiceArea(),
                new TestSpecialtyCatalog(),
                new TestProfileDAO(),
                new TestOffice()
        );
    }

    @Test
    void testCreate() {
        RegInput[] inputs = new RegInput[]{
                new RegInput("invalid", new Passport("111", LocalDate.now(), "11111111","1111111111"), "1", -1),
                new RegInput("test", new Passport("111", LocalDate.parse("1999-01-01"), "11111111","1111111111"), "1", -1),
                new RegInput("test", new Passport("111", LocalDate.parse("1999-01-01"), "44444444","4444444444"), "1", -1),
                new RegInput("test", new Passport("111", LocalDate.parse("1999-01-01"), "44444444","4444444444"), "121", -1),
                new RegInput("test", new Passport("111", LocalDate.parse("1999-01-01"), "44444444","4444444444"), "121", 1)
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
