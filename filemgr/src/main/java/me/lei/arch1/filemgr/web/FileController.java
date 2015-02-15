package me.lei.arch1.filemgr.web;

import me.lei.arch1.filemgr.service.IFileService;
import me.lei.arch1.filemgr.vo.FileModel;
import me.lei.arch1.filemgr.vo.FileQueryModel;
import me.lei.pagination.dto.PageMyBatis;
import me.lei.pagination.mvc.TableParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value="/file")
public class FileController {
	@Autowired
	private IFileService iservice = null;

	@RequestMapping(value="toAdd",method=RequestMethod.GET)
	public String toAdd(){
		
		return "file/add";
	}
	@RequestMapping(value="add",method=RequestMethod.POST)
	public String add(@ModelAttribute("m") FileModel m){
		iservice.create(m);
		return "file/success";
	}
	@RequestMapping(value="toUpdate/{uuid}",method=RequestMethod.GET)
	public String toUpdate(Model model,@PathVariable("uuid") int uuid){
		FileModel m = iservice.getByUuid(uuid);
		model.addAttribute("m", m);
		return "file/update";
	}
	@RequestMapping(value="update",method=RequestMethod.POST)
	public String post(@ModelAttribute("m") FileModel m){
		iservice.update(m);
		return "file/success";
	}
	@RequestMapping(value="toDelete/{uuid}",method=RequestMethod.GET)
	public String toDelete(Model model,@PathVariable("uuid") int uuid){
		FileModel m = iservice.getByUuid(uuid);
		model.addAttribute("m", m);
		return "file/delete";
	}
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public String post(@RequestParam("uuid") int uuid){
		iservice.delete(uuid);
		return "file/success";
	}
	@RequestMapping(value={"toList",""},method=RequestMethod.GET)
    public String toList(@TableParam FileQueryModel qm,Model model){
        PageMyBatis<FileModel> pagedList = iservice.getByPage(qm);
        model.addAttribute("pagedList", pagedList);

        return "file/list";
	}
	@RequestMapping(value="toQuery",method=RequestMethod.GET)
	public String toQuery(){
		return "file/query";
	}	
}
