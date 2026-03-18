package utils;

public enum APIResources {
    AuthAdmin("/api/auth/login");
    private String resourceAPI;

    APIResources(String resource) {
        this.resourceAPI = resource;
    }

    public String getResources() {
        return resourceAPI;
    }
}
