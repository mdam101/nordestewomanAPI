package com.tienda.nordeste.models.lineaPedido;

import com.tienda.nordeste.configurations.StringPrefixedSequenceIdGenerator;
import com.tienda.nordeste.models.pedido.Pedido;
import com.tienda.nordeste.models.ropa.Ropa;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lineapedido")
public class LineaPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lineapedido_seq")
    @GenericGenerator(
            name = "lineapedido_seq",
            strategy = "com.tienda.nordeste.configurations.StringPrefixedSequenceIdGenerator",
            parameters = {
                   @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                   @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "LIN"),
                   @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d")
            })
    private String id;
    private Integer numero_linea;
    private Integer cantidad;
    private Double precio_unidad;

    //Relaciones
    @ManyToOne
    private Pedido pedido;

    @ManyToOne
    private Ropa ropa;
}
