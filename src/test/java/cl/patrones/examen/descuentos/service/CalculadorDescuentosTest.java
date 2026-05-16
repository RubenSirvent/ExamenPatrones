package cl.patrones.examen.descuentos.service;

import cl.patrones.examen.descuentos.strategy.DescuentoStrategy;
import cl.patrones.examen.productos.domain.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.security.core.Authentication;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalculadorDescuentosTest {

    private CalculadorDescuentos
            calculador;

    @Mock
    private Producto producto;

    @Mock
    private Authentication authentication;

    @Mock
    private DescuentoStrategy
            descuento1;

    @Mock
    private DescuentoStrategy
            descuento2;

    @Mock
    private DescuentoStrategy
            descuento3;

    @BeforeEach
    void setUp() {

        calculador =
                new CalculadorDescuentos(

                        List.of(
                                descuento1,
                                descuento2,
                                descuento3
                        )
                );
    }

    @Test
    void debeSeleccionarMayorDescuento() {

        when(
                descuento1
                        .aplicarDescuento(
                                producto,
                                authentication
                        )
        ).thenReturn(5L);

        when(
                descuento2
                        .aplicarDescuento(
                                producto,
                                authentication
                        )
        ).thenReturn(10L);

        when(
                descuento3
                        .aplicarDescuento(
                                producto,
                                authentication
                        )
        ).thenReturn(6L);

        Long resultado =

                calculador
                        .obtenerMayorDescuento(
                                producto,
                                authentication
                        );

        assertEquals(
                10L,
                resultado
        );
    }

    @Test
    void debeRetornarCeroSinDescuentos() {

        when(
                descuento1
                        .aplicarDescuento(
                                producto,
                                authentication
                        )
        ).thenReturn(0L);

        when(
                descuento2
                        .aplicarDescuento(
                                producto,
                                authentication
                        )
        ).thenReturn(0L);

        when(
                descuento3
                        .aplicarDescuento(
                                producto,
                                authentication
                        )
        ).thenReturn(0L);

        Long resultado =

                calculador
                        .obtenerMayorDescuento(
                                producto,
                                authentication
                        );

        assertEquals(
                0L,
                resultado
        );
    }
}