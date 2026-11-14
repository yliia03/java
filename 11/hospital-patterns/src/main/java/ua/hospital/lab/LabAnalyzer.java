package ua.hospital.lab;

import java.util.List;

public interface LabAnalyzer {

    String name();

    List<LabResult> fetchResults(String patientId);
}
