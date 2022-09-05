package assign6;

public enum Sex {
    M,
    F;

    public static Sex random() {
        return Sex.values()[(int)(Math.random() * Sex.values().length)];
    }
}
