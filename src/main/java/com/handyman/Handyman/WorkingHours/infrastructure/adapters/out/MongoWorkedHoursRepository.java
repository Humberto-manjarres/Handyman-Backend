package com.handyman.Handyman.WorkingHours.infrastructure.adapters.out;



import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface MongoWorkedHoursRepository extends ReactiveMongoRepository<ServiceData,String> {


   Mono<com.handyman.Handyman.WorkingHours.infrastructure.adapters.out.ServiceData> findByIdTechnical(String id);

   @Query("{idTechnical: ?0, week: ?1}")
   Flux<ServiceData> findByIds(String id,String week);

   @Query("{idTechnical: ?0, week: ?1}")
   List<ServiceData> findByIdsList(String id, String week);

   @Query("{idTechnical: ?0, $and: [{startDate: {$gte: new Date(?1)}},{endDate: {$lte: new Date(?2)}}]}")
   Mono<ServiceData> findById1(String id,String startDate,String endDate);

}
