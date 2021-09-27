package localhost.animal;

class Lion extends Animal{
    private static int numOfLions;
    private String name;
    private int age;

    public Lion(){
    }

    public Lion(String food, int life, int age, String pname){
        super(food, life);
        setAge(age);
        this.name = pname;
        numOfLions++;
    }

    public void setAge(int page){
        this.age = page;
    }

    public String getNumOfLions(){
        return "Lions in the wild: " + numOfLions;
    }

}
