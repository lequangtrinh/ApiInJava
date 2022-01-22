package com.ApiMongo.Controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ApiMongo.Models.Category;
import com.ApiMongo.Models.Login;
import com.ApiMongo.Models.ResponseObject;
import com.ApiMongo.Reponsi.ResponsiCate;
import com.ApiMongo.Reponsi.ResponsiLogin;

@SpringBootApplication
@RestController 
@RequestMapping("/api/category")
public class CategoryController {
	@Autowired
	private ResponsiCate reqcate;
	@GetMapping("")
	public ResponseEntity<ResponseObject> GetAll(){
		List<Category> Category=reqcate.findAll();
		List<Category> res =Category.stream().filter(x -> 1 == x.getState()).toList();
		if(Category.size()>0) {
			
			 return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok","Thành Công",res));
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("failed","Vui Lòng Kiểm Tra Thông Tin",""));
		}
	}
	@GetMapping("/getid")
	public ResponseEntity<ResponseObject> GetidCategory(@RequestParam(name="id") String id){
		List<Category> lg=reqcate.findAll();
		List<Category> res =lg.stream().filter(x -> 1 == x.getState() && id.equals(x.getId())).toList();
		if(res.toString().equals("[]")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("failed","Vui Lòng Kiểm TRA Thông Tin",""));
		}
			
		else {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok","Thành Công",res)); 
		}
	}
	@PostMapping("")
	public ResponseEntity<ResponseObject> CreateCategory(@RequestBody Category data){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseObject("ok","Thêm Thành Công",""));
		 
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> UpdateCategory(@PathVariable("id")String id,@RequestBody Category data){
		
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok","Update Thành Công",""));	
		
	}
	@DeleteMapping("")
	public ResponseEntity<ResponseObject> DeleteCategory(@RequestParam(name="id") String id,@RequestBody Category data){
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok","Xóa Thành Công",""));	
	}
}
