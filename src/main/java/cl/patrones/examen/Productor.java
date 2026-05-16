package cl.patrones.examen;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cl.patrones.examen.productos.service.ProductoServiceImpl;

@Configuration
public class Productor {

	@Bean
	ProductoServiceImpl productoServiceImpl() {
		return new ProductoServiceImpl();
	}
}
