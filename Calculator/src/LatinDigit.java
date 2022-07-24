public enum LatinDigit {
    I(1), V(5), X(10), IV(4), IX(9), L(50);
    private int digit;
    LatinDigit(int dig){
        this.digit = dig;
    }
    public int getDigit(){
        return digit;
    }
}
