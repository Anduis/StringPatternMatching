//shifting example
public class t {
  public static void main(String[] args) {
    int D = 0;
    System.out.println(format(D));
    D = D << 1;
    D = (D | 1) << 7;
    System.out.println(format(D));
    D = D << 1;
    D = (D | 1) << 5;
    System.out.println(format(D));
    D = D << 1;
    D = (D | 1) << 7;
    System.out.println(format(D));
  }

  static void imprimeB(int[] A) {
    for (int i = 0; i < A.length; i++) {
      if (A[i] != 0)
        System.out.println("B[" + (char) (i + 'a') + "] " + format(A[i]));
    }
  }

  public static String format(int x) {
    int y = 0;
    if (x <= 255)
      y = 1;
    if (x <= 65535 && x > 255)
      y = 2;
    if (x > 65535)
      y = 3;
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
