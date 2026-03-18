package TestData;

import java.util.Random;

public class RandomEmailGenerator {
    private static final String[] DOMAINS = {"gmail.com", "yahoo.com", "outlook.com", "example.com", "testmail.com"};
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final int EMAIL_LENGTH = 8;

    public static String generateRandomEmail() {
        Random random = new Random();
        StringBuilder username = new StringBuilder();
        for (int i = 0; i < EMAIL_LENGTH; i++) {
            username.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        String domain = DOMAINS[random.nextInt(DOMAINS.length)];
        return username + "@" + domain;
    }
}
