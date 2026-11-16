package task2_composite;

public class Bed implements HospitalUnit {
    private final String bedNumber;
    private final boolean occupied;

    public Bed(String bedNumber, boolean occupied) {
        this.bedNumber = bedNumber;
        this.occupied = occupied;
    }

    @Override
    public String getName() { return "Ліжко №" + bedNumber; }

    @Override
    public int getTotalBeds() { return 1; }

    @Override
    public int getFreeBeds() { return occupied ? 0 : 1; }

    @Override
    public void printTree(String indent) {
        String status = occupied ? "зайняте" : "вільне";
        System.out.println(indent + "- " + getName() + " [" + status + "]");
    }
}
