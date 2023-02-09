import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    @BeforeAll
    static public void setUp() {
        RestAssured.baseURI = "https://demowebshop.tricentis.com";
    }
}
