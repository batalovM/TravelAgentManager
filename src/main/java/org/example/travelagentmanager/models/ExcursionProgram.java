package org.example.travelagentmanager.models;

/**
 * @author batal
 * @Date 19.09.2024
 */
//Экскурсионная программа
public class ExcursionProgram {
    private int excursionProgramId;
    private String excursionProgramName;
    public ExcursionProgram() {}
    public ExcursionProgram(int excursionProgramId, String excursionProgramName) {
        this.excursionProgramId = excursionProgramId;
        this.excursionProgramName = excursionProgramName;
    }

    public int getExcursionProgramId() {
        return excursionProgramId;
    }

    public void setExcursionProgramId(int excursionProgramId) {
        this.excursionProgramId = excursionProgramId;
    }

    public String getExcursionProgramName() {
        return excursionProgramName;
    }

    public void setExcursionProgramName(String excursionProgramName) {
        this.excursionProgramName = excursionProgramName;
    }
}
