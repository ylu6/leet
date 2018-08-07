/**
 * Created by yeqing on 12/16/2017.
 */
public class IntegerToEnglishWords {
    String[] map1 = {"Zero", "One", "Two", "Three", "Four", "Five", "Six","Seven","Eight","Nine","Ten",
            "Eleven", "Twelve","Thirteen","Fourteen","Fifteen","Sixteen","Seventeen","Eighteen","Nineteen"};
    String[] map2 = {"","","Twenty", "Thirty", "Forty", "Fifty", "Sixty","Seventy","Eighty","Ninety"};
    String[] map3 = {"", "Thousand", "Million", "Billion"};

    public String numberToWords(int num) {
        if(num == 0) return "Zero";

        int idx = 0;
        String res = "";
        while(num > 0) {
            if(num%1000 != 0) {
                String cur = parse(num%1000);
                res = cur + (idx ==0 ? "" : " " + map3[idx]) + (res.length()==0 ? "": " " + res);
            }
            idx++;
            num /= 1000;
        }

        return res;
    }

    String parse(int num) {
        if(num == 0) return "";
        String res = "";
        if(num > 99) res += map1[num/100] + " " + "Hundred";
        num = num%100;
        if(num == 0) return res;
        if(res != "") res += " ";
        if(num < 20) res += map1[num];
        else {
            res += map2[num/10];
            res += num%10 == 0 ? "" : " " + map1[num%10];
        }
        return res;
    }

    public static void main(String[] args) {
        IntegerToEnglishWords sol = new IntegerToEnglishWords();
        System.out.println(sol.parse(1));
        System.out.println(sol.numberToWords(1000));
    }
}
