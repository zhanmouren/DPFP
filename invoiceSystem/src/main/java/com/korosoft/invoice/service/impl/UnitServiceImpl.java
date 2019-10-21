package com.korosoft.invoice.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.korosoft.invoice.bean.UnitDataBean;
import com.korosoft.invoice.bean.UnitUserBean;
import com.korosoft.invoice.mapper.master.*;
import com.korosoft.invoice.mapper.second.*;
import com.korosoft.invoice.service.UnitService;
import com.korosoft.invoice.vo.ResponseData;

@Service
public class UnitServiceImpl implements UnitService {
    @Autowired
    private UnitMapper UnitMapper;
    
    @Autowired
    private UserMapper UserMapper;

	@Override
	public List<UnitDataBean> findAllUnit() {		
		return UnitMapper.findAllUnit();
	}

	@Override
	public List<UnitDataBean> findUnitByName(String name) {
		return UnitMapper.findUnitByName(name);
	}

	@Override
	public UnitDataBean findById(int id) {
		return UnitMapper.findById(id);
	}

	@Override
	public ResponseData ImportUnit(String filePath) {
		try {
            String encoding="GBK";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                	String txtData[] = lineTxt.split("~~");
                	boolean tem = UnitMapper.importUnit(txtData[1],txtData[3],txtData[4],txtData[5]);
                	System.out.println(tem);
                    System.out.println(lineTxt);
                }
                read.close();
        		return ResponseData.success("导入成功！");
             
        }else{
        System.out.println("找不到指定的文件");    
        return ResponseData.success("找不到指定的文件");
       }
     } catch (Exception e) {
        System.out.println("读取文件内容出错");
        e.printStackTrace();
        return ResponseData.success("读取文件内容出错");
      }
		
	}

	@Override
	public ResponseData UnitAdd(UnitDataBean data) {  
		boolean type=UnitMapper.UnitAdd(data);
		if(type==false) {
			return ResponseData.faill("增加单位资料出错！");
		}else {
			return ResponseData.success("增加单位资料成功！");
		}
		
	}

	@Override
	public ResponseData deleteUnit(int[] id) {

	try {
		UnitMapper.deleteUnit(id);
		return ResponseData.success("删除单位资料成功！");
	   
		} catch (Exception e) {
		  return ResponseData.faill("删除单位资料出错！");
		}
		
	}

	@Override
	public ResponseData updateUnit(UnitDataBean data) {
		boolean type=UnitMapper.updateUnit(data);
		if(type==false) {
			return ResponseData.faill("修改单位资料出错！");
		}else {
			return ResponseData.success("修改单位资料成功！");
		}
	}

	@Override
	public List<UnitUserBean> findUnitUserById(int id) {
		return UnitMapper.findUnitUserById(id);
	}

	@Override
	public List<UnitUserBean>  findUnitUserByCtmNum(String ctmnum) {
				
		 List<UnitUserBean> data =UnitMapper.findUnitUserByCtmNum(ctmnum);
		 if(data==null||data.size()==0) {
			 System.out.println("单位资料里面没有找到该用户！"+ctmnum);	
			 List<UnitUserBean> userlist = UserMapper.findUserByCtmNum(ctmnum);
			 if(userlist==null||userlist.size()==0) {
				 System.out.println("普通用户里面没有找到该用户！"+ctmnum);	
				 return null;
			 }else {
				 return userlist;
			 }	 
			 
		 }else {
			 return data;
		 }
		
	}

	@Override
	public ResponseData addUser(int id, String ctmnum) {
		//1.检查该用户是否被其他资料使用
		//2.检查该用户是否存在在该单位资料底下
		int unit_id =UnitMapper.checkUser(ctmnum);
		if(unit_id==id) {
			return ResponseData.faill("已存在此用户!");
		}else if(unit_id>0){
			return ResponseData.faill("此用户已经在其他单位资料下面，不能添加!");
		}else {
			//添加用户至该单位
			boolean type = UnitMapper.addUser(id,ctmnum);	
			if(type==true) {
				return ResponseData.success("用户添加成功!");
			}else {
				return ResponseData.faill("用户添加失败!");
			}
	    }
		
		
		
	
	}

	@Override
	public ResponseData updateUser(int[] ctmnum) {
		try {
			UnitMapper.updateUser(ctmnum);
			return ResponseData.success("移除用户成功！");
		   
			} catch (Exception e) {
			  return ResponseData.faill("移除用户出错！");
			}
	}



}