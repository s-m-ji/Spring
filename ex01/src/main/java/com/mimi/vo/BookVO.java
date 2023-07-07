package com.mimi.vo;

import lombok.Data;

@Data
public class BookVO {
	private int idx;		// 도서 일련번호
	private String title;	// 도서명
	private String writer;	// 작가
	private String publisher;	// 출판사
	private String post_date;	
	private String ofile;	
	private String sfile;	
	private String id;	// 대출자 아이디
	private String rent_yn;	// 도서 대여여부
	private String rent_str;	// 도서 대여여부 출력용 !!! 화면에서 y를 대출중으로 나오게 처리
	private String rent_no;	// 도서 대여번호
	private String rent_date;	// 도서 대여일 startDate
	private String return_exp_date;	// 반납예정일 endDate
	private String return_date;	// 반납일 returnDate
	private String rent_count;	// 도서 대출 횟수
	private String review;	// 도서 리뷰
	private int rest_date;	// 잔여일
	private String basket ; // 책 바구니
	private String reserve_no; // 도서 예약번호
	private String no_rent; // 도서 대출 정지 기간
	
	// rentStr 대신에 이렇게 써봄 ! 
	// 이렇게 해도 되지만 추후 rent_yn의 y,n 값을 활용할 경우도 있기에 분기해서 쓰는게 낫다고 하심..
	// 예시) 체크박스로 y의 값을 받아옴 등등
	/*
	public void setRent_yn(String rent_yn) {
		this.rent_yn = rent_yn;
		if("y".equalsIgnoreCase(rent_yn)) {
			this.rent_yn = "대출중";
		} else {
			this.rent_yn = "";
		}
	}
	*/
}
