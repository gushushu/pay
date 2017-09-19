package com.gushushu.pay.repository;

import com.gushushu.pay.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders,String> {


}
