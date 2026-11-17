
package com.devtrack.backend.controller;

import com.devtrack.backend.model.JobApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final List<JobApplication> applications =
            new CopyOnWriteArrayList<>();

    private final AtomicLong nextId = new AtomicLong(1); // Gera um indentificador para cada candidatura

    @GetMapping // Consultar candidaturas
    public List<JobApplication> getAllApplications() {
        return List.copyOf(applications);
    }

    @PostMapping // Cria uma nova candidatura
    public ResponseEntity<JobApplication> createApplication(
            @RequestBody JobApplication request) { // Recebe os dados JSON enviados

        JobApplication application = new JobApplication(
                nextId.getAndIncrement(),
                request.company(),
                request.role(),
                request.status()
        );

        applications.add(application);

        return ResponseEntity // Devolve a candidatura criada e o código http 201
                .status(HttpStatus.CREATED)
                .body(application);
    }
}
