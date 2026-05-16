package cl.patrones.examen.descuentos.strategy;

import cl.patrones.examen.productos.domain.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DescuentoEmpleadoTest {

    private DescuentoEmpleado
            descuentoEmpleado;

    @Mock
    private Producto producto;

    @Mock
    private Authentication authentication;

    @Mock
    private GrantedAuthority authority;

    @BeforeEach
    void setUp() {

        descuentoEmpleado =
                new DescuentoEmpleado();
    }

    @Test
    void debeAplicarDescuentoEmpleado() {

        when(authority.getAuthority())
                .thenReturn(
                        "ROLE_EMPLEADO"
                );

        Collection<? extends GrantedAuthority> authorities =
                List.of(authority);

        Mockito.<Collection<? extends GrantedAuthority>>when(authentication.getAuthorities())
                .thenReturn(authorities);

        Long descuento =

                descuentoEmpleado
                        .aplicarDescuento(
                                producto,
                                authentication
                        );

        assertEquals(
                5L,
                descuento
        );
    }

    @Test
    void noDebeAplicarDescuentoNoEmpleado() {

        when(authority.getAuthority())
                .thenReturn(
                        "ROLE_CLIENTE"
                );

        Collection<? extends GrantedAuthority> authorities =
                List.of(authority);

        Mockito.<Collection<? extends GrantedAuthority>>when(authentication.getAuthorities())
                .thenReturn(authorities);

        Long descuento =

                descuentoEmpleado
                        .aplicarDescuento(
                                producto,
                                authentication
                        );

        assertEquals(
                0L,
                descuento
        );
    }
}