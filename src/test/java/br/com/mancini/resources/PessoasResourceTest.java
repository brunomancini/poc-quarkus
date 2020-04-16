package br.com.mancini.resources;

import io.quarkus.test.junit.QuarkusTest;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

@QuarkusTest
public class PessoasResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/v1/pessoas")
          .then()
             .statusCode(200);
    }

}