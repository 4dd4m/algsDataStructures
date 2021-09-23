package petchallange;
import java.util.Scanner;
import java.util.ArrayList;

public class PetChallange {
        boolean goodAnswer;
        ArrayList<Pet> pets = new ArrayList<Pet>();
        Pet[] pet = new Pet[5];
        int catCounter;
        int dogCounter;

    
    private void fillArray(){
        Scanner scan = new Scanner(System.in);
        
        for(int i = 0; i < 5;i++){
            boolean goodchoice = false;
            while(!goodchoice){
            System.out.print("What type of pet you want to create?\n1. Cat\n2. Dog\n>>>");
            String choice = scan.next();
            if("1".equals(choice)){
                pet[i] = new Cat();
                goodchoice = true;
                catCounter++;
            }else if("2".equals(choice)){
                pet[i] = new Dog();
                goodchoice = true;
                dogCounter++;
            }else if("exit".equals(choice)){
                System.exit(0);
            }else{
                System.out.print("\nThis is not a valid choice\n");
                goodchoice = false;
            }
        }
    }
         System.out.println("We have created " + catCounter + " cats and " + dogCounter + " dogs.");
    }
    
    public void speak(){
        
        Scanner scan = new Scanner(System.in);
        String name = "";
        while(!"exit".equals(name)){
        System.out.print("Pick a pet to speak: ");
        name = scan.next();
        boolean found = false;
        for(int i=0;i<5;i++){
            if(pet[i].name.equals(name)){
                found = true;
                System.out.println(pet[i].speak());
                break;
                
            }
        }
            
        }
        System.out.println("Bye");
    }



    public static void main( String[] args ) {
        PetChallange a = new PetChallange();
        a.fillArray();
        a.speak();
    }
}

