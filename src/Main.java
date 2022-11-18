import java.awt.*;
import java.util.Objects;
import java.util.Scanner;

class Student {
    int ID;
    boolean flag;
    boolean checker = false;
    int grade;
    Student(int id) {
        ID = id;
    }
    @Override
    public String toString() {
        return "Student " + ID + "\t" + grade;
    }
}

public class Main {
    static Student[] students;
    public static void random_student(int n, Scanner in) {
        var number = ((int)(Math.random() * n)) % n;
        var choosen_student = students[number];
        choosen_student.checker = true;
        System.out.printf("Отвечает Студент %d\n", number);
        System.out.println("Присутствует ли на паре?");
        var answer = in.next();
        if (Objects.equals(answer, "y")) {
            choosen_student.flag = true;
            System.out.print("Оценка за ответ: ");
            choosen_student.grade = in.nextInt() % 10;
        } else {
            choosen_student.flag = false;
        }
    }
    public static void grade_list() {
        for (var student : students) {
            if (student.checker && student.flag) {
                System.out.println(student);
            }
        }
    }
    public static void menu() {
        System.out.println("Введите одну из команд: ");
        System.out.println("/r - выбрать рандомного студента и опросить его");
        System.out.println("/l - вывести список студентов с оценками за текущий семинар");
        System.out.println("/e - закончить семинар (выйти из программы)");
    }
    public static void main(String[] args) {
        var in = new Scanner(System.in);
        System.out.println("Введите количество студентов: ");
        var n = in.nextInt();
        students = new Student[n];
        for (int i = 0; i < n; i++) {
            students[i] = new Student(i + 1);
        }
        boolean exit = false;
        do {
            menu();
            String command = in.next();
            switch (command) {
                case "/r":
                    random_student(n, in);
                    break;
                case "/l":
                    grade_list();
                    break;
                case "/e":
                    exit = true;
                    break;
            }
        } while (!exit);
    }
}
