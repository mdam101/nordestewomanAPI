package com.tienda.nordeste.models.talla;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tienda.nordeste.configurations.StringPrefixedSequenceIdGenerator;
import com.tienda.nordeste.models.ropa.Ropa;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tallaropa")
public class TallaRopa {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tallaropa_seq")
    @GenericGenerator(
            name = "tallaropa_seq",
            strategy = "com.tienda.nordeste.configurations.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "TAR"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d")
            })
    private String idTallaRopa;

    private Integer stock;

    @Enumerated(EnumType.STRING)
    private Talla talla;

    @ManyToOne
    @JsonBackReference
    private Ropa ropa;
}
