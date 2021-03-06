package org.npathai;

import java.math.BigDecimal;

public class TaxInfo {
    private final BigDecimal annualSalary;
    private final BigDecimal MAX_TAX_FREE_ALLOWANCE = BigDecimal.valueOf(11000).setScale(2);
    private BigDecimal annualTaxFreeAllowance;
    private BigDecimal annualTaxPayable;
    private final BigDecimal annualTaxableIncome;

    public TaxInfo(BigDecimal annualSalary) {
        this.annualSalary = annualSalary.setScale(2);
        this.annualTaxFreeAllowance = calculateTaxFreeAllowance();
        this.annualTaxableIncome = calculateTaxableIncome();
        this.annualTaxPayable = BigDecimal.ZERO.setScale(2);
        calculateTaxPayable();
    }

    private BigDecimal calculateTaxFreeAllowance() {
        if (annualSalary.compareTo(BigDecimal.valueOf(100000)) > 0) {
            BigDecimal surplus = annualSalary.subtract(BigDecimal.valueOf(100000));
            BigDecimal decreasedAllowance = surplus.multiply(BigDecimal.valueOf(0.5)).setScale(2);
            return MAX_TAX_FREE_ALLOWANCE.compareTo(decreasedAllowance) > 0
                    ? MAX_TAX_FREE_ALLOWANCE.subtract(decreasedAllowance)
                    : BigDecimal.ZERO.setScale(2);
        } else if (annualSalary.compareTo(MAX_TAX_FREE_ALLOWANCE) > 0) {
            return MAX_TAX_FREE_ALLOWANCE;
        } else {
            return annualSalary;
        }
    }

    private BigDecimal calculateTaxableIncome() {
        if (annualSalary.compareTo(MAX_TAX_FREE_ALLOWANCE) > 0) {
            return annualSalary.subtract(annualTaxFreeAllowance);
        } else {
            return BigDecimal.ZERO.setScale(2);
        }
    }

    private void calculateTaxPayable() {
        calculateAtNormalRate();
        calculateAtHigherRate();
        calculateAtHighestRate();
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
        if (annualSalary.compareTo(BigDecimal.valueOf(43000)) > 0) {
            BigDecimal taxableIncome = annualSalary.compareTo(BigDecimal.valueOf(150000)) > 0
                    ? BigDecimal.valueOf(150000)
                    : annualSalary;

            BigDecimal surplus = taxableIncome.subtract(BigDecimal.valueOf(43000));
            if (annualSalary.compareTo(BigDecimal.valueOf(100000)) > 0) {
                BigDecimal allowanceSurplus = MAX_TAX_FREE_ALLOWANCE.subtract(annualTaxFreeAllowance);
                surplus = surplus.add(allowanceSurplus);
            }
            BigDecimal tax = surplus.multiply(BigDecimal.valueOf(0.4)).setScale(2);
            annualTaxPayable = annualTaxPayable.add(tax);
        }
    }

    private void calculateAtHighestRate() {
        if (annualSalary.compareTo(BigDecimal.valueOf(150000)) > 0) {
            BigDecimal surplus = annualSalary.subtract(BigDecimal.valueOf(150000));
            BigDecimal tax = surplus.multiply(BigDecimal.valueOf(0.45)).setScale(2);
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
