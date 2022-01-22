package com.ApiMongo.Controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.boot.json.GsonJsonParser;
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

import com.ApiMongo.Models.Login;
import com.ApiMongo.Models.ResponseObject;
import com.ApiMongo.Reponsi.ResponsiLogin;

@SpringBootApplication
@RestController 
@RequestMapping("/api/login")
public class LoginController {
	@Autowired
	private ResponsiLogin reqlg;
	
	@GetMapping("")
	public ResponseEntity<ResponseObject> GetAll(){
		List<Login> lg=reqlg.findAll();
		List<Login> res =lg.stream().filter(x -> 1 == x.getState()).toList();
		if(lg.size()>0) {
			 return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok","Thành Công",res));
		}
		else {
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("failed","Vui Lòng Kiểm TRA Thông Tin",""));
		}
	}
	@GetMapping("/getid")
	public ResponseEntity<ResponseObject> GetidLogin(@RequestParam(name="id") String id){
		List<Login> lg=reqlg.findAll();
		List<Login> res =lg.stream().filter(x -> 1 == x.getState() && id.equals(x.getId())).toList();
		if(res.toString().equals("[]")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("failed","Vui Lòng Kiểm TRA Thông Tin",""));
		}
		else {
			 return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok","Thành Công",res));	 
		}
	}
	@PostMapping("")
	public ResponseEntity<ResponseObject> CreateLogin(@RequestBody Login data){
		List<Login> lg=reqlg.findAll();
		if(data.getUsername() !="" || data.password !="") {
			List<Login> res =lg.stream().filter(x -> 1 == x.getState() && (data.getUsername()).toLowerCase().equals(x.getUsername().toLowerCase())).toList();
			if(res.toString().equals("[]")){
				data.setCreateAt(new Date(System.currentTimeMillis()));
				data.setUpdateAt(null);
				data.setState(1);
				reqlg.save(data);
				return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseObject("ok","Thêm Thành Công",""));	
			}
			else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("failed","Tên đã tồn: "+data.getUsername()+" tại vui lòng nhâp",""));
			}
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("failed","Vui Lòng Kiểm Tra Thông Tin Tên Và Mật Khẩu",""));
		}	 
	}
	@PutMapping("/{id}")
	public ResponseEntity<ResponseObject> UpdateLogin(@PathVariable("id")String id,@RequestBody Login data){
		Optional<Login> lg=reqlg.findById(id);
		lg=lg.filter(x -> 1 == x.getState());
//		Optional<Login> res =reqlg.stream().filter(x -> 1 == x.getState() && data.getId().equals(x.getId()));
		if(lg.equals("[]")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("failed","Vui Lòng Kiểm TRA Thông Tin",""));
		}
		else {
			Login dt=lg.get();
			dt.setUsername(data.getUsername() !=null? data.getUsername():dt.getUsername());
			dt.setPassword(data.getPassword() !=null ? data.getPassword():dt.getPassword());
			dt.setUpdateAt(new Date(System.currentTimeMillis()));
			dt.setCreateAt(data.getCreateAt()!=null ?data.getCreateAt():dt.getCreateAt());
			dt.setState(data.getState() != 0 ? data.getState():dt.getState());
			reqlg.save(dt);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok","Update Thành Công",""));	
		} 
	}
	@DeleteMapping("")
	public ResponseEntity<ResponseObject> DeleteLogin(@RequestParam(name="id") String id){
		System.out.println("giấ trị value "+id);
		 Login data =new Login();
		Optional<Login> lg=reqlg.findById(id);
		lg=lg.filter(x -> 1 == x.getState());
		System.out.println("giấ trị value "+lg);
		if(lg.isPresent()) {
			Login dt=lg.get();
			dt.setUsername(data.getUsername() !=null? data.getUsername():dt.getUsername());
			dt.setPassword(data.getPassword() !=null ? data.getPassword():dt.getPassword());
			dt.setUpdateAt(new Date(System.currentTimeMillis()));
			dt.setCreateAt(data.getCreateAt()!=null ?data.getCreateAt():dt.getCreateAt());
			dt.setState(0);
			reqlg.save(dt);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok","Xóa Thành Công",""));	
			
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("failed","Vui Lòng Kiểm Tra Thông Tin",""));
		} 
	}
	@GetMapping("/DoLogin")
	public ResponseEntity<ResponseObject> LoginDo(@RequestBody Login data){		
		List<Login> lg=reqlg.findAll();
		List<Login> res =lg.stream().filter(x -> 1 == x.getState() && data.getUsername().toLowerCase().equals(x.getUsername().toLowerCase()) && data.getPassword().toLowerCase().equals(x.getPassword().toLowerCase())).toList();
		
		if(res.toString().equals("[]")){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("failed","Vui Lòng Kiểm Tra Thông Tin",""));
		}
		else{
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok","Đăng nhập thành công",""));	
		}
	}
}
