import java.util.Scanner;

class Palindromo {
    public static boolean isPalindromo(int numero) {
        int original = numero;
        int reverso = 0;

        while (numero > 0) {
            int digito = numero % 10;
            reverso = reverso * 10 + digito;
            numero /= 10;
        }

        return original == reverso;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite um número inteiro: ");
        int numero = scanner.nextInt();

        if (isPalindromo(numero)) {
            System.out.println(numero + " é um número palíndromo!");
        } else {
            System.out.println(numero + " não é um número palíndromo.");
        }

        scanner.close();
    }
}
