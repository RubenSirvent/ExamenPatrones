package cl.patrones.examen.descuentos.strategy;

import cl.patrones.examen.productos.domain.Categoria;
import cl.patrones.examen.productos.domain.Producto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DescuentoTaladroMiercolesTest {

    @Mock
    private Producto producto;

    @Mock
    private Categoria categoria;

    @Mock
    private Authentication authentication;

    @Test
    void debeAplicarDescuentoTaladroMiercoles() {

        Clock clock =
                Clock.fixed(
                        Instant.parse(
                                "2026-05-20T12:00:00Z"
                        ),
                        ZoneId.systemDefault()
                );

        DescuentoTaladroMiercoles descuento =
                new DescuentoTaladroMiercoles(
                        clock
                );

        when(producto.getCategoria())
                .thenReturn(categoria);

        when(categoria.getNombre())
                .thenReturn(
                        "Taladros Percutores"
                );

        Long resultado =
                descuento.aplicarDescuento(
                        producto,
                        authentication
                );

        assertEquals(
                10L,
                resultado
        );
    }

    @Test
    void noDebeAplicarDescuentoSiNoEsMiercoles() {

        Clock clock =
                Clock.fixed(
                        Instant.parse(
                                "2026-05-18T12:00:00Z"
                        ),
                        ZoneId.systemDefault()
                );

        DescuentoTaladroMiercoles descuento =
                new DescuentoTaladroMiercoles(
                        clock
                );

        when(producto.getCategoria())
                .thenReturn(categoria);

        when(categoria.getNombre())
                .thenReturn(
                        "Taladros Percutores"
                );

        Long resultado =
                descuento.aplicarDescuento(
                        producto,
                        authentication
                );

        assertEquals(
                0L,
                resultado
        );
    }
}