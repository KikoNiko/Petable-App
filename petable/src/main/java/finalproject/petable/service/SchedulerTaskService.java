package finalproject.petable.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class SchedulerTaskService {
    private final Logger LOGGER = LoggerFactory.getLogger(SchedulerTaskService.class);
    private final PetService petService;

    public SchedulerTaskService(PetService petService) {
        this.petService = petService;
    }

    @Scheduled(cron = "0 0 * * * *")
    void removeAdoptedPets() {
        petService.removeAdoptedPets();
        LOGGER.info("Adopted pets removed at " + Instant.now());
    }
}
