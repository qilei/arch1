package me.lei.arch1.storemgr.web;

import me.lei.arch1.storemgr.service.IStoreService;
import me.lei.arch1.storemgr.vo.StoreModel;
import me.lei.arch1.storemgr.vo.StoreQueryModel;
import me.lei.pagination.dto.PageMyBatis;
import me.lei.spring.TableParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value="/store")
public class StoreController {
	@Autowired
	private IStoreService iservice = null;
	
	@RequestMapping(value="toAdd",method=RequestMethod.GET)
	public String toAdd(){
		
		return "store/add";
	}
	@RequestMapping(value="add",method=RequestMethod.POST)
	public String add(@ModelAttribute("m") StoreModel m){
		iservice.create(m);
		return "store/success";
	}
	@RequestMapping(value="toUpdate/{uuid}",method=RequestMethod.GET)
	public String toUpdate(Model model,@PathVariable("uuid") int uuid){
		StoreModel m = iservice.getByUuid(uuid);
		model.addAttribute("m", m);
		return "store/update";
	}
	@RequestMapping(value="update",method=RequestMethod.POST)
	public String post(@ModelAttribute("m") StoreModel m){
		iservice.update(m);
		return "store/success";
	}
	@RequestMapping(value="toDelete/{uuid}",method=RequestMethod.GET)
	public String toDelete(Model model,@PathVariable("uuid") int uuid){
		StoreModel m = iservice.getByUuid(uuid);
		model.addAttribute("m", m);
		return "store/delete";
	}
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public String post(@RequestParam("uuid") int uuid){
		iservice.delete(uuid);
		return "store/success";
	}
	@RequestMapping(value={"toList",""},method=RequestMethod.GET)
    public String toList(@TableParam StoreQueryModel qm,Model model){
        PageMyBatis<StoreModel> pagedList = iservice.getByPage(qm);
        model.addAttribute("pagedList", pagedList);
				
		return "store/list";
	}
	@RequestMapping(value="toQuery",method=RequestMethod.GET)
	public String toQuery(){
		return "store/query";
	}	
}
