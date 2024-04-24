public class Test {
  public static void main(String[] args) {
    String p = "anounnce";
    String c = "";
    for (int i = 0; i < p.length(); i++)
      if (!c.contains(p.subSequence(i, i + 1)))
        c += p.charAt(i);
    System.out.println(c);
    for (int i = 0; i < c.length(); i++)
      System.out.println("indice de " + c.charAt(i) + " .- " + c.indexOf(c.charAt(i)));
  }
}
