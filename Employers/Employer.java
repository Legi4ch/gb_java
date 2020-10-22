package Employers;

import java.text.DecimalFormat;

public class Employer {
    protected String name;
    protected String position;
    protected String email;
    protected String phone;
    private double salary;
    private int age;

    public Employer(String _name,String _position,String _email,String _phone,double _salary,int _age) {
        this.name = _name;
        this.position = _position;
        this.email = _email;
        this.phone = _phone;
        this.salary = (_salary > 0) ? _salary : 0;
        this.age = (_age > 18 && _age < 65) ? _age : 0;
    }

    public int getAge() {
        return this.age;
    }

    public void empInfo() {
        System.out.printf("Карточка сотрудника: %s \n",this.name);
        System.out.printf("Должность: %s \n",this.position);
        System.out.printf("Email: %s \n",this.email);
        System.out.printf("Телефон: %s \n",this.phone);
        System.out.printf("Зарплата: %s \n",formatSalary(this.salary));
        System.out.printf("Возраст: %d \n",this.age);
        System.out.println("______________________");
    }

    public String toString() {
        return this.name + " / " + this.position;
    }

    private String formatSalary(double _salary) {
        DecimalFormat frmt = new DecimalFormat("###,###.## ₽");
        String result = frmt.format(_salary);
        return result;
    }
}
