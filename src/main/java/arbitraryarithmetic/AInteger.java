package arbitraryarithmetic;


public class AInteger{
    public String value;
    //this takes input with zeroes removed left side of integer
    public AInteger(String value){
        boolean isNegative = false;

    if (value.charAt(0) == '-') {
        isNegative = true;
        value = value.substring(1);
    }

    int index = 0;
    while (index < value.length() - 1 && value.charAt(index) == '0') {
        index++;
    }

    this.value = value.substring(index);

    if (isNegative) {
        this.value = "-" + this.value;
    }
    }
    public static AInteger parse(AInteger a){
        return new AInteger(a.value);
    }
    //addition function to add 2 integers
    public static AInteger add (AInteger a,AInteger b){
        if(a.value.charAt(0) == '-' && b.value.charAt(0) == '-') {
            AInteger temp = add(new AInteger(a.value.substring(1)), new AInteger(b.value.substring(1)));
            if (temp.value.equals("0")) return new AInteger("0");
            return new AInteger("-" + temp.value);
            //return new AInteger("-" + add(new AInteger(a.value.substring(1)), new AInteger(b.value.substring(1))).value);
        }
        if(a.value.charAt(0) == '-' && b.value.charAt(0) != '-') {
            return sub(new AInteger(b.value), new AInteger(a.value.substring(1)));
        }
        if(a.value.charAt(0) != '-' && b.value.charAt(0) == '-') {
            return sub(new AInteger(a.value), new AInteger(b.value.substring(1)));
        }
        String result = "";
        int carry = 0;
        int i = a.value.length() - 1;
        int j = b.value.length() - 1;
        while (i >= 0 || j >= 0 || carry > 0) {
            int digitA = (i >= 0) ? a.value.charAt(i) - '0' : 0;
            int digitB = (j >= 0) ? b.value.charAt(j) - '0' : 0;
            int sum = digitA + digitB + carry;
            result = (sum % 10) + result;
            if(sum >= 10) {
                carry = 1;
            } else {
                carry = 0;
            }
            i--;
            j--;
        }
        int index=0;
        while (index <= result.length()-1 && result.charAt(index) == '0') {
            index++;
        }
        if (index == result.length()) {
            return new AInteger("0");
        }
        String finalresult = "";
        for (int k = index; k < result.length(); k++) {
            finalresult += result.charAt(k);
        }
        return new AInteger(finalresult);
        //return new AInteger(result);
    }
    //subtraction function to subtract integers
    public static AInteger sub(AInteger a,AInteger b){
        //handling negative cases
        if(a.value.charAt(0) == '-' && b.value.charAt(0) == '-') {
            return sub(new AInteger(b.value.substring(1)), new AInteger(a.value.substring(1)));
        }
        if(a.value.charAt(0) == '-' && b.value.charAt(0) != '-') {
            AInteger temp = add(new AInteger(a.value.substring(1)), b);
            if (temp.value.equals("0")) return new AInteger("0");
            return new AInteger("-" + temp.value);
        }
        if(a.value.charAt(0) != '-' && b.value.charAt(0) == '-') {
            return add(a, new AInteger(b.value.substring(1)));
        }
        if(a.value.length() < b.value.length()) {
            AInteger temp = sub(new AInteger(b.value), new AInteger(a.value));
            if (temp.value.equals("0")) return new AInteger("0");
            return new AInteger("-" + temp.value);
        }
        if(a.value.length() == b.value.length()) {
            for (int i = 0; i < a.value.length(); i++) {
                if (a.value.charAt(i) < b.value.charAt(i)) {
                    return new AInteger("-" + sub(new AInteger(b.value), new AInteger(a.value)).value);
                }
                if (a.value.charAt(i) > b.value.charAt(i)) {
                    break;
                }
            }
        }
        String result = "";
        int borrow = 0;
        int i = a.value.length() - 1;
        int j = b.value.length() - 1;
        while (i >= 0 || j >= 0 || borrow > 0) {
            int digitA = (i >= 0) ? a.value.charAt(i) - '0' : 0;
            int digitB = (j >= 0) ? b.value.charAt(j) - '0' : 0;
            int diff= digitA - digitB - borrow;
            if (diff < 0) {
                diff += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }
            result = diff + result;
            i--;
            j--;
        }
        int index=0;
        while (index <= result.length()-1 && result.charAt(index) == '0') {
            index++;
        }
        if (index == result.length()) {
            return new AInteger("0");
        }
        String finalresult = "";
        for (int k = index; k < result.length(); k++) {
            finalresult += result.charAt(k);
        }
        return new AInteger(finalresult);
    }
    //function to multiply 2 integers
    public static AInteger mul(AInteger a, AInteger b){
        // handling negative cases
        if(a.value.equals("0") || b.value.equals("0")|| a.value.equals("-0") || b.value.equals("-0")) {
            return new AInteger("0");
        }
        if(a.value.charAt(0) == '-' && b.value.charAt(0) == '-') {
            return mul(new AInteger(a.value.substring(1)), new AInteger(b.value.substring(1)));
        }
        if(a.value.charAt(0) == '-' && b.value.charAt(0) != '-') {
            AInteger temp = mul(new AInteger(a.value.substring(1)), b);
            if (temp.value.equals("0")) return new AInteger("0");
            return new AInteger("-" + temp.value);
            //return new AInteger("-" + mul(new AInteger(a.value.substring(1)), b).value);
        }
        if(a.value.charAt(0) != '-' && b.value.charAt(0) == '-') {
            AInteger temp = mul(a,new AInteger(b.value.substring(1)));
            if (temp.value.equals("0")) return new AInteger("0");
            return new AInteger("-" + temp.value);
            //return new AInteger("-" + mul(a, new AInteger(b.value.substring(1))).value);
        }
       
        String result = "0";
        for (int i = b.value.length() - 1; i >= 0; i--) {
            int digitB = b.value.charAt(i) - '0';
            String temp = "";
            int carry = 0;
            for (int j = a.value.length() - 1; j >= 0; j--) {
                int digitA = a.value.charAt(j) - '0';
                int product = digitA * digitB + carry;
                //System.out.println("product: " + product + " digitA: " + digitA + " digitB: " + digitB);
                //System.out.println("j: " + j + " i: " + i + " carry: " + carry);
                temp = (product % 10) + temp;
                carry = product / 10;
            }
            if (carry > 0) {
                temp = carry + temp;
            }
            for (int k = 0; k < b.value.length() - 1 - i; k++) {
                temp += "0";
            }
            //System.out.println("temp: " + temp+" result: " + result+" digitB: " + digitB+" digitA: " + a.value.charAt(i) + " carry: " + carry);
            AInteger partialResult = new AInteger(temp);
            result = add(new AInteger(result), partialResult).value;
        }
        int index=0;
        while (index <= result.length()-1 && result.charAt(index) == '0') {
            index++;
        }
        if (index == result.length()) {
            return new AInteger("0");
        }
        String finalresult = "";
        for (int k = index; k < result.length(); k++) {
            finalresult += result.charAt(k);
        }
        return new AInteger(finalresult);
        //return new AInteger(result);
    }
    //function to divide 2 integers
    public static AInteger div(AInteger a, AInteger b) {
        String dividend = a.value;
        String divisor = b.value;
        // also did checking
        if (dividend.charAt(0) == '-') {
            dividend = dividend.substring(1);
        }
        while (dividend.length() > 1 && dividend.charAt(0) == '0') {
            dividend = dividend.substring(1);
        }
    
        if (divisor.charAt(0) == '-') {
            divisor = divisor.substring(1);
        }
        while (divisor.length() > 1 && divisor.charAt(0) == '0') {
            divisor = divisor.substring(1);
        }
    
        if (divisor.equals("0")) {
            throw new ArithmeticException("Division by zero is not allowed.");
        }
    
        if (a.value.charAt(0) == '-' && b.value.charAt(0) == '-') {
            return div(new AInteger(dividend), new AInteger(divisor));
        }
        if (a.value.charAt(0) == '-' && b.value.charAt(0) != '-') {
            AInteger temp = div(new AInteger(dividend), new AInteger(b.value));
            if (temp.value.equals("0")) return new AInteger("0");
            return new AInteger("-" + temp.value);
        }
        if (a.value.charAt(0) != '-' && b.value.charAt(0) == '-') {
            AInteger temp = div(new AInteger(a.value), new AInteger(divisor));
            if (temp.value.equals("0")) return new AInteger("0");
            return new AInteger("-" + temp.value);
        }
    
        String result = "";
        int dividendLength = dividend.length();
        String current = "";
    
        for (int i = 0; i < dividendLength; i++) {
            current += dividend.charAt(i);
            while (current.length() > 1 && current.charAt(0) == '0') {
                current = current.substring(1);
            }
    
            int count = 0;
            while (
                (current.length() > divisor.length()) ||
                (current.length() == divisor.length() &&
                 sub(new AInteger(current), new AInteger(divisor)).value.charAt(0) != '-')
            ) {
                current = sub(new AInteger(current), new AInteger(divisor)).value;
                while (current.length() > 1 && current.charAt(0) == '0') {
                    current = current.substring(1);
                }
                count++;
            }
            result += count;
        }
    
        int index = 0;
        while (index < result.length() - 1 && result.charAt(index) == '0') {
            index++;
        }
    
        String finalresult = result.substring(index);
        if (finalresult.equals("") || finalresult.equals("0")) {
            return new AInteger("0");
        }
    
        return new AInteger(finalresult);
    }
    public static AInteger remainder(AInteger a, AInteger b){
        String dividend = a.value;
        String divisor = b.value;
    
        if (dividend.charAt(0) == '-') {
            dividend = dividend.substring(1);
        }
        while (dividend.length() > 1 && dividend.charAt(0) == '0') {
            dividend = dividend.substring(1);
        }
    
        if (divisor.charAt(0) == '-') {
            divisor = divisor.substring(1);
        }
        while (divisor.length() > 1 && divisor.charAt(0) == '0') {
            divisor = divisor.substring(1);
        }
    
        if (divisor.equals("0")) {
            throw new ArithmeticException("Division by zero is not allowed.");
        }
    
        // if (a.value.charAt(0) == '-' && b.value.charAt(0) == '-') {
        //     return remainder(new AInteger(dividend), new AInteger(divisor));
        // }
        // if (a.value.charAt(0) == '-' && b.value.charAt(0) != '-') {
        //     AInteger temp = remainder(new AInteger(dividend), new AInteger(b.value));
        //     if (temp.value.equals("0")) return new AInteger("0");
        //     return new AInteger("-" + temp.value);
        // }
        // if (a.value.charAt(0) != '-' && b.value.charAt(0) == '-') {
        //     AInteger temp = remainder(new AInteger(a.value), new AInteger(divisor));
        //     if (temp.value.equals("0")) return new AInteger("0");
        //     return new AInteger("-" + temp.value);
        // }
    
        String result = "";
        int dividendLength = dividend.length();
        String current = "";
    
        for (int i = 0; i < dividendLength; i++) {
            current += dividend.charAt(i);
            while (current.length() > 1 && current.charAt(0) == '0') {
                current = current.substring(1);
            }
    
            int count = 0;
            while (
                (current.length() > divisor.length()) ||
                (current.length() == divisor.length() &&
                 sub(new AInteger(current), new AInteger(divisor)).value.charAt(0) != '-')
            ) {
                current = sub(new AInteger(current), new AInteger(divisor)).value;
                while (current.length() > 1 && current.charAt(0) == '0') {
                    current = current.substring(1);
                }
                count++;
            }
            result += count;
        } 
        int index = 0;
        while (index < result.length() - 1 && result.charAt(index) == '0') {
            index++;
        }
        String finalresult = current.substring(index);
        if (finalresult.equals("") || finalresult.equals("0")) {
            return new AInteger("0");
        }
        return new AInteger(finalresult);
    }
    /*public static AInteger div(AInteger a, AInteger b){
        if(b.value.equals("0") || b.value.equals("-0")) {
            throw new ArithmeticException("Division by zero is not allowed.");
        }
        if(a.value.charAt(0) == '-' && b.value.charAt(0) == '-') {
            return div(new AInteger(a.value.substring(1)), new AInteger(b.value.substring(1)));
        }
        if(a.value.charAt(0) == '-' && b.value.charAt(0) != '-') {
            return new AInteger("-" + div(new AInteger(a.value.substring(1)), b).value);
        }
        if(a.value.charAt(0) != '-' && b.value.charAt(0) == '-') {
            return new AInteger("-" + div(a, new AInteger(b.value.substring(1))).value);
        }
        String result = "";
        String dividend = a.value;
        String divisor = b.value;
        int dividendLength = dividend.length();
        int divisorLength = divisor.length();
        String current = "";
        for (int i = 0; i < dividendLength; i++) {
            current+=dividend.charAt(i);
            if (current.length() >= divisorLength) {
                int count = 0;
                while (compare(current, divisor) >= 0) {
                    current = sub(new AInteger(current.toString()), new AInteger(divisor)).value;
                    count++;
                }
                result += count;
            } else {
                result += "0";
            }
        }
        int index=0;
        while (index <= result.length()-1 && result.charAt(index) == '0') {
            index++;
        }
        if (index == result.length()) {
            return new AInteger("0");
        }
        String finalresult = "";
        for (int k = index; k < result.length(); k++) {
            finalresult += result.charAt(k);
        }
        return new AInteger(finalresult);
    }*/
}

// public class integer{
//         public static void main(String[] args) {
//         AInteger a = new AInteger("7002");
//         AInteger b = new AInteger("300");
//         AInteger c = AInteger.div(a, b);
//         System.out.println(c.value); // Output: 246469050
//     }
// }
