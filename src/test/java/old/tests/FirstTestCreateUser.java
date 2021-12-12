package old.tests;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.testng.TestNGCitrusTestRunner;
import com.consol.citrus.message.MessageType;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestContext;
import org.testng.annotations.Test;
import old._pojo.CreateUserResponse;

public class FirstTestCreateUser extends TestNGCitrusTestRunner {
    String name = "Nick";
    String job = "Teacher";
    private TestContext context;

    @Test
    @CitrusTest
    public void getTestActions() {
        http(httpActionBuilder -> httpActionBuilder
                .client("restClientReqres")
                .send()
                .post("users")
                .payload("{\n" +
                        "    \"name\": \"" + name + "\",\n" +
                        "    \"job\": \"" + job + "\"\n" +
                        "}")
        );
        http(httpActionBuilder -> httpActionBuilder
                .client("restClientReqres")
                .receive()
                .response(HttpStatus.CREATED)
                .messageType(MessageType.JSON)
                .payload(getResponseData(name, job), "objectMapper")
                .extractFromPayload("$.id", "currentId")
                .extractFromPayload("$.createdAt", "createdAt")
                .ignore("$.createdAt")
                .ignore("$.id")

        );
        echo("currentId = ${currentId} and createdAt = ${createdAt}");
    }

    public CreateUserResponse getResponseData(String name, String job) {

        CreateUserResponse createUserResponse = new CreateUserResponse();
        createUserResponse.setName(name);
        createUserResponse.setJob(job);
        createUserResponse.setId("@isNumber()@");
        createUserResponse.setCreatedAt("unknown");

        return createUserResponse;
    }
}
