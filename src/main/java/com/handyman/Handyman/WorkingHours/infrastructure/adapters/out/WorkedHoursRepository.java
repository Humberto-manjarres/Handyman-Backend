package com.handyman.Handyman.WorkingHours.infrastructure.adapters.out;


import com.handyman.Handyman.WorkingHours.application.domain.WorkedHoursDetail;
import com.handyman.Handyman.WorkingHours.application.models.QueryServiceResponse;
import com.handyman.Handyman.WorkingHours.application.ports.out.IWorkedHours;
import com.handyman.Handyman.commons.operations.Operations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.time.temporal.ChronoUnit.HOURS;

@Service
public class WorkedHoursRepository implements IWorkedHours {
    private static final Logger log = LoggerFactory.getLogger(WorkedHoursRepository.class);
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final MongoWorkedHoursRepository repository;

    public WorkedHoursRepository(MongoWorkedHoursRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<QueryServiceResponse> query(WorkedHoursDetail workedHoursDetail) {

        Flux<ServiceData> byIds = repository.findByIds(workedHoursDetail.getIdTechnical().getIdTechnical(), workedHoursDetail.getWeek().getWeek());
        Mono<List<ServiceData>> listMono = byIds.collectList();
        return listMono.flatMap(service -> {
            if (service.isEmpty()) { throw new RuntimeException("technician has no hours worked in "+ workedHoursDetail.getWeek().getWeek());}
            return Mono.just(getWorkedHours(service));
        }).map(h -> new QueryServiceResponse(h.regularHours, h.nightHours, h.sundayHours, h.regularHoursExtras, h.nightHoursExtras,h.sundayOvertime));

    }

    public HoursTotal getWorkedHours(List<ServiceData> service) {
        int hourNormalCount = Operations.ZERO, nightHoursCount = Operations.ZERO, sundayCount = Operations.ZERO,sundayOvertime = Operations.ZERO;
        for (ServiceData s : service) {
            LocalDateTime dateTime1 = LocalDateTime.parse(s.getStartDate(), formatter);
            LocalDateTime dateTime2 = LocalDateTime.parse(s.getEndDate(), formatter);
            long totalHours = HOURS.between(dateTime1, dateTime2);
            int hoursDay = Operations.DAY_TIME_HOURS,sixHour = Operations.SIX;
            int twentyHour = Operations.TWENTY, seven = Operations.SEVEN,position = Operations.ZERO;
            int hourBegin = Integer.parseInt(dateTime1.getHour() + "");
            List<LocalDateTime> day = getDay(dateTime1, dateTime2);
            for (int i = hourBegin; i < (int) (totalHours + hourBegin); ++i) {
                if (i > hoursDay) {
                    hoursDay += Operations.DAY_TIME_HOURS;
                    sixHour += Operations.DAY_TIME_HOURS;
                    twentyHour += Operations.DAY_TIME_HOURS;
                    seven += Operations.DAY_TIME_HOURS;
                }
                if (i >= hoursDay){
                    position++;
                }
                if (!day.get(position).getDayOfWeek().toString().equals(Operations.SUNDAY)) {
                    if (i > sixHour && i < twentyHour) {
                        hourNormalCount++;
                    }
                    if (i < seven || (i >= twentyHour && i <= hoursDay)) {
                        nightHoursCount++;
                    }
                }else sundayCount++;
            }
        }

        if (getQuantityHoursRegularAndNight(hourNormalCount,nightHoursCount) > Operations.MAX_EXTRAS_HOURS){
            sundayOvertime = sundayCount;
            sundayCount = Operations.ZERO;
        }
        return new HoursTotal(hourNormalCount,nightHoursCount,sundayCount,getRegularHoursExtras(hourNormalCount),getNightHours(nightHoursCount),sundayOvertime);
    }

    public Integer getRegularHoursExtras(Integer regularHours) {
        return (regularHours > Operations.MAX_EXTRAS_HOURS) ? regularHours - Operations.MAX_EXTRAS_HOURS : 0;
    }

    public Integer getNightHours(Integer nightHours) {
        return (nightHours > Operations.MAX_EXTRAS_HOURS) ? nightHours - Operations.MAX_EXTRAS_HOURS : 0;
    }

    public List<LocalDateTime> getDay(LocalDateTime start, LocalDateTime end) {
        return Stream.iterate(start, date -> date.plusDays(1))
                .limit(ChronoUnit.DAYS.between(start, end) + 1).collect(Collectors.toList());
    }

    public Integer getQuantityHoursRegularAndNight(Integer regular, Integer night){
        return regular + night;
    }

    public class HoursTotal {
        private Integer regularHours;
        private Integer nightHours;
        private Integer sundayHours;
        private Integer regularHoursExtras;
        private Integer nightHoursExtras;
        private Integer sundayOvertime;

        public HoursTotal(Integer regularHours, Integer nightHours, Integer sundayHours, Integer regularHoursExtras, Integer nightHoursExtras,Integer sundayOvertime) {
            this.regularHours = regularHours;
            this.nightHours = nightHours;
            this.sundayHours = sundayHours;
            this.regularHoursExtras = regularHoursExtras;
            this.nightHoursExtras = nightHoursExtras;
            this.sundayOvertime = sundayOvertime;
        }

        public Integer getRegularHours() {
            return regularHours;
        }

        public void setRegularHours(Integer regularHours) {
            this.regularHours = regularHours;
        }

        public Integer getNightHours() {
            return nightHours;
        }

        public void setNightHours(Integer nightHours) {
            this.nightHours = nightHours;
        }

        public Integer getSundayHours() {
            return sundayHours;
        }

        public void setSundayHours(Integer sundayHours) {
            this.sundayHours = sundayHours;
        }

        public Integer getRegularHoursExtras() {
            return regularHoursExtras;
        }

        public void setRegularHoursExtras(Integer regularHoursExtras) {
            this.regularHoursExtras = regularHoursExtras;
        }

        public Integer getNightHoursExtras() {
            return nightHoursExtras;
        }

        public void setNightHoursExtras(Integer nightHoursExtras) {
            this.nightHoursExtras = nightHoursExtras;
        }

        public Integer getSundayOvertime() {
            return sundayOvertime;
        }

        public void setSundayOvertime(Integer sundayOvertime) {
            this.sundayOvertime = sundayOvertime;
        }
    }

}
