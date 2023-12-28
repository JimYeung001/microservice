package com.eazy.core.mapper;

import com.eazy.core.dto.LoansDto;
import com.eazy.core.entities.loans.Loans;

public class LoansMapper {

	public static LoansDto mapToLoansDto(Loans loans, LoansDto loansDto) {
		loansDto.setMobileNumber(loans.getMobileNumber());
		loansDto.setLoanNumber(loans.getLoanNumber());
		loansDto.setLoanType(loans.getLoanType());
		loansDto.setAmountPaid(loans.getAmountPaid());
		loansDto.setOutstandingAmount(loans.getOutstandingAmount());
		loansDto.setTotalLoan(loans.getTotalLoan());
		return loansDto;
	}

	public static Loans mapToLoans(LoansDto loansDto, Loans loans) {
		loans.setMobileNumber(loansDto.getMobileNumber());
		loans.setLoanNumber(loansDto.getLoanNumber());
		loans.setLoanType(loansDto.getLoanType());
		loans.setAmountPaid(loansDto.getAmountPaid());
		loans.setOutstandingAmount(loansDto.getOutstandingAmount());
		loans.setTotalLoan(loansDto.getTotalLoan());
		return loans;
	}

}
