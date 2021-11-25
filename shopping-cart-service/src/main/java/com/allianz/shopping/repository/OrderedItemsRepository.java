package com.allianz.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.allianz.shopping.entity.OrderedItems;

public interface OrderedItemsRepository extends JpaRepository<OrderedItems, Integer> {

}
