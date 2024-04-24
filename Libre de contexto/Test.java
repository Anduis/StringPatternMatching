import java.util.Scanner;

public class Test {
  int i = 0;
  String s = "";
  int[] consta = new int[3];

  Test(String x) {
    s = x;
  }

  void corre() {
    E(0);
  }

  int[] E(int pos) {
    if (pos + 1 <= s.length()) {
      int[] temp1 = T(pos);
      if (s.charAt(pos) == '|') {
        int[] temp2 = E(pos + 1);
        System.out.println("(" + (temp2[1] + 1) + "," + 'e' + "," + temp1[0] + ")");
        System.out.println("(" + (temp2[1] + 1) + "," + 'e' + "," + temp2[0] + ")");
        System.out.println("(" + temp1[1] + "," + 'e' + "," + (temp2[1] + 2) + ")");
        System.out.println("(" + temp2[1] + "," + 'e' + "," + (temp2[1] + 2) + ")");
        temp1[0] = i++;
        temp1[1] = i++;
        return temp1;
      } else
        return temp1;
    } else {
      return consta;
    }
  }

  int[] T(int pos) {
    if (pos + 1 <= s.length()) {
      int[] temp1 = P(pos);
      if (s.charAt(pos) == '.') {
        int[] temp2 = T(pos + 1);
        System.out.println("(" + (temp1[1]) + "," + 'e' + "," + temp2[0] + ")");
        temp1[1] = temp2[1];
        return temp1;
      } else
        return temp1;
    } else
      return consta;
  }

  int[] P(int pos) {
    int[] temp1 = F(pos);
    if (s.charAt(pos) == '*') {
      int[] temp2 = { i++, i++ };
      System.out.println("(" + (temp2[0]) + "," + 'e' + "," + temp1[0] + ")");
      System.out.println("(" + (temp2[0]) + "," + 'e' + "," + temp2[1] + ")");
      System.out.println("(" + (temp1[1]) + "," + 'e' + "," + temp2[1] + ")");
      System.out.println("(" + (temp1[1]) + "," + 'e' + "," + temp1[0] + ")");
      pos++;
      return temp2;
    } else
      return temp1;
  }

  int[] F(int pos) {
    int[] temp = new int[2];
    if (isIn(s.charAt(pos))) {
      temp[0] = i++;
      temp[1] = i++;
      System.out.println("(" + temp[0] + "," + s.charAt(pos) + "," + temp[1] + ")");
      pos++;
      return temp;
    } else if (s.charAt(pos) == '(') {
      pos++;
      temp = E(pos);
      if (s.charAt(pos) == ')')
        pos++;
      return temp;
    }
    return consta;
  }

  boolean isIn(char c) {
    if (c == 'a' | c == 'b' | c == 'G' | c == 'T')
      return true;
    else
      return false;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String x = sc.next();
    Test ll = new Test(x);
    ll.corre();
    sc.close();
  }
}
