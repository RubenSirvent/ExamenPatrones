package cl.patrones.examen.productos.controller;

import cl.patrones.examen.descuentos.decorator.ProductoConDescuento;
import cl.patrones.examen.descuentos.service.CalculadorDescuentos;
import cl.patrones.examen.productos.service.ProductoService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    private final ProductoService productoService;
    private final CalculadorDescuentos calculadorDescuentos;

    public AppController(
            ProductoService productoService,
            CalculadorDescuentos calculadorDescuentos
    ) {
        this.productoService = productoService;
        this.calculadorDescuentos = calculadorDescuentos;
    }

    @GetMapping("/")
    String inicio(Model model) {

        Authentication authentication =

                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        var productos =
                productoService.getProductos();

        var productosConDescuento =

                productos.stream()

                        .map(producto ->
                                new ProductoConDescuento(
                                        producto,
                                        calculadorDescuentos,
                                        authentication
                                )
                        )

                        .toList();

        model.addAttribute(
                "productos",
                productosConDescuento
        );

        return "inicio";
    }
}