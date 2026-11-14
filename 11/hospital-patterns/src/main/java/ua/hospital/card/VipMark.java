package ua.hospital.card;

import ua.hospital.common.Log;

public class VipMark extends CardMark {

    public VipMark(PatientCard inner) {
        super(inner);
    }

    @Override
    public String render() {
        Log.log("VipMark", "Додає позначку VIP і передає картку далі");
        return super.render() + "\n    [VIP] Підвищена конфіденційність, окрема палата";
    }
}
