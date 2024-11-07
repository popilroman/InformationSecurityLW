import java.util.Arrays;
import java.util.Random;

public class ThirdLW {

    public static void main(String[] args) {
        //задаем открытый текст и преобразуем строку в массив байтов
        String message = "Штирлиц – Вы Герой!!";
        System.out.println("Открытый текст: " + message);
        byte[] plaintext = message.getBytes();

        //генерируем ключ, такой же длины как и сообщение
        byte[] key = generateKey(plaintext.length);
        System.out.println("Ключ (hex): " + bytesToHex(key));

        //шифруем сообщение
        byte[] ciphertext = encrypt(plaintext, key);
        System.out.println("Шифротекст (hex): " + bytesToHex(ciphertext));

        //дешифруем сообщение
        byte[] decryptedText = decrypt(ciphertext, key);
        //преобразовываем массив байтов в строку
        String decryptedMessage = new String(decryptedText);
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
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
