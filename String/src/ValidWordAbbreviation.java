public class ValidWordAbbreviation {
    /**
     * @param word: a non-empty string
     * @param abbr: an abbreviation
     * @return: true if string matches with the given abbr or false
     */
    public boolean validWordAbbreviation(String word, String abbr) {
        int i1 = 0, i2 = 0;
        while (i1 < word.length() && i2 < abbr.length()) {
            if(Character.isLetter(abbr.charAt(i2))) {
                if(word.charAt(i1) != abbr.charAt(i2)) return false;
                i1++;
                i2++;
            } else {
                if(abbr.charAt(i2) == '0') return false;
                int count = 0;
                while(i2 < abbr.length() && Character.isDigit(abbr.charAt(i2))) {
                    count = count*10 + (abbr.charAt(i2) - '0');
                    i2++;
                }
                i1 += count;
            }
        }
        return i1==word.length() && i2==abbr.length();
    }

    public static void main(String[] args) {
        String s = "internationalization", abbr = "i12iz4n";
        ValidWordAbbreviation sol = new ValidWordAbbreviation();
        System.out.println(sol.validWordAbbreviation(s, abbr));
    }
}
