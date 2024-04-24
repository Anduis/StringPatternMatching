import java.util.Scanner;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) {
    // Preprocessing
    Scanner sc = new Scanner(System.in);
    String temp = sc.next().toLowerCase(); // al derecho
    String P = ""; // al revez
    String T = sc.next().toLowerCase();
    for (int i = temp.length() - 1; i >= 0; i--)
      P += temp.charAt(i);
    Oracle Oraculo = new Oracle(P);
    int m = P.length();
    int n = T.length();
    // Searching
    int pos = 0;
    int counter = 0;
    while (pos <= (n - m)) {
      int Current = 0;
      int j = m;
      while (j >= 1 && Current != -1) {
        if (Oraculo.C.indexOf(T.charAt(pos + j - 1)) != -1)
          Current = Oraculo.delta[Current][Oraculo.C.indexOf(T.charAt(pos + j - 1))];
        else // para evitar los caracteres que no existen en P
          Current = -1;
        j--;
      }
      if (Current != -1)
        counter++;
      pos = pos + j + 1;
    }
    System.out.println(counter);
    Oraculo.printDelta();
    System.out.println();
    Oraculo.printSuply();
    sc.close();
  }
}

class Oracle {
  int[][] delta; // tabla de transiciones
  int[] S; // suply state
  String C = "";// letras diferentes
  int n;// len del patron

  Oracle(String p) {
    for (int i = 0; i < p.length(); i++)
      if (!C.contains(p.subSequence(i, i + 1)))
        C += p.charAt(i);

    n = p.length();
    S = new int[n + 1];
    delta = new int[n + 1][C.length()];
    Arrays.fill(S, -1);
    fillDelta();

    for (int i = 0; i < n; i++)
      OracleAddLetter(i, p.charAt(i));
  }

  void fillDelta() {
    for (int i = 0; i < delta.length; i++)
      Arrays.fill(delta[i], -1);
  }

  void OracleAddLetter(int m, char c) {
    int s;
    int sigma = C.indexOf(c);
    delta[m][sigma] = m + 1;
    int k = S[m];
    while (k != -1 && delta[k][sigma] == -1) {
      delta[k][sigma] = m + 1;
      k = S[k];
    }
    if (k == -1)
      s = 0;
    else
      s = delta[k][sigma];
    S[m + 1] = s;
  }

  void printSuply() {
    System.out.println("i | S(i)");
    for (int i = 0; i < S.length; i++) {
      System.out.println(i + " | " + S[i]);
    }
  }

  void printDelta() {
    System.out.println("    " + C);
    for (int i = 0; i < delta.length; i++) {
      System.out.print("[" + i + "] ");
      for (int j = 0; j < delta[i].length; j++)
        if (delta[i][j] == -1)
          System.out.print("'");
        else
          System.out.print(delta[i][j]);
      System.out.println();
    }
  }
}
