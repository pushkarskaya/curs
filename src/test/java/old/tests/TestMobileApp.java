package old.tests;

import com.consol.citrus.dsl.testng.TestNGCitrusTestRunner;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.hamcrest.Matchers.is;

public class TestMobileApp extends TestNGCitrusTestRunner {
    private static int PORT = 8080;
    private static String HOST = "localhost";
    private static String BASE_URL = HOST + ":" + PORT;
    private static String jsonUserScore = "{\"name\":\"Test user\", \"score\": 78}";


    private static String jsonGetUsers = "[\n" +
            "{'id': int,\n" +
            "'name': String,\n" +
            "'grade': String,\n" +
            "},\n" +
            "{'id': int,\n" +
            "'name': String,\n" +
            "'grade': String,\n" +
            "},\n" +
            "]";
    private static String jsonUserAll="{\"name\":\"Test user\",\n" +
            " \"cource\":\"QA\",\n" +
            "  \"email\":\"test@test.test\",\n" +
            "  \"age\": 23}";
    private static WireMockServer wireMockServer =
            new WireMockServer(WireMockConfiguration.options()
                    .extensions(new ResponseTemplateTransformer(false))
                    .port(PORT));

    @BeforeAll
    public static void beforeAll() {
        wireMockServer.start(); //Запуск сервера
        configureFor(HOST, PORT);

        MappingBuilder getAllUser = get(urlMatching("/users/get/all"));
        ResponseDefinitionBuilder responseGetAllUsers = aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody(jsonGetUsers);



        stubFor(getAllUser.willReturn(responseGetAllUsers));//Заглушка получение списка пользователей
//        stubFor(getUserCources.willReturn(responseGetUserCources));//Заглушка Получение списка курсов
//        stubFor(getUserAll.willReturn(responseGetUserAll));//Получение списка пользователей
    }

    @AfterAll
    public static void afterAll() {
        wireMockServer.stop();
    } //Остановка сервера

    @Test
    @DisplayName("Get all users")//получение списка пользователей
    void testUserScore() {
        String getUserUrl = "/users/get/all";

        RestAssured.
                when()
                .get("http://" + BASE_URL + getUserUrl)
                .then()
                .contentType(ContentType.JSON)
                .log().all()
                .assertThat()
                .statusCode(200);
                //.body("score", is(78));

    }



}
