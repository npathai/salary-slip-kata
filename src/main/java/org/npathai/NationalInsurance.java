package org.npathai;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NationalInsurance {
    private final BigDecimal annualGrossSalary;
    private BigDecimal contribution = BigDecimal.ZERO.setScale(2);

    public NationalInsurance(BigDecimal annualSalary) {
        this.annualGrossSalary = annualSalary;
        calculateNationalInsuranceContribution(annualSalary);
    }

    private void calculateNationalInsuranceContribution(BigDecimal annualSalary) {
        calculateAtNormalRate(annualSalary);
        calculateAtLowerRate(annualSalary);
    }

    private void calculateAtLowerRate(BigDecimal annualSalary) {
        if (annualSalary.compareTo(BigDecimal.valueOf(43000)) > 0) {
            BigDecimal surplus = annualSalary.subtract(BigDecimal.valueOf(43000));

            BigDecimal contribution = surplus.setScale(10)
                    .multiply(BigDecimal.valueOf(0.02))
                    .setScale(2, RoundingMode.HALF_UP);

            this.contribution = this.contribution.add(contribution);
        }
    }

    private void calculateAtNormalRate(BigDecimal annualSalary) {
        if (annualSalary.compareTo(BigDecimal.valueOf(8060)) > 0) {
            BigDecimal applicableAmount = annualSalary.compareTo(BigDecimal.valueOf(43000)) > 0
                    ? BigDecimal.valueOf(43000)
                    : annualSalary;
            BigDecimal surplus = applicableAmount.subtract(BigDecimal.valueOf(8060));

            BigDecimal contribution = surplus.setScale(10)
                    .multiply(BigDecimal.valueOf(0.12))
                    .setScale(2, RoundingMode.HALF_UP);

            this.contribution = this.contribution.add(contribution);
        }
    }

    public BigDecimal contribution() {
        return contribution;
    }
}
