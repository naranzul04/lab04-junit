```markdown
# Лаборатори №4 — JUnit 5 ашиглан нэгжийн тест бичих ба мутацийн шинжилгээ

## Оюутны мэдээлэл
- **Нэр:** А. Наранзул
- **Оюутны код:** B222270823
- **Хичээл:** Програм хангамжийн чанарын баталгаа ба туршилт
- **Репозитори:** https://github.com/naranzul04/lab04-junit.git

## Хөгжүүлэлтийн орчин

```text
$ java -version
openjdk version "17.0.16" 2025-07-15
OpenJDK Runtime Environment Temurin-17.0.16+8 (build 17.0.16+8)
OpenJDK 64-Bit Server VM Temurin-17.0.16+8 (build 17.0.16+8, mixed mode, sharing)

$ mvn -version
Apache Maven 3.9.16 (2bdd9fddda4b155ebf8000e807eb73fd829a51d5)
Maven home: C:\ProgramData\chocolatey\lib\maven\apache-maven-3.9.16
Java version: 17.0.16, vendor: Eclipse Adoptium, runtime: C:\Program Files\Eclipse Adoptium\jdk-17.0.16.8-hotspot
Default locale: en_US, platform encoding: Cp1252
OS name: "windows 11", version: "10.0", arch: "amd64", family: "windows"

```

## Тестийн үр дүн

* **Тестийн методын тоо:** `9` (`@Test` 7 метод, `@ParameterizedTest` 2 метод)
* **Maven Surefire `Tests run` тоо:** `29` (`Failures: 0, Errors: 0, Skipped: 0`, `BUILD SUCCESS`)
* **Гаралтын файлууд:** `results/mvn-test.txt`, `results/mvn-test-mutant.txt`

## Мутацийн туршилтын дүн

`GradeCalculator.letterGrade(double score)` доторх `score >= 90.0` нөхцөлийг түр `score > 90.0` болгон өөрчилж `mvn test` ажиллуулахад яг `90.0` онооны хил дээр `"A"` буцаах ёстой тестүүд унасан (`results/mvn-test-mutant.txt`):

* `ninetyIsExactlyA` (`@Test`)
* `letterGradeBoundaries` (`@ParameterizedTest` — `90,A`)

## Нэмэлт даалгавар (AI-аар тест өргөтгөх ба хянах)

* **Нэмсэн тест тохиолдлууд:** `GradeCalculatorTest` дээр `fineBoundaryTotalScoreComponentsThrow` (`@Test` — ирц, лаб, сорил 1, сорил 2, шалгалтын бүрэлдэхүүн хэсгийн `0.01` хэлбэлзэл ба `-0.01` сөрөг утга) болон `letterGradeBoundaries` `@ParameterizedTest` дотор `99.99`, `89.99`, `89.9`, `79.9`, `69.9`, `59.99`, `59.9`, `0.01` зэрэг нарийн бутархай хилүүдийг нэмж, нийт `29` тест болгон өргөтгөв.
* **AI ашиглалтын дүгнэлт:** AI нь хил хязгаар, буруу оролт болон `@ParameterizedTest` (`@CsvSource`) тохиолдлуудыг хурдан төлөвлөж, давхардал багатай тест загвар гаргахад үр дүнтэй байсан ч бодит кодын хязгаар ба хүлээгдэх зан төлөвийг заавал өөрөө шалгаж, `mvn test` (`results/mvn-test.txt`) гаралтыг уншиж нягтлах шаардлагатай байлаа. Ингэснээр завсрын бутархай утга болон бүрэлдэхүүн хэсгийн хязгаар алдагдах эрсдэлийг илүү нарийн барьж чадав.

## Дүгнэлт

Энэхүү лабораторийн ажлаар JUnit 5 болон Maven Surefire ашиглан `GradeCalculator` классын `letterGrade` болон `totalScore` методуудад нэгжийн, хил хязгаарын, хүчингүй оролтын болон `@ParameterizedTest` тестүүдийг бичиж амжилттай ажиллууллаа. Хил хязгаарын утгууд болох `0`, `60`, `70`, `80`, `90`, `100` болон бутархай хил (`59.99`, `89.99` зэрэг) зэргийг шалгаснаар дүнгийн хуваарилалтын логик зөв ажиллаж буйг баталгаажуулав. Мөн бүрэлдэхүүн хэсгийн оноонуудын хязгаар (`0..10`, `0..40`, `0..30`) болон `0..100`-аас хэтэрсэн утгуудад `IllegalArgumentException` зөв шидэж байгааг шалгалаа. Гараар `score >= 90.0`-ийг `score > 90.0` болгон мутаци үүсгэхэд хилийн тестүүд унасан нь одоогийн тест багц тэгш хэмийн болон хилийн нөхцөлийн өөрчлөлтийг илрүүлж чадаж байгааг харууллаа. Түүнчлэн `results/mvn-test.txt` болон `results/mvn-test-mutant.txt` файлуудыг хадгалж, `.gitignore`-оор `target/` хавтас болон түр файлуудыг тусгаарласнаар төслийн бүтэц цэгцтэй болсон. Цаашид `NaN`, `Infinity` зэрэг тусгай float/double утгуудын тестүүдийг нэмж өргөжүүлэх нь нэгжийн тестийн найдвартай байдлыг улам нэмэгдүүлнэ.

```

```
