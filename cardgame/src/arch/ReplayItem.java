package arch;

public class ReplayItem extends Node{
    private String  data;

    public ReplayItem(String pValue) {
        super(pValue);
        data = pValue;
    }

    public String toString(){
        return data;
    }

    public String getData() {return data;}
}
