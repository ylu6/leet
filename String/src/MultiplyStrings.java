public class MultiplyStrings {
    public String multiply(String num1, String num2) {
        int len1 = num1.length(), len2 = num2.length();
        int[] arr = new int[len1+len2];

        for(int i = len1-1; i >= 0; i--) {
            for(int j = len2-1; j >= 0; j--) {
                int mul = (num1.charAt(i)-'0') * (num2.charAt(j)-'0');
                int idx1 = i+j, idx2 = idx1+1;
                int sum = mul + arr[idx2];

                arr[idx2] = sum % 10;
                arr[idx1] += sum/10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < arr.length; i++) {
            if(sb.length() != 0 || arr[i] != 0)
                sb.append(arr[i]);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }
}
