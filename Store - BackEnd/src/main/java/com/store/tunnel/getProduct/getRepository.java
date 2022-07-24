package com.store.tunnel.getProduct;

import com.store.tunnel.product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface getRepository extends JpaRepository<product, Long> {

    List<product> findAll();
}
