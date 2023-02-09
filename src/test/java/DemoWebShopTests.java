import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class DemoWebShopTests extends BaseTest{
    String COOKIE_VALUE = "fc06465c332dbd696c292ada76ec7d7733108ecefdc4475406a3ff997944f96b;" +
            " ARRAffinitySameSite=fc06465c332dbd696c292ada76ec7d7733108ecefdc4475406a3ff997944f96b;" +
            " __utma=78382081.200163836.1675961800.1675961800.1675961800.1; __utmc=78382081;" +
            " __utmz=78382081.1675961800.1.1.utmcsr=qa.guru|utmccn=(referral)|utmcmd=referral|utmcct=/;" +
            " __RequestVerificationToken=9Hw5_lVnPwDYtunqU0WfeQUXymcpSQ5-eZKk_UULOHNLqKb5Iw9HvsRboCrdvlkbLVJLJfd3woW1NFtDFSel-PSRQC4h7w2crzuNlMxIBgE1;";
    String COOKIE_KEY = "ARRAffinity";
    String body = "product_attribute_74_5_26=81&product_attribute_74_6_27=83&product_attribute_74_3_28=86&addtocart_74.EnteredQuantity=1";


    @Test
    @Tag("Smoke")
    @DisplayName("Adding an item to cart")
    void addItemToCart() {
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie(COOKIE_KEY, COOKIE_VALUE)
                .body(body)
                .when()
                .post("/addproducttocart/details/74/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"));
    }

    @Test
    @Tag("Smoke")
    @DisplayName("Subscribe to newsletter with valid email")
    void subscribeToNewsletterWithValidEmail() {
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie(COOKIE_KEY, COOKIE_VALUE)
                .body("email=johnydoe%40test.com")
                .when()
                .post("/subscribenewsletter")
                .then()
                .log().all()
                .statusCode(200)
                .body("Success", is(true))
                .body("Result", is("Thank you for signing up! A verification email has been sent. We appreciate your interest."));
    }

    @Test
    @Tag("Smoke")
    @DisplayName("Subscribe to newsletter with invalid email")
    void subscribeToNewsletterWithInvalidEmail() {
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie(COOKIE_KEY, COOKIE_VALUE)
                .body("email=123123")
                .when()
                .post("/subscribenewsletter")
                .then()
                .log().all()
                .statusCode(200)
                .body("Success", is(false))
                .body("Result", is("Enter valid email"));
    }
}
