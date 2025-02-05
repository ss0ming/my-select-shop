package com.ss0ming.myselectshop.repository;

import com.ss0ming.myselectshop.entity.Folder;
import com.ss0ming.myselectshop.entity.Product;
import com.ss0ming.myselectshop.entity.ProductFolder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductFolderRepository extends JpaRepository<ProductFolder, Long> {
    Optional<ProductFolder> findByProductAndFolder(Product product, Folder folder);
}
