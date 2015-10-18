package com.asu.cse545.group12.services;

import java.util.List;

import com.asu.cse545.group12.domain.Transfer;
import com.asu.cse545.group12.domain.UserPII;
import com.asu.cse545.group12.domain.Users;


public interface TransferService {

	
	public int insertRow(Transfer transfer);
	public Transfer getTransferByTransferId(int transferId);
	public int updateRow(Transfer transfer);
}
