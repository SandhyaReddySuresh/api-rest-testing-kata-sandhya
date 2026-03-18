package utils;

public enum APIResources {
    AuthAdmin("/api/auth/login"),
    ListOfRoomsAPI("/api/room"),
    InvalidResourceListOfRoomsAPI("/api/rooms"),
    InvalidIdentifierListOfRoomsAPI("/api/room/abc"),
    CheckAvailability("/api/room");

    private String resourceAPI;

    APIResources(String resource) {
        this.resourceAPI = resource;
    }

    public String getResources() {
        return resourceAPI;
    }
}
