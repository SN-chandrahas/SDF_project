package arbitraryarithmetic;
//import arbitraryarithmetic.AInteger;
public class AFloat {
    public String value;
    public AFloat(String value){
        this.value = value;
    }
    public static AFloat parse(AFloat a){
        return new AFloat(a.value);
    }
    //hi
    public static AFloat eraseleftzeroes(AFloat afloat){
        String value=afloat.value;
        int i=0;
        while(value.charAt(i)!='.' && value.charAt(i)=='0'){
            i++;
        }
        if(value.charAt(i)=='.'){
            return new AFloat(value.substring(i-1));
        }
        else {
            return new AFloat(value.substring(i));
        }
    }
    public static AFloat eraserightzeroes(AFloat afloat){
        String value=afloat.value;
        int i=value.length()-1;
        while(value.charAt(i)!='.' && value.charAt(i)=='0'){
            i--;
        }
        if(value.charAt(i)=='.'){
            return new AFloat(value.substring(0,i+2));
        }
        else {
            return new AFloat(value.substring(0,i+1));
        }
    }
    public static AFloat add(AFloat a, AFloat b){
        String finalresult="";
        if(a.value.charAt(0)!='-'&&b.value.charAt(0)!='-'){
            int dotcheckerA=-1;
            int dotcheckerB=-1;
            for(int i=0;i<a.value.length();i++){
                if(a.value.charAt(i)=='.'){
                    dotcheckerA=i;
                    break;
                }
            }
            for(int i=0;i<b.value.length();i++){
                if(b.value.charAt(i)=='.'){
                    dotcheckerB=i;
                    break;
                }
            }
            String intpartA="";
            String intpartB="";
            String decpartA="";
            String decpartB="";
            if(dotcheckerA==-1){
                intpartA=a.value;
                decpartA="0";
            }
            else{
                intpartA=a.value.substring(0,dotcheckerA);
                decpartA=a.value.substring(dotcheckerA+1);
            }
            if(dotcheckerB==-1){
                intpartB=b.value;
                decpartB="0";
            }
            else{
                intpartB=b.value.substring(0,dotcheckerB);
                decpartB=b.value.substring(dotcheckerB+1);
            }

            if(decpartA.length()>decpartB.length()){
                int Length = decpartA.length() - decpartB.length();
                for(int i=0;i<Length;i++){
                    decpartB= decpartB+"0";
                }
            }
            else if(decpartA.length()<decpartB.length()){
                int Length = decpartB.length() - decpartA.length();
                for(int i=0;i<Length;i++){
                    decpartA=decpartA+"0";
                }
            }
            //System.out.println("intpartA: "+intpartA+" intpartB: "+intpartB+" decpartA: "+decpartA+" decpartB: "+decpartB);
            if(intpartA.length()>intpartB.length()){
                int Length = intpartA.length() - intpartB.length();
                for(int i=0;i<Length;i++){
                    //System.out.println("hi");
                    intpartB="0"+intpartB;
                }
            }
            else if(intpartA.length()<intpartB.length()){
                int Length = intpartB.length() - intpartA.length();
                for(int i=0;i<Length;i++){
                    intpartA="0"+intpartA;
                }
            }
            String resultint="";
            String resultdec="";
            int carry=0;
            //System.out.println("intpartA: "+intpartA+" intpartB: "+intpartB+" decpartA: "+decpartA+" decpartB: "+decpartB);
            for(int i=decpartA.length()-1;i>=0;i--){
                int sum=(decpartA.charAt(i)-'0')+(decpartB.charAt(i)-'0')+carry;
                carry=sum/10;
                resultdec=(sum%10)+resultdec;
            }
            for(int i=intpartA.length()-1;i>=0;i--){
                int sum=(intpartA.charAt(i)-'0')+(intpartB.charAt(i)-'0')+carry;
                carry=sum/10;
                resultint=(sum%10)+resultint;
            }
            if(carry!=0){
                resultint=carry+resultint;
            }
            finalresult=resultint+"."+resultdec;
            finalresult=eraserightzeroes(new AFloat(finalresult)).value;
            finalresult=eraseleftzeroes(new AFloat(finalresult)).value;
            return new AFloat(finalresult);
        }
        else if(a.value.charAt(0)=='-'&&b.value.charAt(0)=='-'){
            a.value=a.value.substring(1);
            b.value=b.value.substring(1);
            AFloat c=AFloat.add(a, b);
            finalresult="-"+c.value;
            return new AFloat(finalresult);
        }
        else if(a.value.charAt(0)=='-'&&b.value.charAt(0)!='-'){
            a.value=a.value.substring(1);
            AFloat c=AFloat.sub(b, a);
            finalresult=c.value;
            return new AFloat(finalresult);
        }
        else if(a.value.charAt(0)!='-'&&b.value.charAt(0)=='-'){
            b.value=b.value.substring(1);
            AFloat c=AFloat.sub(a, b);
            finalresult=c.value;
            return new AFloat(finalresult);
        }
        else{
            return null;
        }
    }
    public static AFloat sub(AFloat a, AFloat b){
        if(a.value.charAt(0)!='-'&&b.value.charAt(0)!='-'){
            int dotcheckerA=-1;
            int dotcheckerB=-1;
            String intpartA="";
            String intpartB="";
            String decpartA="";
            String decpartB="";
            for(int i=0;i<a.value.length();i++){
                if(a.value.charAt(i)=='.'){
                    dotcheckerA=i;
                    break;
                }
            }
            for(int i=0;i<b.value.length();i++){
                if(b.value.charAt(i)=='.'){
                    dotcheckerB=i;
                    break;
                }
            }
            if(dotcheckerA==-1){
                intpartA=a.value;
                decpartA="0";
            }
            else{
                intpartA=a.value.substring(0,dotcheckerA);
                decpartA=a.value.substring(dotcheckerA+1);
            }
            if(dotcheckerB==-1){
                intpartB=b.value;
                decpartB="0";
            }
            else{
                intpartB=b.value.substring(0,dotcheckerB);
                decpartB=b.value.substring(dotcheckerB+1);
            }
            if(decpartA.length()>decpartB.length()){
                int Length = decpartA.length() - decpartB.length();
                for(int i=0;i<Length;i++){
                    decpartB= decpartB+"0";
                }
            }
            else if(decpartA.length()<decpartB.length()){
                int Length = decpartB.length() - decpartA.length();
                for(int i=0;i<Length;i++){
                    decpartA=decpartA+"0";
                }
            }
            if(intpartA.length()>intpartB.length()){
                int Length = intpartA.length() - intpartB.length();
                for(int i=0;i<Length;i++){
                    intpartB="0"+intpartB;
                }
            }
            else if(intpartA.length()<intpartB.length()){
                int Length = intpartB.length() - intpartA.length();
                for(int i=0;i<Length;i++){
                    intpartA="0"+intpartA;
                }
            }
            int negative=0;
            if(compare(new AFloat(intpartA+"."+decpartA),new AFloat(intpartB+"."+decpartB))==-1){
                negative=1;
                String temp=intpartA;
                intpartA=intpartB;
                intpartB=temp;
                temp=decpartA;
                decpartA=decpartB;
                decpartB=temp;
            }
            int borrow=0;
            String resultint="";
            String resultdec="";
            for(int i=decpartA.length()-1;i>=0;i--){
                int sub=(decpartA.charAt(i)-'0')-(decpartB.charAt(i)-'0')-borrow;
                if(sub<0){
                    sub+=10;
                    borrow=1;
                }
                else{
                    borrow=0;
                }
                resultdec=sub+resultdec;
            }
            for(int i=intpartA.length()-1;i>=0;i--){
                int sub=(intpartA.charAt(i)-'0')-(intpartB.charAt(i)-'0')-borrow;
                if(sub<0){
                    sub+=10;
                    borrow=1;
                }
                else{
                    borrow=0;
                }
                resultint=sub+resultint;
            }
            /*if(negative==1){
                resultint="-"+resultint;
            }*/
            String finalresult=resultint+"."+resultdec;
            finalresult=eraserightzeroes(new AFloat(finalresult)).value;
            finalresult=eraseleftzeroes(new AFloat(finalresult)).value;
            if(negative==1){
                finalresult="-"+finalresult;
            }
            return new AFloat(finalresult);
        }
        else if (a.value.charAt(0)=='-'&&b.value.charAt(0)=='-'){
            a.value=a.value.substring(1);
            b.value=b.value.substring(1);
            AFloat c=AFloat.sub(b, a);
            return new AFloat(c.value);
        }
        else if(a.value.charAt(0)=='-'&&b.value.charAt(0)!='-'){
            a.value=a.value.substring(1);
            AFloat c=AFloat.add(a, b);
            c.value="-"+c.value;
            return new AFloat(c.value);
        }
        else if(a.value.charAt(0)!='-'&&b.value.charAt(0)=='-'){
            b.value=b.value.substring(1);
            AFloat c=AFloat.add(a, b);
            return new AFloat(c.value);
        }
        else{
            return null;
        }
    }
    public static AFloat mul(AFloat a, AFloat b){
       // if(a.value.charAt(0)!='-'&&b.value.charAt(0)!='-'){
        
            int dotcheckerA = -1;
            int dotcheckerB = -1;
            String intpartA = "";
            String intpartB = "";
            String decpartA = "";
            String decpartB = "";
        
            // Find decimal positions
            for (int i = 0; i < a.value.length(); i++) {
                if (a.value.charAt(i) == '.') {
                    dotcheckerA = i;
                    break;
                }
            }
            for (int i = 0; i < b.value.length(); i++) {
                if (b.value.charAt(i) == '.') {
                    dotcheckerB = i;
                    break;
                }
            }
        
            // Separate integer and decimal parts
            if (dotcheckerA == -1) {
                intpartA = a.value;
                decpartA = "";
            } else {
                intpartA = a.value.substring(0, dotcheckerA);
                decpartA = a.value.substring(dotcheckerA + 1);
            }
            if (dotcheckerB == -1) {
                intpartB = b.value;
                decpartB = "";
            } else {
                intpartB = b.value.substring(0, dotcheckerB);
                decpartB = b.value.substring(dotcheckerB + 1);
            }
        
            int totalDecimalPlaces = decpartA.length() + decpartB.length();
        
            String afull = intpartA + decpartA;
            String bfull = intpartB + decpartB;
        
            boolean negative = false;
            if (afull.charAt(0) == '-') {
                negative = !negative;
                afull = afull.substring(1);
            }
            if (bfull.charAt(0) == '-') {
                negative = !negative;
                bfull = bfull.substring(1);
            }
        
            String result = "0";
            for (int i = bfull.length() - 1; i >= 0; i--) {
                String temp = "";
                int carry = 0;
                for (int j = afull.length() - 1; j >= 0; j--) {
                    int mul = (bfull.charAt(i) - '0') * (afull.charAt(j) - '0') + carry;
                    carry = mul / 10;
                    temp = (mul % 10) + temp;
                }
                if (carry != 0) {
                    temp = carry + temp;
                }
                for (int k = 0; k < bfull.length() - 1 - i; k++) {
                    temp += "0";
                }
                result = AInteger.add(new AInteger(result), new AInteger(temp)).value;
            }
        
            // Insert decimal point
            String finalresult = "";
            if (result.length() <= totalDecimalPlaces) {
                finalresult = "0.";
                for (int i = 0; i < totalDecimalPlaces - result.length(); i++) {
                    finalresult += "0";
                }
                finalresult += result;
            } else {
                int dotIndex = result.length() - totalDecimalPlaces;
                String intPart = result.substring(0, dotIndex);
                String decPart = result.substring(dotIndex);
                finalresult = intPart + "." + decPart;
            }
        
            finalresult = eraserightzeroes(new AFloat(finalresult)).value;
            finalresult = eraseleftzeroes(new AFloat(finalresult)).value;
        
            if (negative && !finalresult.equals("0")) {
                finalresult = "-" + finalresult;
            }
        
            return new AFloat(finalresult);
        }
            
