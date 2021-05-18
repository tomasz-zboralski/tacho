package com.crud.tacho.scheduler;

import com.crud.tacho.service.InfringementService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InfringementScheduler {

    private final InfringementService infringementService;

    @Scheduled(cron = "0 0 0 * * *")
    public void deleteInfringementsOlderThanTwentyEightDays() {
        infringementService.deleteAllNotValidInfringements();
    }
}
