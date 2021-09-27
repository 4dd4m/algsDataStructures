package localhost.animal;
public class Animal {
    protected String food;
    protected int life;
    protected String name;
    protected int age = 0;

    public Animal(){
        food = null;
        life = 0;
    }
    public Animal(String pfood){
        food = pfood;
        life = 0;
    }
    public Animal(int plife){
        food = null;
        life = plife;
    }
    public Animal(String pfood, int plife){
        food = pfood;
        life = plife;
    }

    public String toString(){
        String result = "This ";
        if(name != null){
            result+= "animal called " + name;
        }else{
            result += "unknown animal ";
        }
        if(food != null) result += food + " eater ";
        if(age != 0) result += "is " + age + " years old, ";
        if(life == 0){
            result += "lives unknown number of years";
        }else{
            result += "lives " + life + " years";
        }
        System.out.println(result);
        return result;
    }

    public static void main(String[] args){
        Animal a1 = new Animal();
        Animal a2 = new Animal("meat", 6);
        Animal a3 = new Animal(15);
        Animal a4 = new Animal("vegan",100);
        a1.toString();
        a2.toString();
        a3.toString();
        a4.toString();
    }

}
