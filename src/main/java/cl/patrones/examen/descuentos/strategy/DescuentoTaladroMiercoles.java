package cl.patrones.examen.descuentos.strategy;

import cl.patrones.examen.productos.domain.Producto;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDate;

@Component
public class DescuentoTaladroMiercoles
        implements DescuentoStrategy {

    private final Clock clock;

    public DescuentoTaladroMiercoles() {
        this.clock =
                Clock.systemDefaultZone();
    }

    public DescuentoTaladroMiercoles(
            Clock clock
    ) {
        this.clock = clock;
    }

    @Override
    public Long aplicarDescuento(
            Producto producto,
            Authentication authentication
    ) {

        boolean esMiercoles =

                LocalDate.now(clock)
                        .getDayOfWeek()
                        == DayOfWeek.WEDNESDAY;

        boolean esTaladro =

                producto
                        .getCategoria()
                        .getNombre()
                        .equalsIgnoreCase(
                                "Taladros Percutores"
                        );

        return esMiercoles && esTaladro
                ? 10L
                : 0L;
    }
}