package fitnesstracker.util;

import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class BasicApplicationRateLimiter {
    private final Map<String, LocalTime> apiKeyToTimestamp = new ConcurrentHashMap<>();

    public boolean tooManyRequestsForKey(String apiKey) {
        if(!apiKeyToTimestamp.containsKey(apiKey) || LocalTime.now().isAfter(apiKeyToTimestamp.get(apiKey).plusSeconds(1))) {
            apiKeyToTimestamp.put(apiKey, LocalTime.now());
            return false;
        }
        return true;
    }
}
