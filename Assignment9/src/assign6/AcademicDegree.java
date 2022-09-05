package assign6;

public enum AcademicDegree {
    BACHELOR,
    MASTER,
    PHD;

    public static AcademicDegree random() {
        return AcademicDegree.values()[(int)(Math.random() * AcademicDegree.values().length)];
    }
}
