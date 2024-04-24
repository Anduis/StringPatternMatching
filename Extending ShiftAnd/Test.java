public class Test {
  public static void main(String[] args) {
    int L = 8;
    int a = 1;
    int b = 3;
    int i = 3;
    System.out.println(potencia((L - i), 1, (i - 1)));
    // System.out.println(Integer.parseInt(potencia((L-i), 1 ,(i-1)),2));
    System.out.println(potencia((L - (i + b - a) - 1), 1, (i + b - a)));
    System.out.println(Integer.toBinaryString(~32));
    // System.out.println(Integer.parseInt(potencia((L-(i+b-a)-1), 1 ,(i+b-a)),2));
  }

  static int potencia(int a, int b, int c) // pt 1, pt c
  {
    String temp = "";
    for (int i = a; i > 0; i--)
      temp += "0";
    for (int i = b; i > 0; i--)
      temp += "1";
    for (int i = c; i > 0; i--)
      temp += "0";
    return Integer.parseInt(temp, 2);
  }
}
/*
 * 00100000=I
 * 00000100=F
 * 00011100
 * abcxxxde
 * abcabcffdeee
 * 128 64 32 16 8 4 2 1
 * 0 0 1 0 0 0 0 0
 */
