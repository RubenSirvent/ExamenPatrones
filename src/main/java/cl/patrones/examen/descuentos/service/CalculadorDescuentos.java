package cl.patrones.examen.descuentos.service;

import cl.patrones.examen.descuentos.strategy.DescuentoStrategy;
import cl.patrones.examen.productos.domain.Producto;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculadorDescuentos {

    private final List<DescuentoStrategy>
            descuentos;

    public CalculadorDescuentos(
            List<DescuentoStrategy>
                    descuentos
    ) {
        this.descuentos = descuentos;
    }

    public Long obtenerMayorDescuento(
            Producto producto,
            Authentication authentication
    ) {

        return descuentos.stream()

                .map(descuento ->
                        descuento
                                .aplicarDescuento(
                                        producto,
                                        authentication
                                )
                )

                .max(Long::compareTo)

                .orElse(0L);
    }
}