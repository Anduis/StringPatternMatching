// This class provides a method to format an integer into binary format.

public class binaryFormat {
  public static void main(String[] args) {
    System.out.println(format(args[0]));
  }

  public static String format(String a) {
    int x = Integer.valueOf(a);
    int y = 0;
    if (x <= 255)
      y = 1;
    if (x <= 65535 && x > 255)
      y = 2;
    String temp = Integer.toBinaryString(x);
    String respuesta = "";
    for (int i = 0; i < (y * 8); i++) {
      if (i % 4 == 0 && i != 0)
        respuesta = respuesta + " ";
      if (i < (y * 8) - temp.length())
        respuesta = respuesta + "0";
      if (i >= (y * 8) - temp.length())
        respuesta = respuesta + temp.charAt(i - ((y * 8) - temp.length()));
    }
    return respuesta;
  }
}
