import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        System.out.println(calc(str));
    }

    public static String calc(String str){

        boolean isOperationFound = false;
        int result = 0;
        String sResult = null;
        str = str.replaceAll(" ", ""); //removing extra spaces
        for (Operand opr : Operand.values()) // operand cycle
        {
            if (str.contains(opr.getOper())) { //operation is found

                String[] str1 = str.split(opr.getEoper()); //getting operands
                isOperationFound = true;
                if (str1.length>2) throw new RuntimeException("Error: only 2 operands are allowed");
                if (isLatin(str1)){ //operand format checking -> LATIN
                    result = arabicCalculate(lat2Arabic(str1[0]), lat2Arabic(str1[1]), opr.getEoper());
                    if (result <= 0) {
                        throw new RuntimeException("Error: The result of latin expression <= 0 ");
                    }
                    sResult = arabic2Lat(result);
                }

                else if (isArabic(str1)) { // both operands are arabic
                    int oper1 = Integer.parseInt(str1[0]);
                    int oper2 = Integer.parseInt(str1[1]);
                    result = arabicCalculate(oper1, oper2, opr.getEoper());
                    sResult = result + ""; //Converting to string
                }
                else throw new RuntimeException("Incorrect format");
            }
        }
        if (!isOperationFound) throw new RuntimeException("You haven't entered a correct operation");
        //System.out.println(result);

        return sResult;
    }
    public static boolean isArabic(String[] str){
        for (int i = 0; i<2; i++){
            for (char c : str[i].toCharArray()) {
                if (!Character.isDigit(c)){
                   return false;
                }
            }
        }
        return true;
    }

    public static boolean isLatin(String[] str){
        for (int i = 0; i<2; i++){
            for (char c : str[i].toCharArray()) {
                if ((c != 'I') && (c != 'V') && (c != 'X') && (c != 'L')) return false;
            }
        }
        return true;
    }
    public static int arabicCalculate(int oper1,int oper2, String oper) {
        int result = 0;
        if (oper1 > 10 || oper2 > 10) {
            throw new RuntimeException("Error! At lease one Entered value is more than 10");
        }
        switch (oper) {
            case "\\+" : {
                result = oper1 + oper2;
                break;
            }
            case "-" : {
                result = oper1 - oper2;
                break;
            }
            case "\\*" : {
                result = oper1 * oper2;
                break;
            }
            case "\\/" : {
                result = oper1 / oper2;
                break;
            }
        }
        return result;
    }

    public static int lat2Arabic(String str){
        int result = 0;
        int prevDigit = 0 ;
        String[] arr = str.split("");
        for (int i = arr.length-1; i>=0; i--) {
            if (arr[i].equals("I")) {
                if (prevDigit <= 1) result += 1;
                else result -= 1;
                prevDigit = 1;
            }
            if (arr[i].equals("V")) {
                if (prevDigit <= 5) result += 5;
                else result -= 5;
                prevDigit = 5;
            }
            if (arr[i].equals("X")) {
                if (prevDigit <= 10) result += 10;
                else result -= 10;
                prevDigit = 10;
            }
        }
        return result;
    }

    public static String arabic2Lat(int digit){
        String result = "";
        while (digit > 0) {
            if (digit / 50 > 0) {
                digit -= LatinDigit.L.getDigit();
                result += LatinDigit.L;
                continue;
            }
            if (digit / 10 > 0) {
                digit -= LatinDigit.X.getDigit();
                result += LatinDigit.X;
                continue;
            }
            if (digit == 9) { //обработка частного случая 9
                digit = 0;
                result += LatinDigit.IX;
                continue;
            }

            if (digit / 5 > 0) {
                digit -= LatinDigit.V.getDigit();
                result += LatinDigit.V;
                continue;
            }
             //обработка единиц
                switch (digit) {
                    case 4 : { //обработка особого случая 4
                        result += LatinDigit.IV;
                        digit = 0;
                        break;
                    }
                    default : {
                        digit -= LatinDigit.I.getDigit();
                        result += LatinDigit.I;
                        break;
                    }
                }
        }
        return result;
    }
}