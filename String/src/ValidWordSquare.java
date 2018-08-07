public class ValidWordSquare {
    /**
     * @param words: a list of string
     * @return: a boolean
     */
    public boolean validWordSquare(String[] words) {
        for(int i = 0; i < words.length; i++) {
            for(int j = 0; j < words[i].length(); j++) {
                if(words[j].length() <= i || words[j].charAt(i) != words[i].charAt(j))
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[] words = {"abcd",
                "bnrt",
                "crmy",
                "dtye"};

        ValidWordSquare sol = new ValidWordSquare();
        System.out.println(sol.validWordSquare(words));
    }
}
