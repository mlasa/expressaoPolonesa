package resolvecapeta;


import java.util.StringTokenizer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class Verifica 
{
    private char operador [] = {'(','^','*','/','+','-',')'};
    private int pos1=0;
    private int pos2=0;
    private boolean mat [][] = {{false,false,false,false,false,false,true},
    {false,true,true,true,true,true,true},
    {false,false,true,true,true,true,true},
    {false,false,true,true,true,true,true},
    {false,false,false,false,true,true,true},
    {false,false,false,false,true,true,true},
    {false,false,false,false,false,false,false}};
    
    //CONFERE SE É NUMERO OU NÃO
    public boolean ehNumero(String valor)
    {
        try
        {
            Double.parseDouble(valor);
            return true;
        } catch(Exception e)
        {
            return false;
        }
    }
    
    //VERIFICA SE É OPERADOR VÁLIDO
    public boolean ehOperador(char pedaco)
    {
        for(int i=0;i<7;i++)
            {
                if(pedaco == operador[i])
                {
                    return true;
                }
            }
        return false;
    }
    
    //VÊ SE O NUMERO DE PARENTESES É VÁLIDO
    public boolean parentesesConfere(String valor)
    {
        StringTokenizer token = new StringTokenizer(valor, "()", true);
        int parentesesAbertura = 0;
        int parentesesFechadura = 0;
        
        while(token.hasMoreTokens())
        {
            String atual = token.nextToken();
            if(atual.equals("("))
                parentesesAbertura++;
            else if(atual.equals(")"))
                parentesesFechadura++;
        }
        
        return (parentesesAbertura == parentesesFechadura);
    }
    
    //CONFERE SE TEM QUE TIRAR OU NÃO DA PILHA
    public boolean remove(char p, char seq)
    {
        for(int i=0;i<7;i++)
            {
                if(p == operador[i])
                    {
                        pos1 = i;
                    }
                if(seq == operador[i])// SEQ PEGA O OPERADOR DO TOKENIZER
                    {
                        pos2 = i;
                    }
            }
        if(mat[pos1][pos2] == false)
            {
                return false;
            }
        else
            {
                return true;
            }
    }
    
    //FAZ OPERAÇÃO
    public double calcula(char op, double n1, double n2)
    {
        switch (op) {
            case '+':
                return n1 + n2;
            case '-':
                return n1 - n2;
            case '*':
                return n1 * n2;
            case '/':
                return n1 / n2;
            case '^':
                return Math.pow(n1, n2);
            default:
                return 0;
        }
    }
    //MÉTODOS CANÔNICOS ---------------------------------------------------------------------------
        //TOSTRING
   public String toString()
   {
       //operadores,pAbre,pFecha
        String ret = "";
        ret = "pos1 :"+ pos1 +"pos2:"+ pos2 ;
        for (int i=0;i<7;i++)
        {
            ret = ret + operador[i];
        }
         for (int i=0;i<7;i++)//linha 
           for(int c=0;c<7;c++)//coluna
             {
                ret = ret + mat[i][c];
             }
        return ret; 
    }
   //HASHCODE
     public int hashcode()
       {
        int numero = 777;  
        numero = 7*numero+new Integer(this.pos1).hashCode();
        numero = 7*numero+new Integer(this.pos2).hashCode();
        for (int i=0;i<7;i++)
        {
           numero = 7*numero+new Integer(this.operador[i]).hashCode(); 
        }
        for (int i=0;i<7;i++)//linha 
           for(int c=0;c<7;c++)//coluna
             {
               numero = 7*numero +this.mat .hashCode();
             }
        return numero;
       }
      //EQUALS
    public boolean equals (Object obj)
        { //private int operadores = 0,pAbre = 0,pFecha = 0;
            if (obj==null)
                return true;
             for (int i=0;i<7;i++)
             {
                if (obj == (Object)operador[i] )
                return true;
             }

            if (obj == Integer.valueOf(pos1) )
                return true;
            if (obj == Integer.valueOf(pos2) )
                return true;
            
            
            for(int l = 0; l < mat.length-1;l++)
                {
                    for(int c = 0;c< mat.length-1;c++)
                        {
                            if(mat[l][c]==mat[l][c])
                                {
                                    return true; 
                                } 
                            else 
                                {
                                    return false;
                                }
                        }
                }
            return false;
       
    }
}
