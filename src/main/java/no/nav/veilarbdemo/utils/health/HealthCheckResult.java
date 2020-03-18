package no.nav.veilarbdemo.utils.health;

public class HealthCheckResult {

    private final boolean isHealthy;

    private final String message;

    private HealthCheckResult(boolean isHealthy, String message) {
        this.isHealthy = isHealthy;
        this.message = message;
    }

    public static HealthCheckResult unhealthy(String message) {
        return new HealthCheckResult(false, message);
    }

    public static HealthCheckResult healthy() {
        return new HealthCheckResult(true,null);
    }

    public boolean isHealthy() {
        return isHealthy;
    }

    public String getMessage() {
        return message;
    }

}
