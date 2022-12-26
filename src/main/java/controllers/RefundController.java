package controllers;

import java.util.LinkedList;

import java.util.List;

import Database.*;
import Transactions.*;
import Users.*;

public class RefundController {
	private static TransactionsLog Translog;
	private static RefundRequestsLog refunds;

	public RefundController() {
		Translog = TransactionsLog.getLog();
		refunds = RefundRequestsLog.getRefundLog();
	}

	public List<Transaction> addRequest(GeneralUser user) {
		List<Transaction> transactions = Translog.listUserTrans(user);
		List<Transaction> res = new LinkedList<>();
		for (Transaction trans : transactions) {
			if (trans instanceof RefundTransaction || trans.isRefunded() || trans.isRefundRequested())
				continue;

			res.add(trans);
		}
		return res;
	}

	public void refundTrans(Transaction trans) {
		refunds.addRequest(trans);
	}
}