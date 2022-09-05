import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pesel {
    private static final int[] checkSumArr = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};

    @SuppressWarnings("unused")
    private static boolean isValid(String pesel) {
        int origControlDigit = (int)(Long.parseLong(pesel) % 10);
        int calcControlDigit;
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += Integer.parseInt(String.valueOf(pesel.toCharArray()[i])) + checkSumArr[i];
        }
        calcControlDigit = sum % 10 == 0 ? 0 : 10 - (sum % 10);
        return calcControlDigit == origControlDigit;
    }

    @SuppressWarnings("unused")
    private static LocalDate getDate(String pesel) {
        int year = Integer.parseInt(pesel.substring(0, 2));
        int month = Integer.parseInt(pesel.substring(2, 4));
        int day = Integer.parseInt(pesel.substring(4, 6));
        if (month - 80 >= 0)
            return getDateFromParse(year, month, day, 80, "18");
        else if (month - 40 >= 0)
            return getDateFromParse(year, month, day, 40, "21");
        else if (month - 20 >= 0)
            return getDateFromParse(year, month, day, 20, "20");
        else
            return getDateFromParse(year, month, day, 0, "19");
    }
    private static LocalDate getDateFromParse(int year, int month, int day, int monthModifier, String yearTwoDigits) {
        return LocalDate.parse(yearTwoDigits + (year < 10 ? "0" + year : year) + "-" +
                        ((month - monthModifier) < 10 ? "0" + (month - monthModifier) : (month - monthModifier)) + "-" +
                        (day < 10 ? "0" + day : day),
                DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @SuppressWarnings("unused")
    private static String getSex(String pesel) {
        return Integer.parseInt(pesel.substring(9, 10)) == 0 ? "M" : "F";
    }
}