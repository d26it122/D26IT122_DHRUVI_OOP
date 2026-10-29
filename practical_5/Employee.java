abstract class employe {

    String name;
    int id;

    employe(String name, int id) {
        this.name = name;
        this.id = id;
    }

    abstract double monthlySalary();
}

class FullTime extends employe {

    double salary;

    FullTime(String name, int id, double salary) {
        super(name, id);
        this.salary = salary;
    }

    double monthlySalary() {
        return salary;
    }
}

class PartTime extends employe {

    double hours;
    double rate;

    PartTime(String name, int id, double hours, double rate) {
        super(name, id);
        this.hours = hours;
        this.rate = rate;
    }

    double monthlySalary() {
        return hours * rate;
    }
}

class Intern extends employe {

    double stipend;

    Intern(String name, int id, double stipend) {
        super(name, id);
        this.stipend = stipend;
    }

    double monthlySalary() {
        return stipend;
    }
}

public class Employee{

    public static void main(String[] args) {

        employe[] employees = {
            new FullTime("Alice", 101, 40000),
            new PartTime("Bob", 102, 100, 200),
            new Intern("John", 103, 10000)
        };

        double total = 0;

        for (int i = 0; i < employees.length; i++) {

            double salary = employees[i].monthlySalary();

            System.out.println(employees[i].name + " Salary = " + salary);

            total = total + salary;

            if (employees[i] instanceof Intern) {
                System.out.println("This employee is an Intern");
            }
        }

        System.out.println("Total Payroll = " + total);
    }
}