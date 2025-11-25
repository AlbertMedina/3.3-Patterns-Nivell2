package room;

public enum Difficulty {
    EASY, MEDIUM, HARD;

    public static Difficulty fromInt(int n) {
        if (n > 0 && n <= Difficulty.values().length) {
            return Difficulty.values()[n - 1];
        }
        return null;
    }
}
