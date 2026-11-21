package task1;

public interface MedicalOrder {
    void execute();
    void undo();
    String description();
}
