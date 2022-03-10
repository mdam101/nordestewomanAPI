package com.tienda.nordeste.models.pedido;

import com.tienda.nordeste.configurations.StringPrefixedSequenceIdGenerator;
import com.tienda.nordeste.models.lineaPedido.LineaPedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedido_seq")
    @GenericGenerator(
            name = "pedido_seq",
            strategy = "com.tienda.nordeste.configurations.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "PED"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d")
            })
    private String id;
    private Date fecha_pedido;
    private Double precio_pedido;
    private String metodo_pago;
    private Boolean entregado;

    //Relaciones
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<LineaPedido> lineaspedidos;
}
