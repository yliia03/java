package hospital;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {
    public static void main(String[] args) throws Exception {
        situation1Singleton();
        situation2Prototype();
        situation3ObjectPool();
    }

    static void situation1Singleton() throws Exception {
        header("СИТУАЦІЯ 1. Реєстр ліжкового фонду (Singleton)");
        Logger.resetClock(8, 0);

        Patient ivanenko = new Patient("Іваненко О.П.", "Кардіологія", "Стенокардія", "д-р Мельник");
        Patient petrenko = new Patient("Петренко І.М.", "Кардіологія", "Аритмія", "д-р Мельник");
        Patient sydorenko = new Patient("Сидоренко Т.В.", "Кардіологія", "Гіпертонічна криза", "д-р Лук'яненко");

        Logger.log("Приймальне відділення", "Запит реєстру ліжок");
        BedRegistry fromReception = BedRegistry.getInstance();
        Logger.log("Кардіологія", "Запит реєстру ліжок");
        BedRegistry fromCardiology = BedRegistry.getInstance();
        Logger.log("Система", "Той самий об'єкт у приймальному та кардіології: "+ (fromReception == fromCardiology) + "; створено екземплярів: " + BedRegistry.getCreatedInstances());

        Queue<BedRegistry> seen = new ConcurrentLinkedQueue<>();
        List<Thread> threads = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            Thread t = new Thread(() -> seen.add(BedRegistry.getInstance()));
            threads.add(t);
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
        boolean same = seen.stream().allMatch(r -> r == fromReception);
        Logger.log("Система", "4 одночасні запити з різних робочих місць отримали один реєстр: " + same);

        Logger.tick(600);
        fromReception.admit(ivanenko);
        Logger.log("Кардіологія", "Вільних ліжок у відділенні: " + fromCardiology.freeCount("Кардіологія"));
        fromReception.admit(petrenko);
        Logger.log("Кардіологія", "Вільних ліжок у відділенні: " + fromCardiology.freeCount("Кардіологія"));
        Logger.log("Приймальне відділення", "Надійшов " + sydorenko);
        fromReception.admit(sydorenko);

        Logger.tick(3600);
        fromCardiology.discharge(ivanenko);
        fromReception.admit(sydorenko);
        Logger.log("Система", "Подвійного призначення ліжка не було: стан фонду єдиний для всіх відділень");
    }

    static void situation2Prototype() {
        header("СИТУАЦІЯ 2. Плани обстеження й лікування (Prototype)");
        Logger.resetClock(9, 0);

        PlanTemplateRegistry templates = new PlanTemplateRegistry();
        TreatmentPlan pneumonia = TreatmentPlan.buildFromProtocol("Негоспітальна пневмонія",
                Arrays.asList("Загальний аналіз крові", "С-реактивний білок", "Рентген грудної клітки"),
                Arrays.asList("Інгаляції", "Дихальна гімнастика"),
                Arrays.asList("Амоксиклав 1 г 2 р/доба", "Парацетамол 500 мг за потреби"),
                "Стіл №15", "Ліжковий режим");
        templates.register("пневмонія", pneumonia);

        Patient a = new Patient("Коваль Н.Р.", "Терапія", "Пневмонія", "д-р Бондар");
        Patient b = new Patient("Шевчук Д.А.", "Терапія", "Пневмонія", "д-р Бондар");
        Patient c = new Patient("Гнатюк Л.С.", "Терапія", "Пневмонія", "д-р Бондар");

        Logger.tick(60);
        TreatmentPlan planA = templates.createFor("пневмонія", a);
        TreatmentPlan planB = templates.createFor("пневмонія", b);
        TreatmentPlan planC = templates.createFor("пневмонія", c);

        Logger.log("Д-р Бондар", "Коваль Н.Р. має алергію на пеніциліни: коригую власну копію плану");
        planA.removePrescription("Амоксиклав 1 г 2 р/доба");
        planA.addPrescription("Азитроміцин 500 мг 1 р/доба");
        Logger.log("Д-р Бондар", "Шевчук Д.А. — діабет: змінюю дієту в його копії");
        planB.setDiet("Стіл №9");
        planC.setRegime("Напівліжковий режим");

        Logger.log("План", planA.describe());
        Logger.log("План", planB.describe());
        Logger.log("План", planC.describe());
        Logger.log("План", templates.getTemplate("пневмонія").describe());
        Logger.log("Система", "Типовий план не змінився, копії незалежні: " + (planA != pneumonia && planA != planB));
    }

    static void situation3ObjectPool() {
        header("СИТУАЦІЯ 3. Апарати УЗД (Object Pool)");
        Logger.resetClock(10, 0);

        UltrasoundPool pool = new UltrasoundPool(2);

        Patient p1 = new Patient("Бойко А.В.", "Терапія", "Холецистит", "д-р Бондар");
        Patient p2 = new Patient("Мороз С.Ю.", "Хірургія", "Апендицит", "д-р Савчук");
        Patient p3 = new Patient("Лисенко В.О.", "Кардіологія", "Серцева недостатність", "д-р Мельник");

        Logger.tick(300);
        Optional<UltrasoundDevice> d1 = pool.acquire(p1, "черевна порожнина");
        Optional<UltrasoundDevice> d2 = pool.acquire(p2, "апендикс");
        Optional<UltrasoundDevice> d3 = pool.acquire(p3, "серце (ЕхоКГ)");

        Logger.tick(1200);
        Logger.log("Терапія", "Дослідження " + p1 + " завершено");
        d1.ifPresent(pool::release);

        Logger.log("Кардіологія", "Повторна спроба для " + p3);
        d3 = pool.acquire(p3, "серце (ЕхоКГ)");
        d3.ifPresent(d -> Logger.log("Кардіологія", p3 + " обстежується на апараті " + d.getId()
                + " (той самий об'єкт, що раніше обслуговував " + p1 + ")"));

        Logger.tick(900);
        d2.ifPresent(pool::release);
        d3.ifPresent(pool::release);
        d3.ifPresent(pool::release);
        Logger.log("Система", "Апаратів створено: 2, пацієнтів обслужено: 3; вільних " + pool.availableCount() + ", зайнятих " + pool.inUseCount());
    }

    private static void header(String title) {
        System.out.println();
        System.out.println("=== " + title + " ===");
    }
}
