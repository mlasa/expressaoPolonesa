/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resolvecapeta;

/**
 *
 * @author aline
 */
import java.lang.reflect.Method;
import java.util.Arrays;

public class Pilha<X> implements Cloneable 
{   
    private Object[] vetor;
    private int topo = -1;
    
    //CONSTRUTOR
    public Pilha(int tamanho) throws Exception
    {
        if(tamanho <= 1)
            throw new Exception("Tamanho inválido");
        
        this.vetor = new Object[tamanho];
    }
    
    //GUARDA NA PILHA
    public void guardar(X valor) throws Exception
    {
        if(valor == null)
            throw new Exception("Valor nulo");
        
        if(this.topo == this.vetor.length - 1)
            throw new Exception("Pilha cheia" + (this.vetor.length - 1) + " " + this.topo);
        
        this.topo++;
        
        if(valor instanceof Cloneable)
        {
            Class classe = valor.getClass();
            Class<?>[] paramsFormais = null;
            Method metodo = classe.getMethod("clone", paramsFormais);
            Object[] paramsReais = null;
            this.vetor[this.topo] = (X)metodo.invoke(valor, paramsReais);
        } else
            this.vetor[this.topo] = valor;
    }
   
    //RECUPERA TOPO DA PILHA
    public X recupere() throws Exception
    {
        if(this.topo == -1)
            throw new Exception("Pilha vazia");
        
        if(this.vetor[this.topo] instanceof Cloneable)
        {
            Class classe = this.vetor[this.topo].getClass();
            Class<?>[] paramsFormais = null;
            Method metodo = classe.getMethod("clone", paramsFormais);
            Object[] paramsReais = null;
            return (X)metodo.invoke(this.vetor[this.topo], paramsReais);
        }
        
        return (X)this.vetor[this.topo];
    }
    
    //JOGA FORA TOPO DA PILHA
    public void jogueFora() throws Exception
    {
        if(this.topo == -1)
            throw new Exception("Pilha vazia");
        
        this.vetor[this.topo] = null;
        this.topo--;
    }
    
    //MÉTODOS CANÔNICOS ------------------------------------------------------------------------------------
    public String toString() 
    {
        String ret = "Pilha: ";
        
        if(this.topo == -1) {
            ret = "0 elementos";
        } else {
            String conteudo = "";
            for(int i = 0; i < this.topo + 1; i++)
                conteudo = conteudo + this.vetor[i] + " ";
            ret = ret + conteudo + "\n" + (this.topo+1)+" elementos";
        }
        
        return ret;
    }
    
    public boolean equals (Object obj)
    {
	if (obj == null)
            return false;

	if (this == obj)
            return true;

	if (this.getClass() != obj.getClass())
            return false;
	
        Pilha<X> p = (Pilha<X>)obj;
        if (this.topo != p.topo)
            return false;
		
	for (int i=0; i <= this.topo; i++)
            if (!this.vetor[i].equals(p.vetor[i]))
		return false;
			
	return true;
    }

    public int hashCode() {
        int ret = 157;
        ret = 7 * ret + new Integer(this.topo).hashCode();
        for (int i=0; i <= this.topo; i++)
            ret = 7  *ret + this.vetor[i].hashCode();
        return ret;
    }
    
    //CONSTRUTOR DE CÓPIA
    public Pilha(Pilha<X> modelo) throws Exception
    {
        if (modelo==null)
            throw new Exception ("Valor nulo");

        this.vetor = new Object [modelo.vetor.length];

        for (int i=0; i<=modelo.topo; i++)
            if (modelo.vetor[i] instanceof Cloneable)
            {
                Class classe            = modelo.vetor[i].getClass();
                Class<?>[] parmsFormais = null;
                Method metodo           = classe.getMethod ("clone", parmsFormais);
                Object[] parmsReais     = null;
                this.vetor[i]           = (X)metodo.invoke(modelo.vetor[i],parmsReais);
            }
            else
                this.vetor[i] = modelo.vetor[i];

        this.topo = modelo.topo;
    }
    
    //CLONE
    public Object clone ()
    {
        Pilha<X> ret=null;

        try
        {
            ret = new Pilha <X> (this);
        }
        catch (Exception erro)
        {}

        return ret;
    }
    
    //PEGA TAMANHO DA PILHA
    public int tamanho()
    {
        return topo + 1;
    }
}
