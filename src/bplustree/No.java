package bplustree;
/**
 *
 * @author Gabriel
 */
public class No
{

    private int[] vInfo;
    private No[] vLig;
    private No ant, prox;
    private int TL;

    public No(int N)
    {
        vInfo = new int[N];
        vLig = new No[N + 1];
        ant = prox = null;
        TL = 0;
    }

    public No(int info, int N)
    {
        vInfo = new int[N];
        vLig = new No[N + 1];
        ant = prox = null;

        vInfo[0] = info;

        TL = 1;
    }

    public void insert(int info)
    {
        int pos = searchPos(info);
        rearrange(pos);
        vInfo[pos] = info;
        TL++;
    }

    public void realloc(int pos)
    {
        for (int i = pos; i < TL - 1; i++)
        {
            vInfo[i] = vInfo[i - 1];
            vLig[i] = vLig[i - 1];
        }
        vLig[TL - 1] = vLig[TL];
        TL--;
    }

    public void rearrange(int pos)
    {
        vLig[TL + 1] = vLig[TL];
        for (int i = TL; i > pos; i--)
        {
            vInfo[i] = vInfo[i - 1];
            vLig[i] = vLig[i - 1];
        }
    }

    public int searchPos(int info)
    {
        int i = 0;
        while (i < TL && vInfo[i] < info)
        {
            i++;
        }
        return i;
    }

    public int getInfo(int pos)
    {
        return vInfo[pos];
    }

    public void setInfo(int pos, int info)
    {
        vInfo[pos] = info;
    }

    public No getLig(int pos)
    {
        return vLig[pos];
    }

    public void setLig(int pos, No no)
    {
        vLig[pos] = no;
    }

    public No getAnt()
    {
        return ant;
    }

    public void setAnt(No ant)
    {
        this.ant = ant;
    }

    public No getProx()
    {
        return prox;
    }

    public void setProx(No prox)
    {
        this.prox = prox;
    }

    public int getTL()
    {
        return TL;
    }

    public void setTL(int TL)
    {
        this.TL = TL;
    }
}
