package com.asu.cse545.group12.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.asu.cse545.group12.constantfile.Const;
import com.asu.cse545.group12.controller.LoginController;
import com.asu.cse545.group12.domain.AccessControl;
import com.asu.cse545.group12.domain.Account;
import com.asu.cse545.group12.domain.Authorization;
import com.asu.cse545.group12.domain.Users;

@Component
public class AuthorizationDaoImpl implements AuthorizationDao {

	private static final Logger logger = Logger.getLogger(AuthorizationDaoImpl.class);

	@Autowired
	SessionFactory sessionfactory;


	//*************************************************************************************************
	//              IN all this methods the Authorization ID will be the input , 
	//										Update the Flags based on the Authorization ID
	//*************************************************************************************************

	@Override
	@Transactional
	public int insertRow(Authorization authorization) {
		// TODO Auto-generated method stub
		Session session = sessionfactory.openSession(); 
		Transaction tx = session.beginTransaction();  
		//*************************++**************************************************
		//      DATA NEEDED TO INSERT THE AUTHORIZATION TABLE ENTRIES
		//*****************************************************************************
		//user.setUserStatus("InActive");
		session.saveOrUpdate(authorization);  
		//session.saveOrUpdate(user.getuserpii()); 
		tx.commit();  
		Serializable authid = session.getIdentifier(authorization);  
		session.close();  
		return (Integer) authid; 
	}

	@Override
	public int approve(Authorization authorization)
	{
		Session session = sessionfactory.openSession(); 
		Transaction tx = session.beginTransaction();  
		//authorization.setApprovalFlag("yes");
		authorization.setRequestStatus(Const.APPROVED);
		session.saveOrUpdate(authorization);  
		tx.commit();  
		Serializable authid = session.getIdentifier(authorization);  
		session.close();  
		return (Integer) authid;		
	}
	//public List<Users> getList();

	@Override
	public int reject(Authorization authorization)
	{
		Session session = sessionfactory.openSession(); 
		Transaction tx = session.beginTransaction();  
		//authorization.setApprovalFlag("no");
		authorization.setRequestStatus(Const.REJECT);
		session.saveOrUpdate(authorization);  
		tx.commit();  
		Serializable authid = session.getIdentifier(authorization);  
		session.close();  
		return (Integer) authid;		
	}

	@Override
	public int forward(Authorization authorization)
	{
		Session session = sessionfactory.openSession(); 
		Transaction tx = session.beginTransaction();  
		//authorization.set("yes");
		authorization.setRequestStatus(Const.FORWARD);
		session.saveOrUpdate(authorization);  
		tx.commit();  
		Serializable authid = session.getIdentifier(authorization);  
		session.close();  
		return (Integer) authid;		
	}

	//	@Override
	//	public List<Authorization> getNotifications() 
	//	{
	//		String whereClause = "from authorization where request_status='pending' or request_status= 'forward'";
	//		List<Authorization> pendingEntries = new ArrayList<Authorization>();
	//		Session session = sessionfactory.openSession(); 
	//		Query query = session.createQuery(whereClause);
	//		
	//		pendingEntries = query.list();
	//		
	//		logger.debug("PENDING ENTRIES SIZE IS:" + pendingEntries.size());
	//		
	//		return pendingEntries;
	//		
	//	}

	@Override
	public List<Authorization> getNotificationsForExternal(Users user) 
	{
		String req_status=Const.PENDING;
		//CAN SEE ONLY THE REQUESTS WHICH HE RAISED ---- ALL THE REQUESTS RAISED TO HIM ONLY --- ONLY PENDING
		String whereClause = "from authorization where authorized_by_userid='"+ user.getUserId()+"' and request_status=:req_status";
		logger.debug("Ext User Notif Where clause:" + whereClause);
		List<Authorization> pendingEntries = new ArrayList<Authorization>();
		Session session = sessionfactory.openSession(); 
		Query query = session.createQuery(whereClause);
		query.setParameter("req_status", req_status);
		pendingEntries = query.list();
		logger.debug("PENDING ENTRIES SIZE IS:" + pendingEntries.size());
		return pendingEntries;
	}

