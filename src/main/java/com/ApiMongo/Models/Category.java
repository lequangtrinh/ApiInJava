package com.ApiMongo.Models;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="categos")
public class Category {
	public String id;
	public  String masp;
	public String tensp;
	public Date createAt;
	public Date updateAt;
	public int state;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMasp() {
		return masp;
	}
	public void setMasp(String masp) {
		this.masp = masp;
	}
	public String getTensp() {
		return tensp;
	}
	public void setTensp(String tensp) {
		this.tensp = tensp;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	public Date getUpdateAt() {
		return updateAt;
	}
	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	
}
