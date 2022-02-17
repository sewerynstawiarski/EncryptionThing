package com.HighLand;

import java.util.*;

public class EncryptionThing {
    private Scanner scanner;
    private ArrayList<Character> list;
    private ArrayList<Character> shuffledList;
    private char character;
    private String line;
    private char[] letters;

    EncryptionThing(){
        scanner = new Scanner(System.in);
        list = new ArrayList<>();
        shuffledList = new ArrayList<>();
        character = ' ';
        newKey();
        askQuestion();
    }
    private void askQuestion() {
        while (true) {
            System.out.println("------------------------------------");
            System.out.println("What do you want to do?");
            System.out.println("N = New Key, G = Get Key, E = Encrypt, D = Decrypt");
            char response = Character.toUpperCase(scanner.nextLine().charAt(0)); // wskazujesz szczytywanie tylko pierwszej litery z dopowiedzi i zmieniasz do dużej litery

            switch (response) {
                case 'N':
                    newKey();
                    break;
                case 'G':
                    getKey();
                    break;
                case 'E':
                    encrypt();
                    break;
                case 'D':
                    decrypt();
                    break;
                case 'Q':
                    quit();
                    break;
                default:
                    System.out.println("Not a valid answer");
            }
        }

    }
    private void newKey() {
        character = ' ';
        list.clear();
        shuffledList.clear();

        for (int i = 32; i<127; i++) {
            list.add(Character.valueOf(character));
            character++;
        }
        shuffledList = new ArrayList<>(list); // przekazujesz pierwotną listę znaków do shuffled(zamieszanej)
        Collections.shuffle(shuffledList); // to jest metoda klasy Collections żeby zamieszać randomowo wartościami
        System.out.println("A new key was generated!");

    }
    private void getKey() {
        System.out.println("Key: ");
        for (Character x : list) {
            System.out.print(x);
        }
        System.out.println();
        for (Character x : shuffledList) {
            System.out.print(x);
        }
        System.out.println();

    }
    private void encrypt() {
        System.out.println("Put in the message to encrypt: ");
        String message = scanner.nextLine();

        letters = message.toCharArray();

        for (int i = 0; i<letters.length; i++) {
            for (int j = 0; j<list.size(); j++) {
                if (letters[i]==list.get(j)) {
                    letters[i] = shuffledList.get(j);
                    break;
                }
            }
        }
        System.out.println("Encrypted message: ");
        for (char x : letters) {
            System.out.print(x);
        }
        System.out.println();
    }
    private void decrypt() {
        System.out.println("Put in message do decrypt: ");
        String message = scanner.nextLine();

        letters = message.toCharArray();

        for (int i = 0; i<letters.length; i++) {
            for (int j = 0; j<shuffledList.size(); j++) {
                if (letters[i]==shuffledList.get(j)) {
                    letters[i] = list.get(j);
                    break;
                }
            }
        }
        System.out.println("Decrypted message: ");
        for (char x : letters) {
            System.out.print(x);
        }
        System.out.println();

    }
    private void quit() {
        System.out.println("Thanks, man!");
        System.exit(0);
    }
}
