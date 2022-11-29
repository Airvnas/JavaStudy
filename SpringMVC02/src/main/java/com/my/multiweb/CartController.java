package com.my.multiweb;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.model.CartVO;
import com.shop.service.ShopService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/user")
@Log4j
public class CartController {
	
	@Inject
	private ShopService shopService;
	
	@PostMapping("/cartAdd")
	public String addCart(
			@RequestParam(defaultValue="0") int pnum,
			@RequestParam(defaultValue="0") int oqty) {
		log.info("pnum===="+pnum+", oqty==="+oqty);
		if(pnum==0||oqty==0) {
			return "redirect:../index";
		}
		CartVO cvo=new CartVO();
		cvo.setPnum_fk(pnum);
		cvo.setOqty(oqty);
		//회원번호는 세션에서 로그인한 사람의 정보를 꺼내서 CartVO객체에 저장
		cvo.setIdx_fk(17);//임의의 회원번호 설정 admin
		int n=this.shopService.addCart(cvo);
		//장바구니에 상품 추가
		
		
		return "shop/cartList";
	}//-----------------------------
	
}////////////////////////////////////
