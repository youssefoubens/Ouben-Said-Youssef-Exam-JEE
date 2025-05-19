package org.example.backend_exam.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.backend_exam.dtos.RepaymentDTO;
import org.example.backend_exam.services.CreditService;
import org.example.backend_exam.services.RepaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/repayments")
@Tag(name = "Repayment Management", description = "APIs for managing repayments")
public class RepaymentController {

    private final RepaymentService repaymentService;
    private final CreditService creditService;

    public RepaymentController(RepaymentService repaymentService, CreditService creditService) {
        this.repaymentService = repaymentService;
        this.creditService = creditService;
    }

    @GetMapping
    @Operation(summary = "Get all repayments", description = "Retrieves a list of all repayments")
    public ResponseEntity<List<RepaymentDTO>> getAllRepayments() {
        return ResponseEntity.ok(repaymentService.getAllRepayments());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get repayment by ID", description = "Retrieves a repayment by its ID")
    public ResponseEntity<RepaymentDTO> getRepaymentById(@PathVariable Long id) {
        return ResponseEntity.ok(repaymentService.getRepaymentById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new repayment", description = "Creates a new repayment")
    public ResponseEntity<RepaymentDTO> createRepayment(@RequestBody RepaymentDTO repaymentDTO) {
        // Verify credit exists
        creditService.getCreditById(repaymentDTO.getCreditId());
        return new ResponseEntity<>(repaymentService.saveRepayment(repaymentDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a repayment", description = "Updates an existing repayment")
    public ResponseEntity<RepaymentDTO> updateRepayment(@PathVariable Long id, @RequestBody RepaymentDTO repaymentDTO) {
        // Verify credit exists
        creditService.getCreditById(repaymentDTO.getCreditId());
        return ResponseEntity.ok(repaymentService.updateRepayment(id, repaymentDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a repayment", description = "Deletes a repayment by its ID")
    public ResponseEntity<Void> deleteRepayment(@PathVariable Long id) {
        repaymentService.deleteRepayment(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/credit/{creditId}")
    @Operation(summary = "Get repayments by credit ID", description = "Retrieves all repayments for a specific credit")
    public ResponseEntity<List<RepaymentDTO>> getRepaymentsByCreditId(@PathVariable Long creditId) {
        // Verify credit exists
        creditService.getCreditById(creditId);
        return ResponseEntity.ok(repaymentService.getRepaymentsByCreditId(creditId));
    }
}