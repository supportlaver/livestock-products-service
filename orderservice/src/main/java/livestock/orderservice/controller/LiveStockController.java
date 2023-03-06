package livestock.orderservice.controller;

import livestock.orderservice.domain.livestock.LiveStock;
import livestock.orderservice.service.LiveStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class LiveStockController {

    private final LiveStockService liveStockService;

    //일단 PigForm 에 의존적으로 만들고 추후에 타임리프를 활용해서 동적으로 폼이 바뀌도록 만들어보자.
    @GetMapping("items/new")
    public String createLiveStock(Model model){
        model.addAttribute("form",new PigForm());
        return "livestock/createLiveStockForm";
    }

    @PostMapping("items/new")
    public String saveLiveStock(PigForm pigForm){
        LiveStock liveStock = new LiveStock();
        liveStock.setStockQuantity(pigForm.getStockQuantity());
        liveStock.setPrice(pigForm.getPrice());
        liveStock.setName(pigForm.getName());
        liveStockService.addLiveStock(liveStock);
        return "redirect:/";
    }
    @GetMapping("/items")
    public String findLiveStock(Model model){
        List<LiveStock> result = liveStockService.findAll();
        model.addAttribute("liveStocks",result);
        return "livestock/findLiveStockForm";
    }




}
