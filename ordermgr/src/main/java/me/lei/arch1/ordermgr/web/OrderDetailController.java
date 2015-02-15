package me.lei.arch1.ordermgr.web;

import me.lei.arch1.ordermgr.service.IOrderDetailService;
import me.lei.arch1.ordermgr.vo.OrderDetailModel;
import me.lei.arch1.ordermgr.vo.OrderDetailQueryModel;
import me.lei.arch1.ordermgr.vo.OrderModel;
import me.lei.arch1.ordermgr.vo.OrderQueryModel;
import me.lei.pagination.dto.PageMyBatis;
import me.lei.pagination.mvc.TableParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value="/orderDetail")
public class OrderDetailController {
	@Autowired
	private IOrderDetailService iservice = null;
	
	@RequestMapping(value="toAdd",method=RequestMethod.GET)
	public String toAdd(){
		
		return "orderDetail/add";
	}
	@RequestMapping(value="add",method=RequestMethod.POST)
	public String add(@ModelAttribute("m") OrderDetailModel m){
		iservice.create(m);
		return "orderDetail/success";
	}
	@RequestMapping(value="toUpdate/{uuid}",method=RequestMethod.GET)
	public String toUpdate(Model model,@PathVariable("uuid") int uuid){
		OrderDetailModel m = iservice.getByUuid(uuid);
		model.addAttribute("m", m);
		return "orderDetail/update";
	}
	@RequestMapping(value="update",method=RequestMethod.POST)
	public String post(@ModelAttribute("m") OrderDetailModel m){
		iservice.update(m);
		return "orderDetail/success";
	}
	@RequestMapping(value="toDelete/{uuid}",method=RequestMethod.GET)
	public String toDelete(Model model,@PathVariable("uuid") int uuid){
		OrderDetailModel m = iservice.getByUuid(uuid);
		model.addAttribute("m", m);
		return "orderDetail/delete";
	}
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public String post(@RequestParam("uuid") int uuid){
		iservice.delete(uuid);
		return "orderDetail/success";
	}
	@RequestMapping(value={"toList",""},method=RequestMethod.GET)
    public String toList(@TableParam OrderDetailQueryModel qm,Model model){
        PageMyBatis<OrderDetailModel> pagedList = iservice.getByPage(qm);
        model.addAttribute("pagedList", pagedList);
				
		return "orderDetail/list";
	}
	@RequestMapping(value="toQuery",method=RequestMethod.GET)
	public String toQuery(){
		return "orderDetail/query";
	}	
}
