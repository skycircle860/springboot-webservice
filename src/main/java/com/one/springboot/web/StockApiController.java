package com.one.springboot.web;

import com.one.springboot.domain.stocks.Stocks;
import com.one.springboot.service.stock.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class StockApiController {

    private final StockService stockService;

    @PostMapping("/api/v1/stock") // 파라미터를 String 타입으로 받았더니 .equals 가 안먹히더라. object로 하니까 작동함.
    public List<Stocks> searchStock (@RequestBody Object name) {
        //System.out.println("StockApiController======================= : "+name);


        if(name.equals("totalVal")){
            //System.out.println("if count        : totalVal");
            return stockService.stockTotalDesc(name);
        } else if(name.equals("incomeVal")){
            //System.out.println("if count        : incomeVal");
            return stockService.stockIncomeDesc(name);
        } else if(name.equals("priceDescVal")){
            //System.out.println("if count        : priceDescVal");
            return stockService.stockPriceDesc(name);
        } else if(name.equals("priceAscVal")){
            //System.out.println("if count        : priceAscVal");
            return stockService.stockPriceAsc(name);
        } else {
            //System.out.println("if count        : findStockDataDefault");
            return stockService.findStockDataDefault(name);
        }

    }




}
