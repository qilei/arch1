package me.lei.arch1.ordermgr.web;

import me.lei.arch1.ordermgr.service.IOrderService;
import me.lei.arch1.ordermgr.vo.OrderModel;
import me.lei.arch1.ordermgr.vo.OrderQueryModel;
import me.lei.pagination.dto.PageMyBatis;
import me.lei.pagination.mvc.TableParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value="/order")
public class OrderController {
	@Autowired
	private IOrderService iservice = null;
	
	@RequestMapping(value="toAdd",method=RequestMethod.GET)
	public String toAdd(){
		
		return "order/add";
	}
	@RequestMapping(value="add",method=RequestMethod.POST)
	public String add(@ModelAttribute("m") OrderModel m){
		iservice.create(m);
		return "order/success";
	}
	@RequestMapping(value="toUpdate/{uuid}",method=RequestMethod.GET)
	public String toUpdate(Model model,@PathVariable("uuid") int uuid){
		OrderModel m = iservice.getByUuid(uuid);
		model.addAttribute("m", m);
		return "order/update";
	}
	@RequestMapping(value="update",method=RequestMethod.POST)
	public String post(@ModelAttribute("m") OrderModel m){
		iservice.update(m);
		return "order/success";
	}
	@RequestMapping(value="toDelete/{uuid}",method=RequestMethod.GET)
	public String toDelete(Model model,@PathVariable("uuid") int uuid){
		OrderModel m = iservice.getByUuid(uuid);
		model.addAttribute("m", m);
		return "order/delete";
	}
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public String post(@RequestParam("uuid") int uuid){
		iservice.delete(uuid);
		return "order/success";
	}
	@RequestMapping(value={"toList",""},method=RequestMethod.GET)
    public String toList(@TableParam OrderQueryModel qm,Model model){
        PageMyBatis<OrderModel> pagedList = iservice.getByPage(qm);
        model.addAttribute("pagedList", pagedList);

        return "order/list";
	}
	@RequestMapping(value="toQuery",method=RequestMethod.GET)
	public String toQuery(){
		return "order/query";
	}	
}
