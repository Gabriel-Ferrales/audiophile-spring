package com.audiophile.audiophile_backend.rest;
import com.audiophile.audiophile_backend.dao.ProductDAOImpl;

import com.audiophile.audiophile_backend.dto.ProductSaleRequest;
import jakarta.persistence.Entity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;
import com.audiophile.audiophile_backend.entity.Product;

@CrossOrigin(origins = "https://audiophile-fullstack-gabriel-ues.netlify.app")

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/products")
public class ProductRestController {

    private final ProductDAOImpl productDAO;

    @Autowired
    public ProductRestController(ProductDAOImpl productDAO) {
        this.productDAO = productDAO;
    }

    // Crear un nuevo producto (CREATE)
   @PostMapping
   public ResponseEntity<Product> createProduct(@RequestBody Product product) {
       Product savedProduct = productDAO.save(product);
       return ResponseEntity.ok(savedProduct);
   }

    @PostMapping("/update-sales")
    public ResponseEntity<?> updateProductSales(@RequestBody List<ProductSaleRequest> products) {
        System.out.println("Iniciando la actualización de ventas. Productos recibidos: " + products.size());

        for (ProductSaleRequest productSaleRequest : products) {
            System.out.println("Procesando producto con ID: " + productSaleRequest.getId() + " y cantidad: " + productSaleRequest.getQuantity());

            Optional<Product> product = productDAO.findById(productSaleRequest.getId());
            if (product.isPresent()) {
                Product existingProduct = product.get();
                int newSoldUnits = existingProduct.getSoldUnits() + productSaleRequest.getQuantity();
                System.out.println("Producto encontrado: " + existingProduct.getName() + ". Unidades vendidas actuales: " + existingProduct.getSoldUnits() + ". Nuevas unidades vendidas: " + newSoldUnits);

                existingProduct.setSoldUnits(newSoldUnits);
                productDAO.save(existingProduct);
                System.out.println("Producto actualizado y guardado correctamente.");
            } else {
                System.out.println("Producto con ID " + productSaleRequest.getId() + " no encontrado.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found: " + productSaleRequest.getId());
            }
        }

        System.out.println("Actualización de ventas completada con éxito.");
        return ResponseEntity.ok("Sales updated successfully");
    }




    // Obtener todos los productos (READ)
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productDAO.findAll();
        return ResponseEntity.ok(products);
    }

    // Obtener un producto por ID (READ)
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productDAO.findById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Actualizar un producto (UPDATE)
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        Optional<Product> optionalProduct = productDAO.findById(id);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(productDetails.getName());
            product.setPrice(productDetails.getPrice());
            product.setCategory(productDetails.getCategory());
            product.setStock(productDetails.getStock());
            product.setSoldUnits(productDetails.getSoldUnits());

            Product updatedProduct = productDAO.save(product);
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un producto (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (productDAO.existsById(id)) {
            productDAO.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
