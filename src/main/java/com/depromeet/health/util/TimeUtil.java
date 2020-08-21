package com.depromeet.health.util;

import java.time.Duration;

public class TimeUtil {
    public static String convertMillisecondToMinuteSecond(Long ms) {
        Duration timeLeft = Duration.ofMillis(ms);
        return String.format("%02d:%02d", timeLeft.toMinutesPart(), timeLeft.toSecondsPart());
    }
}
