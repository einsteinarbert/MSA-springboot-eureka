package io.github.eureka.api.repo;

import io.github.eureka.api.model.Wallets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletsRepository extends JpaRepository<Wallets, Long> {
	Wallets findWalletsByWalletTypeIn(List<Integer> walletType);
	Wallets findWalletsByWalletTypeAndJewelType(Integer walletType, Integer jewelType);
	Wallets findWalletsByWalletType(Integer walletType);
}
