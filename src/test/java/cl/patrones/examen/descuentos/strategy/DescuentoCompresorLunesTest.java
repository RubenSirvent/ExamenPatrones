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
class DescuentoCompresorLunesTest {

    @Mock
    private Producto producto;

    @Mock
    private Categoria categoria;

    @Mock
    private Authentication authentication;

    @Test
    void debeAplicarDescuentoCompresorLunes() {

        Clock clock =
                Clock.fixed(
                        Instant.parse(
                                "2026-05-18T12:00:00Z"
                        ),
                        ZoneId.systemDefault()
                );

        DescuentoCompresorLunes descuento =
                new DescuentoCompresorLunes(
                        clock
                );

        when(producto.getCategoria())
                .thenReturn(categoria);

        when(categoria.getNombre())
                .thenReturn(
                        "Compresores de Aire"
                );

        Long resultado =
                descuento.aplicarDescuento(
                        producto,
                        authentication
                );

        assertEquals(
                6L,
                resultado
        );
    }

    @Test
    void noDebeAplicarDescuentoSiNoEsLunes() {

        Clock clock =
                Clock.fixed(
                        Instant.parse(
                                "2026-05-19T12:00:00Z"
                        ),
                        ZoneId.systemDefault()
                );

        DescuentoCompresorLunes descuento =
                new DescuentoCompresorLunes(
                        clock
                );

        when(producto.getCategoria())
                .thenReturn(categoria);

        when(categoria.getNombre())
                .thenReturn(
                        "Compresores de Aire"
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