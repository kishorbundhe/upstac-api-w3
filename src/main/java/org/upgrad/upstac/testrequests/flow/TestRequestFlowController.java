package org.upgrad.upstac.testrequests.flow;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.upgrad.upstac.exception.AppException;
import org.upgrad.upstac.testrequests.RequestStatus;
import org.upgrad.upstac.testrequests.TestRequest;
import org.upgrad.upstac.testrequests.TestRequestRepository;
import org.upgrad.upstac.testrequests.lab.CreateLabResult;
import org.upgrad.upstac.users.User;

import javax.persistence.*;
import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.util.List;

import static org.upgrad.upstac.exception.UpgradResponseStatusException.asBadRequest;
import static org.upgrad.upstac.exception.UpgradResponseStatusException.asConstraintViolation;

@RestController
@RequestMapping("/api/testrequests")
public class TestRequestFlowController {
    @Autowired
    TestRequestFlowService testRequestFlowService;

    @PreAuthorize("hasAnyRole('DOCTOR')")
    @GetMapping("/flow/{id}")
    public List<TestRequestFlow> updateLabTest(@PathVariable Long id) {
        try {
            return testRequestFlowService.findByRequest(id);
        } catch (ConstraintViolationException e) {
            throw asConstraintViolation(e);
        }catch (AppException e) {
            throw asBadRequest(e.getMessage());
        }
    }
}
