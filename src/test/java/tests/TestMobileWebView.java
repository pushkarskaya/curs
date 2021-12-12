package tests;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;

import org.junit.jupiter.api.*;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;


class TestMobileWebView {
    private static final int PORT = 8080;
    private static final String HOST = "localhost";
    private static final String BASE_URL = "http://" + HOST + ":" + PORT;

    private static final WireMockServer wireMockServer =
            new WireMockServer(WireMockConfiguration.options()
                    .extensions(new ResponseTemplateTransformer(false))
                    .port(PORT).notifier(new ConsoleNotifier(true)));
    @BeforeAll
    public static void beforeAll() {
        wireMockServer.start(); //Запуск сервера
        configureFor(HOST, PORT);
        String jsonUserAll = "[{\"id\":1,\"name\": Name1,\"grade\": Grade1,},{\"id\": 2,\"name\": Name2,\"grade\": Grade2}]";
        String jsonUserById="{\"id\": 1,\"name\": Name1,\"grade\": Grade1,\"school_name\": Schoolname1, \"city\": Moscow}";
        MappingBuilder getUserAll=get(urlMatching("/users/get/all"));
        ResponseDefinitionBuilder responseGetUserAll=aResponse()
                .withStatus(200)
                //.withHeader("Content-Type","application/json")
                .withHeader("Access-Control-Allow-Origin","*")
                //.withHeader("Access-Control-Allow-Headers","content-type")
                .withHeader("Access-Control-Allow-Methods","POST, GET")
                .withBody(jsonUserAll);



        MappingBuilder getUserById=get(urlMatching("/users/\\d"));
        ResponseDefinitionBuilder responseGetUserById=aResponse()
        .withStatus(200)
        .withHeader("Content-Type","application/json")
        .withBody(jsonUserById);
        stubFor(getUserAll.willReturn(responseGetUserAll));//Получение списка пользователей
        stubFor(getUserById.willReturn(responseGetUserById));

    }

    @AfterAll
    public static void afterAll() {
        wireMockServer.stop();
    }

    @Test
    @DisplayName("Get user all /user/get/all")//Получение списка пользователей
    void testUserAll() {
        while (true){
            //System.out.println("TEST ");
        }

//        String requestUrl = "http://0.0.0.0:8080/users/get/all";
//
//        Response response = given()
//                .contentType(ContentType.JSON)
//                .when()
//                .get(requestUrl)
//                .then()
//                .log().all()
//                .extract().response();
//
//        Assertions.assertEquals(200, response.statusCode());
        //Assertions.assertEquals("qui est esse", response.jsonPath().getString("title[1]"));
    }
}




