package arch;

public class LinkedListTest {

    public static void state(LinkedList list){
        try {
            System.out.println("Size:\t\t\t" + list.getSize());
            System.out.println("Empty:\t\t\t" + list.isEmpty());

            System.out.println("First Node:\t\t" + list.getFirstNode().getData());
            System.out.println("Last Node:\t\t" + list.getLastNode().getData());

            System.out.println("toString\t\t" + list.toString());
            System.out.println("toArray.length\t" + list.toArray().length);
        }catch(EmptyDeckException e){
            System.out.println("Empty List");

        }catch(NullPointerException e){
            System.out.println("Empty List");
        }
    }


    public static void main(String[] args) {
        // Creation of a linked List
        LinkedList<String> myList = new LinkedList<>();
        System.out.println("State Of the list:");
        state(myList);
        System.out.println("\n\nAdding a set of suit:");
        myList.addNewEntry("A");
        myList.addNewEntry("K");
        myList.addNewEntry("Q");
        myList.addNewEntry("J");
        myList.addNewEntry("T");
        myList.addNewEntry("9");
        myList.addNewEntry("8");
        myList.addNewEntry("7");
        myList.addNewEntry("6");
        myList.addNewEntry("5");
        myList.addNewEntry("4");
        myList.addNewEntry("3");
        myList.addNewEntry("2");
        state(myList);
            System.out.println("\n\nRemoving The First Element (2):");
            myList.removeFirstElement();
            state(myList);
            System.out.println("\n\nRemoving an element in the middle (T):");
            myList.removeSpfecific("T");
            state(myList);
            System.out.println("\n\nRemoving the last element (A):");
            System.out.println("The first element swaps slip to the deleted posish");
            myList.removeSpfecific("A");
            state(myList);
            System.out.println("\n\nEmpty the list:");
            myList.clear();
            state(myList);
        System.out.println("Remove an element from an empty list");
            try {
                myList.removeFirstElement();
                state(myList);
            }catch (NullPointerException e ){
                System.out.println(e);
            }

    }
}
