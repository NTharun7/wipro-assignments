package assignment;

enum Currency {
    ONE, TWO, FIVE, TEN, FIFTY, HUNDRED
}

public class Q27 {
    public static void main(String[] args) {
        for (Currency c : Currency.values()) {
            System.out.println(c);
        }

        Currency currency = Currency.FIVE;

        switch (currency) {
            case ONE -> System.out.println("1 rupee coin");
            case TWO -> System.out.println("2 rupee coin");
            case FIVE -> System.out.println("5 rupee note");
            case TEN -> System.out.println("10 rupee note");
            case FIFTY -> System.out.println("50 rupee note");
            case HUNDRED -> System.out.println("100 rupee note");
        }
    }
}


/*
ONE
TWO
FIVE
TEN
FIFTY
HUNDRED
5 rupee note
*/