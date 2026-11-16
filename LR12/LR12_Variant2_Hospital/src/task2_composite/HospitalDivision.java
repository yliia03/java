package task2_composite;

import java.util.ArrayList;
import java.util.List;

public class HospitalDivision implements HospitalUnit {
    private final String name;
    private final String type;
    private final List<HospitalUnit> children = new ArrayList<>();

    public HospitalDivision(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public void add(HospitalUnit unit) {
        children.add(unit);
    }

    public void remove(HospitalUnit unit) {
        children.remove(unit);
    }

    @Override
    public String getName() { return type + " «" + name + "»"; }

    @Override
    public int getTotalBeds() {
        int sum = 0;
        for (HospitalUnit unit : children) {
            sum += unit.getTotalBeds();
        }
        return sum;
    }

    @Override
    public int getFreeBeds() {
        int sum = 0;
        for (HospitalUnit unit : children) {
            sum += unit.getFreeBeds();
        }
        return sum;
    }

    @Override
    public void printTree(String indent) {
        int total = getTotalBeds();
        int free = getFreeBeds();
        int occupied = total - free;
        System.out.printf("%s+ %s (Всього: %d, Вільних: %d, Зайнятих: %d)%n",
                indent, getName(), total, free, occupied);
        for (HospitalUnit unit : children) {
            unit.printTree(indent + "    ");
        }
    }
}
