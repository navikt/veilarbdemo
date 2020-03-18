package no.nav.veilarbdemo.utils.health;

import java.util.List;

public class HealthChecker {

    public static void check(List<HealthCheck> healthChecks) {
        for (HealthCheck healthCheck : healthChecks) {
            HealthCheckResult result = healthCheck.checkHealth();

            if (!result.isHealthy()) {
                throw new RuntimeException("Health check failed: " + result.getMessage());
            }
        }
    }

}
