/**
 * Write a description of class Reliable here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public enum Reliable
{
    // instance variables - replace the example below with your own
    HIGH ("HIGH", 10),
    LOW ("LOW", 5);
    
    private final String nombre;
    private final double valor;

    /**
     * Constructor for objects of class Reliable
     */
    Reliable(String nombre, double valor)
    {
        this.nombre = nombre;
        this.valor = valor;
    }

    public String getNombre(){
        return nombre;
    }
    
    public double getValor(){
        return valor;
    }
    
    @Override
    public String toString(){
        return "<Reliable:" + getNombre() + "(valor + getValor() +)>";
    }
}
