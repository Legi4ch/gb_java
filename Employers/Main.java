package Employers;


public class Main {

    public static void main(String[] args) {
        //Employer emp = new Employer("Олег","Директор","sasa@swasd.rr","+79255",100000,40);
        //emp.empInfo();
        Integer x = 1;


        Employer[] empArr = new Employer[5];
        empArr[0] = new Employer("Олег","Директор","1@emm.com","12345",100000,42);
        empArr[1] = new Employer("Иван","Зам.директора","2@emp.com","12345",70000,41);
        empArr[2] = new Employer("Степан","Водитель","3@emp.com","12345",50000,24);
        empArr[3] = new Employer("Рустам","Экспедитор","4@emp.com","12345",35000,28);
        empArr[4] = new Employer("Людмила","Бухгалтер","5s@emp.com","12345",50000,37);

        for (Employer person : empArr) {
            if (person.getAge() > 40) {
                person.empInfo();
            }
        }
    }


}
