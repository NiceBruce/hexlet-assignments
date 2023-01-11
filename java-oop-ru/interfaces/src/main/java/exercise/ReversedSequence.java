package exercise;

// BEGIN
class ReversedSequence implements java.lang.CharSequence {
    String str;

    ReversedSequence(String str) {
        this.str = str;
    }

    @Override
    public int length() {
        return str.length();
    }

    @Override
    public char charAt(int index) {
        return str.charAt(index);
    }

    public String toString(String str) {
        return "";
    }
    @Override
    public CharSequence subSequence(int start, int end) {
        return null;
    }

}
// END
