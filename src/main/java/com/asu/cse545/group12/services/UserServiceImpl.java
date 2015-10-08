package com.asu.cse545.group12.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asu.cse545.group12.dao.UserDao;
import com.asu.cse545.group12.domain.Users;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService {
 
 @Autowired
 UserDao userDao;
 
 
 
 //public void setUserDao(UserDao userDao) {
//	this.userDao = userDao;
//}



@Override
 public int insertRow(Users user) {
  return userDao.insertRow(user);
 }

/* @Override
 public List<User> getList() {
  return userDao.getList();
 }

 @Override
 public User getRowById(int id) {
  return userDao.getRowById(id);
 }

 @Override
 public int updateRow(User user) {
  return userDao.updateRow(user);
 }

 @Override
 public int deleteRow(int id) {
  return userDao.deleteRow(id);
 }
*/
}

