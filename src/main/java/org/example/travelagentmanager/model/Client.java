package org.example.travelagentmanager.model;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

/**
 * @author batal
 * @Date 10.10.2024
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Client {
    private int id;
    private String lastname;
    private String firstname;
    private String surname;
    private LocalDate dateOfBirth;
    private String passportSeries;
    private String passportNumber;
    private LocalDate dateOfIssue;
    private String issueBy;
    private String photo;
}
