package org.npathai;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class TaxInfoTest {

    @CsvSource(value = {
            "11000.00, 11000.00, 0.00, 0.00",
            "5000.00, 5000.00, 0.00, 0.00"
    }, delimiter = ',')
    @DisplayName("Applies 0 percent tax for annual salary upto 11000")
    @ParameterizedTest
    public void appliesNoTax(double annualIncome, double taxFreeAllowance, double taxableIncome, double taxPayable) {
        Employee employee = new Employee(1L, "John", BigDecimal.valueOf(annualIncome).setScale(2));
        TaxInfo taxInfo = new TaxInfo(employee);
        assertThat(taxInfo.taxFreeAllowance()).isEqualTo(BigDecimal.valueOf(taxFreeAllowance).setScale(2));
        assertThat(taxInfo.taxableIncome()).isEqualTo(BigDecimal.valueOf(taxableIncome).setScale(2));
        assertThat(taxInfo.taxPayable()).isEqualTo(BigDecimal.valueOf(taxPayable).setScale(2));
    }

    @CsvSource(value = {
            "12000.00, 11000.00, 1000.00, 200.00",
            "30000.00, 11000.00, 19000.00, 3800.00",
            "43000.00, 11000.00, 32000.00, 6400.00"
    }, delimiter = ',')
    @DisplayName("Applies 20 percent tax for annual salary between 11000 and 43000")
    @ParameterizedTest
    public void applies20PercentTax(double annualIncome, double taxFreeAllowance, double taxableIncome, double taxPayable) {
        Employee employee = new Employee(1L, "John", BigDecimal.valueOf(annualIncome).setScale(2));
        TaxInfo taxInfo = new TaxInfo(employee);
        assertThat(taxInfo.taxFreeAllowance()).isEqualTo(BigDecimal.valueOf(taxFreeAllowance).setScale(2));
        assertThat(taxInfo.taxableIncome()).isEqualTo(BigDecimal.valueOf(taxableIncome).setScale(2));
        assertThat(taxInfo.taxPayable()).isEqualTo(BigDecimal.valueOf(taxPayable).setScale(2));
    }

    @CsvSource(value = {
            "45000.00, 11000.00, 34000.00, 7200.00",
            "50000.00, 11000.00, 39000.00, 9200.00",
            "100000.00, 11000.00, 89000.00, 29200.00"
    }, delimiter = ',')
    @DisplayName("Applies 40 percent tax for annual salary above 43000")
    @ParameterizedTest
    public void applies40PercentTax(double annualIncome, double taxFreeAllowance, double taxableIncome, double taxPayable) {
        Employee employee = new Employee(1L, "John", BigDecimal.valueOf(annualIncome).setScale(2));
        TaxInfo taxInfo = new TaxInfo(employee);
        assertThat(taxInfo.taxFreeAllowance()).isEqualTo(BigDecimal.valueOf(taxFreeAllowance).setScale(2));
        assertThat(taxInfo.taxableIncome()).isEqualTo(BigDecimal.valueOf(taxableIncome).setScale(2));
        assertThat(taxInfo.taxPayable()).isEqualTo(BigDecimal.valueOf(taxPayable).setScale(2));
    }

    @ParameterizedTest
    @CsvSource({
            "101000, 10500.00, 90500.00, 29800.00",
            "111000, 5500.00, 105500.00, 35800.00",
            "122000, 0.00, 122000.00, 42400.00",
            "150000, 0.00, 150000.00, 53600.00"
    })
    public void reducesTaxFreeAllowanceBy1ForEvery2PoundsForSalaryAbove100K(double annualIncome, double taxFreeAllowance,
                                                                            double taxableIncome, double taxPayable) {

        Employee employee = new Employee(1L, "John", BigDecimal.valueOf(annualIncome).setScale(2));
        TaxInfo taxInfo = new TaxInfo(employee);
        assertThat(taxInfo.taxFreeAllowance()).isEqualTo(BigDecimal.valueOf(taxFreeAllowance).setScale(2));
        assertThat(taxInfo.taxableIncome()).isEqualTo(BigDecimal.valueOf(taxableIncome).setScale(2));
        assertThat(taxInfo.taxPayable()).isEqualTo(BigDecimal.valueOf(taxPayable).setScale(2));
    }
}
