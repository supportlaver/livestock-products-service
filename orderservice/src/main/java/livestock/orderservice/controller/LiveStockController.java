package livestock.orderservice.controller;

import livestock.orderservice.domain.livestock.LiveStock;
import livestock.orderservice.domain.livestock.LiveStockCode;
import livestock.orderservice.service.LiveStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class LiveStockController {

    private final LiveStockService liveStockService;

    @ModelAttribute("liveStockCodes")
    public List<LiveStockCode> liveStockCodes(){
        List<LiveStockCode> liveStockCodes = new ArrayList<>();
        liveStockCodes.add(new LiveStockCode("chicken","닭"));
        liveStockCodes.add(new LiveStockCode("pig","돼지"));
        liveStockCodes.add(new LiveStockCode("cow","소"));
        return liveStockCodes;
    }

    //일단 PigForm 에 의존적으로 만들고 추후에 타임리프를 활용해서 동적으로 폼이 바뀌도록 만들어보자.
    @GetMapping("items/new")
    public String createLiveStock(Model model){
        model.addAttribute("form",new PigForm());
        return "livestock/createLiveStockForm";
    }

//    @GetMapping("items/new")
//    public String createLiveStock(Model model){
//        model.addAttribute("form",new PigForm());
//        return "livestock/afterCheckCreateLiveStockForm";
//    }

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

    @GetMapping("/items/{itemId}/edit")
    public String updateLiveStockForm(@PathVariable("itemId") Long itemId , Model model){
        LiveStock findLiveStock = liveStockService.findOne(itemId);
        LiveStock liveStock = new LiveStock();
        liveStock.setName(findLiveStock.getName());
        liveStock.setPrice(findLiveStock.getPrice());
        liveStock.setStockQuantity(findLiveStock.getStockQuantity());
        model.addAttribute("form",liveStock);
        return "livestock/updateLiveStockForm";

    }
    @PostMapping("/items/{itemId}/edit")
    public String updateLiveStock(@PathVariable("itemId") Long itemId , @ModelAttribute("form") LiveStock liveStock){
        liveStockService.updateLiveStock(itemId,liveStock);
        return "redirect:/items";
    }





}
