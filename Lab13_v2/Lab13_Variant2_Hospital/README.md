# Лабораторна робота № 13 — Поведінкові шаблони проєктування (Command, Chain of Responsibility)

**Варіант 2:** Обласна лікарня  
**Мова реалізації:** Java  

## Розподіл ролей у команді
* **Студент А:** реалізація Завдання 1 — «Призначення лікаря» (шаблон **Command**, пакет `src/task1/`), перехресне рев'ю Завдання 2.
* **Студент Б:** реалізація Завдання 2 — «Гаряча лінія лікарні» (шаблон **Chain of Responsibility**, пакет `src/task2/`), перехресне рев'ю Завдання 1.

## Структура проєкту
* `src/Main.java` — єдиний головний файл запуску обох демонстраційних сценаріїв.
* `src/task1/` — реалізація шаблону **Command** (`MedicalOrder`, `AddMedicationOrder`, `ChangeDoseOrder`, `CancelProcedureOrder`, `OrderQueue`, `PatientChart`, `Doctor`, `Nurse`, `ExecutionRecord`, `MainTask1`).
* `src/task2/` — реалізація шаблону **Chain of Responsibility** (`InquiryHandler`, `DutyOperator`, `NurseHandler`, `DutyDoctor`, `EmergencyService`, `HotlineService`, `Inquiry`, `InquiryType`, `MainTask2`).
* `docs/diagrams/` — UML-діаграми класів у форматах `.png` та `.puml`.
* `REVIEW.md` — звіт про перехресне рев'ю коду партнера.

## Інструкція запуску
```bash
javac -encoding UTF-8 -d out src/Main.java src/task1/*.java src/task2/*.java
java -cp out Main
```
