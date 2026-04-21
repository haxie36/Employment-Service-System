package profile;

import common.TestSpecialtyCatalog;
import logic.common.Passport;
import logic.profile.Profile;
import logic.profile.RegInput;
import logic.profile.RegistrationController;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class RegistrationControllerEdgeTest {
    private RegistrationController reg;
    private TestProfileDAO dao;
    @BeforeEach
    public void setup() {
        TestServiceArea sa = new TestServiceArea();
        TestSpecialtyCatalog sc = new TestSpecialtyCatalog();
        dao = new TestProfileDAO();
        TestOffice office = new TestOffice();
        reg = new RegistrationController(sa, sc, dao, office);
    }

    @Test
    public void create_address_edge(){
        RegInput input1 = new RegInput(null, new Passport("test", LocalDate.now().minusYears(30), "12345678", "1234567890"), "121", 10);
        RegInput input2 = new RegInput("test", new Passport("test", LocalDate.now().minusYears(30), "12345678", "1234567890"), "121", 10);
        RegInput input3 = new RegInput("other", new Passport("test", LocalDate.now().minusYears(30), "12345678", "1234567890"), "121", 10);

        assertThrows(NullPointerException.class, () -> reg.create(input1));
        assertDoesNotThrow(() -> reg.create(input2));
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> reg.create(input3));
        assertEquals("Address is not valid!", ex.getMessage());
    }

    @Test
    public void create_passportNumber_edge() {
        RegInput input1 = new RegInput("test", new Passport("test", LocalDate.now().minusYears(30), "1234567", "1234567890"), "121", 10);
        RegInput input2 = new RegInput("test", new Passport("test", LocalDate.now().minusYears(30), "12345678", "1234567890"), "121", 10);
        RegInput input3 = new RegInput("test", new Passport("test", LocalDate.now().minusYears(30), "123456789", "0987654321"), "121", 10);
        RegInput input4 = new RegInput("test", new Passport("test", LocalDate.now().minusYears(30), "1234567890", "1234567890"), "121", 10);

        IllegalArgumentException ex1 = assertThrows(IllegalArgumentException.class, () -> reg.create(input1));
        assertEquals("Invalid Passport Number!", ex1.getMessage());
        assertDoesNotThrow(() -> reg.create(input2));
        assertDoesNotThrow(() -> reg.create(input3));
        IllegalArgumentException ex2 = assertThrows(IllegalArgumentException.class, () -> reg.create(input4));
        assertEquals("Invalid Passport Number!", ex2.getMessage());
    }

    @Test
    public void create_RNOKPP_edge() {
        RegInput input1 = new RegInput("test", new Passport("test", LocalDate.now().minusYears(30), "12345678", "123456789"), "121", 10);
        RegInput input2 = new RegInput("test", new Passport("test", LocalDate.now().minusYears(30), "12345678", "1234567890"), "121", 10);
        RegInput input3 = new RegInput("test", new Passport("test", LocalDate.now().minusYears(30), "12345678", "12345678900"), "121", 10);

        IllegalArgumentException ex1 = assertThrows(IllegalArgumentException.class, () -> reg.create(input1));
        assertEquals("Invalid RNOKPP!", ex1.getMessage());
        assertDoesNotThrow(() -> reg.create(input2));
        IllegalArgumentException ex2 = assertThrows(IllegalArgumentException.class, () -> reg.create(input3));
        assertEquals("Invalid RNOKPP!", ex2.getMessage());
    }

    @Test
    public void create_name_edge() {
        RegInput input1 = new RegInput("test", new Passport("te", LocalDate.now().minusYears(30), "12345678", "1234567890"), "121", 10);
        RegInput input2 = new RegInput("test", new Passport("tes", LocalDate.now().minusYears(30), "12345678", "1234567890"), "121", 10);
        RegInput input3 = new RegInput("test", new Passport("test", LocalDate.now().minusYears(30), "123456789", "0987654321"), "121", 10);
        RegInput input4 = new RegInput("test", new Passport("test".repeat(24)+"tes", LocalDate.now().minusYears(30), "87654321", "1212121212"), "121", 10);
        RegInput input5 = new RegInput("test", new Passport("test".repeat(25), LocalDate.now().minusYears(30), "987654321", "2121212121"), "121", 10);
        RegInput input6 = new RegInput("test", new Passport("test".repeat(25)+"t", LocalDate.now().minusYears(30), "12345678", "1234567890"), "121", 10);

        IllegalArgumentException ex1 = assertThrows(IllegalArgumentException.class, () -> reg.create(input1));
        assertEquals("Invalid Name!", ex1.getMessage());
        assertDoesNotThrow(() -> reg.create(input2));
        assertDoesNotThrow(() -> reg.create(input3));
        assertDoesNotThrow(() -> reg.create(input4));
        assertDoesNotThrow(() -> reg.create(input5));
        IllegalArgumentException ex2 = assertThrows(IllegalArgumentException.class, () -> reg.create(input6));
        assertEquals("Invalid Name!", ex2.getMessage());
    }

    @Test
    public void create_birthdate_edge() {
        RegInput input1 = new RegInput("test", new Passport("test", LocalDate.now().minusYears(17), "12345678", "1234567890"), "121", 10);
        RegInput input2 = new RegInput("test", new Passport("test", LocalDate.now().minusYears(18), "12345678", "1234567890"), "121", 0);
        RegInput input3 = new RegInput("test", new Passport("test", LocalDate.now().minusYears(19), "123456789", "0987654321"), "121", 0);
        RegInput input4 = new RegInput("test", new Passport("test", LocalDate.now().minusYears(119), "87654321", "1212121212"), "121", 10);
        RegInput input5 = new RegInput("test", new Passport("test", LocalDate.now().minusYears(120), "987654321", "2121212121"), "121", 10);
        RegInput input6 = new RegInput("test", new Passport("test", LocalDate.now().minusYears(121), "12345678", "1234567890"), "121", 10);

        IllegalArgumentException ex1 = assertThrows(IllegalArgumentException.class, () -> reg.create(input1));
        assertEquals("Invalid Birthday!", ex1.getMessage());
        assertDoesNotThrow(() -> reg.create(input2));
        assertDoesNotThrow(() -> reg.create(input3));
        assertDoesNotThrow(() -> reg.create(input4));
        assertDoesNotThrow(() -> reg.create(input5));
        IllegalArgumentException ex2 = assertThrows(IllegalArgumentException.class, () -> reg.create(input6));
        assertEquals("Invalid Birthday!", ex2.getMessage());
    }

    @Test
    public void create_specialty_edge() {
        RegInput input1 = new RegInput("test", new Passport("test", LocalDate.now().minusYears(30), "12345678", "1234567890"), null, 10);
        RegInput input2 = new RegInput("test", new Passport("test", LocalDate.now().minusYears(30), "12345678", "1234567890"), "121", 10);
        RegInput input3 = new RegInput("test", new Passport("test", LocalDate.now().minusYears(30), "123456789", "0987654321"), "000", 10);

        assertThrows(NullPointerException.class, () -> reg.create(input1));
        assertDoesNotThrow(() -> reg.create(input2));
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> reg.create(input3));
        assertEquals("Invalid Specialty!", ex.getMessage());
    }

    @Test
    public void create_experience_edge() {
        RegInput input1 = new RegInput("test", new Passport("test", LocalDate.now().minusYears(120), "12345678", "1234567890"), "121", -1);
        RegInput input2 = new RegInput("test", new Passport("test", LocalDate.now().minusYears(120), "12345678", "1234567890"), "121", 0);
        RegInput input3 = new RegInput("test", new Passport("test", LocalDate.now().minusYears(120), "123456789", "0987654321"), "121", 1);
        RegInput input4 = new RegInput("test", new Passport("test", LocalDate.now().minusYears(120), "87654321", "1212121212"), "121", 99);
        RegInput input5 = new RegInput("test", new Passport("test", LocalDate.now().minusYears(120), "987654321", "2121212121"), "121", 100);
        RegInput input6 = new RegInput("test", new Passport("test", LocalDate.now().minusYears(120), "12121212", "1313131313"), "121", 101);

        IllegalArgumentException ex1 = assertThrows(IllegalArgumentException.class, () -> reg.create(input1));
        assertEquals("Invalid Experience!", ex1.getMessage());
        assertDoesNotThrow(() -> reg.create(input2));
        assertDoesNotThrow(() -> reg.create(input3));
        assertDoesNotThrow(() -> reg.create(input4));
        assertDoesNotThrow(() -> reg.create(input5));
        IllegalArgumentException ex2 = assertThrows(IllegalArgumentException.class, () -> reg.create(input6));
        assertEquals("Invalid Experience!", ex2.getMessage());
    }
}
