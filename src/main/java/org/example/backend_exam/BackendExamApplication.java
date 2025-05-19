package org.example.backend_exam;

import org.example.backend_exam.entities.*;
import org.example.backend_exam.entities.Credit.CreditStatus;
import org.example.backend_exam.entities.MortgageCredit.PropertyType;
import org.example.backend_exam.entities.Repayment.RepaymentType;
import org.example.backend_exam.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class BackendExamApplication {
    private static final Logger log = LoggerFactory.getLogger(BackendExamApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BackendExamApplication.class, args);
    }

    @Bean
    public CommandLineRunner testDao(ClientRepository clientRepository,
                                    CreditRepository creditRepository,
                                    PersonalCreditRepository personalCreditRepository,
                                    MortgageCreditRepository mortgageCreditRepository,
                                    ProfessionalCreditRepository professionalCreditRepository,
                                    RepaymentRepository repaymentRepository) {
        return args -> {
            log.info("Starting DAO layer tests...");

            // Test 1: Create and save clients
            log.info("Test 1: Creating and saving clients");
            Client client1 = new Client();
            client1.setName("John Doe");
            client1.setEmail("john.doe@example.com");

            Client client2 = new Client();
            client2.setName("Jane Smith");
            client2.setEmail("jane.smith@example.com");

            clientRepository.saveAll(List.of(client1, client2));
            log.info("Saved clients: {}", clientRepository.count());

            // Test 2: Find client by email
            log.info("Test 2: Finding client by email");
            Optional<Client> foundClient = clientRepository.findByEmail("john.doe@example.com");
            foundClient.ifPresent(c -> log.info("Found client: {}", c.getName()));

            // Test 3: Create and save different types of credits
            log.info("Test 3: Creating and saving different types of credits");

            // Personal Credit
            PersonalCredit personalCredit = new PersonalCredit();
            personalCredit.setClient(client1);
            personalCredit.setRequestDate(new Date());
            personalCredit.setStatus(CreditStatus.IN_PROGRESS);
            personalCredit.setAmount(10000.0);
            personalCredit.setDuration(24);
            personalCredit.setInterestRate(5.5);
            personalCredit.setPurpose("Car Purchase");
            personalCreditRepository.save(personalCredit);

            // Mortgage Credit
            MortgageCredit mortgageCredit = new MortgageCredit();
            mortgageCredit.setClient(client1);
            mortgageCredit.setRequestDate(new Date());
            mortgageCredit.setStatus(CreditStatus.ACCEPTED);
            mortgageCredit.setAcceptanceDate(new Date());
            mortgageCredit.setAmount(200000.0);
            mortgageCredit.setDuration(240);
            mortgageCredit.setInterestRate(3.2);
            mortgageCredit.setPropertyType(PropertyType.APARTMENT);
            mortgageCreditRepository.save(mortgageCredit);

            // Professional Credit
            ProfessionalCredit professionalCredit = new ProfessionalCredit();
            professionalCredit.setClient(client2);
            professionalCredit.setRequestDate(new Date());
            professionalCredit.setStatus(CreditStatus.IN_PROGRESS);
            professionalCredit.setAmount(50000.0);
            professionalCredit.setDuration(36);
            professionalCredit.setInterestRate(4.0);
            professionalCredit.setPurpose("Business Expansion");
            professionalCredit.setCompanyName("Smith Enterprises");
            professionalCreditRepository.save(professionalCredit);

            log.info("Saved credits: {}", creditRepository.count());

            // Test 4: Create and save repayments
            log.info("Test 4: Creating and saving repayments");

            Repayment repayment1 = new Repayment();
            repayment1.setCredit(mortgageCredit);
            repayment1.setDate(new Date());
            repayment1.setAmount(1000.0);
            repayment1.setType(RepaymentType.MONTHLY_PAYMENT);

            // Create a repayment for next month
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, 1);

            Repayment repayment2 = new Repayment();
            repayment2.setCredit(mortgageCredit);
            repayment2.setDate(calendar.getTime());
            repayment2.setAmount(1000.0);
            repayment2.setType(RepaymentType.MONTHLY_PAYMENT);

            Repayment repayment3 = new Repayment();
            repayment3.setCredit(personalCredit);
            repayment3.setDate(new Date());
            repayment3.setAmount(500.0);
            repayment3.setType(RepaymentType.MONTHLY_PAYMENT);

            repaymentRepository.saveAll(List.of(repayment1, repayment2, repayment3));
            log.info("Saved repayments: {}", repaymentRepository.count());

            // Test 5: Test repository query methods
            log.info("Test 5: Testing repository query methods");

            // Find credits by client
            List<Credit> clientCredits = creditRepository.findByClientId(client1.getId());
            log.info("Client 1 has {} credits", clientCredits.size());

            // Find credits by status
            List<Credit> inProgressCredits = creditRepository.findByStatus(CreditStatus.IN_PROGRESS);
            log.info("There are {} credits in progress", inProgressCredits.size());

            // Find personal credits by purpose
            List<PersonalCredit> carLoans = personalCreditRepository.findByPurpose("Car Purchase");
            log.info("There are {} car purchase loans", carLoans.size());

            // Find mortgage credits by property type
            List<MortgageCredit> apartmentLoans = mortgageCreditRepository.findByPropertyType(PropertyType.APARTMENT);
            log.info("There are {} apartment loans", apartmentLoans.size());

            // Find professional credits by company name
            List<ProfessionalCredit> companyLoans = professionalCreditRepository.findByCompanyName("Smith Enterprises");
            log.info("There are {} loans for Smith Enterprises", companyLoans.size());

            // Find repayments by credit
            List<Repayment> mortgageRepayments = repaymentRepository.findByCreditId(mortgageCredit.getId());
            log.info("Mortgage credit has {} repayments", mortgageRepayments.size());

            // Find repayments by type
            List<Repayment> monthlyPayments = repaymentRepository.findByType(RepaymentType.MONTHLY_PAYMENT);
            log.info("There are {} monthly payments", monthlyPayments.size());

            log.info("DAO layer tests completed successfully!");
        };
    }
}
