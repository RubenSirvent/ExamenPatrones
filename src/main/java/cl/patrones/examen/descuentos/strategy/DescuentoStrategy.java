package cl.patrones.examen.descuentos.strategy;

import cl.patrones.examen.productos.domain.Producto;
import org.springframework.security.core.Authentication;

public interface DescuentoStrategy {

    Long aplicarDescuento(
            Producto producto,
            Authentication authentication
    );
}