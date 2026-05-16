package cl.patrones.examen.descuentos.strategy;

import cl.patrones.examen.productos.domain.Producto;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Component
public class DescuentoTaladroMiercoles
        implements DescuentoStrategy {

    @Override
    public Long aplicarDescuento(
            Producto producto,
            Authentication authentication
    ) {

        boolean esMiercoles =

                LocalDate.now()
                        .getDayOfWeek()

                        == DayOfWeek.WEDNESDAY;

        boolean esTaladro =

                producto.getCategoria()
                        .getNombre()
                        .equalsIgnoreCase(
                                "Taladros Percutores"
                        );

        return (esMiercoles && esTaladro)
                ? 10L
                : 0L;
    }
}