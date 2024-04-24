public class test
{
  static void imprimeArbol(State raiz, String C, String indent)
  {
    State temp = raiz;
    if (temp.numhijos != 0);
      for (int x=0; x<temp.hijos.length; x++)
        if(temp.hijos[x]!=null)
        {
          System.out.println(indent+ C.charAt(x));
          imprimeArbol(temp.hijos[x],C, indent+" ");
        }
  }
  public static void main(String[] args)
  {
    String C = "abcd";
    State raiz = new State(4);
    State temp = raiz.addhijo(0);
    raiz.addhijo(2);
    temp=temp.addhijo(0);
    temp=temp.addhijo(1);
    imprimeArbol(raiz,C,"");
  }
}
class State
{
  int numhijos=0;
  State padre;
  boolean fin = false;
  State[] hijos;//el indice indica por cual letra es hijo
  State(int s)//para la raiz
  {
    hijos = new State[s];
  }
  State addhijo(int h)//para hacer un hijo por la rama h
  {
    numhijos++;
    hijos[h] = new State(hijos.length);
    hijos[h].padre = this;
    return hijos[h];
  }
}
