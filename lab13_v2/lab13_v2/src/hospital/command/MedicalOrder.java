package hospital.command;

public interface MedicalOrder {
    void execute();

    void undo();

    String description();
}
