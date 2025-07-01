import java.io.IOException;
import java.util.Scanner;

public class MenuUsuario {

     private static final String[] MONEDAS = {"COP", "EUR",
             "MXN", "ARS", "GBP", "JPY", "CLP"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        System.out.println("=== Conversor de USD a otras monedas ===");

        while (continuar) {
            try {
                System.out.println("\nSeleccione la moneda destino:");
                for (int i = 0; i < MONEDAS.length; i++) {
                System.out.printf("%d. %s%n", i + 1, MONEDAS[i]);
                }

                System.out.print("Opción: ");
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar salto de línea

                if (opcion < 1 || opcion > MONEDAS.length) {
                    System.out.println("Opción inválida. Intente de nuevo.");
                    continue;
                }

                String monedaDestino = MONEDAS[opcion - 1];


                System.out.print("Ingrese la cantidad en USD: ");
                double cantidadUSD = scanner.nextDouble();
                scanner.nextLine(); // Limpiar salto de línea


                double resultado = EnlaceAPI.convertirUSD(cantidadUSD, monedaDestino);

                if (resultado != -1) {
                    System.out.printf(" %.2f USD equivalen a %.2f %s%n", cantidadUSD, resultado, monedaDestino);
                }

                System.out.print("¿Desea hacer otra conversión? (s/n): ");
                String respuesta = scanner.nextLine();
                continuar = respuesta.equalsIgnoreCase("s");

            } catch (IOException | InterruptedException e) {
                System.out.println("Error al conectar con la API: " + e.getMessage());
                continuar = false;
            } catch (Exception e) {
                System.out.println(" Entrada inválida. Intente nuevamente.");
                scanner.nextLine();
            }
        }

        System.out.println(" Gracias por usar el conversor. ¡Hasta pronto!");
        scanner.close();
    }
}
