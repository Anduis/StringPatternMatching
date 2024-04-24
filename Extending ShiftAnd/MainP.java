import java.util.Scanner;

public class MainP {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String[] x = sc.next().split("-");// entrada
    String T = sc.next();
    int n = T.length();
    // Preprocessing
    int B[] = new int[26];
    int L = 0;
    int I = 0;
    int F = 0;
    int i = 0;
    for (String s : x)
      if ((s.charAt(0) == 'x') && (s.charAt(1) == '('))
        L += Integer.parseInt(s.substring(2, s.length() - 1).split(",")[1]);// el limite superior
      else
        L++;
    for (int j = 1; j <= x.length; j++) {
      if ((x[j - 1].charAt(0) == 'x') && (x[j - 1].charAt(1) == '(')) {
        int a = Integer.parseInt(x[j - 1].substring(2, x[j - 1].length() - 1).split(",")[0]);
        int b = Integer.parseInt(x[j - 1].substring(2, x[j - 1].length() - 1).split(",")[1]);
        I = I | potencia(1, (i - 1));
        F = F | potencia(1, (i + b - a));
        for (int c = 0; c < B.length; c++)
          B[c] = B[c] | potencia(b, i);
        i += b;
      } else {
        for (int k = 0; k < x[j - 1].length(); k++)
          B[x[j - 1].charAt(k) - 'a'] = B[x[j - 1].charAt(k) - 'a'] | potencia(1, i);
        i++;
      }
    }
    System.out.println("I: " + format(I));
    System.out.println("F: " + format(F));
    System.out.println();
    imprimeB(B);
    // Searching
    int D = 0;
    /*
     * for (int pos = 1; pos <= n ; pos++ )
     * {
     * D = (D<<1 | 1) & B[T.charAt(pos-1)- 'a'];
     * D = D | (((F-(D&I)) & ~F));
     * if((D & (1<<(L-1))) != 0)
     * System.out.println("occurrence at " + pos);
     * System.out.println(D);
     * }
     */
    for (int pos = 0; pos < n; pos++) {
      System.out.println(pos + 1 + ".- reading " + T.charAt(pos));
      System.out.println(format(D << 1 | 1));
      System.out.println(format(B[T.charAt(pos) - 'a']));
      System.out.println("----------------");
      D = (D << 1 | 1) & B[T.charAt(pos) - 'a'];
      System.out.println(format(D));
      D = D | (((F - (D & I)) & ~F));
      System.out.println(format(D) + " ...");

      if ((D & 1 << (L - 1)) != 0) {
        System.out.println("ocurrance at " + (pos + 1));
      }
    }
    sc.close();

  }

  static int potencia(int b, int c) // pt 1, pt c
  {
    String temp = "";
    for (int i = b; i > 0; i--)
      temp += "1";
    for (int i = c; i > 0; i--)
      temp += "0";
    return Integer.parseInt(temp, 2);
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

  static void imprimeB(int[] A) {
    for (int i = 0; i < A.length; i++) {
      if (A[i] != 0)
        System.out.println("B[" + (char) (i + 'a') + "] " + format(A[i]));
    }
  }
}
