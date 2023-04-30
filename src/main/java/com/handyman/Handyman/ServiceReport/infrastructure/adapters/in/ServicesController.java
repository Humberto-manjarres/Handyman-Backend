package com.handyman.Handyman.ServiceReport.infrastructure.adapters.in;

import com.handyman.Handyman.ServiceReport.application.models.CreateServiceRequest;
import com.handyman.Handyman.ServiceReport.application.models.CreateServiceResponse;
import com.handyman.Handyman.ServiceReport.application.ports.in.CreateServiceUseCase;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = {"http://localhost:4200","*"})
@RestController
@RequestMapping("/api/service")
public class ServicesController {

    private final CreateServiceUseCase createServiceUseCase;

    public ServicesController(CreateServiceUseCase createServiceUseCase) {
        this.createServiceUseCase = createServiceUseCase;
    }

    /*@PostMapping
    public Mono<ResponseEntity<Mono<CreateServiceResponse>>> createService(@RequestBody Mono<CreateServiceRequest> request) {
        System.out.println("prueba");
        return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(request.flatMap(req -> createServiceUseCase.execute(req))));
    }*/

    @PostMapping
    public Mono<ResponseEntity<Mono<CreateServiceResponse>>> createService2(@RequestBody CreateServiceRequest request){
        return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
                .body(createServiceUseCase.execute(request)));
    }

//    @PostMapping
//    public Mono<ResponseEntity<CreateServiceResponse>> createService(@RequestBody Mono<CreateServiceRequest> request){
//        return request.flatMap(req -> createServiceUseCase.execute(req).map(r -> ResponseEntity
//                .created(URI.create("/api/service/".concat(r.getId())))
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(r)));
//    }

}
