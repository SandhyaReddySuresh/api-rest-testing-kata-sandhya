package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;

public class Utils {

    public static RequestSpecification req;
    public static ResponseSpecification res;


    public static RequestSpecification requestSpecification() throws IOException
    {

        if(req==null)
        {
            PrintStream log =new PrintStream(new FileOutputStream("C:\\Users\\sandh\\api-restAssured-testing-kata\\APIRestAssured\\src\\test\\resources\\logging.txt"));
            req=new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("baseUrl"))
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON).build();
            return req;
        }
        return req;


    }

    public static ResponseSpecification responseSpecification()throws IOException
    {

        if(res==null)
        {
            PrintStream log =new PrintStream(new FileOutputStream("logging.txt"));
            res=new ResponseSpecBuilder()
                    .expectContentType(ContentType.JSON)
                    .build();
            //expectResponseTime(lessThan(2000L), TimeUnit.MILLISECONDS);


        }
        return res;

    }
    public static String getGlobalValue(String key) throws IOException
    {
        Properties prop =new Properties();
        FileInputStream fis =new FileInputStream("C:\\Users\\sandh\\api-restAssured-testing-kata\\APIRestAssured\\src\\test\\resources\\logging.txt");
        prop.load(fis);
        return prop.getProperty(key);
    }

    public static String getJsonPath(Response response, String key)
    {
        String resp=response.asString();
        JsonPath js = new JsonPath(resp);
        return js.get(key).toString();
    }
    public static void validateJsonSchema(Response response,String schemaPath) {
        response.then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath(schemaPath));
    }
    public static void checkInvalidStatusCode(Response response)
    {
        assertThat("Status code should be 4xx",
                response.getStatusCode(), allOf(greaterThanOrEqualTo(400), lessThan(500)));
    }
    public static void checkErrorMessage(Response response,String expectedMessage)
    {
        String actualMessage;

        if (response.jsonPath().get("errors") != null) {
            actualMessage = response.jsonPath().getString("errors[0]");
        } else {
            actualMessage = response.jsonPath().getString("error");
        }

        assertThat("Error message mismatch", actualMessage, equalTo(expectedMessage));
    }
    public static void checkSchemaValidation(Response response,String schemaJson)
    {
        try {
            validateJsonSchema(response, "schema/" + schemaJson);
        }
        catch (AssertionError e)
        {
            Assert.fail("Schema validation correctly failed: " + e.getMessage());

        }
    }


}
