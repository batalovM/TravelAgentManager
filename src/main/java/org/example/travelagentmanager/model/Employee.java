package org.example.travelagentmanager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author batal
 * @Date 10.10.2024
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Employee {
    private int id;
    private String lastName;
    private String firstName;
    private String surName;
}
