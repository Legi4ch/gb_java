package OOP;

import TikTak.TikTak;

import com.sun.org.apache.bcel.internal.util.ClassQueue;

public class Main {
    public static void main(String[] args) {
        Cat cat1 = new Cat("Martin","Black",7);
        Cat cat2 = new Cat();
        Cat cat3 = new Cat();
        Cat cat4 = new Cat();
        Cat cat5 = new Cat();
        Cat cat6 = new Cat();
        cat2 = null;
        System.gc();
        cat1.voice();
        //cat2.voice("Гав");
        cat1.setAge(15);
        cat1.printCat();
        System.out.println(Cat.countCats);
        Cat.printCountCats();

        TikTak.playGame();
    }
}