	@Override
	public List<Authorization> getNotificationsForMerchant(Users user) 
	{
		String req_status=Const.PENDING;
		//CAN SEE ONLY THE REQUESTS WHICH HE RAISED ---- ALL THE REQUESTS RAISED TO HIM ONLY --- ONLY PENDING
		String whereClause = "from authorization where authorized_by_userid='"+ user.getUserId()+"' and request_status=:req_status";
		logger.debug("Ext User Notif Where clause:" + whereClause);
		List<Authorization> pendingEntries = new ArrayList<Authorization>();
		Session session = sessionfactory.openSession(); 
		Query query = session.createQuery(whereClause);
		query.setParameter("req_status", req_status);
		pendingEntries = query.list();
		logger.debug("PENDING ENTRIES SIZE IS:" + pendingEntries.size());
		return pendingEntries;
	}

	@Override
	public List<Authorization> getNotificationsForRegular(Users user)
	{
		String req_status=Const.PENDING;
		String req_type =Const.SIGNUP_REQUEST;
		String role = Const.REGULARUSER;
		//String whereClause = "from authorization where authorized_to_userid=" + user.getUserId() + " or (request_status=:req_status and assigned_to_role = 3)";//(select roleId from role where roledescription like :role))";
		logger.debug("*****************************PENDING ENTRIES SIZE IS:trace");
		String whereClause ="select a.* from authorization a,accesscontrol ac where a.AUTHORIZED_TO_USERID=ac.USERID and a.request_status='Pending' and (  (ac.VIEWTRANSACTION=3 and a.REQUEST_TYPE in( 'Credit','Debit','Transfer' )  )  or (ac.MODIFYTRANSACTION=3 and a.REQUEST_TYPE in( 'Credit','Debit','Transfer' ) ) or (ac.CANCELTRANSACTION=3 and a.REQUEST_TYPE in( 'Credit','Debit','Transfer' )  ) or (ac.VIEWPROFILE=3 and a.REQUEST_TYPE='View Profile' ) or (ac.MODIFYEXTERNALUSER=3 and a.REQUEST_TYPE='Modify Profile' ) or (ac.DELETEEXTERNALUSER=3 and a.REQUEST_TYPE='Delete User' ))";
		//		String whereClause = "from authorization where request_status like 'Pending' and request_type not like 'Signup'";
		logger.debug("Regular User Notif Where clause:" + whereClause);
		List<Authorization> pendingEntries = new ArrayList<Authorization>();
		Session session = sessionfactory.openSession();
		SQLQuery query = session.createSQLQuery(whereClause);
		query.addEntity("authorization", Authorization.class);
		//query.setParameter("req_status", req_status);
		//		query.setParameter("req_type",req_type);
		//		query.setParameter("role", role);
		pendingEntries = query.list();
		logger.debug("PENDING ENTRIES SIZE IS:" + pendingEntries.size());
		return pendingEntries;
	}

	public List<Authorization> getNotificationsForManager(Users user)
	{
		String req_type1=Const.PII_ACCESS;
		String whereClause = "from authorization where  (assigned_to_role = 4 and REQUEST_STATUS ='"+Const.PENDING+"')  and request_type not like :req_type1";
		logger.debug("Manager User Notif Where clause:" + whereClause);
		List<Authorization> pendingEntries = new ArrayList<Authorization>();
		Session session = sessionfactory.openSession(); 
		Query query = session.createQuery(whereClause);
		query.setParameter("req_type1", req_type1);
		pendingEntries = query.list();
		logger.debug("PENDING ENTRIES SIZE IS:" + pendingEntries.size());
		return pendingEntries;
	}

	public List<Authorization> getNotificationsForAdmin(Users user)
	{
		String role=Const.ADMIN;
		//		String whereClause = "from authorization where request_type like 'Pii Access'";
		//only show authorization assigned to admin and which are pending
		String whereClause = "from authorization where assigned_to_role = 5 and REQUEST_STATUS ='"+Const.PENDING+"'";//(select roleid from role where roledescription like :role)";
		logger.debug("Admin User Notif Where clause:" + whereClause);
		List<Authorization> pendingEntries = new ArrayList<Authorization>();
		Session session = sessionfactory.openSession(); 
		Query query = session.createQuery(whereClause);
		//query.setParameter("role", role);
		pendingEntries = query.list();
		logger.debug("PENDING ENTRIES SIZE IS:" + pendingEntries.size());
		return pendingEntries;
	}

	@Override
	public Authorization getRowById(int authorizationId) {
		// TODO Auto-generated method stub
		Session session = sessionfactory.openSession();
		Authorization authorization= session.load(Authorization.class, authorizationId);
		return authorization;

	}


