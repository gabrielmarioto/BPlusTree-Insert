package bplustree;
/**
 *
 * @author Gabriel
 */
public class B {
    public static void main(String[] args) 
    {
        
        BPlus bplus = new BPlus(3);
        
        for(int i = 0; i < 93; i++)
            bplus.insert(i);
        
        bplus.exibirFolhas();
    }
    
}
