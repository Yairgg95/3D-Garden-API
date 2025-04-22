package com.threedgarden.api.controller;

import com.threedgarden.api.model.Pedido;
import com.threedgarden.api.service.PedidoService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public Pedido crearPedido(@RequestBody Pedido pedido) {
        return pedidoService.crearPedido(pedido);
    }

    @GetMapping
    public List<Pedido> obtenerPedidos() {
        return pedidoService.listarPedidos();
    }
}
