package org.npathai;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class NationalInsuranceTest {

    @ParameterizedTest
    @DisplayName("No national insurance contribution for annual salary up to 8060")
    @CsvSource({
            "5000, 0.00",
            "8060, 0.00"
    })
    public void considersNoInsuranceContribution(BigDecimal annualGrossSalary, BigDecimal annualNationalInsuranceContribution) {
        Employee employee = new Employee(1L, "John", annualGrossSalary);
        NationalInsurance nationalInsurance = new NationalInsurance(employee);
        assertThat(nationalInsurance.contribution()).isEqualTo(annualNationalInsuranceContribution);
    }

    @ParameterizedTest
    @DisplayName("Considers 12% national insurance contribution for annual salary higher than 8060")
    @CsvSource({
            "9060.00, 120.00",
            "10060.00, 240.00",
            "43000.00, 4192.80"
    })
    public void considers12PercentInsuranceContribution(BigDecimal annualGrossSalary, BigDecimal annualNationalInsuranceContribution) {
        Employee employee = new Employee(1L, "John", annualGrossSalary);
        NationalInsurance nationalInsurance = new NationalInsurance(employee);
        assertThat(nationalInsurance.contribution()).isEqualTo(annualNationalInsuranceContribution);
    }

    @ParameterizedTest
    @DisplayName("Considers 2% national insurance contribution for annual salary higher than 43000")
    @CsvSource({
            "45000.00, 4232.80"
    })
    public void considers2PercentInsuranceContribution(BigDecimal annualGrossSalary, BigDecimal annualNationalInsuranceContribution) {
        Employee employee = new Employee(1L, "John", annualGrossSalary);
        NationalInsurance nationalInsurance = new NationalInsurance(employee);
        assertThat(nationalInsurance.contribution()).isEqualTo(annualNationalInsuranceContribution);
    }
}