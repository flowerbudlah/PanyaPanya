package com.tjoeun.spring.dto;

import lombok.Getter;

@Getter
public class PageDTO {
  
	private int min; // μ΅μ ??΄μ§? λ²νΈ
	private int max; // μ΅λ? ??΄μ§? λ²νΈ
	
	private int prePage; // ?΄?  λ²νΌ ?λ₯΄λ©΄ ?΄??? ??΄μ§? λ²νΈ
	private int nextPage;   // ?€? λ²νΌ ?λ₯΄λ©΄ ?΄??? ??΄μ§? λ²νΈ
	
	private int pageCount; 	// ? μ²? ??΄μ§? κ°μ
	
	private int currentPage; // ??¬ ??΄μ§? λ²νΈ
	
	/*
	  ? μ²? κ²μκΈ?? κ°μ, ??΄μ§??Ή κ²μκΈ?? κ°μ, ??¬ ??΄μ§? λ²νΈ --> ?΄ κ°λ€? κ°?μ§?κ³? ?? κ°λ€? κ³μ°?¨
	    
	  ?΄ ???? ??±??? ?¨
	  ??±?? λ§€κ°λ³??λ‘?
	  int postCnt     : ? μ²? κ²μκΈ?? κ°μ
	  int currentPage    : ??¬ ??΄μ§? λ²νΈ
	  int postPageCnt : ??΄μ§??Ή κ²μκΈ?? κ°μ
	  int paginationCnt  : ??΄μ§? λ²νΌ? κ°μ
	  λ₯? ? ?Έ?¨   
	*/
	// contentCnt    : database ?? κ°?? Έ?΄ (BoardMapper)
	// currentPage   : page parameterλ‘? ? ?¬?¨
	// contentPageCnt, paginationCnt : option.properties ? ?€? ?¨
	public PageDTO(int contentCnt, int currentPage, int contentPageCnt, int paginationCnt) {
		
		this.currentPage = currentPage; // ??¬ ??΄μ§? λ²νΈ
		
		pageCount = contentCnt / contentPageCnt; //? μ²? ??΄μ§? κ°μ = ? μ²? κΈ?κ°μ / ??΄μ§??Ή κΈ?κ°μ
		
		if(contentCnt % contentPageCnt > 0) { pageCount++; }
	  

		min = ((currentPage - 1) / contentPageCnt) * contentPageCnt + 1;
	  
		max = min + paginationCnt - 1;  //  μ΅λ???΄μ§? λ²νΈ = μ΅μ??΄μ§? λ²νΈ + ??΄μ§? λ²νΌ? κ°μ - 1
    
		if(max > pageCount) { max = pageCount;}
		prePage = min - 1;
		nextPage = max + 1;
    
		if(nextPage > pageCount) { nextPage = pageCount; } //?€???΄μ§?λ²νΌ λ²νΈκ°? ? μ²΄ν?΄μ§? κ°μλ₯? ??΄κ°?μ§? ??λ‘? ?¨
    
    
	}
	
	
}




