package org.npathai;

import java.math.BigDecimal;

public class TaxInfo {
    private final BigDecimal annualSalary;
    private final BigDecimal MAX_TAX_FREE_ALLOWANCE = BigDecimal.valueOf(11000).setScale(2);
    private BigDecimal annualTaxFreeAllowance;
    private BigDecimal annualTaxPayable;
    private final BigDecimal annualTaxableIncome;

    public TaxInfo(Employee employee) {
        this.annualSalary = employee.annualSalary();
        this.annualTaxFreeAllowance = calculateTaxFreeAllowance();
        this.annualTaxableIncome = calculateTaxableIncome();
        this.annualTaxPayable = BigDecimal.ZERO.setScale(2);
        calculateTaxPayable();
    }

    private BigDecimal calculateTaxFreeAllowance() {
        if (annualSalary.subtract(MAX_TAX_FREE_ALLOWANCE).compareTo(BigDecimal.ZERO) > 0) {
            return MAX_TAX_FREE_ALLOWANCE;
        } else {
            return annualSalary;
        }
    }

    private BigDecimal calculateTaxableIncome() {
        if (annualSalary.subtract(MAX_TAX_FREE_ALLOWANCE).compareTo(BigDecimal.ZERO) > 0) {
            return annualSalary.subtract(MAX_TAX_FREE_ALLOWANCE);
        } else {
            return BigDecimal.ZERO.setScale(2);
        }
    }

    private void calculateTaxPayable() {
        calculateAtNormalRate();
        calculateAtHigherRate();
    }

    private void calculateAtNormalRate() {
        if (annualSalary.subtract(MAX_TAX_FREE_ALLOWANCE).compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal taxableIncome = annualSalary.compareTo(BigDecimal.valueOf(43000)) > 0
                    ? BigDecimal.valueOf(43000)
                    : annualSalary;

            BigDecimal surplus = taxableIncome.subtract(MAX_TAX_FREE_ALLOWANCE);
            BigDecimal tax = surplus.multiply(BigDecimal.valueOf(0.2)).setScale(2);
            annualTaxPayable = annualTaxPayable.add(tax);
        }
    }

    private void calculateAtHigherRate() {
        if (annualSalary.subtract(BigDecimal.valueOf(43000)).compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal surplus = annualSalary.subtract(BigDecimal.valueOf(43000));
            BigDecimal tax = surplus.multiply(BigDecimal.valueOf(0.4)).setScale(2);
            annualTaxPayable = annualTaxPayable.add(tax);
        }
    }

    public BigDecimal taxFreeAllowance() {
        return annualTaxFreeAllowance;
    }

    public BigDecimal taxableIncome() {
        return annualTaxableIncome;
    }

    public BigDecimal taxPayable() {
        return annualTaxPayable;
    }
}
