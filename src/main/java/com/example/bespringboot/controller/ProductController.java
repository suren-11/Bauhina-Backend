package com.example.bespringboot.controller;

// import ch.qos.logback.core.util.FileUtil;
import com.example.bespringboot.entity.Product;
// import com.example.bespringboot.entity.Staff;
import com.example.bespringboot.entity.Staff;
import com.example.bespringboot.service.ProductService;
// import com.fasterxml.jackson.databind.util.NativeImageUtil;
import lombok.RequiredArgsConstructor;
// import lombok.Value;
// import org.antlr.v4.runtime.misc.NotNull;
// import org.antlr.v4.runtime.misc.Utils;
// import org.apache.catalina.session.FileStore;
// import org.apache.commons.io.FilenameUtils;
// import org.apache.tomcat.util.http.fileupload.FileUpload;
// import org.apache.tomcat.util.http.fileupload.FileUtils;
// import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
// import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class ProductController {

    private final ProductService productService;

    /*public String GenerateCode(){
        Optional<Product> product = productService.findTopProduct();
        String generatedCode;
        if (product.isPresent()){
            String code = product.get().getCode().toString();
            String[] codeArray =  code.split("-");
            int x = Integer.parseInt(codeArray[1]);
            int y = x+1;
            generatedCode = "Item-00"+ y;
            return generatedCode;
        }else {
            generatedCode = "Item-001";
            return generatedCode;
        }
    }*/

    /**save the product to the database, here products comes with a image of the product so need to convert the photo 
     * in to byte code and after saved to the database
    */
    @PostMapping(value = "/productSave",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Product createProduct(@ModelAttribute Product product,
                                 @RequestParam("image") MultipartFile file)
            throws IOException {
        String fileName = System.currentTimeMillis() + StringUtils.cleanPath(file.getOriginalFilename());
        byte[] pro = file.getBytes();
        product.setPicByte(pro);
        Files.copy(file.getInputStream(), Path.of("E:\\New folder (2)\\sample projects\\New folder\\Test\\BEspringboot\\src\\main\\java\\com\\example\\bespringboot\\assets\\productPhotos", fileName), StandardCopyOption.REPLACE_EXISTING);
        return productService.saveProduct(product);
    }

    /** getting the list of the product from the database */
    @GetMapping("/productList")
    public List<Product> getAllProducts(){
        return productService.findAll();
    }

    @GetMapping("/images/{productCode}")
    public ResponseEntity<byte[]> getProductImage(@PathVariable String productCode){
        byte[] imageData = productService.getImage(productCode);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageData);
    }

    @GetMapping("/productType/{type}")
    public List<Product> getByType(@PathVariable String type){
        return productService.getByType(type);
    }

    @PutMapping(value = "/productUpdate/{code}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Product> updateProduct(@PathVariable String code, @ModelAttribute Product product)  throws IOException {
        Optional<Product> selectedProduct = productService.findByCode(code);
        if (selectedProduct.isPresent()){
            Product updateProduct = selectedProduct.get();
            updateProduct.setName(product.getName());
            updateProduct.setCost(product.getCost());
            updateProduct.setType(product.getType());
            if (product.getPicByte()==null){
                updateProduct.setPicByte(updateProduct.getPicByte());
            }
            updateProduct.setQty(product.getQty());
            productService.saveProduct(updateProduct);
            return new ResponseEntity<>(updateProduct, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /** deleting a product from database */
    @DeleteMapping("/delete/{code}")
    public void delete(@PathVariable String code){
        /**searching a selected product from database 
         * if that product is present
         * delete that product from database */
        Optional<Product> selectedProduct = productService.findByCode(code);
        if (selectedProduct.isPresent()){
            Product product = selectedProduct.get();
            productService.delete(product);
        }
    }
}
