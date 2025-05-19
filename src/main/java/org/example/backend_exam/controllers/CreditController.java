package org.example.backend_exam.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.backend_exam.dtos.*;
import org.example.backend_exam.services.CreditService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credits")
@Tag(name = "Credit Management", description = "APIs for managing credits")
public class CreditController {

    private final CreditService creditService;

    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    // Generic credit endpoints
    @GetMapping
    @Operation(summary = "Get all credits", description = "Retrieves a list of all credits")
    public ResponseEntity<List<CreditDTO>> getAllCredits() {
        return ResponseEntity.ok(creditService.getAllCredits());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get credit by ID", description = "Retrieves a credit by its ID")
    public ResponseEntity<CreditDTO> getCreditById(@PathVariable Long id) {
        return ResponseEntity.ok(creditService.getCreditById(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a credit", description = "Deletes a credit by its ID")
    public ResponseEntity<Void> deleteCredit(@PathVariable Long id) {
        creditService.deleteCredit(id);
        return ResponseEntity.noContent().build();
    }

    // Personal credit endpoints
    @GetMapping("/personal")
    @Operation(summary = "Get all personal credits", description = "Retrieves a list of all personal credits")
    public ResponseEntity<List<PersonalCreditDTO>> getAllPersonalCredits() {
        return ResponseEntity.ok(creditService.getAllPersonalCredits());
    }

    @GetMapping("/personal/{id}")
    @Operation(summary = "Get personal credit by ID", description = "Retrieves a personal credit by its ID")
    public ResponseEntity<PersonalCreditDTO> getPersonalCreditById(@PathVariable Long id) {
        return ResponseEntity.ok(creditService.getPersonalCreditById(id));
    }

    @PostMapping("/personal")
    @Operation(summary = "Create a new personal credit", description = "Creates a new personal credit")
    public ResponseEntity<PersonalCreditDTO> createPersonalCredit(@RequestBody PersonalCreditDTO personalCreditDTO) {
        return new ResponseEntity<>(creditService.savePersonalCredit(personalCreditDTO), HttpStatus.CREATED);
    }

    @PutMapping("/personal/{id}")
    @Operation(summary = "Update a personal credit", description = "Updates an existing personal credit")
    public ResponseEntity<PersonalCreditDTO> updatePersonalCredit(@PathVariable Long id, @RequestBody PersonalCreditDTO personalCreditDTO) {
        return ResponseEntity.ok(creditService.updatePersonalCredit(id, personalCreditDTO));
    }

    // Mortgage credit endpoints
    @GetMapping("/mortgage")
    @Operation(summary = "Get all mortgage credits", description = "Retrieves a list of all mortgage credits")
    public ResponseEntity<List<MortgageCreditDTO>> getAllMortgageCredits() {
        return ResponseEntity.ok(creditService.getAllMortgageCredits());
    }

    @GetMapping("/mortgage/{id}")
    @Operation(summary = "Get mortgage credit by ID", description = "Retrieves a mortgage credit by its ID")
    public ResponseEntity<MortgageCreditDTO> getMortgageCreditById(@PathVariable Long id) {
        return ResponseEntity.ok(creditService.getMortgageCreditById(id));
    }

    @PostMapping("/mortgage")
    @Operation(summary = "Create a new mortgage credit", description = "Creates a new mortgage credit")
    public ResponseEntity<MortgageCreditDTO> createMortgageCredit(@RequestBody MortgageCreditDTO mortgageCreditDTO) {
        return new ResponseEntity<>(creditService.saveMortgageCredit(mortgageCreditDTO), HttpStatus.CREATED);
    }

    @PutMapping("/mortgage/{id}")
    @Operation(summary = "Update a mortgage credit", description = "Updates an existing mortgage credit")
    public ResponseEntity<MortgageCreditDTO> updateMortgageCredit(@PathVariable Long id, @RequestBody MortgageCreditDTO mortgageCreditDTO) {
        return ResponseEntity.ok(creditService.updateMortgageCredit(id, mortgageCreditDTO));
    }

    // Professional credit endpoints
    @GetMapping("/professional")
    @Operation(summary = "Get all professional credits", description = "Retrieves a list of all professional credits")
    public ResponseEntity<List<ProfessionalCreditDTO>> getAllProfessionalCredits() {
        return ResponseEntity.ok(creditService.getAllProfessionalCredits());
    }

    @GetMapping("/professional/{id}")
    @Operation(summary = "Get professional credit by ID", description = "Retrieves a professional credit by its ID")
    public ResponseEntity<ProfessionalCreditDTO> getProfessionalCreditById(@PathVariable Long id) {
        return ResponseEntity.ok(creditService.getProfessionalCreditById(id));
    }

    @PostMapping("/professional")
    @Operation(summary = "Create a new professional credit", description = "Creates a new professional credit")
    public ResponseEntity<ProfessionalCreditDTO> createProfessionalCredit(@RequestBody ProfessionalCreditDTO professionalCreditDTO) {
        return new ResponseEntity<>(creditService.saveProfessionalCredit(professionalCreditDTO), HttpStatus.CREATED);
    }

    @PutMapping("/professional/{id}")
    @Operation(summary = "Update a professional credit", description = "Updates an existing professional credit")
    public ResponseEntity<ProfessionalCreditDTO> updateProfessionalCredit(@PathVariable Long id, @RequestBody ProfessionalCreditDTO professionalCreditDTO) {
        return ResponseEntity.ok(creditService.updateProfessionalCredit(id, professionalCreditDTO));
    }
}