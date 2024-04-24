import java.util.Scanner;

public class Trie {
  String[] patrones;
  int[] F;// indice indica el state, elemento indica patron
  int[][] delta; // tabla de transiciones
  String C = "";// letras diferentes

  Trie(String[] x) {
    patrones = x;
    int r = x.length;
    for (String s : x)
      for (int i = 0; i < s.length(); i++)
        if (!C.contains(s.subSequence(i, i + 1)))
          C += s.charAt(i);
    int sum = 1;// maxima cantidad de estados posibles
    for (String d : x)
      sum += d.length();
    delta = new int[sum][C.length()];
    F = new int[sum];
    fillDelta();
    int state = 0;
    for (int i = 0; i < r; i++) {
      int current = 0;
      int j = 0;
      int m = x[i].length();
      while ((j < m) && (delta[current][C.indexOf(x[i].charAt(j))] != -1)) {
        current = delta[current][C.indexOf(x[i].charAt(j))];
        j++;
      }
      while (j < m) {
        state++;
        delta[current][C.indexOf(x[i].charAt(j))] = state;
        current = state;
        j++;
      }
      F[current] = i + 1;
    }
  }

  void fillDelta() {
    for (int i = 0; i < delta.length; i++)
      for (int j = 0; j < delta[i].length; j++)
        delta[i][j] = -1;
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

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int np = sc.nextInt();
    String[] patrones = new String[np];
    for (int i = 0; i < np; i++)
      patrones[i] = sc.next();
    for (String x : patrones)
      System.out.print(x + " ");
    Trie t = new Trie(patrones);
    System.out.println(t.C);
    t.printDelta();
    for (int w : t.F) {
      System.out.print(w + " ");
    }
    sc.close();
  }
}
