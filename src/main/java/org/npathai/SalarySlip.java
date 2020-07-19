package org.npathai;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SalarySlip {
    private final Employee employee;
    private final BigDecimal monthlyGrossSalary;

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
}
