package com.one.springboot.domain.stocks;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StocksRepository extends JpaRepository<Stocks,Long> {

    @Query("SELECT p FROM Stocks p ORDER BY p.date")
    List<Stocks> findAllList();

    @Query(nativeQuery = true, value="SELECT * FROM Stocks p limit 10")//쿼리 제작 생각해야함.
    public List<Stocks> findStockDataDefault();

    @Query(nativeQuery = true, value="SELECT * FROM Stocks p ORDER BY p.total_revenue_percent desc ")//쿼리 제작 생각해야함.
    public List<Stocks> stockTotalDesc();

    @Query(nativeQuery = true, value="SELECT * FROM Stocks p ORDER BY p.total_income_percent desc ")//쿼리 제작 생각해야함.
    public List<Stocks> stockIncomeDesc();

    @Query(nativeQuery = true, value="SELECT * FROM Stocks p ORDER BY p.price_percent desc ")//쿼리 제작 생각해야함.
    public List<Stocks> stockPriceDesc();

    @Query(nativeQuery = true, value="SELECT * FROM Stocks p ORDER BY p.price_percent asc ")//쿼리 제작 생각해야함.
    public List<Stocks> stockPriceAsc();

}
