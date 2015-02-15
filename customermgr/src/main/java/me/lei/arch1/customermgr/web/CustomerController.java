package me.lei.arch1.customermgr.web;

import me.lei.arch1.customermgr.service.ICustomerService;
import me.lei.arch1.customermgr.vo.CustomerModel;
import me.lei.arch1.customermgr.vo.CustomerQueryModel;
import me.lei.pagination.dto.PageMyBatis;
import me.lei.pagination.dto.datatables.PagingCriteria;
import me.lei.spring.TableParam;
import me.lei.util.format.DateFormatHelper;
import me.lei.util.json.JsonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.naming.ldap.PagedResultsControl;

@Controller
@RequestMapping(value="/customer")
public class CustomerController {
	@Autowired
	private ICustomerService ics = null;
	
	@RequestMapping(value="toAdd",method=RequestMethod.GET)
	public String toAdd(){
		
		return "customer/add";
	}
	@RequestMapping(value="add",method=RequestMethod.POST)
	public String add(@ModelAttribute("cm") CustomerModel cm){
		cm.setRegisterTime(DateFormatHelper.long2str(System.currentTimeMillis()));
		ics.create(cm);
		return "customer/success";
	}
	@RequestMapping(value="toUpdate/{customerUuid}",method=RequestMethod.GET)
	public String toUpdate(Model model,@PathVariable("customerUuid") int customerUuid){
		CustomerModel cm = ics.getByUuid(customerUuid);
		model.addAttribute("cm", cm);
		return "customer/update";
	}
	@RequestMapping(value="update",method=RequestMethod.POST)
	public String post(@ModelAttribute("cm") CustomerModel cm){
		ics.update(cm);
		return "customer/success";
	}
	@RequestMapping(value="toDelete/{customerUuid}",method=RequestMethod.GET)
	public String toDelete(Model model,@PathVariable("customerUuid") int customerUuid){
		CustomerModel cm = ics.getByUuid(customerUuid);
		model.addAttribute("cm", cm);
		return "customer/delete";
	}
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public String post(@RequestParam("uuid") int customerUuid){
		ics.delete(customerUuid);
		return "customer/success";
	}
	@RequestMapping(value={"toList",""},method=RequestMethod.GET)
	public String toList(@TableParam CustomerQueryModel qm,Model model){
        PageMyBatis<CustomerModel> pagedList = ics.getByPage(qm);
        model.addAttribute("pagedList", pagedList);
		return "customer/list";
	}
    @RequestMapping(value="toQuery",method=RequestMethod.GET)
    public String toQuery(){
        return "customer/query";
    }
}
