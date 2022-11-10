package exercise;

public class Student {
    String name;
    char sex;
    int age;
    int cource;
    double avgGrade;

    public Student(String name, char sex, int age, int cource, double avgGrade) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.cource = cource;
        this.avgGrade = avgGrade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", cource=" + cource +
                ", avgGrade=" + avgGrade +
                '}';
    }
}
