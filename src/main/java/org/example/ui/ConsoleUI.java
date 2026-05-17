package org.example.ui;

import org.example.service.HabitService;
import org.example.storage.HabitStorage;
import org.example.storage.SaveToDB;
import org.example.storage.SaveToFile;

import java.sql.SQLException;
import java.util.Scanner;

public class ConsoleUI {
    public static void printMenu(){
        System.out.println("Привет, это трекер твоих привычек!");
        System.out.print("""
                Главное меню:
                1. Создать привычку
                2. Отметить привычку выполненной
                3. Показать статистику
                4. Удалить привычку
                0. Выход
                """);
    }

    public static void run(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Сохранять в файл (1) или в базу данных (2)\n По умолчанию сохранение в бд");
        while (!sc.hasNextInt()) {
            System.out.println("Это не число. Попробуйте еще раз.");
            sc.next();
        }
        int a = sc.nextInt();

        HabitStorage storage = null;
        try {
            if(a == 1) storage = new HabitStorage(new SaveToFile());
            else storage = new HabitStorage(new SaveToDB());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        HabitService service = new HabitService(storage, "habits_data.txt");
        boolean f = true;
        while(f){
            int c;
            while (!sc.hasNextInt()) {
                System.out.println("Это не число. Попробуйте еще раз.");
                sc.next();
            }
            c = sc.nextInt();
            switch (c){
                case 1 :
                    create(sc, service);
                    break;
                case 2 :
                    checkHabit(sc, service);
                    break;
                case 3 :
                    System.out.printf(service.statsOut());
                    break;
                case 4 :
                    deleteHabit(sc, service);
                    break;
                case 0 : f = false;
                    break;
                default :
                    System.out.println("Неправильный ввод");
            }
        }
    }

    static private void create(Scanner sc, HabitService service){
        System.out.print("Введите название привычки: ");
        String name = sc.next();
        service.createHabit(name);
    }

    static private void checkHabit(Scanner sc, HabitService service){
        System.out.print("Введите название привычки: ");
        String name = sc.next();
        boolean successFlag = service.checkHabit(name);
        if(!successFlag) System.out.println("Привычка не найдена: " + name);

    }

    static private void deleteHabit(Scanner sc, HabitService service){
        System.out.print("Введите название привычки: ");
        String name = sc.next();
        boolean successFlag = service.deleteHabit(name);
        if(!successFlag) System.out.println("Привычка не найдена: " + name);
    }


}
