//Общий алгоритм программы
//1.Создается сообщение и преобразовывается в массив байт
//2.Генерируется ключ, длина которого совпадает с длиной сообщения.
//  Он также является массивом байт, который генерируется случайным образом
//3.Сообщение шифруется путем попарного выполнения операции XOR с каждым байтом сообщения и каждым байтом ключа
//4.Сообщение дешифруется путем повторного выполнения операции XOR, тем самым оно приводится к первоначальному виду


import java.util.Random;

public class ThirdLW {

    public static void main(String[] args) {
        //задаем открытый текст и преобразуем строку в массив байтов
        String message = "Штирлиц подумал. Ему понравилось, и он подумал еще раз";
        System.out.println("Открытый текст: " + message);
        byte[] plaintext = message.getBytes();

        //генерируем ключ, такой же длины как и сообщение
        byte[] key = generateKey(plaintext.length);
        System.out.println("Ключ: " + bytesToHex(key));

        //шифруем сообщение
        byte[] ciphertext = encrypt(plaintext, key);
        System.out.println("Зашифрованный текст: " + bytesToHex(ciphertext));

        //дешифруем сообщение
        byte[] decryptedText = decrypt(ciphertext, key);
        //преобразовываем массив байтов в строку
        String decryptedMessage = new String(decryptedText);
        System.out.println("\nПрименяем метод шифрования повторно");
        System.out.println("Расшифрованный текст: " + decryptedMessage);
    }

    //метод шифрования принимает на вход массив байтов и ключ в виде массива
    public static byte[] encrypt(byte[] plaintext, byte[] key) {
        //проверка, совпадает ли длина ключа с длиной сообщения, если нет, то обработка исключения
        if (plaintext.length != key.length) {
            throw new IllegalArgumentException("Длина ключа должна совпадать с длиной сообщения.");
        }
        //создаемый новый массив байтов для хранения зашифрованного текста
        //в цикле перебираем каждый байт открытого текста и ключа
        //согласно формуле, выполняем операцию XOR между соответствующими байтами текста и ключа и записываем в массив результат
        byte[] ciphertext = new byte[plaintext.length];
        for (int i = 0; i < plaintext.length; i++) {
            ciphertext[i] = (byte) (plaintext[i] ^ key[i]); //используем явное привидение к типу byte
        }
        //результат метода
        return ciphertext;
    }

    //метод дешифрования принимает на вход зашифрованный текст и ключ
    public static byte[] decrypt(byte[] ciphertext, byte[] key) {
        //используем метод шифрования для дешифрования, так как повторное XOR возвращает значения к исходным
        return encrypt(ciphertext, key);
    }

    //метод генерации ключа, где на вход подается длина
    public static byte[] generateKey(int length) {
        //создаем массив байт и заполняем его случайными байтами
        byte[] key = new byte[length];
        new Random().nextBytes(key); // генерация случайного ключа
        return key;
    }

    //вспомогательная функция для преобразования массива байтов в строку, содержащую шестнадцатеричное представление байтов.
    static String bytesToHex(byte[] bytes) {
        //используем класс StringBuilder для более эффективной работы со строками, чтобы каждый раз не создавать новый объект строки
        StringBuilder sb = new StringBuilder();
        //перебираем каждый байт в цикле формата for-each
        for (byte b : bytes) {
            //преобразуем байт в шестнадцатиричную строку (X), где минимальная длина равняется двум (02)
            //если длина символа равняется одному, то слева добавляется нуль
            //конкатинируем получившуюся строку к общей строке
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }
}
