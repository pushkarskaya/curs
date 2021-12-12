package old.tests;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.dsl.testng.TestNGCitrusTestRunner;
import com.consol.citrus.message.MessageType;
import com.consol.citrus.testng.CitrusParameters;
import org.springframework.http.HttpStatus;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestGetAllUsers {

    public class TestGetAllUser extends TestNGCitrusTestRunner {
        private TestContext context;
        @DataProvider(name = "dataProvider")
        public Object[][] cardTypeProvider() {
            return new Object[][] {
                    new Object[] { 1, "Driver","Grade1" },
                    new Object[] { 2, "Teacher","Grade2" },
                    new Object[] { 3, "Tester", "Grade3" },
                    new Object[] { 4, "Actor", "Grade4" },
            };
        }

        @Test(description = "Получение информации о пользователе", dataProvider = "dataProvider")
        @Parameters({ "context" })
        @CitrusTest
        @CitrusParameters({ "id", "name" })
        public void getTestActions(int id, String name) {
            this.context = citrus.createTestContext();
            http(httpActionBuilder -> httpActionBuilder
                    .client("mobileApp")
                    .send()
                    .post("/users/get/all")
                    .payload("{\n" +
                            "    \"id\": \"" + id + "\",\n" +
                            "    \"name\": \"" + name + "\"\n" +
                            "}"));

            http(httpActionBuilder -> httpActionBuilder
                            .client("mobileApp")
                            .receive()
                            .response(HttpStatus.CREATED)
                            .messageType(MessageType.JSON)
                           // .payload(getResponseData(name), "objectMapper")
                            .extractFromPayload("$.id", "currentId")
                            .extractFromPayload("$.createdAt", "createdAt")
                            .ignore("$.createdAt")
                    // .ignore("$.id")
            );
            echo("currentId = ${currentId} and createdAt = ${createdAt}");
        }
//        public CreateUserResponse getResponseData(String name) {
//            CreateUserResponse createUserResponse = new CreateUserResponse();
//            createUserResponse.setName(name);
//            createUserResponse.setJob(job);
//            createUserResponse.setId("@isNumber()@");
//            createUserResponse.setCreatedAt("unknown");
//
//            return createUserResponse;
//        }

    }

}
