package hospital.discharge;

public interface DischargeDocument {

    String title();

    String patientName();

    void sign(String doctor);

    boolean isSigned();

    /** Повний текст документа. */
    String content();
}
