package bplustree;
/**
 *
 * @author Gabriel
 */
public class BPlus
{

    private No raiz;
    private int N;

    public BPlus(int N)
    {
        this.raiz = null;
        this.N = N;
    }

    public void insert(int info)
    {
        if (raiz == null)
        {
            raiz = new No(info, N);
        } else
        {
            No leaf = gotoLeaf(info);
            leaf.insert(info);
            if (leaf.getTL() == N)
            {
                No father = searchFather(info, leaf);
                split(father, leaf);
            }
        }
    }

    public void split(No father, No leaf)
    {
        if (leaf == father)
        {
            father = raiz = new No(N);
        }

        int div = leaf.getLig(0) == null ? N / 2 : (N - 1) / 2;
        int info = leaf.getInfo(div), posFather;
        No b1 = new No(N), b2 = new No(N);
        int gap = div;
        if (leaf.getLig(0) != null)
        {
            gap++;
        }

        for (int i = 0; i < div; i++)
        {
            b1.insert(leaf.getInfo(i));
            b1.setLig(b1.getTL() - 1, leaf.getLig(i));
        }
        b1.setLig(b1.getTL(), leaf.getLig(div));
        for (int i = gap; i < leaf.getTL(); i++)
        {
            b2.insert(leaf.getInfo(i));
            b2.setLig(b2.getTL() - 1, leaf.getLig(i));
        }
        b2.setLig(b2.getTL(), leaf.getLig(leaf.getTL()));

        father.insert(info);
        posFather = father.searchPos(info);
        father.setLig(posFather, b1);
        father.setLig(posFather + 1, b2);

        if (leaf.getLig(0) == null)
        {
            b1.setProx(b2);
            b2.setAnt(b1);
            b1.setAnt(leaf.getAnt());
            if (leaf.getAnt() != null)
            {
                leaf.getAnt().setProx(b1);
            }
            b2.setProx(leaf.getProx());
            if (leaf.getProx() != null)
            {
                leaf.getProx().setAnt(b2);
            }
        }

        if (father.getTL() == N)
        {
            leaf = father;
            father = searchFather(father.getInfo(0), father);
            split(father, leaf);
        }
    }

    public void exibirFolhas()
    {
        No aux = gotoLeaf(0);
        while (aux != null)
        {
            for (int i = 0; i < aux.getTL(); i++)
            {
                System.out.print(" " + aux.getInfo(i));
            }
            System.out.println("");
            aux = aux.getProx();
        }
    }

    public void inOrdem()
    {
        this.inOrdem(this.raiz, 1);
    }

    private void inOrdem(No no, int lvl)
    {
        if (no != null)
        {
            for (int i = 0; i < no.getTL(); i++)
            {
                this.inOrdem(no.getLig(i), lvl + 1);
                System.out.println(String.format("%" + lvl * 6 + "d", no.getInfo(i)));
            }
            this.inOrdem(no.getLig(no.getTL()), lvl + 1);
        }
    }

    public No gotoLeaf(int info)
    {
        No aux = raiz;
        while (aux.getLig(0) != null)
        {
            aux = aux.getLig(aux.searchPos(info));
        }
        return aux;
    }

    public No searchFather(int info, No leaf)
    {
        No father = raiz;
        No aux = raiz;
        while (aux != leaf)
        {
            father = aux;
            aux = aux.getLig(father.searchPos(info));
        }
        return father;
    }

    public No getRaiz()
    {
        return raiz;
    }

    public void setRaiz(No raiz)
    {
        this.raiz = raiz;
    }

    public int getN()
    {
        return N;
    }

    public void setN(int N)
    {
        this.N = N;
    }
}
