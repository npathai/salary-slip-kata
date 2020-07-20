package org.npathai;

public class SalarySlipGenerator {

    public SalarySlip generateFor(Employee employee) {
        SalarySlip salarySlip = new SalarySlip(employee, new NationalInsurance(employee.annualSalary()),
                new TaxInfo(employee.annualSalary()));
        return salarySlip;
    }
}
