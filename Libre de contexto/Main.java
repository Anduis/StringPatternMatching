import java.util.Scanner;

public class Main {
  int i = 0;
  String s = "";
  int pos = 0;
  int len;

  Main(String x) {
    s = x;
    len = s.length();
    @SuppressWarnings("unused")
    int[] mar = E();
  }

  void plr(int x, char y, int z) {
    // System.out.println("("+x+","+y+","+z+")");
    System.out.println(x + " " + y + " " + z);
  }

  int[] E() {
    int[] temp = T();
    if (pos < len && s.charAt(pos) == '|') {
      pos++;
      int[] timp = E();
      int[] re = { i++, i++ };
      plr(re[0], 'e', temp[0]);
      plr(re[0], 'e', timp[0]);
      plr(temp[1], 'e', re[1]);
      plr(timp[1], 'e', re[1]);
      return re;
    } else
      return temp;
  }

  int[] T() {
    int[] temp = P();
    if (pos < len && s.charAt(pos) == '.') {
      pos++;
      int[] timp = T();
      int[] re = { temp[0], timp[1] };
      plr(temp[1], 'e', timp[0]);
      return re;
    } else
      return temp;
  }

  int[] P() {
    int[] temp = F();
    if (pos < len && s.charAt(pos) == '*') {
      int[] re = { i++, i++ };
      plr(re[0], 'e', re[1]);
      plr(re[0], 'e', temp[0]);
      plr(temp[1], 'e', re[1]);
      plr(temp[1], 'e', temp[0]);
      pos++;
      return re;
    } else
      return temp;
  }

  int[] F() {
    if (isIn(s.charAt(pos))) {
      int[] re = { i++, i++ };
      plr(re[0], s.charAt(pos), re[1]);
      pos++;
      return re;
    } else if (pos < len && s.charAt(pos) == '(') {
      pos++;// lee '('
      int[] temp = E();
      if (s.charAt(pos) == ')') {
        pos++;// lee ')'
        return temp;
      }
      System.exit(1);
    }
    return new int[2];
  }

  boolean isIn(char c) {
    if (c == 'A' | c == 'C' | c == 'G' | c == 'T')
      return true;
    else
      return false;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String text = sc.next();
    Main mia = new Main(text);
    System.out.println(mia.i);
    sc.close();
  }
}
