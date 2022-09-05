package assign6;

import java.util.Locale;

public enum Nationality {
    PL(new Locale("Polish")),
    UA(new Locale("Ukrainian")),
    BY(new Locale("Belarusian")),
    SK(new Locale("Slovak")),
    LT(new Locale("Lithuanian")),
    LV(new Locale("Latvian")),
    UK(new Locale("English")),
    IN(new Locale("Indian")),
    CN(new Locale("Chinese")),
    VN(new Locale("Vietnamese"));

    private final Locale locale;

    Nationality(Locale locale) {
        this.locale = locale;
    }

    public Locale getLocale() {
        return locale;
    }

    public static Nationality random() {
        return Nationality.values()[(int)(Math.random() * Nationality.values().length)];
    }
}