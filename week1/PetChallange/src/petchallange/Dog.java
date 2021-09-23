package petchallange;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Dog extends Pet{
    String breed;
    static int dogCounter;
    final public String[] dogBreed = {"smallDog","bigDog","cleanDog","messyDog"};

    public Dog(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter your Pet name: ");
        name = scan.next();
        Random random = new Random();
        age = random.nextInt(10);
        breed = setBreed();
    }
    
     public Dog(String aname, int aage){
        name = aname;
        age = aage;
        setBreed();
        petCounter++;
        dogCounter++;
    }
     
    public Dog(String aname, int aage, String abreed){
        name = aname;
        age = aage;
        breed = abreed;
        petCounter++;
        dogCounter++;
    }
     private String setBreed(){
        int index = (int) (Math.random() * dogBreed.length );
        return dogBreed[index];
    }

    public String speak(){
    return "Wuff! " + " I am " + name + ", a " + age + " year old " + breed;
    }
}
