package com.one.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StockSearchDto {
    private String stock_title;
    private String ckflag;

    @Builder
    public StockSearchDto(String stock_title,String ckflag){
        this.stock_title = stock_title;
        this.ckflag = ckflag;

    }
}
