package org.npathai;

public class SalarySlipGenerator {

    public SalarySlip generateFor(Employee employee) {
        SalarySlip salarySlip = new SalarySlip(employee);
        NationalInsurance nationalInsurance = new NationalInsurance(employee.annualSalary());
        salarySlip.setNationalInsurance(nationalInsurance);
        salarySlip.setTaxInfo(new TaxInfo(employee.annualSalary()));
        return salarySlip;
    }
}
