package gr.grnet.pccapi;

import gr.grnet.pccapi.dto.ProviderResponseDTO;
import gr.grnet.pccapi.endpoint.ProviderEndpoint;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import javax.inject.Inject;
import java.util.List;


@QuarkusTest
@TestHTTPEndpoint(ProviderEndpoint.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProviderEndpointTest {

    /**
     * Test the /providers/{id} endpoint
     */
    @Test
    public void testGetById() {
        var response = given()
                .contentType(ContentType.JSON)
                .get("1")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .as(ProviderResponseDTO.class);

        assertEquals(1, response.id);
        assertEquals("GRNET", response.name);
    }

    /**
     * Test the /providers endpoint
     */
    @Test
    public void testGetList() {
        var response = given()
                .contentType(ContentType.JSON)
                .get()
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .as(ProviderResponseDTO[].class);

        assertEquals(3, response.length);

        assertEquals(1, response[0].id);
        assertEquals("GRNET", response[0].name);

        assertEquals(2, response[1].id);
        assertEquals("DKRZ", response[1].name);

        assertEquals(3, response[2].id);
        assertEquals("SURF", response[2].name);

    }
}