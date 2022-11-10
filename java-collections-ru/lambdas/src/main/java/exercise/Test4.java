package exercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Test4{
    public static void main(String[] args) {
        List<Employee> e = new ArrayList<>();
        Employee e1 = new Employee(1000, "Anna", "Akhmatova", 100);
        Employee e2 = new Employee(330, "Lev", "Tolstoy", 230);
        Employee e3 = new Employee(300, "Tom", "Kruz", 999);
        Employee e4 = new Employee(200, "Bob", "ARBUZ", 99);
        e.add(e1);
        e.add(e2);
        e.add(e3);
        e.add(e4);
        System.out.println("Before Sort: \n" + e);
        Collections.sort(e, new SalaryComparator());
        System.out.println(e);


    }
}

 class Employee {
//         implements Comparable<Employee> {
    int id;
    String name;
    String surname;
    int salary;

    public Employee(int id, String name, String surname, int salary) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.salary = salary;
    }

     @Override
     public String toString() {
         return "Employee{" +
                 "id=" + id +
                 ", name='" + name + '\'' +
                 ", surname='" + surname + '\'' +
                 ", salary=" + salary +
                 '}';
     }

//     @Override
//     public int compareTo(Employee anotherEmp) {
////         if (this.id == anotherEmp.id) {
////             return 0;
////         } else if (this.id < anotherEmp.id) {
////             return -1;
////         }
////         else {
////             return 1;
////         }
////         return this.id = anotherEmp.id;
//         int res = this.name.compareTo(anotherEmp.name);
//         if (res == 0) {
//             res = this.surname.compareTo(anotherEmp.surname);
//         }
//         return res;
//     }
 }

 class IdComparator implements Comparator<Employee> {
     @Override
     public int compare(Employee emp1, Employee emp2) {
         if (emp1.id == emp2.id) {
             return 0;
         } else if (emp1.id < emp2.id) {
             return -1;
         }
         else {
             return 1;
         }
     }
 }

class NameComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee emp1, Employee emp2) {
        return emp1.name.compareTo(emp2.name);
    }
}

class SalaryComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee emp1, Employee emp2) {
        return emp1.salary- emp2.salary;
    }
}