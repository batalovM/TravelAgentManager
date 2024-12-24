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
@Getter
@Setter
public class Route {
    private int id;
    private int countryId;
    private String countryName;
    private String routeName;
    private int duration;
}

