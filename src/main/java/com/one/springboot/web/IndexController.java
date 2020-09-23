package com.one.springboot.web;

import com.one.springboot.config.auth.LoginUser;
import com.one.springboot.config.auth.dto.SessionUser;
import com.one.springboot.domain.stocks.Stocks;
import com.one.springboot.service.posts.PostsService;
import com.one.springboot.service.stock.StockService;
import com.one.springboot.web.dto.PostsResponseDto;
import com.one.springboot.web.dto.StockResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;
    private final HttpSession httpSession;
    private final StockService stockService;
//    @GetMapping("/")
//    public String index(Model model, @LoginUser SessionUser user){
//        /** model 은 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장 할 수 있다.
//         여기서는 findalldesc 함수로 가져온 결과를 posts로 index.mustache에 전달한다.
//         LoginUser 관련 설정들을 해주었으므로, 어느 컨트롤러든지 @LoginUser 를 사용해서 세션정보를 가져올 수 있다.
//         **/
//        model.addAttribute("posts",postsService.findAllDesc());
//
//        if(user != null){
//            model.addAttribute("userName",user.getName()); //세션에 저장된 값이 있을때만 model에 userName으로 등록한다. 세션에 값이 없으면 model엔 값이 없으니 로그인 버튼이 보이게됨
//        }
//
//        return "index";
//        //머스테치 스타터 덕분에 컨트롤러에서 문자열을 반환할때 앞의 경로와 뒤의 파일 확장자는 자동으로 지정된다.
//        //앞의 경로는 /src/main/resources/templates 로, 뒤의 확장자는 .mustache 가 붙는다.
//        //즉 여기서는 /src/main/resources/templates/index.mustache 로 전환되어 View Resolver가 처리하게 된다.
//    }
    @GetMapping("/")
    public String index(Model model){
        /** model 은 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장 할 수 있다.
         여기서는 findalldesc 함수로 가져온 결과를 posts로 index.mustache에 전달한다.
         LoginUser 관련 설정들을 해주었으므로, 어느 컨트롤러든지 @LoginUser 를 사용해서 세션정보를 가져올 수 있다.
         **/

        return "index";
        //머스테치 스타터 덕분에 컨트롤러에서 문자열을 반환할때 앞의 경로와 뒤의 파일 확장자는 자동으로 지정된다.
        //앞의 경로는 /src/main/resources/templates 로, 뒤의 확장자는 .mustache 가 붙는다.
        //즉 여기서는 /src/main/resources/templates/index.mustache 로 전환되어 View Resolver가 처리하게 된다.
    }

    @GetMapping("/stock_prediction")
    public String stock_prediction(Model model){

        System.out.println("stock_prediction method1-indexcontroller");
        model.addAttribute("stocks",stockService.findAllList()); //stockService 클래스를 통해 모든 값을 가진 리스트를 가져와서 머스태치에 사용

        System.out.println("stock_prediction method2-indexcontroller");


        return "stock_prediction";
    }

    //@RequestMapping(value = "/refreshStockList",method = {RequestMethod.POST,RequestMethod.GET})
    //@PostMapping("/refreshStockList")
    //@ResponseBody
    @GetMapping("/refreshStockList")
    public String refreshStockList(String data, Model model) throws Exception{

        List<Stocks> dataList;
        if(data.equals("totalVal")){
            //System.out.println("if refresh        : totalVal");
            dataList= stockService.stockTotalDesc(data);
        } else if(data.equals("incomeVal")){
            //System.out.println("if refresh        : incomeVal");
            dataList= stockService.stockIncomeDesc(data);
        } else if(data.equals("priceDescVal")){
            //System.out.println("if refresh        : priceDescVal");
            dataList= stockService.stockPriceDesc(data);
        } else if(data.equals("priceAscVal")){
            //System.out.println("if refresh        : priceAscVal");
            dataList= stockService.stockPriceAsc(data);
        } else {
            //System.out.println("if refresh        : findStockDataDefault");
            dataList= stockService.findStockDataDefault(data);
        }
        model.addAttribute("data",dataList);
        return "refreshStockList";
    }
    @GetMapping("/post")
    public String post(Model model){
        return "post";

    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id,Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post",dto);

        return "posts-update";
    }
    @GetMapping("/posts/read/{id}")
    public String postsRead(@PathVariable Long id,Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post",dto);

        return "posts-read";
    }

}
