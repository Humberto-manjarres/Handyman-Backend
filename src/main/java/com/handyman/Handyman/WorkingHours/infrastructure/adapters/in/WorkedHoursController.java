package com.handyman.Handyman.WorkingHours.infrastructure.adapters.in;

import com.handyman.Handyman.WorkingHours.application.models.QueryServiceRequest;
import com.handyman.Handyman.WorkingHours.application.models.QueryServiceResponse;
import com.handyman.Handyman.WorkingHours.application.ports.in.QueryWorkedHoursUseCase;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = {"http://localhost:4200","*"})
@RestController
@RequestMapping("/api/worked-hours")
public class WorkedHoursController {

    private final QueryWorkedHoursUseCase queryWorkedHoursUseCase;

    public WorkedHoursController(QueryWorkedHoursUseCase queryWorkedHoursUseCase) {
        this.queryWorkedHoursUseCase = queryWorkedHoursUseCase;
    }

    /*@GetMapping
    public Mono<QueryServiceResponse> queryWorkedHours(@RequestBody QueryServiceRequest request){
        return queryWorkedHoursUseCase.execute(request);
    }*/

    /*@GetMapping
    public Mono<ResponseEntity<Mono<QueryServiceResponse>>> queryWorkedHours(@RequestBody QueryServiceRequest request){
        return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(queryWorkedHoursUseCase.execute(request)));
    }*/

    @GetMapping
    public Mono<ResponseEntity<QueryServiceResponse>> queryWorkedHours(@RequestBody QueryServiceRequest request){
        return queryWorkedHoursUseCase.execute(request)
                .map(resp -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(resp))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/{idTechnical}/{week}")
    public Mono<ResponseEntity<QueryServiceResponse>> queryWorkedHoursParams(@PathVariable String idTechnical,@PathVariable String week){
        return queryWorkedHoursUseCase.execute(new QueryServiceRequest(idTechnical,week))
                .map(resp -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(resp))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
