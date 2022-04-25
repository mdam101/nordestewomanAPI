package com.tienda.nordeste.models.ropa;

import com.tienda.nordeste.configurations.StringPrefixedSequenceIdGenerator;
import com.tienda.nordeste.models.categoria.Categoria;
import com.tienda.nordeste.models.lineaPedido.LineaPedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ropa")
public class Ropa {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ropa_seq")
    @GenericGenerator(
            name = "ropa_seq",
            strategy = "com.tienda.nordeste.configurations.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "ROP"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d")
            })
    private String id;
    private String nombre;
    private String descripcion;
    private Double precio;
    @Column(name = "imagen" , length = 4096000)
    @Lob()
    private byte[] imagen;

    //Relaciones
    @ManyToOne
    private Categoria categoria;

    @OneToMany(mappedBy = "ropa", cascade = CascadeType.ALL)
    private List<LineaPedido> lineaspedidos;
}
