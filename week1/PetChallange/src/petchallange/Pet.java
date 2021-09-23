package petchallange;
import java.util.Scanner;

public class Pet{
    
    String name;
    int age;
    String breed;
    static int petCounter;

    public Pet(int aAge){
        name = null;
        age = aAge;
        petCounter++;
    }

    public Pet(String aName, int aAge, String aBreed){
        name = aName;
        age = aAge;
        breed = aBreed;
        petCounter++;
    }

    public  Pet(){
    }

    public int getAge(){
        return age;
    }

    public void setAge(int aAge){
        age = aAge;
    }

    public String getName(){
        return name;
    } 
    
    public String getBreed(){
        return breed;
    }

    public void setName(String aName){
        name = aName;
    }
    public String speak(){
        return "";
    }

}
