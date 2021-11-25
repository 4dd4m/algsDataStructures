package arch;

public interface StackInterface {
    public int[] peek();
    public int getSize();
    public boolean push(int[] newEntry);
    public boolean isEmpty();
    public int[] pop ();
    public void clear ();
}
