package OOP;

public class Cat {
    protected String name;
    protected String color;
    private int age;
    protected static int countCats;

    public Cat() {
        this("Undefined","Undefined",0);
    }

    public Cat(String _name,String _color,int _age) {
        this.name = _name;
        this.color = _color;
        this.age = _age;
        countCats++;
    }



    public void voice() {
        System.out.printf("Кот %s сказал мяу\n", this.name);
    }

    public void voice (String word) {
        System.out.printf("Кот %s сказал %s\n", this.name, word);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", age=" + age +
                '}';
    }


    public void printCat(){
        System.out.println(this);
    }

    public void setAge(int _age) {
        this.age = (_age > 0 && _age < 30) ? _age : 0;
    }

    public int getAge() {
        return age;
    }

    public static void printCountCats(){
        System.out.println("Кол-во: " + countCats);
    }

    @Override
    protected void finalize() {
        countCats--;
        System.runFinalization();
        System.out.println("Кот " + this.name + " покинул нас");
    }


}
