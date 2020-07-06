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

public class Fila<Y> implements Cloneable 
{
    private Object[] fila;
    private int tamanho = 0;
    private int total;

    public Fila(Integer total) throws Exception
    {
        if(total <= 0)
            throw new Exception("Tamanho inválido");

        this.total = total;
        this.fila = new Object[this.total];
    }

    public void guardar(Y valor) throws Exception
    {
        if(this.tamanho == this.total - 1)
            throw new Exception("Fila cheia");

        
        if (valor instanceof Cloneable)
        {
            Class classe            = valor.getClass();
            Class<?>[] parmsFormais = null;
            Method metodo           = classe.getMethod ("clone", parmsFormais);
            Object[] parmsReais     = null;
            this.fila[this.tamanho]   = (Y)metodo.invoke(valor, parmsReais);
        }
        else
            this.fila[this.tamanho] = valor;
        
        this.tamanho++;
    }
	
    public Y recupere() throws Exception
    {
        if(this.tamanho == 0)
            throw new Exception("Fila vazia");

        if (this.fila[0] instanceof Cloneable)
        {
            Class classe            = this.fila[0].getClass();
            Class<?>[] parmsFormais = null;
            Method metodo           = classe.getMethod ("clone", parmsFormais);
            Object[] parmsReais     = null;
            return (Y)metodo.invoke(this.fila[0],parmsReais);
        }
        else
            return (Y)this.fila[0];
    }
	
    public void jogueFora() throws Exception
    {
        if(this.tamanho == 0)
            throw new Exception("2");

        for(int i = 0; i < this.tamanho; i++)
        {
            if(i == 0) continue;
                this.fila[i - 1] = this.fila[i];
        }

        this.tamanho--;
    }
    
    public String obterFila() throws Exception
    {
        String fila = "";
        Fila<String> f = new Fila<String>((Fila<String>) this);
        for(int i = 0; i < this.tamanho; i++)
        {
            fila = fila + f.recupere() + "\n";
            f.jogueFora();
        }
        return fila;
    }
    
    public String toString()
    {
        String ret = "Fila: ";
        
        if(this.tamanho == 0) {
            ret = "0 elementos";
        } else {
            String conteudo = "";
            for(int i = 0; i < this.tamanho; i++)
                conteudo = conteudo + this.fila[i] + " ";
            ret = ret + conteudo + "\n" + this.tamanho+" elementos";
        }
        
        return ret;
    }
    
    public Fila(Fila<Y> modelo) throws Exception
    {
        if (modelo == null)
            throw new Exception ("Valor nulo");

        this.fila = new Object [modelo.fila.length];

        for (int i = 0; i <= modelo.tamanho; i++)
            if (modelo.fila[i] instanceof Cloneable)
            {
                Class classe            = modelo.fila[i].getClass();
                Class<?>[] parmsFormais = null;
                Method metodo           = classe.getMethod ("clone", parmsFormais);
                Object[] parmsReais     = null;
                this.fila[i]           = (Y)metodo.invoke(modelo.fila[i], parmsReais);
            }
            else
                this.fila[i] = modelo.fila[i];

        this.tamanho = modelo.tamanho;
    }
    
    public Object clone ()
    {
        Fila<Y> ret=null;

        try
        {
            ret = new Fila <Y> (this);
        }
        catch (Exception erro)
        {}

        return ret;
    }
    
    public int tamanho()
    {
        return this.tamanho;
    }
}
