import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int r = sc.nextInt();// cantidad de patrones
    String T = sc.next();// texto
    String[] P = new String[r];// patrones
    for (int i = 0; i < r; i++)
      P[i] = sc.next();
    // preprocessing
    int[] B = new int[26];
    int l = 0;
    for (int k = 0; k < r; k++) {
      for (int j = 1; j <= P[k].length(); j++)
        B[P[k].charAt(j - 1) - 'A'] = (B[P[k].charAt(j - 1) - 'A'] | 1 << (l + j - 1));
      l = l + P[k].length();
    }
    int Di = 1;
    for (int i = 1; i < r; i++) {
      Di = Di << P[i].length();
      Di = (Di | 1);
    }
    int Df = 0;
    for (int i = 0; i < r; i++) {
      Df = Df << 1;
      Df = (Df | 1) << (P[i].length() - 1);
    }
    int[] finales = new int[r];
    for (int i = 0; i < r; i++)// estado final de cada patron
    {
      finales[i] = 1;
      int j = i;
      while (j < r) {
        finales[i] = finales[i] << (P[j].length());
        j++;
      }
      finales[i] = finales[i] >> 1;
    }
    // searching
    int D = 0;
    int[] counter = new int[r];
    for (int pos = 0; pos < T.length(); pos++) {
      D = ((D << 1) | Di) & (B[T.charAt(pos) - 'A']);
      if ((D & Df) != 0)
        for (int i = 0; i < r; i++)
          if ((finales[i] & (D & Df)) != 0)
            counter[i]++;
    }
    for (int i = 1; i <= r; i++)// salida
      System.out.println(i + " " + counter[r - i]);
    sc.close();
  }
}
