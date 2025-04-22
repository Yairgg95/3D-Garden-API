package com.threedgarden.api.service;

import com.threedgarden.api.model.Pedido;
import java.util.List;

public interface PedidoService {
    Pedido crearPedido(Pedido pedido);
    List<Pedido> listarPedidos();
}
