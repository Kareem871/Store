package com.store.tunnel.add;


import com.store.tunnel.product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface addRepository extends JpaRepository<product, Long> {
    @Query(value = "SELECT filename FROM product")
    List<String> findFileNames();
}
