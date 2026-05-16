package cl.patrones.examen.descuentos.decorator;

import cl.patrones.examen.descuentos.service.CalculadorDescuentos;
import cl.patrones.examen.productos.domain.Producto;
import org.springframework.security.core.Authentication;

public class ProductoConDescuento
        extends ProductoDecorator {

    private final CalculadorDescuentos
            calculador;

    private final Authentication
            authentication;

    public ProductoConDescuento(
            Producto producto,
            CalculadorDescuentos calculador,
            Authentication authentication
    ) {

        super(producto);

        this.calculador = calculador;
        this.authentication = authentication;
    }

    @Override
    public Long getDescuento() {

        return calculador
                .obtenerMayorDescuento(
                        producto,
                        authentication
                );
    }

    @Override
    public Long getPrecioFinal() {

        Long porcentaje =
                getDescuento();

        return producto.getPrecioLista()

                - (

                producto.getPrecioLista()
                        * porcentaje
                        / 100

        );
    }
}