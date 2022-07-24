public enum Operand {
    PLUS ("+", "\\+"), MIN("-", "-"), MULT("*", "\\*"), DEV("/", "\\/");
    private String sig;
    private String sig1;
    Operand(String sig, String sig1){
        this.sig = sig;
        this.sig1 = sig1;
    }
    public String getOper(){
        return sig;
    }
    public String getEoper(){
        return sig1;
    }
    public String toString(){
        return "+";
    }
}
