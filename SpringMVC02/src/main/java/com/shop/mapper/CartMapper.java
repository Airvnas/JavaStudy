package com.shop.mapper;

import java.util.List;

import com.shop.model.CartVO;

public interface CartMapper {
	
	Integer selectCartCountByPnum(CartVO cartVO);
	int updateCartQty(CartVO cartVO);
	int addCart(CartVO cartVO);
	
	List<CartVO> selectCartView(int idx_fk);
	int delCart(int cartNum);
	int editCart(CartVO cartVO);
	CartVO getCartTotal(int idx_fk);
	
	
}
