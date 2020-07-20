package org.npathai;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class SalarySlipTest {

    @ParameterizedTest
    @CsvSource({
            "5000, 416.67",
            "6000, 500.00",
            "11000, 916.67",
            "12000, 916.67",
            "30000, 916.67"
    })
    public void providesMonthlyTaxFreeAllowance(BigDecimal annualGrossSalary, BigDecimal monthlyTaxFreeAllowance) {
        Employee employee = new Employee(1L, "John", annualGrossSalary);
        SalarySlip salarySlip = new SalarySlip(employee, new NationalInsurance(employee.annualSalary()), new TaxInfo(employee.annualSalary()));
        assertThat(salarySlip.taxFreeAllowance()).isEqualTo(monthlyTaxFreeAllowance);
    }

    @ParameterizedTest
    @CsvSource({
            "6000, 0.00",
            "11000, 0.00",
            "12000, 83.33",
            "30000, 1583.33"
    })
    public void providesMonthlyTaxableIncome(BigDecimal annualGrossSalary, BigDecimal monthlyTaxableIncome) {
        Employee employee = new Employee(1L, "John", annualGrossSalary);
        SalarySlip salarySlip = new SalarySlip(employee, new NationalInsurance(employee.annualSalary()), new TaxInfo(employee.annualSalary()));
        assertThat(salarySlip.taxableIncome()).isEqualTo(monthlyTaxableIncome);
    }

    @ParameterizedTest
    @CsvSource({
            "6000, 0.00",
            "11000, 0.00",
            "12000, 16.67",
            "30000, 316.67"
    })
    public void providesMonthlyTaxPayable(BigDecimal annualGrossSalary, BigDecimal monthlyTaxPayable) {
        Employee employee = new Employee(1L, "John", annualGrossSalary);
        SalarySlip salarySlip = new SalarySlip(employee, new NationalInsurance(employee.annualSalary()), new TaxInfo(employee.annualSalary()));
        assertThat(salarySlip.taxPayable()).isEqualTo(monthlyTaxPayable);
    }

    @ParameterizedTest
    @CsvSource({
            "6000, 0.00",
            "11000, 29.40",
            "12000, 39.40",
            "30000, 219.40"
    })
    public void providesMonthlyNationalInsurance(BigDecimal annualGrossSalary, BigDecimal monthlyNationalInsurance) {
        Employee employee = new Employee(1L, "John", annualGrossSalary);
        SalarySlip salarySlip = new SalarySlip(employee, new NationalInsurance(employee.annualSalary()), new TaxInfo(employee.annualSalary()));
        assertThat(salarySlip.nationalInsuranceContribution()).isEqualTo(monthlyNationalInsurance);
    }
}