package cl.patrones.examen.descuentos.strategy;

import cl.patrones.examen.productos.domain.Producto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class DescuentoEmpleado
        implements DescuentoStrategy {

    @Override
    public Long aplicarDescuento(
            Producto producto,
            Authentication authentication
    ) {

        boolean esEmpleado =

                authentication
                        .getAuthorities()
                        .stream()
                        .map(
                                GrantedAuthority
                                        ::getAuthority
                        )
                        .anyMatch(
                                role ->
                                        role.equals(
                                                "ROLE_EMPLEADO"
                                        )
                        );

        return esEmpleado
                ? 5L
                : 0L;
    }
}