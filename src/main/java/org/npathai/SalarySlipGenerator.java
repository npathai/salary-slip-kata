package org.npathai;

public class SalarySlipGenerator {

    public SalarySlip generateFor(Employee employee) {
        SalarySlip salarySlip = new SalarySlip(employee);
        NationalInsurance nationalInsurance = new NationalInsurance(employee);
        salarySlip.setNationalInsurance(nationalInsurance);
        salarySlip.setTaxInfo(new TaxInfo(employee));
        return salarySlip;
    }
}
