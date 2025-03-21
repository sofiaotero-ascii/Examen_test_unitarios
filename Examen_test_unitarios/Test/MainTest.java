import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MainTest {

    // Test parametrizado para comprobar la validez de un DNI y su letra
    @ParameterizedTest
    @CsvSource({
            "00000000, T, true",
            "00000001, R, true",
            "00000002, W, true",
            "00000003, A, true",
            "00000004, G, true"
    })
    void testComprobarDNI(String dni, char letra, boolean validez) {
        assertEquals(validez, Main.comprobarDNI(dni, letra));
    }

    // Test parametrizado para calcular la letra del DNI
    @ParameterizedTest
    @CsvSource({
            "00000000, T",
            "00000001, R",
            "00000002, W",
            "00000003, A",
            "00000004, G"
    })
    void testCalcularLetra(String dni, char letra) {
        assertEquals(letra, Main.calcularLetraDNI(dni));
    }

    // Test para verificar que el código maneja correctamente una longitud inválida de DNI
    @Test
    void testComprobarDNILongitudIncorrecta() {
        String invalidDNI = "12345"; // Longitud incorrecta
        assertThrows(IllegalArgumentException.class, () -> {
            Main.comprobarDNI(invalidDNI, 'T');
        });
    }

    // Test para comprobar que "S" y "Q" estaén bien posicionadas
    @Test
    void testCalcularLetraEnPosicion() {
        // Asegurarse de que la letra "S" y "Q" están correctamente posicionadas
        char[] letrasDNI = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};

        // Test con un DNI que debería devolver la letra 'S'
        String dni = "00000016"; // El resto de 00000016 % 23 = 16, que corresponde a la letra 'S'
        assertEquals('S', Main.calcularLetraDNI(dni));

        // Test con un DNI que debería devolver la letra 'Q'
        dni = "00000017"; // El resto de 00000017 % 23 = 17, que corresponde a la letra 'Q'
        assertEquals('Q', Main.calcularLetraDNI(dni));
    }
}