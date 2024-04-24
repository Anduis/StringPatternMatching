import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String P = sc.next().toLowerCase();
    String T = sc.next().toLowerCase();
    int m = P.length();
    int n = T.length();
    int d[] = new int[26];
    // Preprocessing
    for (int i = 0; i < 26; i++)
      d[i] = m;
    for (int i = 1; i < m; i++)
      d[P.charAt(i - 1) - 'a'] = (m - i);
    // Searching
    int pos = 0;
    int count = 0;
    while (pos <= n - m) {
      int j = m;
      while (j > 0 && T.charAt(pos + j - 1) == P.charAt(j - 1))
        j--;
      if (j == 0)
        count++;
      pos = pos + d[T.charAt(pos + m - 1) - 'a'];
    }
    System.out.println(count);
    sc.close();
  }
}