    public static AFloat div(AFloat a,AFloat b){
        int dotcheckerA=-1;
        int dotcheckerB=-1;
        String intpartA="";
        String intpartB="";
        String decpartA="";
        String decpartB="";
        for(int i=0;i<a.value.length();i++){
            if(a.value.charAt(i)=='.'){
                dotcheckerA=i;
                break;
            }
        }
        for(int i=0;i<b.value.length();i++){
            if(b.value.charAt(i)=='.'){
                dotcheckerB=i;
                break;
            }
        }
        if(dotcheckerA==-1){
            intpartA=a.value;
            decpartA="0";
        }
        else{
            intpartA=a.value.substring(0,dotcheckerA);
            decpartA=a.value.substring(dotcheckerA+1);
        }
        if(dotcheckerB==-1){
            intpartB=b.value;
            decpartB="0";
        }
        else{
            intpartB=b.value.substring(0,dotcheckerB);
            decpartB=b.value.substring(dotcheckerB+1);
        }
        int totalDecimalPlaces = decpartA.length() - decpartB.length();
        String afull=intpartA+decpartA;
        String bfull=intpartB+decpartB;
        if(afull.charAt(0)=='-'){
            afull=afull.substring(1);
        }
        if(bfull.charAt(0)=='-'){
            bfull=bfull.substring(1);
        }
        String dividend =afull;
        String divisor = bfull;
        if(eraserightzeroes(eraseleftzeroes(new AFloat(bfull+".0"))).value.equals("0.0")){
            throw new ArithmeticException("Division by zero is not allowed.");
        }
        String result = "";
        String current = "";
        int decimalchecker=-1;
        int decplace=0;
        for (int i = 0; current != "0"; i++) {
            if(i < dividend.length()){
                current += dividend.charAt(i);
            }
            else{
                current += "0";
            }
            //current += dividend.charAt(i);
            int count = 0;
            //System.out.println("current: "+current+" divisor: "+divisor);
            while (compare(eraseleftzeroes(new AFloat(current+".0")), new AFloat(divisor+".0")) != -1) {
                current = AFloat.sub(new AFloat(current), new AFloat(divisor)).value.substring(0, AFloat.sub(new AFloat(current), new AFloat(divisor)).value.length()-2);
                //System.out.println("current: "+current+" divisor: "+divisor);
                count++;
            }
            if(i== dividend.length() && decimalchecker == -1){
                decimalchecker = result.length();
                //result += ".";
            }
            if(decimalchecker != -1){
                decplace++;
            }

            result += count;
            if(decplace == 30-totalDecimalPlaces){
                break;
            }
        }
        if(result.length() <=30){
            int Length = 30 - result.length();
            for(int i=0;i<Length;i++){
                result="0"+result;
            }
            result="0."+result;

        }
        else{
            int dotchecker=result.length()-totalDecimalPlaces-decplace;
            String intpart=result.substring(0,dotchecker);
            String decpart=result.substring(dotchecker);
            result=intpart+"."+decpart;
        }
        result=eraseleftzeroes(new AFloat(result)).value;
        result=eraserightzeroes(new AFloat(result)).value;  
        return new AFloat(result);
        
    }
    public static int compare(AFloat a, AFloat b){
        AFloat aValue = eraseleftzeroes(a);
        AFloat bValue = eraseleftzeroes(b);
        aValue = eraserightzeroes(aValue);
        bValue = eraserightzeroes(bValue);
        //System.out.println("aValue: "+aValue.value+" bValue: "+bValue.value);
        if (aValue.equals(bValue)) {
            return 0;
        }
        int dotcheckerA = -1;
        int dotcheckerB = -1;
        String intpartA = "";
        String intpartB = "";
        String decpartA = "";
        String decpartB = "";
        for (int i = 0; i < aValue.value.length(); i++) {
            if (aValue.value.charAt(i) == '.') {
                dotcheckerA = i;
                break;
            }
        }
        for (int i = 0; i < bValue.value.length(); i++) {
            if (bValue.value.charAt(i) == '.') {
                dotcheckerB = i;
                break;
            }
        }
        if (dotcheckerA == -1) {
            intpartA = aValue.value;
            decpartA = "0";
        } else {
            intpartA = aValue.value.substring(0, dotcheckerA);
            decpartA = aValue.value.substring(dotcheckerA + 1);
        }
        if (dotcheckerB == -1) {
            intpartB = bValue.value;
            decpartB = "0";
        } else {
            intpartB = bValue.value.substring(0, dotcheckerB);
            decpartB = bValue.value.substring(dotcheckerB + 1);
        }
        if(decpartA.length() > decpartB.length()) {
            int Length = decpartA.length() - decpartB.length();
            for (int i = 0; i < Length; i++) {
                decpartB = decpartB + "0";
            }
        } else if (decpartA.length() < decpartB.length()) {
            int Length = decpartB.length() - decpartA.length();
            for (int i = 0; i < Length; i++) {
                decpartA = decpartA + "0";
            }
        }
        if(intpartA.length() > intpartB.length()) {
            int Length = intpartA.length() - intpartB.length();
            for (int i = 0; i < Length; i++) {
                intpartB = "0" + intpartB;
            }
        } else if (intpartA.length() < intpartB.length()) {
            int Length = intpartB.length() - intpartA.length();
            for (int i = 0; i < Length; i++) {
                intpartA = "0" + intpartA;
            }
        }
        String afull=intpartA + decpartA;
        String bfull=intpartB + decpartB;
        //System.out.println("afull: "+afull+" bfull: "+bfull);
            for(int i = 0; i < afull.length(); i++){
                if (afull.charAt(i) > bfull.charAt(i)) {
                    return 1;
                } 
                else if( afull.charAt(i) < bfull.charAt(i)) {
                    return -1;
                }
            }
            return 0;
    }
    //public static void main(String[] args) {
      //  AFloat a = new AFloat("0.00006");
        //AFloat b = new AFloat("0.0");
        //AFloat c = AFloat.div(a, b);
        //System.out.println(c.value); // Output: 10.212
        //System.out.println(b.value); // Output: .1234
//         System.out.println(div(new AFloat("10"), new AFloat("2")).value); // 5
// System.out.println(div(new AFloat("7"), new AFloat("3")).value); // 2.333333...
// System.out.println(div(new AFloat("0"), new AFloat("5")).value); // 0
// System.out.println(div(new AFloat("100"), new AFloat("4")).value); // 25
// System.out.println(div(new AFloat("1"), new AFloat("3")).value); // 0.333333...
// System.out.println(div(new AFloat("12345"), new AFloat("12345")).value); // 1
// System.out.println(div(new AFloat("1"), new AFloat("1000000")).value); // 0.000001
// System.out.println(div(new AFloat("99999"), new AFloat("10")).value); // 9999.9
// System.out.println(div(new AFloat("5"), new AFloat("2")).value); // 2.5
// System.out.println(div(new AFloat("2.5"), new AFloat("0.5")).value); // 5
// System.out.println(div(new AFloat("3.1415926535"), new AFloat("2")).value); // 1.57079632675
// System.out.println(div(new AFloat("100000000000000"), new AFloat("10")).value); // 10000000000000
// System.out.println(div(new AFloat("0.0000001"), new AFloat("0.00001")).value); // 0.01
// System.out.println(div(new AFloat("123.456"), new AFloat("7.89")).value); // 15.642580
// System.out.println(div(new AFloat("0.1"), new AFloat("0.3")).value); // 0.333333...
// System.out.println(div(new AFloat("6000"), new AFloat("3")).value); // 2000
// System.out.println(div(new AFloat("60"), new AFloat("100")).value); // 0.6
// System.out.println(div(new AFloat("1"), new AFloat("7")).value); // 0.142857...
// System.out.println(div(new AFloat("1"), new AFloat("9")).value); // 0.111111...
// System.out.println(div(new AFloat("81"), new AFloat("9")).value); // 9
// System.out.println(div(new AFloat("0.000001"), new AFloat("0.000001")).value); // 1
// System.out.println(div(new AFloat("10.5"), new AFloat("0.5")).value); // 21
// System.out.println(div(new AFloat("6000.000000000000000000000000000000"), new AFloat("3")).value); // 2000.000000...
// System.out.println(div(new AFloat("1.000000000000000000000000000000"), new AFloat("3")).value); // 0.333333...
// System.out.println(div(new AFloat("2"), new AFloat("4")).value); // 0.5
// System.out.println(div(new AFloat("9"), new AFloat("2")).value); // 4.5
// System.out.println(div(new AFloat("1"), new AFloat("6")).value); // 0.166666...
// System.out.println(div(new AFloat("0.25"), new AFloat("0.05")).value); // 5
// System.out.println(div(new AFloat("0.0000000001"), new AFloat("10000000000")).value); // 0.00000000000000001
// System.out.println(div(new AFloat("5000"), new AFloat("0.1")).value); // 50000
// System.out.println(div(new AFloat("2500"), new AFloat("0.25")).value); // 10000
// System.out.println(div(new AFloat("100000"), new AFloat("333")).value); // ~300.3003
// System.out.println(div(new AFloat("123456789"), new AFloat("1")).value); // 123456789
// System.out.println(div(new AFloat("0.333333333333333333333333333333"), new AFloat("1")).value); // 0.333333...
// System.out.println(div(new AFloat("999999999999999999"), new AFloat("3")).value); // 333333333333333333
// System.out.println(div(new AFloat("1"), new AFloat("1")).value); // 1
// System.out.println(div(new AFloat("22"), new AFloat("7")).value); // ~3.142857
// System.out.println(div(new AFloat("1"), new AFloat("11")).value); // ~0.090909...
// System.out.println(div(new AFloat("100000000"), new AFloat("100000000")).value); // 1
// System.out.println(div(new AFloat("0.0001"), new AFloat("0.0002")).value); // 0.5
// System.out.println(div(new AFloat("123456.789"), new AFloat("0.001")).value); // 123456789
// System.out.println(div(new AFloat("1000000"), new AFloat("1000")).value); // 1000
// System.out.println(div(new AFloat("0.00000000000001"), new AFloat("1000000000000")).value); // 0.00000000000000000001
// System.out.println(div(new AFloat("1000000000000"), new AFloat("0.00000000000001")).value); // 1e+26
// System.out.println(div(new AFloat("1"), new AFloat("0.000000000000000000000000000001")).value); // 1e+30
// System.out.println(div(new AFloat("0.000000000000000000000000000001"), new AFloat("1")).value); // 1e-30
// System.out.println(div(new AFloat("5.5"), new AFloat("2.2")).value); // 2.5
// System.out.println(div(new AFloat("100"), new AFloat("33")).value); // ~3.0303...
// System.out.println(div(new AFloat("1"), new AFloat("0")).value); // Error or Infinity, depending on handling


    //}
}
