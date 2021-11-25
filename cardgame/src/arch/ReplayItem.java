package arch;

public class ReplayItem extends Node{
    private String  data;
    private ReplayItem firsItem, lastItem;

    public ReplayItem(String pValue) {
        super(pValue);
        data = pValue;
    }

    public String toString(){
        return data;
    }

    public String getData() {return data;}
}
