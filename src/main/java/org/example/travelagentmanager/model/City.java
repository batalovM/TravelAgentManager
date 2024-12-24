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
public class City {
    private int id;
    private int hotelId;
    private int excursionProgramId;
    private String hotelName;
    private String excursionProgramName;
    private String cityName;
}
