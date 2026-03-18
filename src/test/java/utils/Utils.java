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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

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
}
