package com.one.springboot.web.dto;

import com.one.springboot.domain.stocks.Stocks;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Getter
public class StockResponseDto implements Serializable{
    private Long id;
    private String date;
    private String code;
    private String name;
    private int total_revenue;
    private int gross_profit;
    private int total_income;
    private float net_profit_margin;
    private float roe;
    private float dept_ratio;
    private int eps;
    private float per;
    private int bps;
    private float pbr;
    private int per_share;
    private float yield_ratio;
    private float total_revenue_percent ,gross_profit_percent , total_income_percent ,net_profit_margin_percent ,dept_ratio_percent ,per_percent
            ,yield_ratio_percent ,peg_percent ,price_percent;
    private int b2yprice ,b1yprice ,nowprice;


    public StockResponseDto(Stocks entity){

        this.id = entity.getId();
        this.date = entity.getDate();
        this.code = entity.getCode();
        this.name = entity.getName();
        this.total_revenue = entity.getTotal_revenue();
        this.gross_profit = entity.getGross_profit();
        this.total_income = entity.getTotal_income();
        this.net_profit_margin = entity.getNet_profit_margin();
        this.roe = entity.getRoe();
        this.dept_ratio = entity.getDept_ratio();
        this.eps = entity.getEps();
        this.per = entity.getPer();
        this.bps = entity.getBps();
        this.pbr = entity.getPbr();
        this.per_share = entity.getPer_share();
        this.yield_ratio = entity.getYield_ratio();

        this.total_revenue_percent = entity.getTotal_revenue_percent();
        this.gross_profit_percent = entity.getGross_profit_percent();
        this.total_income_percent = entity.getTotal_income_percent();
        this.net_profit_margin_percent = entity.getNet_profit_margin_percent();
        this.dept_ratio_percent = entity.getDept_ratio_percent();
        this.peg_percent = entity.getPeg_percent();
        this.per_percent = entity.getPer_percent();
        this.yield_ratio_percent = entity.getYield_ratio_percent();
        this.price_percent = entity.getPrice_percent();
        this.b2yprice = entity.getB2yprice();
        this.b1yprice = entity.getB1yprice();
        this.nowprice = entity.getNowprice();


    }
}
