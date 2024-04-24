import java.util.Scanner;
import java.util.Arrays;

public class Oracle {
  int[][] delta; // tabla de transiciones
  int[] S; // suply state
  String C = "";// letras diferentes
  int n;// tamaño del patron

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

  void fillDelta() {
    for (int i = 0; i < delta.length; i++)
      for (int j = 0; j < delta[i].length; j++)
        delta[i][j] = -1;
  }

  void printSuply() {
    System.out.println("i | S(i)");
    for (int i = 0; i < S.length; i++) {
      System.out.println(i + " | " + S[i]);
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Oracle test = new Oracle("ecnuonna");
    test.printDelta();
    test.printSuply();
    sc.close();
  }
}
