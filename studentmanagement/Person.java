package studentmanagement;
public class Person{
    private String name;
    public Person(){}
    public void walk(){System.out.println("walking..........");}
    public void eating(){System.out.println("eating..........");}
    public void speaking(){System.out.println("speaking..........");}

    public String getName(){return this.name;}
    public void setName(String name){this.name=name;}
}