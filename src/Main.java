import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("Пароль №1: " + firstPassword("sony", "hewlett", "packard"));
        System.out.println("Пароль №2: " + secondPassword("scleroses", "scoliosis", "paradantoz"));

        Scanner sc = new Scanner(System.in);
        System.out.print("Проверка пароля №");
        int n = sc.nextInt();
        if (n == 1)
        {
            enterPassword(firstPassword("sony", "hewlett", "packard"));
        } else if (n == 2){
            enterPassword(secondPassword("scleroses", "scoliosis", "paradantoz"));
        } else {System.out.println("Ошибка ввода");}

    }

    public static String firstPassword(String firstWord, String secondWord, String thirdWord)
    {
        //Начальные данные
        String result;
        String alpabet = "a b c d e f g h i j k l m n o p q r s t u v w x y z";
        String[] arrAlpabet = alpabet.split(" ");
        //Первая буква
        char firstLetter = ' ';
        int n = firstWord.length() + thirdWord.length();
        n = (n > 26 ? n % 26 : n);
        result = arrAlpabet[n-1];

        //Вторая буква
        char secondLetter = secondWord.charAt(secondWord.length()-1);
        for (int i = 0; i < arrAlpabet.length-1; i++)
        {
            if (arrAlpabet[i].equals(String.valueOf(secondLetter)))
            {
                result += arrAlpabet[i-1];
            }
        }
        //Третья буква
        char thirdLetter = ' ';
        if (thirdWord.length() % 2 != 0)
        {
            for (int i = 0; i < thirdWord.length()/2; i++)
            {
                thirdLetter = thirdWord.charAt(i+1);
            }
        } else
        {
            for (int i = 0; i < thirdWord.length()/2; i++)
            {
                thirdLetter = thirdWord.charAt(i);
            }
        }
        for (int i = 0; i < arrAlpabet.length-1; i++)
        {
            if (arrAlpabet[i].equals(String.valueOf(thirdLetter)))
            {
                result += arrAlpabet[i-1];
            }
        }
        //Четвертая буква
        char fourthLetter = firstWord.charAt(0);
        for (int i = 0; i < arrAlpabet.length-1; i++)
        {
            if (arrAlpabet[i].equals(String.valueOf(fourthLetter)))
            {
                result += arrAlpabet[i-1];
            }
        }
        return result;
    }

    public static String secondPassword(String firstWord, String secondWord, String thirdWord)
    {
        //Начальные данные
        String result = "";
        String alpabet = "a b c d e f g h i j k l m n o p q r s t u v w x y z";
        String[] arrAlpabet = alpabet.split(" ");
        //Первая буква
        char firstLetter = firstWord.charAt(1);
        for (int i = 0; i < arrAlpabet.length; i++)
        {
            if (arrAlpabet[i].equals(String.valueOf(firstLetter)))
            {
                result += arrAlpabet[i+1];
            }
        }
        //Вторая буква
        char secondLetter = secondWord.charAt(secondWord.length()-1);
        for (int i = 0; i < arrAlpabet.length; i++)
        {
            if (arrAlpabet[i].equals(String.valueOf(secondLetter)))
            {
                result += arrAlpabet[i-2];
            }
        }
        //Третья буква
        char thirdLetter = ' ';
        if (thirdWord.length() % 2 != 0)
        {
            for (int i = 0; i < thirdWord.length()/2; i++)
            {
                thirdLetter = thirdWord.charAt(i+1);
            }
        } else
        {
            for (int i = 0; i < thirdWord.length()/2; i++)
            {
                thirdLetter  = thirdWord.charAt(i);
            }
        }
        for (int i = 0; i < arrAlpabet.length-1; i++)
        {
            if (arrAlpabet[i].equals(String.valueOf(thirdLetter)))
            {
                result += arrAlpabet[i-1];
            }
        }
        //Четвертая буква
        int n = firstWord.length() + thirdWord.length() + 1;
        n = (n > 26 ? n % 26 : n);
        result += arrAlpabet[n-1];

        return result;
    }

    public static void enterPassword(String truePassword)
    {
        JFrame frame = new JFrame("Проверка пароля");
        frame.setSize(300, 180);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        // Метка для поля ввода пароля
        JLabel label = new JLabel("Введите пароль:");
        label.setBounds(20, 50, 120, 25);
        frame.add(label);

        // Поле для ввода пароля
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(140, 50, 120, 25);
        frame.add(passwordField);

        // Кнопка для проверки пароля
        JButton button = new JButton("Ввод");
        button.setBounds(100, 90, 80, 25);
        frame.add(button);

        // Метка для результата проверки
        JLabel resultLabel = new JLabel("");
        resultLabel.setBounds(20, 10, 240, 25);
        frame.add(resultLabel);

        // Добавляем обработчик нажатия на кнопку
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputPassword = new String(passwordField.getPassword());

                if (inputPassword.equals(truePassword)) {
                    resultLabel.setText("Пароль верен!");
                } else {
                    resultLabel.setText("Неправильный пароль!");
                }
            }
        });

        // Отображаем окно
        frame.setVisible(true);
    }
}