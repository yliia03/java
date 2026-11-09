package hospital.referral;

import hospital.util.EventLog;

public class ReferralDirector {

    private final ReferralBuilder builder;

    public ReferralDirector(ReferralBuilder builder) {
        this.builder = builder;
    }

    public Referral buildPreoperative(String patient, String doctor) {
        EventLog.log("Директор", "Типове направлення: передопераційне обстеження");
        return builder
                .forPatient(patient)
                .addAnalysis("Загальний аналіз крові")
                .addAnalysis("Коагулограма")
                .addProcedure("ЕКГ")
                .urgency(Urgency.PLANNED)
                .fasting()
                .note("Передопераційне обстеження")
                .signedBy(doctor)
                .build();
    }
    
    public Referral buildCheckup(String patient, String doctor) {
        EventLog.log("Директор", "Типове направлення: профогляд");
        return builder
                .forPatient(patient)
                .addAnalysis("Загальний аналіз крові")
                .addAnalysis("Загальний аналіз сечі")
                .addProcedure("Флюорографія")
                .signedBy(doctor)
                .build();
    }
}