	@Override
	public Authorization getAuthorizationByAuthorizationId(int authorizationId){
		Session session = sessionfactory.openSession();
		Query query = session.createQuery("from authorization where authorizationid =:authorizationId ");
		query.setParameter("authorizationId", authorizationId);
		List results = query.list();
		if(logger.isDebugEnabled()){
			logger.debug("Authorization by authorizationId: "+authorizationId);
		}
		if(logger.isDebugEnabled()){
			logger.debug("Authorization by authorizationId: "+results);
		}
		session.close();
		if(results.size()==1){
			return (Authorization)results.get(0);
		} else {
			return null;
		}
	}
	//public int deleteRow(int AccountId);

	@Override
	public Authorization getAuthorizationByTransactionId(int transactionId) {

		Session session = sessionfactory.openSession();
		Query query = session.createQuery("from authorization where TRANSACTION_ID =:transactionId ");
		query.setParameter("transactionId", transactionId);
		List results = query.list();
		if(logger.isDebugEnabled()){
			logger.debug("Authorization by transactionId: "+transactionId);
		}
		if(logger.isDebugEnabled()){
			logger.debug("Authorization by transactionId: "+results);
		}
		session.close();
		if(results.size()==1){
			return (Authorization)results.get(0);
		} else {
			return null;
		}
	}

	@Override
	@Transactional
	public int updateRow(Authorization authorization) {
		// TODO Auto-generated method stub
		Session session = sessionfactory.openSession(); 
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(authorization);
		tx.commit();
		return authorization.getAuthorizationId();
	}

	@Override
	public List<Authorization> getAuthorizednotifications(int fromuser,
			int touser, String reqType, String requestStatus ) {
		Session session = sessionfactory.openSession(); 

		Query query= session.createQuery("from authorization where AUTHORIZED_BY_USERID=:touser and AUTHORIZED_TO_USERID=:fromuser  and REQUEST_TYPE=:reqType and REQUEST_STATUS=:requestStatus");
		query.setParameter("fromuser", fromuser);
		query.setParameter("touser", touser);
		query.setParameter("requestStatus", requestStatus);
		query.setParameter("reqType", reqType);
		List<Authorization> authlist = query.list();
		
		return authlist;
	}

	@Override
	public List<Authorization> getApprovedPendingNotificationsForManager(Users user) {
		String req_type1=Const.PII_ACCESS;
		//authorization only created by manager and approved by manager
		String whereClause = "from authorization where (authorized_to_userid=" + user.getUserId() +")or (assigned_to_role = 4 and REQUEST_STATUS ='"+Const.APPROVED+"')  and request_type not like :req_type1";
		logger.debug("Manager User Notif Where clause:" + whereClause);
		List<Authorization> pendingEntries = new ArrayList<Authorization>();
		Session session = sessionfactory.openSession(); 
		Query query = session.createQuery(whereClause);
		query.setParameter("req_type1", req_type1);
		pendingEntries = query.list();
		logger.debug("PENDING ENTRIES SIZE IS:" + pendingEntries.size());
		return pendingEntries;
	}

	@Override
	public List<Authorization> getApprovedPendingNotificationsForAdmin(Users user) {
		String role=Const.ADMIN;
		//only show authorization created by admin and approved by admin
		String whereClause = "from authorization where (authorized_to_userid=" + user.getUserId() +") or (assigned_to_role = 5 and REQUEST_STATUS ='"+Const.APPROVED+"')";//(select roleid from role where roledescription like :role)";
		logger.debug("Admin User Notif Where clause:" + whereClause);
		List<Authorization> pendingEntries = new ArrayList<Authorization>();
		Session session = sessionfactory.openSession(); 
		Query query = session.createQuery(whereClause);
		//query.setParameter("role", role);
		pendingEntries = query.list();
		logger.debug("PENDING ENTRIES SIZE IS:" + pendingEntries.size());
		return pendingEntries;
	}

	@Override
	public List<Authorization> getApprovedPendingNotificationsForRegular(Users user) {

		String req_status=Const.APPROVED;
		//CAN SEE ONLY THE REQUESTS WHICH HE RAISED (PENDING or APPROVED) ---- ALL THE REQUESTS RAISED TO HIM ONLY (APPROVED ONLY)
		String whereClause = "from authorization where authorized_to_userid='"+ user.getUserId()+"' or (authorized_by_userid='"+ user.getUserId()+"' and request_status=:req_status)";
		logger.debug("Ext User Notif Where clause:" + whereClause);
		List<Authorization> pendingEntries = new ArrayList<Authorization>();
		Session session = sessionfactory.openSession(); 
		Query query = session.createQuery(whereClause);
		query.setParameter("req_status", req_status);
		pendingEntries = query.list();
		logger.debug("PENDING ENTRIES SIZE IS:" + pendingEntries.size());
		return pendingEntries;
	}

