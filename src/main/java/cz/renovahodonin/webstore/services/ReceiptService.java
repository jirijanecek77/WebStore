package cz.renovahodonin.webstore.services;

import cz.renovahodonin.webstore.model.Receipt;

public interface ReceiptService
{
	Receipt findById(Long storeId, Long receiptId);

	Receipt save(Receipt receipt);

	void delete(Long storeId, Long receiptId);
}
