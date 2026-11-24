package mn.edu.must.sqat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GradeCalculatorTest {

    @Test
    @DisplayName("90 оноо яг A дүн байх ёстой (хязгаарын тохиолдол)")
    void ninetyIsExactlyA() {
        GradeCalculator calc = new GradeCalculator();
        String grade = calc.letterGrade(90.0);
        assertEquals("A", grade);
    }

    @Test
    @DisplayName("89.99 оноо B дүн байх ёстой (A-ийн дээд хязгаарын яг доор)")
    void eightyNinePointNineNineIsB() {
        GradeCalculator calc = new GradeCalculator();
        String grade = calc.letterGrade(89.99);
        assertEquals("B", grade);
    }

    @Test
    @DisplayName("0 ба 100 хязгаарын оноонуудад зөв үсгэн дүн буцаах ёстой")
    void zeroAndHundredBoundaryScores() {
        GradeCalculator calc = new GradeCalculator();
        assertEquals("F", calc.letterGrade(0.0));
        assertEquals("A", calc.letterGrade(100.0));
    }

    @Test
    @DisplayName("0-100 хязгаараас гарсан оноонд IllegalArgumentException шидэх ёстой")
    void invalidLetterGradeScoreThrows() {
        GradeCalculator calc = new GradeCalculator();
        assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(-1.0));
        assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(101.0));
    }

    @Test
    @DisplayName("Бүрэлдэхүүн хэсгийн дээд оноонууд (10, 40, 10, 10, 30) нийлээд 100 гарах ёстой")
    void validMaxTotalScore() {
        GradeCalculator calc = new GradeCalculator();
        double total = calc.totalScore(10.0, 40.0, 10.0, 10.0, 30.0);
        assertEquals(100.0, total, 1e-6);
    }

    @Test
    @DisplayName("totalScore-ийн сөрөг болон хэтэрсэн утгуудад IllegalArgumentException шидэх ёстой")
    void invalidTotalScoreComponentsThrow() {
        GradeCalculator calc = new GradeCalculator();
        assertThrows(IllegalArgumentException.class, () -> calc.totalScore(-5.0, 20.0, 5.0, 5.0, 15.0));
        assertThrows(IllegalArgumentException.class, () -> calc.totalScore(10.0, 41.0, 10.0, 10.0, 30.0));
        assertThrows(IllegalArgumentException.class, () -> calc.totalScore(10.0, 40.0, 10.0, 10.0, 30.5));
    }

    @Test
    @DisplayName("totalScore-ийн бүрэлдэхүүн хэсгийн жижиг хэлбэлзэлд IllegalArgumentException шидэх")
    void fineBoundaryTotalScoreComponentsThrow() {
        GradeCalculator calc = new GradeCalculator();
        assertThrows(IllegalArgumentException.class, () -> calc.totalScore(-0.01, 20.0, 5.0, 5.0, 15.0));
        assertThrows(IllegalArgumentException.class, () -> calc.totalScore(10.01, 20.0, 5.0, 5.0, 15.0));
        assertThrows(IllegalArgumentException.class, () -> calc.totalScore(10.0, 40.01, 5.0, 5.0, 15.0));
        assertThrows(IllegalArgumentException.class, () -> calc.totalScore(10.0, 20.0, 10.01, 5.0, 15.0));
        assertThrows(IllegalArgumentException.class, () -> calc.totalScore(10.0, 20.0, 5.0, 10.01, 15.0));
        assertThrows(IllegalArgumentException.class, () -> calc.totalScore(10.0, 20.0, 5.0, 5.0, 30.01));
    }

    @ParameterizedTest(name = "оноо={0} -> дүн={1}")
    @CsvSource({
            "99.99,A",
            "95,A",
            "90,A",
            "89.99,B",
            "89.9,B",
            "85,B",
            "80,B",
            "79.9,C",
            "75,C",
            "70,C",
            "69.9,D",
            "65,D",
            "60,D",
            "59.99,F",
            "59.9,F",
            "30,F",
            "0.01,F",
            "0,F"
    })
    @DisplayName("Ердийн, нарийн бутархай ба хязгаарын оноонуудыг parameterized тестээр шалгах")
    void letterGradeBoundaries(double score, String expected) {
        GradeCalculator calc = new GradeCalculator();
        String actual = calc.letterGrade(score);
        assertEquals(expected, actual);
    }

    @ParameterizedTest(name = "ирц={0}, лаб={1}, сорил1={2}, сорил2={3}, шалгалт={4} -> нийлбэр={5}")
    @CsvSource({
            "0,0,0,0,0,0",
            "10,40,10,10,30,100",
            "8,32,7,8,24,79",
            "10,35,9,8,25,87"
    })
    @DisplayName("totalScore-ийн зөв оролтуудыг parameterized тестээр шалгах")
    void validTotalScoreParameterized(double att, double lab, double quiz1, double quiz2, double exam, double expectedTotal) {
        GradeCalculator calc = new GradeCalculator();
        double actualTotal = calc.totalScore(att, lab, quiz1, quiz2, exam);
        assertEquals(expectedTotal, actualTotal, 1e-6);
    }
}