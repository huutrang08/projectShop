package com.shop.ex.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.ex.domain.Acc;
@Repository
public interface AccountRepository extends JpaRepository<Acc, String> {

}
