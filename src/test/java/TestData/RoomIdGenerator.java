package TestData;

import java.util.Random;

public class RoomIdGenerator {

    private static final Random RANDOM = new Random();
    public static int roomId;

    public static int generateRoomId(int min, int max) {
        return RANDOM.nextInt(max - min + 1) + min;
    }

    public static int randomRoomID() {
        for (int i = 0; i < 10; i++) {
            roomId = generateRoomId(1, 100);
            System.out.println("\"roomid\": " + roomId);
        }
        return roomId;
    }
}