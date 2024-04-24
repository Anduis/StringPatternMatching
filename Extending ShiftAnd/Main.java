
/*
//Gaps-Shift-And
x(a,b)-> gap; pi->pattern char
input: p1-p2-p3-x(a,b)-p4 text
output: #occurrences
*/
import java.util.Scanner;

public class Main {
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
          B[x[j - 1].charAt(k) - 'A'] = B[x[j - 1].charAt(k) - 'A'] | potencia(1, i);
        i++;
      }
    }
    // Searching
    int count = 0;
    int D = 0;
    for (int pos = 1; pos <= n; pos++) {
      D = (D << 1 | 1) & B[T.charAt(pos - 1) - 'A'];
      D = D | (((F - (D & I)) & ~F));
      if ((D & (1 << (L - 1))) != 0)
        count++;
    }
    System.out.println(count);
    sc.close();
  }

  static int potencia(int b, int c) // b-> #1 c-> #0
  {
    String temp = "";
    for (int i = b; i > 0; i--)
      temp += "1";
    for (int i = c; i > 0; i--)
      temp += "0";
    return Integer.parseInt(temp, 2);
  }
}
