package mn.edu.must.sqat;

public class GradeCalculator {

    public String letterGrade(double score) {
        if (Double.isNaN(score) || Double.isInfinite(score) || score < 0.0 || score > 100.0) {
            throw new IllegalArgumentException("Оноо 0-100 хооронд байх ёстой: " + score);
        }
        if (score >= 90.0) {
            return "A";
        } else if (score >= 80.0) {
            return "B";
        } else if (score >= 70.0) {
            return "C";
        } else if (score >= 60.0) {
            return "D";
        } else {
            return "F";
        }
    }

    public double totalScore(double att, double lab, double quiz1, double quiz2, double exam) {
        validateComponent("Ирц", att, 0.0, 10.0);
        validateComponent("Лаб+бие даалт", lab, 0.0, 40.0);
        validateComponent("Сорил 1", quiz1, 0.0, 10.0);
        validateComponent("Сорил 2", quiz2, 0.0, 10.0);
        validateComponent("Шалгалт", exam, 0.0, 30.0);

        return att + lab + quiz1 + quiz2 + exam;
    }

    private void validateComponent(String name, double value, double min, double max) {
        if (Double.isNaN(value) || Double.isInfinite(value) || value < min || value > max) {
            throw new IllegalArgumentException(
                    String.format("%s оноо [%.1f, %.1f] хязгаарт байх ёстой: %.2f", name, min, max, value)
            );
        }
    }
}
