package pl.joble.domain.numberreciver;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

import java.time.temporal.TemporalAdjusters;

class DrawDateGenerator {

    LocalDateTime generateNextDrawDate(Clock clock){
        LocalDateTime now = LocalDateTime.now(clock);

        if(now.getDayOfWeek()==DayOfWeek.SATURDAY && now.getHour() <=10) return now.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY)).withHour(10);

        else return now.with(TemporalAdjusters.next(DayOfWeek.SATURDAY)).withHour(10);

    }
}
