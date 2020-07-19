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
        where:
        annualSalary || monthlyGrossSalary || nationalInsuranceContribution
        5000         || 416.67             || 0.00
        9060.00      || 755.00             || 10.00
    }

}
