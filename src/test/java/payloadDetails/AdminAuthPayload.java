package payloadDetails;

public class AdminAuthPayload {

    public static String adminAuthPayload(String userName,String Password)
    {
        return "{\n" +
                "  \"username\": \""+userName+"\",\n" +
                "  \"password\": \""+Password+"\"\n" +
                "}";
    }
}
