/* package co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities;

import javax.persistence.*;

@Entity
@Table(name = "Factura")
public class Factura {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente objCliente;

    public Factura() {
    }
    
    public Cliente getObjCliente() {
        return objCliente;
    }
    public void setObjCliente(Cliente objCliente) {
        this.objCliente = objCliente;
    } 
    
}
 */