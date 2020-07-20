package org.npathai;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SalarySlip {
    private final Employee employee;
    private final BigDecimal monthlyGrossSalary;
    private NationalInsurance nationalInsurance;
    private TaxInfo taxInfo;

    public SalarySlip(Employee employee, NationalInsurance nationalInsurance, TaxInfo taxInfo) {
        this.employee = employee;
        this.monthlyGrossSalary = calculateMonthlyGrossSalary();
        this.nationalInsurance = nationalInsurance;
        this.taxInfo = taxInfo;
    }

    private BigDecimal calculateMonthlyGrossSalary() {
        return toMonthly(employee.annualSalary().setScale(2));
    }

    public BigDecimal monthlyGrossSalary() {
        return monthlyGrossSalary;
    }

    public BigDecimal nationalInsuranceContribution() {
        return toMonthly(nationalInsurance.contribution());
    }

    public BigDecimal taxFreeAllowance() {
        return toMonthly(taxInfo.taxFreeAllowance());
    }

    public BigDecimal taxableIncome() {
        return toMonthly(taxInfo.taxableIncome());
    }

    public BigDecimal taxPayable() {
        return toMonthly(taxInfo.taxPayable());
    }

    private BigDecimal toMonthly(BigDecimal taxableIncome) {
        return taxableIncome.divide(BigDecimal.valueOf(12), RoundingMode.HALF_UP).setScale(2);
    }
}
