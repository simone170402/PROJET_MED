package org.example.Services;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

@Service
public class MetricsService {
    private final Counter reservationCounter;
    private final MeterRegistry meterRegistry;

    public MetricsService(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        this.reservationCounter = Counter.builder("reservations.total")
                .description("Total number of reservations made")
                .register(meterRegistry);
    }

    public void incrementReservationCount() {
        reservationCounter.increment();
    }

    public double getReservationCount() {
        return reservationCounter.count();
    }
}
