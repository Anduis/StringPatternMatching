import java.util.Scanner;

public class Test {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int r = sc.nextInt();// cantidad de patrones
    String[] P = new String[r];// patrones
    for (int i = 0; i < r; i++)
      P[i] = sc.next().toLowerCase();
    String T = sc.next().toLowerCase();// texto
    // preprocessing
    int[] B = new int[26];
    int l = 0;
    int Di = 1;
    int Df = 0;
    for (int k = 0; k < r; k++) {
      for (int j = 1; j <= P[k].length(); j++)
        B[P[k].charAt(j - 1) - 'a'] = (B[P[k].charAt(j - 1) - 'a'] | 1 << (l + j - 1));
      l = l + P[k].length();
    }
    for (int i = 1; i < r; i++) {
      Di = Di << P[i].length();
      Di = (Di | 1);
    }
    for (int i = 0; i < r; i++) {
      Df = Df << 1;
      Df = (Df | 1) << (P[i].length() - 1);
    }
    int[] finales = new int[r];
    for (int i = 0; i < r; i++) {
      finales[i] = 1;
      int j = i;
      while (j < r) {
        finales[i] = finales[i] << (P[j].length());
        j++;
      }
      finales[i] = finales[i] >> 1;
    }
    imprimeB(B);
    System.out.println("Di: " + format(Di));
    System.out.println("Df: " + format(Df));
    // searching
    int D = 0;
    for (int pos = 0; pos < T.length(); pos++) {
      // System.out.println(pos+1+".- reading " + T.charAt(pos));
      // System.out.println(format(D));
      // System.out.println(format(B[T.charAt(pos) - 'a']));
      // System.out.println("----------------");
      D = ((D << 1) | Di) & (B[T.charAt(pos) - 'a']);
      // System.out.println(format(D));
      if ((D & Df) != 0)
        for (int i = 0; i < r; i++) {
          if ((finales[i] & (D & Df)) != 0)
            System.out.println("ocurre " + P[i]);
        }
      ;
    }
    sc.close();

  }

  static void imprimeB(int[] A) {
    for (int i = 0; i < A.length; i++) {
      if (A[i] != 0)
        System.out.println("B[" + (char) (i + 'a') + "] " + format(A[i]));
    }
  }

  public static String format(int x) {
    int y = 3;
    /*
     * if (x<=255)
     * y = 1;
     * if (x<=65535&& x>255)
     * y = 2;
     * if (x>65535 )
     * y = 3;
     */
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
