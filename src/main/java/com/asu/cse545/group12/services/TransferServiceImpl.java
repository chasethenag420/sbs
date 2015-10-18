package com.asu.cse545.group12.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asu.cse545.group12.dao.TransferDao;
import com.asu.cse545.group12.dao.UserDao;
import com.asu.cse545.group12.domain.Transfer;
import com.asu.cse545.group12.domain.Users;

@Service("TransferServiceImpl")
public class TransferServiceImpl implements TransferService {

	@Autowired
	TransferDao transferDao;
	
	@Override
	public int insertRow(Transfer transfer) {
		
		return transferDao.insertRow(transfer);
	}

	@Override
	public Transfer getTransferByTransferId(int transferId) {
		return transferDao.getTransferByTransferId(transferId);
	}

	@Override
	public int updateRow(Transfer transfer) {
		
		return transferDao.updateRow(transfer);
	}
}