	@Override
	public List<Authorization> getApprovedPendingNotificationsForMerchant(Users user) {
		String req_status=Const.APPROVED;
		//CAN SEE ONLY THE REQUESTS WHICH HE RAISED (PENDING or APPROVED) ---- ALL THE REQUESTS RAISED TO HIM ONLY (APPROVED ONLY)
		String whereClause = "from authorization where authorized_to_userid='"+ user.getUserId()+"' or (authorized_by_userid='"+ user.getUserId()+"' and request_status=:req_status)";
		logger.debug("Ext User Notif Where clause:" + whereClause);
		List<Authorization> pendingEntries = new ArrayList<Authorization>();
		Session session = sessionfactory.openSession(); 
		Query query = session.createQuery(whereClause);
		query.setParameter("req_status", req_status);
		pendingEntries = query.list();
		logger.debug("PENDING ENTRIES SIZE IS:" + pendingEntries.size());
		return pendingEntries;
	}

	@Override
	public List<Authorization> getApprovedPendingNotificationsForExternal(Users user) {
		String req_status=Const.APPROVED;
		//CAN SEE ONLY THE REQUESTS WHICH HE RAISED (PENDING or APPROVED) ---- ALL THE REQUESTS RAISED TO HIM ONLY (APPROVED ONLY)
		String whereClause = "from authorization where authorized_to_userid='"+ user.getUserId()+"' or (authorized_by_userid='"+ user.getUserId()+"' and request_status=:req_status)";
		logger.debug("Ext User Notif Where clause:" + whereClause);
		List<Authorization> pendingEntries = new ArrayList<Authorization>();
		Session session = sessionfactory.openSession(); 
		Query query = session.createQuery(whereClause);
		query.setParameter("req_status", req_status);
		pendingEntries = query.list();
		logger.debug("PENDING ENTRIES SIZE IS:" + pendingEntries.size());
		return pendingEntries;
	}
	
	
	@Override
	public List<AccessControl> getAccessControlToView(int touser, int roleID ) {
		Session session = sessionfactory.openSession(); 

		Query query= session.createQuery(" from accesscontrol where USERID=:touser and VIEWPROFILE=:roleID");
		query.setParameter("touser", touser);
		query.setParameter("roleID", roleID);
		List<AccessControl> authlist = query.list();
		
		return authlist;
	}
	
	@Override
	public List<AccessControl> getAccessControlToModify(int touser, int roleID ) {
		Session session = sessionfactory.openSession(); 

		Query query= session.createQuery(" from accesscontrol where USERID=:touser and MODIFYEXTERNALUSER=:roleID");
		query.setParameter("touser", touser);
		query.setParameter("roleID", roleID);
		List<AccessControl> authlist = query.list();
		
		return authlist;
	}
	
	@Override
	public List<AccessControl> getAccessControlToDelete(int touser, int roleID ) {
		Session session = sessionfactory.openSession(); 

		Query query= session.createQuery(" from accesscontrol where USERID=:touser and DELETEEXTERNALUSER=:roleID");
		query.setParameter("touser", touser);
		query.setParameter("roleID", roleID);
		List<AccessControl> authlist = query.list();
		
		return authlist;
	}
	
	public List<AccessControl> getAccessControlToViewTransaction(int touser, int roleID ) {
		Session session = sessionfactory.openSession(); 

		Query query= session.createQuery(" from accesscontrol where USERID=:touser and VIEWTRANSACTION=:roleID");
		query.setParameter("touser", touser);
		query.setParameter("roleID", roleID);
		List<AccessControl> authlist = query.list();
		
		return authlist;
	}
	
	public List<AccessControl> getAccessControlToModifyTransaction(int touser, int roleID ) {
		Session session = sessionfactory.openSession(); 

		Query query= session.createQuery(" from accesscontrol where USERID=:touser and MODIFYTRANSACTION=:roleID");
		query.setParameter("touser", touser);
		query.setParameter("roleID", roleID);
		List<AccessControl> authlist = query.list();
		
		return authlist;
	}
	
	public List<AccessControl> getAccessControlToDeleteTransaction(int touser, int roleID ) {
		Session session = sessionfactory.openSession(); 

		Query query= session.createQuery(" from accesscontrol where USERID=:touser and CANCELTRANSACTION=:roleID");
		query.setParameter("touser", touser);
		query.setParameter("roleID", roleID);
		List<AccessControl> authlist = query.list();
		
		return authlist;
	}



}
