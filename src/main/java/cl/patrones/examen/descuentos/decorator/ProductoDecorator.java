package cl.patrones.examen.descuentos.decorator;

import cl.patrones.examen.productos.domain.Categoria;
import cl.patrones.examen.productos.domain.Producto;

public abstract class ProductoDecorator
        implements Producto {

    protected Producto producto;

    public ProductoDecorator(
            Producto producto
    ) {
        this.producto = producto;
    }

    @Override
    public String getSku() {
        return producto.getSku();
    }

    @Override
    public String getNombre() {
        return producto.getNombre();
    }

    @Override
    public String getImagen() {
        return producto.getImagen();
    }

    @Override
    public Long getCosto() {
        return producto.getCosto();
    }

    @Override
    public Long getPrecioLista() {
        return producto.getPrecioLista();
    }

    @Override
    public Categoria getCategoria() {
        return producto.getCategoria();
    }
}