package com.rafaelalvim.lanchonete.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity(name="pedido")
@Table(name="pedido")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy =  "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens;

    @Column(name="valor_total")
    private BigDecimal valorTotal;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;
}
