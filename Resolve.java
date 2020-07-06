/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resolve;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.System.in;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Resolve
{
    private static final String FIXO_VALOR = "+";
    
    public static void main (String[] args)
    {
        try {
            boolean lookparenteses = false;
            BufferedReader teclado = new BufferedReader(
                    new InputStreamReader(in)
            );
            
            Verifica ver = new Verifica();
            
            System.out.println("Digite a expressão a ser resolvida:");
            String exp = teclado.readLine();                                                                                                                                                                                                                                                             exp += FIXO_VALOR;
            int tam = exp.length();
            Pilha<String> pilha = new Pilha<String>(tam);
            Fila<String> fila = new Fila<String>(tam);
            
            StringTokenizer quebrador = new StringTokenizer(exp, "+-*/^()", true);
            
            // Verifica a quantidade de parenteses
            if(!ver.parentesesConfere(exp))
                throw new Exception("Numero de parenteses invalidos");
            
            while(quebrador.hasMoreTokens())
            {
                String pedaco = quebrador.nextToken().trim();
                boolean parenteses = false;
                
                if(!ver.ehNumero(pedaco) && !ver.ehOperador(pedaco.charAt(0)))
                    throw new Exception("Operador invalido encontrado.");
                
                
                if(ver.ehNumero(pedaco))
                {
                    fila.guardar(pedaco);
                } 
                else //OPERADOR
                { 
                    if(pilha.tamanho() > 0)
                    {
                        if(!ver.remove(pilha.recupere().charAt(0),pedaco.charAt(0)))
                        {
                            pilha.guardar(pedaco);
                        } 
                        else 
                        {
                            while(ver.remove(pilha.recupere().charAt(0),pedaco.charAt(0)))
                            {
                                if(pedaco.equals(")")) 
                                {
                                    while(pilha.tamanho() > 0)
                                    {
                                        if(!pilha.recupere().equals("("))
                                        {
                                            fila.guardar(pilha.recupere());
                                            pilha.jogueFora();
                                        } else {
                                            pilha.jogueFora();
                                            parenteses = true; //SE ACHOU PARENTESES
                                            break; 
                                        }
                                    }
                                    
                                    if(parenteses == false)
                                        throw new Exception("Quantidade de parenteses invalida");
                                    
                                    break;
                                } 
                                else {
                                    fila.guardar(pilha.recupere());
                                    pilha.jogueFora();
                                    
                                    if(pilha.tamanho() == 0 || (ver.remove(pilha.recupere().charAt(0),pedaco.charAt(0)) == false))
                                    {
                                        pilha.guardar(pedaco);
					break;
                                    }
                                }
                            }
                        }
                    } 
                    else 
                    { 
                        if(pedaco.equals(")"))
                            throw new Exception("Má formação dos parenteses.");
                        
                        pilha.guardar(pedaco);
                    } 
                } 
            } 
            
            double n1 = 0, n2 = 0;
            char op;
            
            while(fila.tamanho() > 0)
            {
                if(ver.ehNumero(fila.recupere())) //NUMERO
                { 
                    pilha.guardar(fila.recupere());
                    fila.jogueFora();
                } 
                else //OPERADOR
                { 
                    op = fila.recupere().charAt(0);
                    fila.jogueFora();
                    
                    n2 = Double.parseDouble(pilha.recupere());
                    pilha.jogueFora();
                    
                    n1 = Double.parseDouble(pilha.recupere());
                    pilha.jogueFora();
                    
                    Double operacao = ver.calcula(op, n1, n2);
                    pilha.guardar(operacao + "");
                }
            }
            
            System.out.println("Resultado: " + pilha.recupere());
            
        } 
        catch(Exception er) 
        {
            System.out.println(er.getMessage());
        }
  }
}
