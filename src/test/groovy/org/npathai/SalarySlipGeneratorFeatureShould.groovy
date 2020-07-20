package org.npathai

import spock.lang.Specification

class SalarySlipGeneratorFeatureShould extends Specification {

    def "calculates salary slip"() {
        given: "I have an employee John Doe with annual salary of ${annualSalary} Euros"
        Employee employee = new Employee(1L, "John Doe", annualSalary)
        when: "I generate a monthly salary slip for the employee"
        SalarySlip actualSalarySlip = new SalarySlipGenerator().generateFor(employee)
        then: "monthly salary slip should contain monthly gross salary"
        actualSalarySlip.monthlyGrossSalary().doubleValue() == monthlyGrossSalary
        actualSalarySlip.nationalInsuranceContribution().doubleValue() == nationalInsuranceContribution
        actualSalarySlip.taxFreeAllowance().doubleValue() == taxFreeAllowance
        actualSalarySlip.taxableIncome().doubleValue() == taxableIncome
        actualSalarySlip.taxPayable().doubleValue() == taxPayable
        where:
        annualSalary || monthlyGrossSalary || nationalInsuranceContribution || taxFreeAllowance || taxableIncome || taxPayable
        5000         || 416.67             || 0.00                          || 416.67           || 0.00          || 0.00
        9060.00      || 755.00             || 10.00                         || 755.00           || 0.00          || 0.00
        12000        || 1000               || 39.40                         || 916.67           || 83.33         || 16.67
        45000        || 3750               || 352.73                        || 916.67           || 2833.33       || 600.00
        101000.00    || 8416.67            || 446.07                        || 875.00           || 7541.67       || 2483.33
        111000.00    || 9250.00            || 462.73                        || 458.33           || 8791.67       || 2983.33
        122000.00    || 10166.67           || 481.07                        || 0.00             || 10166.67      || 3533.33
        150000.00    || 12500.00           || 527.73                        || 0.00             || 12500.00      || 4466.67
        160000.00    || 13333.33           || 544.40                        || 0.00             || 13333.33      || 4841.67
    }

}
