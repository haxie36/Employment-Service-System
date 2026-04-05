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
                new Profiles(new Profile[]{new Profile("2")}),
                new Office()
        );
        reg.newCreation();
    }

    @Test
    void testServiceArea_valid() {
        boolean result = reg.isServiceArea("1");
        assertTrue(result);
    }

    @Test
    void testServiceArea_invalid() {
        boolean result = reg.isServiceArea("2");
        assertFalse(result);
    }

    @Test
    void testRegistered_success() {
        boolean result = reg.isRegistered(new Passport(
                "1",
                LocalDate.now(),
                "1",
                "1"));
        assertFalse(result);
    }

    @Test
    void testRegistered_invalid() {
        boolean result = reg.isRegistered(new Passport(
                "2",
                LocalDate.now(),
                "2",
                "2"));
        assertTrue(result);
    }

    @Test
    void testRealSpecialty_success() {
        boolean result = reg.isRealSpecialty("1");
        assertTrue(result);
    }

    @Test
    void testRealSpecialty_invalid() {
        boolean result = reg.isRealSpecialty("2");
        assertFalse(result);
    }

    @Test
    void testRecord_success() {
        boolean result = reg.record(1);
        assertTrue(result);
    }

    @Test
    void testRecord_invalid() {
        boolean result = reg.record(-1);
        assertFalse(result);
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
        boolean[] outputs = new boolean[]{false,false,false,false,true};

        for (int i = 0; i < outputs.length; i++) {
            boolean result = reg.create(inputs[i]);
            assertEquals(outputs[i], result);
        }
    }
}
