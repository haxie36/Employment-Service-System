package profile;

import common.TestSpecialtyCatalog;
import logic.common.Passport;
import logic.profile.Profile;
import logic.profile.RegInput;
import logic.profile.RegistrationController;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class RegistrationControllerEquivalentTest {
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
    public void create_validInput_addsProfile() {
        RegInput input = new RegInput(
                "test",
                new Passport(
                        "test",
                        LocalDate.now().minusYears(30),
                        "12345678",
                        "1234567890"),
                "121",
                10
        );

        assertDoesNotThrow(() -> reg.create(input));
        assertNotNull(dao.getByPassport("12345678"));
    }


    @Test
    public void create_invalidAddress_throws() {
        RegInput input = new RegInput(
                "other",
                new Passport(
                        "test",
                        LocalDate.now().minusYears(30),
                        "12345678",
                        "1234567890"),
                "121",
                0
        );

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> reg.create(input));
        assertEquals("Address is not valid!", ex.getMessage());
    }

    @Test
    public void create_passportNumber_tooShort_throws() {
        RegInput input = new RegInput(
                "test",
                new Passport(
                        "test",
                        LocalDate.now().minusYears(30),
                        "123456",
                        "1234567890"),
                "121",
                0
        );

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> reg.create(input));
        assertEquals("Invalid Passport Number!", ex.getMessage());
    }

    @Test
    public void create_passportNumber_tooLong_throws() {
        RegInput input = new RegInput(
                "test",
                new Passport(
                        "test",
                        LocalDate.now().minusYears(30),
                        "1234567890",
                        "1234567890"),
                "121",
                0
        );

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> reg.create(input));
        assertEquals("Invalid Passport Number!", ex.getMessage());
    }

    @Test
    public void create_rnokpp_invalidLength_throws() {
        RegInput input = new RegInput(
                "test",
                new Passport(
                        "test", LocalDate.now().minusYears(30),
                        "12345678",
                        "123456"),
                "121",
                0
        );

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> reg.create(input));
        assertEquals("Invalid RNOKPP!", ex.getMessage());
    }

    @Test
    public void create_name_tooShort_throws() {
        RegInput input = new RegInput(
                "test",
                new Passport(
                        "te",
                        LocalDate.now().minusYears(30),
                        "12345678",
                        "1234567890"),
                "121",
                0
        );

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> reg.create(input));
        assertEquals("Invalid Name!", ex.getMessage());
    }

    @Test
    public void create_name_tooLong_throws() {
        RegInput input = new RegInput(
                "test",
                new Passport(
                        "test".repeat(26),
                        LocalDate.now().minusYears(30),
                        "12345678",
                        "1234567890"),
                "121",
                0
        );

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> reg.create(input));
        assertEquals("Invalid Name!", ex.getMessage());
    }

    @Test
    public void create_birthday_tooYoung_throws() {
        RegInput input = new RegInput(
                "test",
                new Passport(
                        "test",
                        LocalDate.now().minusYears(12),
                        "12345678",
                        "1234567890"),
                "121",
                0
        );

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> reg.create(input));
        assertEquals("Invalid Birthday!", ex.getMessage());
    }

    @Test
    public void create_birthday_tooOld_throws() {
        RegInput input = new RegInput(
                "test",
                new Passport(
                        "test",
                        LocalDate.now().minusYears(150),
                        "12345678",
                        "1234567890"),
                "121",
                0
        );

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> reg.create(input));
        assertEquals("Invalid Birthday!", ex.getMessage());
    }

    @Test
    public void create_specialty_invalid_throws() {
        RegInput input = new RegInput(
                "test",
                new Passport(
                        "test",
                        LocalDate.now().minusYears(30),
                        "12345678",
                        "1234567890"),
                "000",
                0
        );

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> reg.create(input));
        assertEquals("Invalid Specialty!", ex.getMessage());
    }

    @Test
    public void create_experience_negative_throws() {
        RegInput input = new RegInput(
                "test",
                new Passport(
                        "test",
                        LocalDate.now().minusYears(30),
                        "12345678",
                        "1234567890"),
                "121",
                -10
        );

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> reg.create(input));
        assertEquals("Invalid Experience!", ex.getMessage());
    }

    @Test
    public void create_experience_tooLarge_throws() {
        RegInput input = new RegInput(
                "test",
                new Passport(
                        "test",
                        LocalDate.now().minusYears(30),
                        "12345678",
                        "1234567890"),
                "121",
                150
        );

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> reg.create(input));
        assertEquals("Invalid Experience!", ex.getMessage());
    }

    @Test
    public void create_experience_exceedsAgeMinus16_throws() {
        //Age = 25 -> age - 16 = 9, experience = 10 should be invalid
        RegInput input = new RegInput(
                "test",
                new Passport(
                        "test",
                        LocalDate.now().minusYears(25),
                        "12345678",
                        "1234567890"),
                "121",
                20
        );

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> reg.create(input));
        assertEquals("Invalid Experience!", ex.getMessage());
    }

    @Test
    public void create_experience_equalsAgeMinus16_allowed() {
        //Age = 25 -> age - 16 = 9, experience = 9 should be valid
        RegInput input = new RegInput(
                "test",
                new Passport(
                        "test",
                        LocalDate.now().minusYears(25),
                        "12345678",
                        "1234567890"),
                "121",
                9
        );

        assertDoesNotThrow(() -> reg.create(input));
        assertNotNull(dao.getByPassport("12345678"));
    }

    @Test
    public void create_alreadyRegistered_byPassport_throws() {
        //Prep, add a "copy"
        Profile existing = new Profile();
        existing.setPassportInfo(new Passport(
                "Copy",
                LocalDate.now().minusYears(40),
                "12345678",
                "1234567890"));
        dao.add(existing);

        RegInput input = new RegInput(
                "test",
                new Passport(
                        "test",
                        LocalDate.now().minusYears(30),
                        "12345678",
                        "0987654321"),
                "121",
                0
        );

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> reg.create(input));
        assertEquals("Profile is already registered!", ex.getMessage());
    }

    @Test
    public void create_alreadyRegistered_byRNOKPP_throws() {
        //Prep, add a "copy"
        logic.profile.Profile existing = new logic.profile.Profile();
        existing.setPassportInfo(new Passport(
                "Copy",
                LocalDate.now().minusYears(40),
                "87654321",
                "1234567890"));
        dao.add(existing);

        RegInput input = new RegInput(
                "test",
                new Passport("test",
                        LocalDate.now().minusYears(30),
                        "12345678",
                        "1234567890"),
                "121",
                0
        );

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> reg.create(input));
        assertEquals("Profile is already registered!", ex.getMessage());
    }

    @Test
    public void create_passportLengthBoundary_8_and_9_allowed() {
        //length 8
        RegInput input8 = new RegInput(
                "test",
                new Passport(
                        "test", 
                        LocalDate.now().minusYears(30),
                        "12345678",
                        "1234567890"),
                "121",
                0
        );
        assertDoesNotThrow(() -> reg.create(input8));
        assertNotNull(dao.getByPassport("12345678"));

        //length 9
        RegInput input9 = new RegInput(
                "test",
                new Passport(
                        "test",
                        LocalDate.now().minusYears(30),
                        "123456789",
                        "0987654321"),
                "121",
                0
        );
        assertDoesNotThrow(() -> reg.create(input9));
        assertNotNull(dao.getByPassport("123456789"));
    }

    @Test
    public void create_rnokpp_length10_allowed() {
        RegInput input = new RegInput(
                "test",
                new Passport(
                        "test",
                        LocalDate.now().minusYears(30),
                        "123456789",
                        "1234567890"),
                "121",
                0
        );
        assertDoesNotThrow(() -> reg.create(input));
        assertNotNull(dao.getByPassport("123456789"));
    }
}
