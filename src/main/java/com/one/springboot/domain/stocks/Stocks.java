package com.one.springboot.domain.stocks;

import com.one.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;


@Getter             //클래스 내 모든 필드의 Getter 메소드를 자동생성
@NoArgsConstructor  //기본 생성자 자동 추가. 이 클래스에서는 public Posts() 와 같은 효과
@Entity //테이블과 링크될 클래스를 나타냄. 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍으로 테이블 이름을 매칭
//ex) SalesManager.java -> sales_manager table
//Entity 클래스는 기본 Repository 와 함께 움직여야 하므로 도메인 패키지에서 함께 관리한다.

public class Stocks extends BaseTimeEntity implements Serializable {
    @Id //해당 테이블의 PK 필드를 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK의 생성 규칙을 나타냄.스프링 부트 2.0에서는 해당 옵션을 넣어야함
    private Long id;

    @Column(length = 500, nullable = false) //테이블 칼럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 칼럼이 된다
                                            //사용하는 이유는 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용한다.
                                            //문자열의 경우 VARCHAR(255)가 기본값인데, 사이즈를 500으로 늘리고 싶거나
                                            //타입을 TEXT로 변경하고 싶거나 등의 경우에 사용한다.
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

    @Builder  //해당 클래스의 빌더 패턴 클래스를 생성.
    //생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함 // 생성자 자동생성 단축키 alt + insert

    public Stocks(Long id, String date, String code, String name, int total_revenue, int gross_profit, int total_income, float net_profit_margin, float roe, float dept_ratio, int eps, float per, int bps, float pbr, int per_share, float yield_ratio, float total_revenue_percent, float gross_profit_percent, float total_income_percent, float net_profit_margin_percent, float dept_ratio_percent, float per_percent, float yield_ratio_percent, float peg_percent, float price_percent, int b2yprice, int b1yprice, int nowprice) {
        this.id = id;
        this.date = date;
        this.code = code;
        this.name = name;
        this.total_revenue = total_revenue;
        this.gross_profit = gross_profit;
        this.total_income = total_income;
        this.net_profit_margin = net_profit_margin;
        this.roe = roe;
        this.dept_ratio = dept_ratio;
        this.eps = eps;
        this.per = per;
        this.bps = bps;
        this.pbr = pbr;
        this.per_share = per_share;
        this.yield_ratio = yield_ratio;
        this.total_revenue_percent = total_revenue_percent;
        this.gross_profit_percent = gross_profit_percent;
        this.total_income_percent = total_income_percent;
        this.net_profit_margin_percent = net_profit_margin_percent;
        this.dept_ratio_percent = dept_ratio_percent;
        this.per_percent = per_percent;
        this.yield_ratio_percent = yield_ratio_percent;
        this.peg_percent = peg_percent;
        this.price_percent = price_percent;
        this.b2yprice = b2yprice;
        this.b1yprice = b1yprice;
        this.nowprice = nowprice;
    }

//    public void update(String title,String content){
//        this.title = title;
//        this.content = content;
//    }
}
