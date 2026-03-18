package utils;

public enum APIResources {
    AuthAdmin("/api/auth/login"),
    ListOfRoomsAPI("/api/room"),
    InvalidResourceListOfRoomsAPI("/api/rooms"),
    InvalidIdentifierListOfRoomsAPI("/api/room/abc"),
    CheckAvailability("/api/room"),
    GetByRoomId("/api/room/{roomId}"),
    CreateBookingAPI("/api/booking");


    private String resourceAPI;

    APIResources(String resource) {
        this.resourceAPI = resource;
    }

    public String getResources() {
        return resourceAPI;
    }
}
