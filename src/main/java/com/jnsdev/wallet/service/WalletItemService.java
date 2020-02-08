package com.jnsdev.wallet.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.jnsdev.wallet.entity.WalletItem;
import com.jnsdev.wallet.util.enums.TypeEnum;

public interface WalletItemService {

	WalletItem save(WalletItem wi);

	Page<WalletItem> findBetweenDates(Long wallet, Date start, Date end, int page);

	List<WalletItem> findByWalletAndType(Long wallet, TypeEnum type);

	BigDecimal sumByWalletId(Long wallet);

	Optional<WalletItem> findById(Long id);
	
	void deleteById(Long id);

}
