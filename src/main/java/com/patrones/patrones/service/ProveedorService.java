package com.patrones.patrones.service;

import com.patrones.patrones.model.Proveedor;

import com.patrones.patrones.repository.ProveedorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    public List<Proveedor> getAllProveedores() {
        return proveedorRepository.findAll();
    }

    public Proveedor getProveedorById(Long id) {

        return proveedorRepository.findById(id).orElse(null);
    }

    public Proveedor saveProveedor(Proveedor proveedor) {

        return proveedorRepository.save(proveedor);
    }

  /*  public Proveedor updateProveedor(Long id, Proveedor proveedor) {
        Proveedor proveedorExistente = proveedorRepository.findById(id).orElse(null);
        if (proveedorExistente != null) {
            proveedorExistente.setNombre(proveedor.getNombre());
            proveedorExistente.setDireccion(proveedor.getDireccion());
            proveedorExistente.setTelefono(proveedor.getTelefono());
            proveedorExistente.setEmail(proveedor.getEmail());
            return proveedorRepository.save(proveedorExistente);
        } else {
            return null;
        }
    }*/

  public Proveedor updateProveedor(Long id, Proveedor proveedor) {
      Proveedor proveedorExistente = proveedorRepository.findById(id).orElse(null);
      if (proveedorExistente != null) {
          if (proveedor.getNombre() != null && !proveedor.getNombre().isEmpty()) {
              proveedorExistente.setNombre(proveedor.getNombre());
          }
          if (proveedor.getDireccion() != null && !proveedor.getDireccion().isEmpty()) {
              proveedorExistente.setDireccion(proveedor.getDireccion());
          }
          if (proveedor.getTelefono() != null && !proveedor.getTelefono().isEmpty()) {
              proveedorExistente.setTelefono(proveedor.getTelefono());
          }
          if (proveedor.getEmail() != null && !proveedor.getEmail().isEmpty()) {
              proveedorExistente.setEmail(proveedor.getEmail());
          }
          return proveedorRepository.save(proveedorExistente);
      } else {
           throw new EntityNotFoundException("proveedor no encontrado con id: " + id);
      }
  }
    public void deleteProveedor(Long id) {
        // Verificar si el proveedor existe
        if (!proveedorRepository.existsById(id)) {
            throw new EntityNotFoundException("Proveedor no encontrado con id: " + id);
        }

        proveedorRepository.deleteById(id); // Elimina el proveedor
    }
   /* public void deleteProveedor(Long id) {
        proveedorRepository.deleteById(id);
    }*/
}