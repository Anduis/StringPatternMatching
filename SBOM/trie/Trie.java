import java.util.Scanner;

public class Trie {
  String[] patrones;
  String C = "";// letras diferentes
  State raiz;

  Trie(String[] x) {
    patrones = x;
    int r = x.length;
    for (String s : x)
      for (int i = 0; i < s.length(); i++)
        if (!C.contains(s.subSequence(i, i + 1)))
          C += s.charAt(i);
    raiz = new State(C.length(), r);
    for (int i = 0; i < r; i++) {
      State current = raiz;
      int j = 0;
      int m = x[i].length();
      while ((j < m) && (current.hijos[C.indexOf(x[i].charAt(j))] != null)) {
        current = current.hijos[C.indexOf(x[i].charAt(j))];
        j++;
      }
      while (j < m) {
        current = current.addhijo(C.indexOf(x[i].charAt(j)));
        j++;
      }
      if (current.fin)
        current.id[i] = true;
      else {
        current.fin = true;
        current.id[i] = true;
      }
    }
  }

  static void printTree(State raiz, String C, String indent) {
    State temp = raiz;
    if (temp.numhijos != 0)
      ;
    for (int x = 0; x < temp.hijos.length; x++)
      if (temp.hijos[x] != null) {
        System.out.print(indent + C.charAt(x) + temp.hijos[x].numhijos);
        if (temp.hijos[x].fin)
          for (int i = 0; i < temp.id.length; i++)
            if (temp.hijos[x].id[i])
              System.out.print(" " + (i + 1));
        System.out.println();
        printTree(temp.hijos[x], C, indent + "|");
      }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int np = sc.nextInt();
    String[] patrones = new String[np];
    for (int i = 0; i < np; i++)
      patrones[i] = sc.next();
    Trie t = new Trie(patrones);
    printTree(t.raiz, t.C, "");
    sc.close();
  }
}

class State {
  int numhijos = 0;
  State padre;
  boolean fin = false;
  boolean[] id;
  State[] hijos;// el indice indica por cual letra es hijo

  State(int s, int i)// para la raiz
  {
    hijos = new State[s];
    id = new boolean[i];
  }

  State addhijo(int h)// para hacer un hijo por la rama h
  {
    numhijos++;
    hijos[h] = new State(hijos.length, id.length);
    hijos[h].padre = this;
    return hijos[h];
  }
}
