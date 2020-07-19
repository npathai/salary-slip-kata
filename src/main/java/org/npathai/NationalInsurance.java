package org.npathai;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NationalInsurance {
    private BigDecimal contribution = BigDecimal.ZERO;

    public NationalInsurance(Employee employee) {
        calculateNationalInsuranceContribution(employee.annualSalary());
    }

    private void calculateNationalInsuranceContribution(BigDecimal annualSalary) {
        BigDecimal surplus = annualSalary.subtract(BigDecimal.valueOf(8060));
        if (BigDecimal.ZERO.compareTo(surplus) == -1) {
            BigDecimal contribution = surplus.setScale(10)
                    .divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP)
                    .setScale(2, RoundingMode.HALF_UP);
            this.contribution = this.contribution.add(contribution);
        }
    }

    public BigDecimal contribution() {
        return contribution;
    }
}
