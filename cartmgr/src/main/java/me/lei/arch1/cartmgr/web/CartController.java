package me.lei.arch1.cartmgr.web;

import me.lei.arch1.cartmgr.service.ICartService;
import me.lei.arch1.cartmgr.vo.CartModel;
import me.lei.arch1.cartmgr.vo.CartQueryModel;
import me.lei.pagination.dto.PageMyBatis;
import me.lei.spring.TableParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value="/cart")
public class CartController {
	@Autowired
	private ICartService iservice = null;
	
	@RequestMapping(value="toAdd",method=RequestMethod.GET)
	public String toAdd(){
		
		return "cart/add";
	}
	@RequestMapping(value="add",method=RequestMethod.POST)
	public String add(@ModelAttribute("m") CartModel m){
		iservice.create(m);
		return "cart/success";
	}
	@RequestMapping(value="toUpdate/{uuid}",method=RequestMethod.GET)
	public String toUpdate(Model model,@PathVariable("uuid") int uuid){
		CartModel m = iservice.getByUuid(uuid);
		model.addAttribute("m", m);
		return "cart/update";
	}
	@RequestMapping(value="update",method=RequestMethod.POST)
	public String post(@ModelAttribute("m") CartModel m){
		iservice.update(m);
		return "cart/success";
	}
	@RequestMapping(value="toDelete/{uuid}",method=RequestMethod.GET)
	public String toDelete(Model model,@PathVariable("uuid") int uuid){
		CartModel m = iservice.getByUuid(uuid);
		model.addAttribute("m", m);
		return "cart/delete";
	}
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public String post(@RequestParam("uuid") int uuid){
		iservice.delete(uuid);
		return "cart/success";
	}
	@RequestMapping(value={"toList",""},method=RequestMethod.GET)
    public String toList(@TableParam CartQueryModel qm,Model model){
        PageMyBatis<CartModel> pagedList = iservice.getByPage(qm);
        model.addAttribute("pagedList", pagedList);
				
		return "cart/list";
	}
	@RequestMapping(value="toQuery",method=RequestMethod.GET)
	public String toQuery(){
		return "cart/query";
	}	
}
