package task2;

import java.util.Arrays;

public class MainTask2 {
    public static void main(String[] args) {
        System.out.println("=== ЗАВДАННЯ 2: ГАРЯЧА ЛІНІЯ ЛІКАРНІ (ШАБЛОН CHAIN OF RESPONSIBILITY) ===");
        EventLog log = new EventLog();
        HotlineService hotline = new HotlineService(log);

        InquiryHandler operator = new DutyOperator("Черговий оператор", log);
        InquiryHandler nurse = new NurseHandler("Медсестра-консультант", log);
        InquiryHandler doctor = new DutyDoctor("Черговий лікар", log);
        InquiryHandler emergency = new EmergencyService("Служба екстреної допомоги", log);

        System.out.println();
        System.out.println("--- Крок 1: Денний режим (повний ланцюг із 4 ланок) ---");
        hotline.configure("Денна зміна", Arrays.asList(operator, nurse, doctor, emergency));

        hotline.submit(new Inquiry(101, "Іваненко О.М.", InquiryType.GENERAL, "Як працює кабінет УЗД?"));
        System.out.println();
        hotline.submit(new Inquiry(102, "Сидоренко В.П.", InquiryType.TREATMENT, "Чи можна приймати таблетки після їжі?"));
        System.out.println();
        hotline.submit(new Inquiry(103, "Мельник Т.А.", InquiryType.URGENT_MEDICAL, "Висока температура 39.5, не збивається"));
        System.out.println();
        hotline.submit(new Inquiry(104, "Бондар К.С.", InquiryType.LIFE_THREAT, "Гострий біль у грудях, втрата свідомості"));

        System.out.println();
        System.out.println("--- Крок 2: Нічний режим (перебудова ланцюга без оператора та медсестри) ---");
        hotline.configure("Нічна зміна", Arrays.asList(doctor, emergency));

        hotline.submit(new Inquiry(105, "Кравчук М.І.", InquiryType.LIFE_THREAT, "Травма після ДТП, сильна кровотеча"));
        System.out.println();
        hotline.submit(new Inquiry(106, "Ткачук Л.В.", InquiryType.OTHER, "Запит на отримання архівної довідки за 2020 рік"));
    }
}
