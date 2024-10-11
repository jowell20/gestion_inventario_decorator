package com.patrones.patrones.service;

import com.patrones.patrones.model.Categoria;
import com.patrones.patrones.model.Producto;
import com.patrones.patrones.model.Proveedor;
import com.patrones.patrones.repository.CategoriaRepository;
import com.patrones.patrones.repository.ProveedorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;


    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }

    public Categoria getCategoriaById(Long id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    public Categoria saveCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria updateCategoria(Long id, Categoria categoria) {
        Categoria categoriarExistente = categoriaRepository.findById(id).orElse(null);

        if (categoriarExistente != null) {
            if (categoria.getNombre() != null && !categoria.getNombre().isEmpty()) {
                categoriarExistente.setNombre(categoria.getNombre());
            }
            if (categoria.getDescripcion() != null && !categoria.getDescripcion().isEmpty()) {
                categoriarExistente.setDescripcion(categoria.getDescripcion());
            }

            return categoriaRepository.save(categoriarExistente);
        } else {
            throw new EntityNotFoundException("categoria no encontrado con id: " + id);
        }
    }

    public void deleteCategoria(Long id) {
        // Verificar si la categoría existe antes de eliminar
        if (!categoriaRepository.existsById(id)) {
            throw new EntityNotFoundException("Categoría no encontrada con id: " + id);
        }
        // Eliminar la categoría si existe
        categoriaRepository.deleteById(id);
    }
   /* public void deleteCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }*/
}
