public class ShiftOrP {
  public static void main(String[] args) {
    String P = args[0].toLowerCase();
    String T = args[1].toLowerCase();
    int m = P.length();
    int n = T.length();
    int size = 26;// del alfabeto
    int B[] = new int[size];// la tabla B representa las posiciones del patron y sus caracteres
    int mask = 1;
    // preprocessing
    for (int i = 0; i < m; i++) {
      int p = P.charAt(i) - 'a';
      B[p] = (B[p] | mask);
      mask = mask << 1;
    }
    for (int i = 0; i < size; i++) {
      B[i] = ~B[i];
    }

    imprimeB(B);
    // Searching
    int D = ~0;
    for (int i = 0; i < n; i++) {
      System.out.println(i + 1 + ".- reading " + T.charAt(i));
      System.out.println(format(D << 1));
      System.out.println(format(B[T.charAt(i) - 'a']));
      System.out.println("----------------");
      D = (D << 1 | B[T.charAt(i) - 'a']);
      System.out.println(format(D));
      if ((D & 1 << (m - 1)) == 0)
        System.out.println("ocurrance at " + (i - m + 1));
    }
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
