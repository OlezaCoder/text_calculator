import java.util.InputMismatchException;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws InvalidOperatorException, InputMismatchException{
        Scanner expression = new Scanner(System.in);
        String currentLine = expression.nextLine();
        try {
            if (!inputMismatch(currentLine)) {
                System.out.println(calc(currentLine));
            } else {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException e) {
            e.printStackTrace();
        }
    }
    public static String calc(String input) throws InvalidOperatorException{
        String[] variablesWithoutMarks = variablesWithoutMarks(input);
        String result;
        if (input.contains(" + ")) {
            result = variablesWithoutMarks[0] + variablesWithoutMarks[1];
        } else if (input.contains(" - ")) {
            result = variablesWithoutMarks[0].replace(variablesWithoutMarks[1], "");
        } else if (input.contains(" / ")) {
            result = variablesWithoutMarks[0].substring(0, variablesWithoutMarks[0].length() / Integer.parseInt(variablesWithoutMarks[1]));
        } else if (input.contains(" * ")) {
            result = variablesWithoutMarks[0].repeat(Integer.parseInt(variablesWithoutMarks[1]));
        } else {
            throw new InvalidOperatorException("Введен не верный оператор");
        }
        if (result.length() < 41) {
            return "\"" + result + "\"";
        } else {
            return "\"" + result.substring(0,40) + "..." + "\"";
        }
    }

    static boolean inputMismatch(String input) {
        String[] variables = variables(input);
        if (variables.length != 2) {
            return true;
        } else if (!variables[0].contains("\"")) {
            return true;
        } else if (!variables[1].contains("\"")) {
            if (input.contains(" - ") || input.contains(" + ")) {
                return true;
            } else if (1 <= Integer.parseInt(variables[1]) & Integer.parseInt(variables[1]) <= 10) {
                return false;
            } else {
                return true;
            }
        } else if (variables[0].contains("\"")) {
            if ((variables[0].length()) - 2 <= 10) {
                return false;
            } else {return true;}
        } else if (variables[1].contains("\"")) {
            if ((variables[1].length()) - 2 <= 10) {
                return false;
            } else {return true;}
        } else if (input.contains(" / ") || input.contains(" * ")) {
            if (variables[1].contains("\"")) {
                return true;
            }
        }
        return false;
    }
    static String[] arrayOfValues(String input) {
        return input.split(" [+-/*] ");
    }

    static String[] variables(String input) {
        String[] variables = arrayOfValues(input);
        return variables;
    }

    static String[] variablesWithoutMarks(String input) {
        String[] variablesWithoutMarks = variables(input);
        for (int i = 0; i < variablesWithoutMarks.length; i++) {
            variablesWithoutMarks[i] = variablesWithoutMarks[i].replace("\"", "");
        }
        return variablesWithoutMarks;
    }
}