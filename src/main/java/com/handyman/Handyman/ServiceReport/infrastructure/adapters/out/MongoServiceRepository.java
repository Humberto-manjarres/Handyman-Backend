package com.handyman.Handyman.ServiceReport.infrastructure.adapters.out;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface MongoServiceRepository extends ReactiveMongoRepository<ServiceData,String> {

    @Query("{idService: ?0}")
    Mono<ServiceData> findServices(String idService);

}
