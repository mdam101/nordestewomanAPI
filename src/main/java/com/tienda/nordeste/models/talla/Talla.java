package com.tienda.nordeste.models.talla;

import com.tienda.nordeste.configurations.StringPrefixedSequenceIdGenerator;
import com.tienda.nordeste.models.ropa.Ropa;
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
@Table(name = "talla")
public class Talla {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "talla_seq")
    @GenericGenerator(
            name = "talla_seq",
            strategy = "com.tienda.nordeste.configurations.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "TAL"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d")
            })
    private String id;
    private String talla;

    //Relaciones
    @ManyToMany(mappedBy = "talla")
    private List<Ropa> ropas;
}
