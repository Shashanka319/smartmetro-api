
package com.smartmetro;
import org.jspecify.annotations.Nullable;
import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Service;

@Service
public class ReportingServicieHealth implements HealthIndicator {

    @Override
    public @Nullable Health health() {
        boolean healthy = cherckReportingServiceHealth();
        if(healthy){
            return Health.up().withDetail("Reporting Service ","Available").build();
        }
        else {
            return  Health.down().withDetail("Reporting Service ","Unavailable").build();
        }

    }
    private boolean cherckReportingServiceHealth(){
        return  false;
    }
}


