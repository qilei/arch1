package me.lei.arch1.customermgr.web;

import me.lei.arch1.customermgr.service.ICustomerService;
import me.lei.arch1.customermgr.vo.CustomerModel;
import me.lei.arch1.customermgr.vo.CustomerQueryModel;
import me.lei.pagination.dto.PageMyBatis;
import me.lei.pagination.dto.datatables.PagingCriteria;
import me.lei.util.format.DateFormatHelper;
import me.lei.util.json.JsonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
	public String toList(@ModelAttribute("wm")CustomerWebModel wm,Model model){

        PagingCriteria baseCriteria = PagingCriteria.createCriteria(0, 10, 1);
        CustomerQueryModel qm = new CustomerQueryModel(baseCriteria);
        PageMyBatis<CustomerModel> pagedList = ics.getByPage(qm);

        model.addAttribute("pagedList", pagedList);

//		CustomerQueryModel cqm = null;
//		if(wm.getQueryJsonStr()==null || wm.getQueryJsonStr().trim().length()==0){
//			cqm =  new CustomerQueryModel();
//		}else{
//			cqm = (CustomerQueryModel) JsonHelper.str2Object(wm.getQueryJsonStr(), CustomerQueryModel.class);
//		}
//
//		cqm.getPage().setNowPage(wm.getNowPage());
//		if(wm.getPageShow() > 0){
//			cqm.getPage().setPageShow(wm.getPageShow());
//		}
//
//		Page dbPage = ics.getByConditionPage(cqm);
//
//		//
//		model.addAttribute("wm", wm);
//		model.addAttribute("page", dbPage);
				
		return "customer/list";
	}
}
