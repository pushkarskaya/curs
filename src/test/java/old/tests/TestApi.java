package old.tests;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;


public class TestApi {

    private static final int PORT = 8080;
    private static final String HOST = "localhost";
    private static final String BASE_URL = HOST + ":" + PORT;

    private static final WireMockServer wireMockServer =
            new WireMockServer(WireMockConfiguration.options()
                    .extensions(new ResponseTemplateTransformer(false))
                    .port(PORT));

    @BeforeAll
    public static void beforeAll() {
        wireMockServer.start(); //Запуск сервера
        configureFor(HOST, PORT);


        MappingBuilder getUserAll=get(urlMatching("/user/get/all"));
        String jsonUserAll = "[]";
        ResponseDefinitionBuilder responseGetUserAll=aResponse()
                .withStatus(200)
                .withHeader("Content-Type","application/json")
                .withBody(jsonUserAll);


        stubFor(getUserAll.willReturn(responseGetUserAll));//Получение списка пользователей
    }

    @AfterAll
    public static void afterAll() {
        wireMockServer.stop();
    } //Остановка сервера

    @Test
    @DisplayName("Get user all /user/get/all")//Получение списка пользователей
    void testUserAll() {

    }

//        String getUserUrl = "/user/get/all";
//        RestAssured.
//                when()
//                .get("http://" + BASE_URL + getUserUrl)
//                .then()
//                .contentType(ContentType.JSON)
//                .log().all()
//                .assertThat()
//                .statusCode(200)
//                //.body("size()", is(2))
//                .body(containsString(jsonUserAll))
//              //  .body(matchesJsonSchemaInClasspath("schema/AllUser.json"));
//    }
}




