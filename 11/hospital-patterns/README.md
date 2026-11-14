# ЛР №11. Структурні шаблони: Adapter, Decorator, Facade, Proxy

Варіант 2 — **Обласна лікарня**. Мова: Java 21 (без зовнішніх бібліотек).

## Розподіл у команді

| Завдання | Ситуація | Шаблон | Пакет | Виконавець |
|---|---|---|---|---|
| 1 | Перегляд медичних знімків | **Proxy** (віртуальний + захисний) | `ua.hospital.imaging` | Студент А |
| 2 | Підключення лабораторного аналізатора | **Adapter** | `ua.hospital.lab` | Студент А |
| 3 | Госпіталізація пацієнта | **Facade** | `ua.hospital.admission` | Студент Б |
| 4 | Позначки в картці пацієнта | **Decorator** | `ua.hospital.card` | Студент Б |

Діаграми кожен будує для своїх завдань (А — 01, 02; Б — 03, 04). Завдання 6 (крос-рев'ю) — спільно, див. `docs/REVIEW.md`.

## Збірка і запуск

```bash
mkdir -p out
javac -encoding UTF-8 -d out $(find src -name '*.java')
java -Dfile.encoding=UTF-8 -cp out ua.hospital.Main
```

У Windows-консолі перед запуском виконайте `chcp 65001`.

Раннер `ua.hospital.Main` має по одному методу на ситуацію: `situation1ProxyImages`, `situation2AdapterAnalyzer`, `situation3FacadeHospitalization`, `situation4DecoratorCardMarks`.
Журнал має формат `[час] [джерело] подія`; час умовний. Джерело показує, хто саме діє: обгортка (`ImageProxy`, `BioChemDriverAdapter`, `HospitalizationFacade`, `AllergyMark`…) чи обгорнутий об'єкт (`RemoteMedicalImage`, `BioChemDriver`, підсистеми, `BasicPatientCard`).

## Діаграми

`docs/diagrams/` — вихідні `.puml` (PlantUML) і зображення `.png`/`.svg`.
Зображення згенеровано Graphviz з тієї самої моделі класів, що й `.puml`. Щоб отримати «рідний» вигляд PlantUML: `plantuml docs/diagrams/*.puml`.
Умовні позначення: `+` public, `-` private, `#` protected, `~` package-private, підкреслення — static.

## Структура

```
src/main/java/ua/hospital/
  Main.java            раннер
  common/Log.java      журнал
  imaging/ lab/ admission/ card/
docs/diagrams/         UML
docs/REVIEW.md         крос-рев'ю та аргументація для захисту
```
