package org.npathai;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SalarySlip {
    private final Employee employee;
    private final BigDecimal monthlyGrossSalary;
    private NationalInsurance nationalInsurance;
    private TaxInfo taxInfo;

    public SalarySlip(Employee employee) {
        this.employee = employee;
        monthlyGrossSalary = calculateMonthlyGrossSalary();
    }

    private BigDecimal calculateMonthlyGrossSalary() {
        return employee.annualSalary()
                .setScale(10)
                .divide(BigDecimal.valueOf(12.0), RoundingMode.HALF_UP)
                .setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal monthlyGrossSalary() {
        return monthlyGrossSalary;
    }

    public BigDecimal nationalInsuranceContribution() {
        return toMonthly(nationalInsurance.contribution());
    }

    public void setNationalInsurance(NationalInsurance nationalInsurance) {
        this.nationalInsurance = nationalInsurance;
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

    public void setTaxInfo(TaxInfo taxInfo) {
        this.taxInfo = taxInfo;
    }

    private BigDecimal toMonthly(BigDecimal taxableIncome) {
        return taxableIncome.divide(BigDecimal.valueOf(12), RoundingMode.HALF_UP).setScale(2);
    }
}
