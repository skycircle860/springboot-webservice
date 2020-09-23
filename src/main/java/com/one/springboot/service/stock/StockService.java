package com.one.springboot.service.stock;

import com.one.springboot.domain.stocks.Stocks;
import com.one.springboot.domain.stocks.StocksRepository;
import com.one.springboot.web.dto.StockResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StockService {
    private final StocksRepository stocksRepository;

    @Transactional(readOnly = true)
    public List<StockResponseDto> findAllList(){
        return stocksRepository.findAllList().stream()
                .map(StockResponseDto::new)//람다식. .map(posts -> new PostsListResponseDto(posts)) 과 같다
                .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public List<Stocks> findStockDataDefault(Object name) {
        System.out.println("Service  findStockDataDefault: "+name);
        return stocksRepository.findStockDataDefault();

    }

    @Transactional(readOnly = true)
    public List<Stocks> stockTotalDesc(Object name) {
        System.out.println("Service  stockTotalDesc: "+name);
        return stocksRepository.stockTotalDesc();

    }

    @Transactional(readOnly = true)
    public List<Stocks> stockIncomeDesc(Object name) {
        System.out.println("Service  stockIncomeDesc: "+name);
        return stocksRepository.stockIncomeDesc();

    }

    @Transactional(readOnly = true)
    public List<Stocks> stockPriceDesc(Object name) {
        System.out.println("Service  stockPriceDesc: "+name);
        return stocksRepository.stockPriceDesc();

    }
    @Transactional(readOnly = true)
    public List<Stocks> stockPriceAsc(Object name) {
        System.out.println("Service  stockPriceAsc: "+name);
        return stocksRepository.stockPriceAsc();

    }

    public String stockSearch(StockResponseDto stockResponseDto){

        return "";
    }

    //**
    // @Transactional(readOnly = true)
    //    public List<StockResponseDto> findSearchData(String name) throws Exception{
    //        List<StockResponseDto> tList = null;
    //        try{
    //            tList = stocksRepository.findSearchData(name).stream()
    //                    .map(StockResponseDto::new)
    //                    .collect(Collectors.toList());
    //
    //        } catch (Exception e){
    //            System.out.println("해당 종목이 없습니다. name="+name);
    //            e.printStackTrace();
    //        }
    //        //  Stocks entity = stocksRepository.findSearchData(name).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. name="+name));
    //        return tList;
    //
    //
    //    **//

    // 해당 클래스에서 필요한 데이터를 만들어야한다. 비즈니스 로직들..


//    public String stockSearch(StockSearchDto stockSearchDto ) throws JepException {
//        System.out.println("StockService class ********************************     :"+stockSearchDto.getStock_title());
//        String scheck = stockSearchDto.getCkflag();
//
//
//        Jep jep = new Jep(false, "C:\\Users\\neo\\AppData\\Local\\Programs\\Python\\Python38-32\\Lib\\site-packages");
//        jep.runScript("./src/main/resources/stockdata/stock_prediction.py");
//        jep.eval("html=stockCodeSearch('"+stockSearchDto.getStock_title()+"')");
//        html =(String) jep.getValue("html");
////        jep.close();  jep 사용을 철회해야 할 것 같다. 해당 프로젝트 자체가 내장톰캣을 사용하는데 내장톰캣도 스레드가 돌고, jep 클래스도 내부에서 스레드를 돌리고 있는데 두개가 충돌이 나서 뭘 할수가 없음
////        if(scheck.equals("true")){
////            System.out.println("scheck  if  :"+scheck);
////            jep = new Jep(false, "C:\\Users\\neo\\AppData\\Local\\Programs\\Python\\Python38-32\\Lib\\site-packages");
////            jep.runScript("./src/main/resources/stockdata/stock_prediction.py");
////            jep.eval("html=stockCodeSearch('"+stockSearchDto.getStock_title()+"')");
////            html =(String) jep.getValue("html");
////
////        } else{
////            System.out.println("scheck else  :"+scheck);
////            jep.runScript("./src/main/resources/stockdata/stock_prediction.py");
////            jep.eval("html=stockCodeSearch('"+stockSearchDto.getStock_title()+"')");
////            html =(String) jep.getValue("html");
////        }
//
//
//
//
//        System.out.println(html);
//        //return "testvalue";
//        return html;
//    }
}
