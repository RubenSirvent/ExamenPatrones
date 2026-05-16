package cl.patrones.examen.descuentos.strategy;

import cl.patrones.examen.productos.domain.Producto;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Component
public class DescuentoEsmerilMartes
        implements DescuentoStrategy {

    @Override
    public Long aplicarDescuento(
            Producto producto,
            Authentication authentication
    ) {

        boolean esMartes =

                LocalDate.now()
                        .getDayOfWeek()

                        == DayOfWeek.TUESDAY;

        boolean esEsmeril =

                producto.getCategoria()
                        .getNombre()
                        .equalsIgnoreCase(
                                "Esmeriles Angulares"
                        );

        return (esMartes && esEsmeril)
                ? 8L
                : 0L;
    }
}