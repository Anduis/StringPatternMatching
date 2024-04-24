import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String P = sc.next().toLowerCase();
    String T = sc.next().toLowerCase();
    int m = P.length();
    int n = T.length();
    int B[] = new int[26];
    // preprocessing
    int mask = 1;
    for (int i = 0; i < m; i++) {
      int p = P.charAt(i) - 'a';
      B[p] = (B[p] | mask);
      mask = mask << 1;
    }
    // Searching
    int D = 0;
    int counter = 0;
    for (int i = 0; i < n; i++) {
      D = (D << 1 | 1) & B[T.charAt(i) - 'a'];
      if ((D & 1 << (m - 1)) != 0)
        counter++;
    }
    System.out.println(counter);
    sc.close();
  }
}
