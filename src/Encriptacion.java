import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

public class Encriptacion {
    //atributos
    String llave = "Probando la encriptación";

    public static void main(String[] args) {
        String encriptada ="";
        String aEncriptar ="";
    Encriptacion encriptacion = new Encriptacion();
    aEncriptar = JOptionPane.showInputDialog("Ingresa la cadena a encriptar");
    encriptada =encriptacion.Encriptar(aEncriptar);
        JOptionPane.showMessageDialog(null,"Encriptado: "+encriptada);
        JOptionPane.showMessageDialog(null,"desencriptado: "+encriptacion.Desencriptar(encriptada));
    }


    //método de encriptación

    //libreria SecretKeySpec
    public SecretKeySpec CrearClave(String llave) {

        try {
            //Cadena array de tipo bytes obtenemos de llave
            byte[] cadena = llave.getBytes("UTF-8");
            //creamos objeto tipo MessageDigest, libreria java.security
            MessageDigest md = MessageDigest.getInstance("SHA-1"); //SHA-1 nuestro metodo
            //nuestro arreglo de bytes cadena pasamos el valor
            cadena = md.digest();
            //le damos tamaño fijo
            cadena = Arrays.copyOf(cadena, 16);
            //creamos SecretKeySpec
            SecretKeySpec secretKeySpec = new SecretKeySpec(cadena, "AES");
            //devolvemos el objeto
            return secretKeySpec;

        } catch (Exception e) {
            return null;
        }
    }
    //metodo para encriptar

    public String Encriptar(String encriptar) {
        try {
            SecretKeySpec secretKeySpec = CrearClave(llave);
            //clase Cipher
            Cipher cipher = Cipher.getInstance("AES");
            //modo encriptacion y llave
            cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec);
            //encriptamos con un arreglo de bytes con la cadena que he pasado por parametro
            byte [] cadena = encriptar.getBytes("UTF-8");
            //encriptamos una segunda cadena ya encriptada de la cadena anterior
            byte [] encriptada = cipher.doFinal(cadena);
            //pasamos arreglo encriptada de bytes a String
            String cadena_encriptada = Base64.getEncoder().encodeToString(encriptada);
            return cadena_encriptada;

        } catch (Exception e) {
            return "";
        }

    }

    public String Desencriptar(String desencriptar) {
        try {
            SecretKeySpec secretKeySpec = CrearClave(llave);
            //clase Cipher
            Cipher cipher = Cipher.getInstance("AES");
            //modo encriptacion y llave
            cipher.init(Cipher.DECRYPT_MODE,secretKeySpec);
            //desencriptamos con un arreglo de bytes con la cadena que he pasado por parametro
            byte [] cadena = Base64.getDecoder().decode(desencriptar);
            //encriptamos una segunda cadena ya encriptada de la cadena anterior
            byte [] desencriptacion = cipher.doFinal(cadena);
            //pasamos arreglo encriptada de bytes a String
            String cadena_desencriptada = new String(desencriptacion);
            return cadena_desencriptada;

        } catch (Exception e) {
            return "";
        }

    }

}
        //metodo para desencriptar

