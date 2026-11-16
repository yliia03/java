package task2_composite;

public interface HospitalUnit {
    String getName();
    int getTotalBeds();
    int getFreeBeds();
    void printTree(String indent);
}
