package com.threedgarden.api.model;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;
    private Double total;
    private String estado;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario; // Asumiendo que ya tienes una entidad Usuario

    // Getters y setters
}
