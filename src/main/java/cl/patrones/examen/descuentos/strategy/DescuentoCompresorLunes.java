package cl.patrones.examen.descuentos.strategy;

import cl.patrones.examen.productos.domain.Producto;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Component
public class DescuentoCompresorLunes
        implements DescuentoStrategy {

    @Override
    public Long aplicarDescuento(
            Producto producto,
            Authentication authentication
    ) {

        boolean esLunes =

                LocalDate.now()
                        .getDayOfWeek()

                        == DayOfWeek.MONDAY;

        boolean esCompresor =

                producto.getCategoria()
                        .getNombre()
                        .equalsIgnoreCase(
                                "Compresores de Aire"
                        );

        return (esLunes && esCompresor)
                ? 6L
                : 0L;
    }
}