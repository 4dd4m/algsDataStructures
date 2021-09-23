package petchallange;
import java.util.Scanner;
import java.util.Random;

public class Cat extends Pet{
    String breed;
    final public String[] catBreed = {"smallCat","bigCat","cleanCat","messyCat"};
    int catCounter;
    
    public Cat(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter your Pet name: ");
        name = scan.next();
        Random random = new Random();
        age = random.nextInt(10);
        breed = setBreed();
    }
    
    public Cat(String aname, int aage){
        name = aname;
        age = aage;
        setBreed();
        petCounter++;
        catCounter++;
    }
    
    public Cat(String aname, int aage, String abreed){
        name = aname;
        age = aage;
        breed = abreed;
        petCounter++;
        catCounter++;
    }
    
    protected String setBreed(){
        int index = (int) (Math.random() * catBreed.length );
        return catBreed[index];
    }


    public String speak(){
        return "Miaow!" + " I am " + name + ", a " + age + " year old " + breed;
    }
}
