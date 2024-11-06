package org.example.travelagentmanager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author batal
 * @Date 10.10.2024
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Hotel {
    private int id;
    private String hotelName;
    private String hotelClass;
}
