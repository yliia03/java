package ua.hospital.card;

public abstract class CardMark implements PatientCard {
    protected final PatientCard inner;

    protected CardMark(PatientCard inner) {
        this.inner = inner;
    }

    @Override
    public String render() {
        return inner.render();
    }

    @Override
    public void assignProcedure(String procedure) {
        inner.assignProcedure(procedure);
    }
}
